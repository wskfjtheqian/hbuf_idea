package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.*;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class HbufAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof HbufFieldTypeElement) {
            HbufFieldTypeElement type = ((HbufFieldTypeElement) element);
            HbufTypeElement base = type.getTypeBase();
            if (null != base) {
                checkType(holder, null == base.getIdentName() ? base.getTypes() : base.getIdentName(), CheckType.Base, CheckType.Data, CheckType.Enum);
                return;
            }
            HbufTypeArrayElement array = type.getTypeArray();
            if (null != array) {
                base = array.getTypeBase();
                checkType(holder, null == base.getIdentName() ? base.getTypes() : base.getIdentName(), CheckType.Base, CheckType.Data, CheckType.Enum);
                return;
            }
            HbufTypeMapElement map = type.getTypeMap();
            if (null != map) {
                checkType(holder, map.getTypes(), CheckType.Base);
                base = map.getTypeBase();
                checkType(holder, null == base.getIdentName() ? base.getTypes() : base.getIdentName(), CheckType.Base, CheckType.Data, CheckType.Enum);
                return;
            }
        }
        if (element instanceof HbufFuncType) {
            HbufFuncType func = (HbufFuncType) element;
            checkType(holder, func, CheckType.Data);
            return;
        }
        if (element instanceof HbufFuncParam) {
            HbufFuncParam param = (HbufFuncParam) element;
            checkType(holder, param.getFuncType(), CheckType.Data);
            return;
        }

        if (element instanceof HbufNameElement) {
            PsiElement parent = element.getParent();
            if (parent instanceof HbufAnnotationFieldElement) {
                checkAnnotationFieldName(holder, (HbufAnnotationFieldElement) parent, element);
                return;
            }
            if (parent instanceof HbufEnumElement) {
                checkEnumName(holder, (HbufEnumElement) parent, element);
                return;
            }
            if (parent instanceof HbufDataElement) {
                checkDataName(holder, (HbufDataElement) parent, element);
                return;
            }
            if (parent instanceof HbufServerElement) {
                checkServerName(holder, (HbufServerElement) parent, element);
                return;
            }
            if (parent instanceof HbufDataFieldElement) {
                checkDataFieldName(holder, (HbufDataFieldElement) parent, element);
                return;
            }
            if (parent instanceof HbufEnumFieldElement) {
                checkEnumFieldName(holder, (HbufEnumFieldElement) parent, element);
                return;
            }
            if (parent instanceof HbufServerFuncElement) {
                checkServerFuncName(holder, (HbufServerFuncElement) parent, element);
                return;
            }
            if (parent instanceof HbufExtendsElement) {
                parent = parent.getParent();
                if (parent instanceof HbufDataElement) {
                    checkDataExtends(holder, (HbufDataElement) parent, element);
                    return;
                }
                if (parent instanceof HbufServerElement) {
                    checkServerExtends(holder, (HbufServerElement) parent, element);
                    return;
                }
            }
        }

        if (element instanceof HbufIdElement) {
            PsiElement parent = element.getParent();
            if (parent instanceof HbufDataElement) {
                checkDataId(holder, (HbufDataElement) parent, (HbufIdElement) element);
                return;
            }
            if (parent instanceof HbufServerElement) {
                checkServerId(holder, (HbufServerElement) parent, (HbufIdElement) element);
                return;
            }
            if (parent instanceof HbufDataFieldElement) {
                checkId(holder, (HbufDataFieldElement) parent, (HbufIdElement) element);
                return;
            }
            if (parent instanceof HbufEnumFieldElement) {
                checkEnumFieldId(holder, (HbufEnumFieldElement) parent, (HbufIdElement) element);
                return;
            }
            if (parent instanceof HbufServerFuncElement) {
                checkServerFuncId(holder, (HbufServerFuncElement) parent, (HbufIdElement) element);
                return;
            }
            if (parent instanceof HbufExtendsElement) {
                parent = parent.getParent();
                if (parent instanceof HbufDataElement) {
                    checkDataExtends(holder, (HbufDataElement) parent, element);
                    return;
                }
                if (parent instanceof HbufServerElement) {
                    checkServerExtends(holder, (HbufServerElement) parent, element);
                    return;
                }
            }
        }

        if (element instanceof HbufImportElement) {
            checkImport(holder, (HbufImportElement) element);
        }
    }

    private void checkImport(AnnotationHolder holder, HbufImportElement element) {
        @NlsSafe String path = HbufUtil.getString(element.getString().getText());
        @Nullable VirtualFile file = element.getContainingFile().getVirtualFile().getParent().findFileByRelativePath(path);
        if (null == file) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Not find file'" + path)
                    .range(element.getString())
                    .highlightType(ProblemHighlightType.ERROR)
                    .withFix(new ImportQuickDeleteFix(element))
                    .create();
        }
    }

    private void checkAnnotationFieldName(AnnotationHolder holder, HbufAnnotationFieldElement parent, PsiElement element) {
        HbufAnnotationElement hae = HbufUtil.getAnnotationByChild(element);
        if (null == hae) {
            return;
        }
        for (HbufAnnotationFieldElement item : hae.getAnnotationList().getFields()) {
            if (item == parent) {
                continue;
            }
            if (Objects.equals(item.getName(), element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Field key'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkDataId(AnnotationHolder holder, HbufDataElement parent, HbufIdElement element) {
        for (HbufDataElement item : HbufUtil.findChildrenOfAnyType(element.getProject(), HbufDataElement.class)) {
            if (item == parent) {
                continue;
            }
            if (item.getNumber() == element.getId()) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Data id'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkServerId(AnnotationHolder holder, HbufServerElement parent, HbufIdElement element) {
        for (HbufServerElement item : HbufUtil.findChildrenOfAnyType(element.getProject(), HbufServerElement.class)) {
            if (item == parent) {
                continue;
            }
            if (item.getNumber() == element.getId()) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Server id'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkDataName(AnnotationHolder holder, HbufDataElement parent, PsiElement element) {
        for (HbufDataElement item : HbufUtil.findChildrenOfAnyType(element.getProject(), HbufDataElement.class)) {
            if (item == parent) {
                continue;
            }
            if (Objects.equals(item.getName(), element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Data name'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkEnumName(AnnotationHolder holder, HbufEnumElement parent, PsiElement element) {
        for (HbufEnumElement item : HbufUtil.findChildrenOfAnyType(element.getProject(), HbufEnumElement.class)) {
            if (item == parent) {
                continue;
            }
            if (Objects.equals(item.getName(), element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Enum name'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkServerName(AnnotationHolder holder, HbufServerElement parent, PsiElement element) {
        for (HbufServerElement item : HbufUtil.findChildrenOfAnyType(element.getProject(), HbufServerElement.class)) {
            if (item == parent) {
                continue;
            }
            if (Objects.equals(item.getName(), element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Server name'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkServerFuncId(AnnotationHolder holder, HbufServerFuncElement parent, HbufIdElement element) {
        HbufServerElement hse = HbufUtil.getServerByChild(element);
        if (null == hse) {
            return;
        }
        for (HbufServerFuncElement item : hse.getServerBody().getFuncList().getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getNumber() == element.getId()) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Func Id'" + element.getId() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .withFix(new ServerFuncIdQuickFix(element))
                        .create();
                return;
            }
        }
    }

    private void checkEnumFieldId(AnnotationHolder holder, HbufEnumFieldElement parent, HbufIdElement element) {
        HbufEnumElement hee = HbufUtil.getEnumByChild(element);
        if (null == hee) {
            return;
        }
        for (HbufEnumFieldElement item : hee.getEnumBody().getEnumFieldList().getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getNumber() == element.getId()) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Field Id'" + element.getId() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .withFix(new EnumFieldIdQuickFix(element))
                        .create();
                return;
            }
        }
    }

    private void checkId(AnnotationHolder holder, HbufDataFieldElement parent, HbufIdElement element) {
        HbufDataElement hde = HbufUtil.getDataByChild(element);
        if (null == hde) {
            return;
        }
        HbufDataFieldsElement fields = hde.getDataBody().getDataFieldList();
        if (null == fields) {
            return;
        }
        for (HbufDataFieldElement item : fields.getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getNumber() == element.getId()) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Field Id'" + element.getId() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .withFix(new DataFieldIdQuickFix(element))
                        .create();
                return;
            }
        }
    }

    private void checkServerExtends(AnnotationHolder holder, HbufServerElement data, PsiElement element) {
        if (data.getName().equals(element.getText())) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Cyclic inheritance involving '" + element.getText() + "'")
                    .range(element)
                    .highlightType(ProblemHighlightType.GENERIC_ERROR)
                    .create();
            return;
        }
        if (HbufUtil.isServer(element.getProject(), element.getText())) {
            return;
        }
        holder.newAnnotation(HighlightSeverity.ERROR, element.getText() + " undefined symbol")
                .range(element)
                .highlightType(ProblemHighlightType.GENERIC_ERROR)
                .create();
    }

    private void checkDataExtends(AnnotationHolder holder, HbufDataElement data, PsiElement element) {
        if (data.getName().equals(element.getText())) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Cyclic inheritance involving '" + element.getText() + "'")
                    .range(element)
                    .highlightType(ProblemHighlightType.GENERIC_ERROR)
                    .create();
            return;
        }
        HbufDataElement dataElement = getDataElement(element.getProject(), element);
        if (null != dataElement) {
            if (dataElement.getContainingFile() != data.getContainingFile()) {
                if (!HbufUtil.isAtImportFile(data.getContainingFile(), dataElement)) {
                    holder.newAnnotation(HighlightSeverity.ERROR, "Cannot resolve symbol \"" + element.getText() + "\"")
                            .range(element)
                            .highlightType(ProblemHighlightType.ERROR)
                            .withFix(new ImportQuickFix(data, dataElement.getContainingFile().getVirtualFile()))
                            .create();
                }
            }
            return;
        }
        holder.newAnnotation(HighlightSeverity.ERROR, element.getText() + " undefined symbol")
                .range(element)
                .highlightType(ProblemHighlightType.GENERIC_ERROR)
                .create();
    }


    private @Nullable HbufDataElement getDataElement(Project project, PsiElement data) {
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                Collection<HbufDataElement> properties = PsiTreeUtil.findChildrenOfAnyType(
                        hbufFile,
                        HbufDataElement.class
                );
                for (HbufDataElement item : properties) {
                    if (Objects.equals(item.getName(), data.getText())) {
                        return item;
                    }
                }
            }
        }
        return null;
    }


    private void checkEnumFieldName(AnnotationHolder holder, HbufEnumFieldElement parent, PsiElement element) {
        HbufEnumElement hee = HbufUtil.getEnumByChild(element);
        if (null == hee) {
            return;
        }
        for (HbufEnumFieldElement item : hee.getEnumBody().getEnumFieldList().getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getName().equals(element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Field '" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkServerFuncName(AnnotationHolder holder, HbufServerFuncElement parent, PsiElement element) {
        HbufServerElement hee = HbufUtil.getServerByChild(element);
        if (null == hee) {
            return;
        }
        for (HbufServerFuncElement item : hee.getServerBody().getFuncList().getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getName().equals(element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Func '" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkDataFieldName(AnnotationHolder holder, HbufDataFieldElement parent, PsiElement element) {
        HbufDataElement hde = HbufUtil.getDataByChild(element);
        if (null == hde) {
            return;
        }
        HbufDataFieldsElement fields = hde.getDataBody().getDataFieldList();
        if (null == fields) {
            return;
        }
        for (HbufDataFieldElement item : fields.getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getName().equals(element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Field '" + element.getText() + "' is already defined in the scope element.getText()")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }


    enum CheckType {
        Base,
        Data,
        Enum,
        Server,
    }

    private void checkType(@NotNull AnnotationHolder holder, PsiElement base, CheckType... types) {
        List<CheckType> list = Arrays.asList(types);
        if (list.contains(CheckType.Base)) {
            switch (base.getText()) {
                case "int8":
                case "int16":
                case "int32":
                case "int64":
                case "uint8":
                case "uint16":
                case "uint32":
                case "uint64":
                case "bool":
                case "float":
                case "double":
                case "decimal":
                case "string":
                case "date":
                    return;
            }
        }
        if (list.contains(CheckType.Data)) {
            if (HbufUtil.isData(base.getProject(), base.getText())) {

                return;
            }
        }
        if (list.contains(CheckType.Enum)) {
            if (HbufUtil.isEnum(base.getProject(), base.getText())) {
                return;
            }
        }
        if (list.contains(CheckType.Server)) {
            if (HbufUtil.isServer(base.getProject(), base.getText())) {
                return;
            }
        }
        holder.newAnnotation(HighlightSeverity.ERROR, base.getText() + " undefined symbol")
                .range(base)
                .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                .create();
        return;
    }


    private class ImportQuickFix extends BaseIntentionAction {
        private final HbufDataElement mData;
        private final VirtualFile mFile;

        public ImportQuickFix(HbufDataElement data, VirtualFile file) {
            this.mData = data;
            this.mFile = file;
        }

        @Override
        public @NotNull
        @IntentionFamilyName String getFamilyName() {
            return "Add import file";
        }

        @Override
        public @IntentionName
        @NotNull String getText() {
            return "Add import \"" + mData.getContainingFile().getName() + "\"";
        }

        @Override
        public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
            return true;
        }


        @Override
        public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
            ApplicationManager.getApplication().invokeLater(() -> {
                addImportElement(project, file.getVirtualFile());
            });
        }

        private void addImportElement(final Project project, final VirtualFile file) {
            WriteCommandAction.writeCommandAction(project).run(() -> {
                HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(file);
                PsiElement element = HbufElementFactory.createCRLF(project);
                ASTNode node = HbufUtil.getImportLastNode(hbufFile).getNode();
                node.addChild(element.getNode());

                element = HbufElementFactory.createImport(project, mFile.getName());
                node.addChild(element.getNode());
                FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
            });
        }

    }

    private class ImportQuickDeleteFix extends BaseIntentionAction {
        private final HbufImportElement mImport;

        public ImportQuickDeleteFix(HbufImportElement mImport) {
            this.mImport = mImport;
        }

        @Override
        public @NotNull
        @IntentionFamilyName String getFamilyName() {
            return "Delete import file";
        }

        @Override
        public @IntentionName
        @NotNull String getText() {
            return "Delete " + mImport.getText();
        }

        @Override
        public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
            return true;
        }


        @Override
        public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
            WriteCommandAction.writeCommandAction(project).run(() -> {
                mImport.delete();
                FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
            });
        }
    }


    private class EnumFieldIdQuickFix extends BaseIntentionAction {
        private final HbufIdElement element;

        public EnumFieldIdQuickFix(HbufIdElement element) {
            this.element = element;
        }

        @Override
        public @NotNull
        @IntentionFamilyName String getFamilyName() {
            return "Field Id repeat";
        }

        @Override
        public @IntentionName
        @NotNull String getText() {
            return "Automatic correction ID";
        }

        @Override
        public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
            return true;
        }


        @Override
        public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
            WriteCommandAction.writeCommandAction(project).run(() -> {
                @NotNull Collection<HbufEnumFieldElement> elements = PsiTreeUtil.findChildrenOfAnyType(element.getParent().getParent(), HbufEnumFieldElement.class);
                for (int i = 0; i < elements.size(); i++) {
                    if (!checkId(i, elements)) {
                        HbufIdElement id = HbufElementFactory.createId(project, i);
                        element.getParent().getNode().replaceChild(element.getNode(), id.getNode());
                        FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
                        return;
                    }
                }

            });
        }

        private boolean checkId(int id, Collection<HbufEnumFieldElement> elements) {
            for (HbufEnumFieldElement item : elements) {
                if (item.getNumber() == id) {
                    return true;
                }
            }
            return false;
        }
    }

    private class DataFieldIdQuickFix extends BaseIntentionAction {
        private final HbufIdElement element;

        public DataFieldIdQuickFix(HbufIdElement element) {
            this.element = element;
        }

        @Override
        public @NotNull
        @IntentionFamilyName String getFamilyName() {
            return "Field Id repeat";
        }

        @Override
        public @IntentionName
        @NotNull String getText() {
            return "Automatic correction ID";
        }

        @Override
        public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
            return true;
        }


        @Override
        public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
            WriteCommandAction.writeCommandAction(project).run(() -> {
                @NotNull Collection<HbufDataFieldElement> elements = PsiTreeUtil.findChildrenOfAnyType(element.getParent().getParent(), HbufDataFieldElement.class);
                for (int i = 0; i < elements.size(); i++) {
                    if (!checkId(i, elements)) {
                        HbufIdElement id = HbufElementFactory.createId(project, i);
                        element.getParent().getNode().replaceChild(element.getNode(), id.getNode());
                        FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
                        return;
                    }
                }
            });
        }

        private boolean checkId(int id, Collection<HbufDataFieldElement> elements) {
            for (HbufDataFieldElement item : elements) {
                if (item.getNumber() == id) {
                    return true;
                }
            }
            return false;
        }
    }
    private class ServerFuncIdQuickFix extends BaseIntentionAction {
        private final HbufIdElement element;

        public ServerFuncIdQuickFix(HbufIdElement element) {
            this.element = element;
        }

        @Override
        public @NotNull
        @IntentionFamilyName String getFamilyName() {
            return "Field Id repeat";
        }

        @Override
        public @IntentionName
        @NotNull String getText() {
            return "Automatic correction ID";
        }

        @Override
        public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
            return true;
        }


        @Override
        public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
            WriteCommandAction.writeCommandAction(project).run(() -> {
                @NotNull Collection<HbufServerFuncElement> elements = PsiTreeUtil.findChildrenOfAnyType(element.getParent().getParent(), HbufServerFuncElement.class);
                for (int i = 0; i < elements.size(); i++) {
                    if (!checkId(i, elements)) {
                        HbufIdElement id = HbufElementFactory.createId(project, i);
                        element.getParent().getNode().replaceChild(element.getNode(), id.getNode());
                        FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
                        return;
                    }
                }

            });
        }

        private boolean checkId(int id, Collection<HbufServerFuncElement> elements) {
            for (HbufServerFuncElement item : elements) {
                if (item.getNumber() == id) {
                    return true;
                }
            }
            return false;
        }
    }


}

package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.*;
import com.hbuf.idea.language.quickfix.*;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

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
                checkType(holder, map.getTypeMapKey(), CheckType.Base, CheckType.Enum);
                base = map.getTypeBase();
                checkType(holder, null == base.getIdentName() ? base.getTypes() : base.getIdentName(), CheckType.Base, CheckType.Data, CheckType.Enum);
                return;
            }
        }
        if (element instanceof HbufFuncType func) {
            if (null != func.getName()) {
                checkType(holder, func, CheckType.Data);
            }
            return;
        }
        if (element instanceof HbufFuncParam param) {
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
                checkEnumName(holder, (HbufEnumElement) parent, (HbufNameElement) element);
                return;
            }
            if (parent instanceof HbufDataElement) {
                checkDataName(holder, (HbufDataElement) parent, (HbufNameElement) element);
                return;
            }
            if (parent instanceof HbufServerElement) {
                checkServerName(holder, (HbufServerElement) parent, (HbufNameElement) element);
                return;
            }
            if (parent instanceof HbufDataFieldElement) {
                checkDataFieldName(holder, (HbufDataFieldElement) parent, (HbufNameElement) element);
                return;
            }
            if (parent instanceof HbufEnumFieldElement) {
                checkEnumFieldName(holder, (HbufEnumFieldElement) parent, (HbufNameElement) element);
                return;
            }
            if (parent instanceof HbufServerFuncElement) {
                checkServerFuncName(holder, (HbufServerFuncElement) parent, (HbufNameElement) element);
                return;
            }
            if (parent instanceof HbufExtendsElement) {
                parent = HbufUtil.getExtendsRoot(parent);
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
            if (parent instanceof HbufDataFieldElement) {
                checkDataFieldId(holder, (HbufDataFieldElement) parent, (HbufIdElement) element);
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
                parent = HbufUtil.getExtendsRoot(parent);
                if (parent instanceof HbufDataElement) {
                    checkExtendsId(holder, ((HbufDataElement) parent).getExtendList(), (HbufIdElement) element);
                    return;
                }
                if (parent instanceof HbufServerElement) {
                    checkExtendsId(holder, ((HbufServerElement) parent).getExtendList(), (HbufIdElement) element);
                    return;
                }
            }
        }

        if (element instanceof HbufImportElement) {
            checkImport(holder, (HbufImportElement) element);
            return;
        }
    }

    private void checkTable(AnnotationHolder holder, PsiElement element) {
        String text = element.getText().substring(1, element.getText().length() - 1);
        if(HbufUtil.findData(element.getProject(), text).isEmpty()){
            holder.newAnnotation(HighlightSeverity.ERROR, "Not find Data'" )
                    .range(element)
                    .highlightType(ProblemHighlightType.ERROR)
                    .withFix(new DataNewQuickFix(element,text))
                    .create();
        }
    }

    private void checkImport(AnnotationHolder holder, HbufImportElement element) {
        String path = HbufUtil.getString(element.getString().getText());
        VirtualFile file = element.getContainingFile().getVirtualFile().getParent().findFileByRelativePath(path);
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
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(HbufSyntaxHighlighter.FIELD)
                .create();
    }

    private void checkExtendsId(AnnotationHolder holder, @NotNull Collection<HbufExtendsElement> extendList, HbufIdElement element) {
        for (HbufExtendsElement item : extendList) {
            if (item.getId() == element) {
                continue;
            }
            if (item.getId().getId() == element.getId()) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Extends id'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .withFix(new ExtendsIdQuickFix(extendList, element))
                        .create();
                return;
            }
        }
        holder.newAnnotation(HighlightSeverity.INFORMATION, "The sort ID extends can be used")
                .range(element)
                .highlightType(ProblemHighlightType.INFORMATION)
                .withFix(new ExtendsIdCollateFix(extendList, element))
                .create();
    }

    private void checkDataName(AnnotationHolder holder, HbufDataElement parent, HbufNameElement element) {
        for (HbufDataElement item : HbufUtil.findChildrenOfAnyType(element.getProject(), HbufDataElement.class)) {
            if (item == parent) {
                continue;
            }
            if (Objects.equals(item.getName(), element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Data name'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .withFix(new RenameQuickFix(element))
                        .create();
                return;
            }
        }
    }

    private void checkEnumName(AnnotationHolder holder, HbufEnumElement parent, HbufNameElement element) {
        for (HbufEnumElement item : HbufUtil.findChildrenOfAnyType(element.getProject(), HbufEnumElement.class)) {
            if (item == parent) {
                continue;
            }
            if (Objects.equals(item.getName(), element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Enum name'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .withFix(new RenameQuickFix(element))
                        .create();
                return;
            }
        }
    }

    private void checkServerName(AnnotationHolder holder, HbufServerElement parent, HbufNameElement element) {
        for (HbufServerElement item : HbufUtil.findChildrenOfAnyType(element.getProject(), HbufServerElement.class)) {
            if (item == parent) {
                continue;
            }
            if (Objects.equals(item.getName(), element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Server name'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .withFix(new RenameQuickFix(element))
                        .create();
                return;
            }
        }
    }

    private void checkServerFuncId(AnnotationHolder holder, HbufServerFuncElement parent, HbufIdElement element) {
        HbufServerElement hse = HbufUtil.getServerByChild(element);
        if (null == hse || null == element) {
            return;
        }
        for (HbufServerFuncElement item : hse.getServerBody().getFuncList().getFuncts()) {
            if (item == parent || null == item) {
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
        holder.newAnnotation(HighlightSeverity.INFORMATION, "The sort ID function can be used")
                .range(element)
                .highlightType(ProblemHighlightType.INFORMATION)
                .withFix(new ServerFuncIdCollateFix(element))
                .create();
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
        holder.newAnnotation(HighlightSeverity.INFORMATION, "The sort ID function can be used")
                .range(element)
                .highlightType(ProblemHighlightType.INFORMATION)
                .withFix(new EnumFieldIdCollateFix(element))
                .create();
    }

    private void checkDataFieldId(AnnotationHolder holder, HbufDataFieldElement parent, HbufIdElement element) {
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
        holder.newAnnotation(HighlightSeverity.INFORMATION, "The sort ID function can be used")
                .range(element)
                .highlightType(ProblemHighlightType.INFORMATION)
                .withFix(new DataFieldIdCollateFix(element))
                .create();
    }

    private void checkServerExtends(AnnotationHolder holder, HbufServerElement data, PsiElement element) {
        if (Objects.equals(data.getName(), element.getText())) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Cyclic inheritance involving '" + element.getText() + "'")
                    .range(element)
                    .highlightType(ProblemHighlightType.GENERIC_ERROR)
                    .create();
            return;
        }

        for (HbufExtendsElement item : data.getExtendList()) {
            if (item.getIdentName() == element) {
                continue;
            }
            if (Objects.equals(item.getIdentName().getName(), element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Extends name'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
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
        if (Objects.equals(data.getName(), element.getText())) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Cyclic inheritance involving '" + element.getText() + "'")
                    .range(element)
                    .highlightType(ProblemHighlightType.GENERIC_ERROR)
                    .create();
            return;
        }
        for (HbufExtendsElement item : data.getExtendList()) {
            if (item.getIdentName() == element) {
                continue;
            }
            if (Objects.equals(item.getIdentName().getName(), element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Extends name'" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
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
        }
    }


    private HbufDataElement getDataElement(Project project, PsiElement data) {
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiElement hbufFile = PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null && hbufFile instanceof HbufFile) {
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
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(HbufSyntaxHighlighter.FIELD)
                .create();
    }

    private void checkServerFuncName(AnnotationHolder holder, HbufServerFuncElement parent, HbufNameElement element) {
        HbufServerElement hee = HbufUtil.getServerByChild(element);
        if (null == hee) {
            return;
        }
        for (HbufServerFuncElement item : hee.getServerBody().getFuncList().getFuncts()) {
            if (null == item || item == parent) {
                continue;
            }
            if (item.getName().equals(element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Func '" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .withFix(new RenameQuickFix(element))
                        .create();
                return;
            }
        }
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(HbufSyntaxHighlighter.METHOD)
                .create();
    }

    private void checkDataFieldName(AnnotationHolder holder, HbufDataFieldElement parent, HbufNameElement element) {
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
                        .withFix(new RenameQuickFix(element))
                        .create();
                return;
            }
        }
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(HbufSyntaxHighlighter.FIELD)
                .create();
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
            if (HbufUtil.DataBaseType.contains(base.getText())) {
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
                .highlightType(ProblemHighlightType.ERROR)
                .withFix(new DataNewQuickFix(base))
                .create();
    }


}

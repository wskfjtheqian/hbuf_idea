package com.hbuf.idea.language.quickfix;

import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufElementFactory;
import com.hbuf.idea.language.psi.HbufFile;
import com.hbuf.idea.language.psi.HbufUtil;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class ImportQuickFix extends BaseIntentionAction {
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

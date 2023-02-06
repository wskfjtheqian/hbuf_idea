package com.hbuf.idea.language.quickfix;

import com.hbuf.idea.language.psi.HbufImportElement;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class ImportQuickDeleteFix extends BaseIntentionAction {
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

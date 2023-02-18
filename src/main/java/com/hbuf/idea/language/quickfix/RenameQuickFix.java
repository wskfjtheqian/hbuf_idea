package com.hbuf.idea.language.quickfix;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.refactoring.RefactoringActionHandlerFactory;
import com.intellij.refactoring.rename.RenameHandler;
import com.intellij.refactoring.rename.RenameHandlerRegistry;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class RenameQuickFix extends BaseIntentionAction {
    private final PsiElement element;

    public RenameQuickFix(PsiElement element) {
        this.element = element;
    }

    @Override
    public @NotNull
    @IntentionFamilyName String getFamilyName() {
        return "Rename";
    }

    @Override
    public @IntentionName
    @NotNull String getText() {
        return "Rename";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        return true;
    }


    @Override
    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
        DataManager.getInstance().getDataContextFromFocusAsync().onSuccess(dataContext -> {
            RenameHandler renameHandler = RenameHandlerRegistry.getInstance().getRenameHandler(dataContext);
            if (renameHandler != null) {
                renameHandler.invoke(project, new PsiElement[]{element}, dataContext);
            } else {
                RefactoringActionHandler renameRefactoringHandler = RefactoringActionHandlerFactory.getInstance().createRenameHandler();
                renameRefactoringHandler.invoke(project, new PsiElement[]{element}, dataContext);
            }
        });
    }


}
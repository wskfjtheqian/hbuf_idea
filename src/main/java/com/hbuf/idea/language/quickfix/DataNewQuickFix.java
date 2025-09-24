package com.hbuf.idea.language.quickfix;

import com.hbuf.idea.language.psi.*;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class DataNewQuickFix extends BaseIntentionAction {
    private final PsiElement element;
    private final String name;

    public DataNewQuickFix(PsiElement element) {
        this.element = element;
        this.name = element.getText();
    }

    public DataNewQuickFix(PsiElement element, String text) {
        this.element = element;
        this.name = text;
    }

    @Override
    public @NotNull
    @IntentionFamilyName String getFamilyName() {
        return "New data";
    }

    @Override
    public @IntentionName
    @NotNull String getText() {
        return "New data " + element.getText() + "";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        return true;
    }


    @Override
    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
        WriteCommandAction.writeCommandAction(project).run(() -> {
            PsiElement child = getAddElement(element);
            ASTNode data = HbufElementFactory.createData(project, name).getNode();
            child.getNode().addChild(data);
            FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
        });
    }

    private PsiElement getAddElement(PsiElement element) {
        if (element instanceof HbufEnumElement ||
                element instanceof HbufDataElement ||
                element instanceof HbufServerElement) {
            return element;
        }
        if (null != element.getParent()) {
            return getAddElement(element.getParent());
        }
        return null;
    }


}
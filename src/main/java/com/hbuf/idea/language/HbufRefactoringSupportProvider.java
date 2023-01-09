package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufDataStatement;
import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;

public class HbufRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
        return element instanceof HbufDataStatement;
    }
}

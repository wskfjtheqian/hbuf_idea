package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufEnumBodyElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class HbufEnumBodyElementImpl extends ASTWrapperPsiElement implements HbufEnumBodyElement {
    public HbufEnumBodyElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @Nullable
    PsiElement getNameIdentifier() {
        return null;
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
        return null;
    }
}
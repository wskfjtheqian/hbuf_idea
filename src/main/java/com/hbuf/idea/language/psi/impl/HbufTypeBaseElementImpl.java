package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufTypeBaseElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class HbufTypeBaseElementImpl extends ASTWrapperPsiElement implements HbufTypeBaseElement {
    public HbufTypeBaseElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String name) throws IncorrectOperationException {
        return null;
    }

    @Override
    @Nullable
    @NlsSafe
    public String getName() {
        if (null != getId()) {
            return getId().getText();
        } else if (null != getTypes()) {
            return getTypes().getText();
        }
        return "";
    }

    abstract PsiElement getId();


    abstract PsiElement getTypes();

    @Override
    public boolean isBaseType() {
        return null != getTypes();
    }
}

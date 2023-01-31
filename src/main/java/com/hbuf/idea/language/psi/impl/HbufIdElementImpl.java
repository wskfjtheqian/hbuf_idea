package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufIdElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public abstract class HbufIdElementImpl extends ASTWrapperPsiElement implements HbufIdElement {

    public HbufIdElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public long getId() {
        return Long.parseLong(getNumber().getText());
    }

    @NotNull
    abstract PsiElement getNumber();
}

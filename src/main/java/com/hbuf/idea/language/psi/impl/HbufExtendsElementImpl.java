package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufExtendsElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;


public abstract class HbufExtendsElementImpl extends ASTWrapperPsiElement implements HbufExtendsElement {
    public HbufExtendsElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }
}
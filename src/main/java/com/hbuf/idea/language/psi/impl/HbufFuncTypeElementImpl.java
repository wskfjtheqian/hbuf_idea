package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufFuncTypeElement;
import com.hbuf.idea.language.psi.HbufNameElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;


public abstract class HbufFuncTypeElementImpl extends ASTWrapperPsiElement implements HbufFuncTypeElement {
    public HbufFuncTypeElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }

    @NotNull
    abstract HbufNameElement getIdentName();

}
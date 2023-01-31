package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufFuncParamElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;


public abstract class HbufFuncParamElementImpl extends ASTWrapperPsiElement implements HbufFuncParamElement {
    public HbufFuncParamElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }
}
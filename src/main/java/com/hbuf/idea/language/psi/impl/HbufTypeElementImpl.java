package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufTypeElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class HbufTypeElementImpl extends ASTWrapperPsiElement implements HbufTypeElement {

    public HbufTypeElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }


}

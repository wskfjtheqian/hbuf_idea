package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufDataFieldElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class HbufDataFieldElementImpl extends ASTWrapperPsiElement implements HbufDataFieldElement {

    public HbufDataFieldElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }

    @NotNull
    @Override
    public long getNumber() {
        return getId().getId();
    }

}

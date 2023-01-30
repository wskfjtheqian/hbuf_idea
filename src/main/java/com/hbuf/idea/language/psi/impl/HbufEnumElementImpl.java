package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufEnumElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class HbufEnumElementImpl extends ASTWrapperPsiElement implements HbufEnumElement {

    public HbufEnumElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }


}

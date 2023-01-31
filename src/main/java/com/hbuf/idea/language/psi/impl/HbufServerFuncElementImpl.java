package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufServerFuncElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public abstract class HbufServerFuncElementImpl extends ASTWrapperPsiElement implements HbufServerFuncElement {

    public HbufServerFuncElementImpl(@NotNull ASTNode node) {
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

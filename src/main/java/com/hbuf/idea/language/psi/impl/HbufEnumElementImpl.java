package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufEnumElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public abstract class HbufEnumElementImpl extends ASTWrapperPsiElement implements HbufEnumElement {

    public HbufEnumElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String var1) {
        getIdentName().setName(var1);
        return this;
    }

}

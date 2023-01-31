package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufDataFieldElement;
import com.hbuf.idea.language.psi.HbufEnumFieldElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
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
        return Long.parseLong(getId().getText());
    }

    @NotNull
    public abstract PsiElement getId();
}

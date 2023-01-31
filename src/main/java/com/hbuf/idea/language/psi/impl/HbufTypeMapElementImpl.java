package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufTypeElement;
import com.hbuf.idea.language.psi.HbufTypeMapElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public abstract class HbufTypeMapElementImpl extends ASTWrapperPsiElement implements HbufTypeMapElement {

    public HbufTypeMapElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    public String getKey() {
        return getTypes().getText();
    }

    @Override
    public boolean isNullable() {
        return "?".equals(getNode().getLastChildNode().getText());
    }
}

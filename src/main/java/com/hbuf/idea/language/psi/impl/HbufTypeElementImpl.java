package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufNameElement;
import com.hbuf.idea.language.psi.HbufTypeElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class HbufTypeElementImpl extends ASTWrapperPsiElement implements HbufTypeElement {

    public HbufTypeElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        HbufNameElement ident = getIdentName();
        if (null != ident) {
            return ident.getName();
        }
        return getTypes().getText();
    }

    @Override
    public boolean isNullable() {
        return "?".equals(getNode().getLastChildNode().getText());
    }
}

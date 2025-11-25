package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufDataFieldElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Override
    public @Nullable PsiElement getComment() {
        PsiElement leaf = this.getParent().getPrevSibling();
        while (leaf instanceof PsiWhiteSpace) {
            leaf = leaf.getPrevSibling();
        }
        if (leaf instanceof PsiComment) {
            return  leaf;
        }
        return null;
    }
}

package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufEnumFieldElement;
import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class HbufEnumFieldElementImpl extends ASTWrapperPsiElement implements HbufEnumFieldElement {
    public HbufEnumFieldElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @Nullable
    PsiElement getNameIdentifier() {
        return getId();
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
        getNode().replaceChild(getId().getNode(), new LeafPsiElement(HbufTypes.ID, s).getNode());
        return this;
    }

    @Override
    public String getName() {
        return getId().getText();
    }

    abstract PsiElement getId();

    abstract PsiElement getNumber();

    @Override
    public int getNo() {
        return Integer.parseInt(getNumber().getText());
    }
}
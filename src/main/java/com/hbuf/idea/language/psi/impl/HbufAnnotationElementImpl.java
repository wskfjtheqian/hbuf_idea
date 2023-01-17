package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class HbufAnnotationElementImpl extends ASTWrapperPsiElement implements HbufAnnotationElement {
    public HbufAnnotationElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
        getNode().replaceChild(getIdentName().getNode(), new LeafPsiElement(HbufTypes.ID, s).getNode());
        return this;
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }

    abstract HbufIdentName getIdentName();

}
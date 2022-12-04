package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufFuncParamElement;
import com.hbuf.idea.language.psi.HbufFuncTypeElement;
import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;


public abstract class HbufFuncParamElementImpl extends ASTWrapperPsiElement implements HbufFuncParamElement {
    public HbufFuncParamElementImpl(@NotNull ASTNode node) {
        super(node);
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

}
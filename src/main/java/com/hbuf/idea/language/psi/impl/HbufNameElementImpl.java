package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.hbuf.idea.language.reference.DataPsiReference;
import com.hbuf.idea.language.reference.ServerPsiReference;
import com.hbuf.idea.language.reference.TypePsiReference;

public abstract class HbufNameElementImpl extends ASTWrapperPsiElement implements HbufNameElement {
    public HbufNameElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @Nullable PsiElement getNameIdentifier() {
        return this;
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
        HbufNameElement element = HbufUtil.createNameElement(getProject(), s);
        getNode().replaceChild(getIdent().getNode(), element.getFirstChild().getNode());
        return this;
    }

    abstract PsiElement getIdent();

    @Override
    public String getName() {
        return getText();
    }

    @Override
    public PsiReference getReference() {
        if (this.getParent() instanceof HbufFuncTypeElement) {
            return new DataPsiReference(this, TextRange.create(0, getIdent().getTextLength()));
        } else if (this.getParent() instanceof HbufExtendsElement) {
            if (this.getParent().getParent() instanceof HbufDataElement) {
                return new DataPsiReference(this, TextRange.create(0, getIdent().getTextLength()));
            } else if (this.getParent().getParent() instanceof HbufServerElement) {
                return new ServerPsiReference(this, TextRange.create(0, getIdent().getTextLength()));
            }
        } else if (this.getParent() instanceof HbufTypeElement) {
            return new TypePsiReference(this, TextRange.create(0, getIdent().getTextLength()));
        }
        return null;
    }





}


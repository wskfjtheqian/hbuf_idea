package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufNameElement;
import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        getNode().replaceChild(getIdent().getNode(), new LeafPsiElement(HbufTypes.ID, s).getNode());
        return this;
    }

    abstract PsiElement getIdent();

    @Override
    public String getName() {
        return getText();
    }

    @Override
    public PsiReference getReference() {
        return new PsiPolyVariantReference() {
            @Override
            public ResolveResult[] multiResolve(boolean b) {
                return new ResolveResult[0];
            }

            @Override
            public @NotNull PsiElement getElement() {
                return HbufNameElementImpl.this;
            }

            @Override
            public @NotNull TextRange getRangeInElement() {
                return HbufNameElementImpl.this.getTextRange();
            }

            @Override
            public @NotNull TextRange getAbsoluteRange() {
                return PsiPolyVariantReference.super.getAbsoluteRange();
            }

            @Override
            public @Nullable PsiElement resolve() {
                return HbufNameElementImpl.this;
            }

            @Override
            public @NotNull
            @NlsSafe String getCanonicalText() {
                return HbufNameElementImpl.this.getText();
            }

            @Override
            public PsiElement handleElementRename(@NotNull String s) throws IncorrectOperationException {
                return HbufNameElementImpl.this;
            }

            @Override
            public PsiElement bindToElement(@NotNull PsiElement psiElement) throws IncorrectOperationException {
                return HbufNameElementImpl.this;
            }

            @Override
            public boolean isReferenceTo(@NotNull PsiElement psiElement) {
                return true;
            }

            @Override
            public Object[] getVariants() {
                return PsiPolyVariantReference.super.getVariants();
            }

            @Override
            public boolean isSoft() {
                return false;
            }
        };
    }
}

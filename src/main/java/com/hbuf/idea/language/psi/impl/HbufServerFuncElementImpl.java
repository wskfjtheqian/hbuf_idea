package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.HbufIcons;
import com.hbuf.idea.language.psi.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.PsiNavigationSupport;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class HbufServerFuncElementImpl extends ASTWrapperPsiElement implements HbufServerFuncElement {
    public HbufServerFuncElementImpl(@NotNull ASTNode node) {
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

    @Override
    public int getNo() {
        return Integer.parseInt(getNumber().getText());
    }

    @Override
    public HbufFuncTypeElement getType() {
        return getFuncType();
    }

    @Override
    public HbufFuncParamElement getParam() {
        return getFuncParam();
    }

    abstract PsiElement getId();

    abstract PsiElement getNumber();

    abstract HbufFuncParam getFuncParam();

    abstract HbufFuncType getFuncType();

    @Override
    public void navigate(boolean requestFocus) {
        assert this.canNavigate() : getId();

        PsiNavigationSupport.getInstance().getDescriptor(getId()).navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
        return PsiNavigationSupport.getInstance().canNavigate(getId());
    }

    @Override
    public boolean canNavigateToSource() {
        return this.canNavigate();
    }

    @Override
    public ItemPresentation getPresentation() {
        PresentationData data = new PresentationData();
        data.setPresentableText(getName());
        data.setIcon(HbufIcons.ENUM);
        return data;
    }
}


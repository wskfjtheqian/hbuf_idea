package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.HbufIcons;
import com.hbuf.idea.language.psi.HbufIdentId;
import com.hbuf.idea.language.psi.HbufIdentName;
import com.hbuf.idea.language.psi.HbufServerElement;
import com.hbuf.idea.language.psi.HbufTypes;
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


public abstract class HbufServerElementImpl extends ASTWrapperPsiElement implements HbufServerElement {
    public HbufServerElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @Nullable
    PsiElement getNameIdentifier() {
        return getIdentName();
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
        getNode().replaceChild(getIdentName().getNode(), new LeafPsiElement(HbufTypes.ID, s).getNode());
        return this;
    }

    @Override
    public String getName() {
        return getIdentName().getText();
    }

    abstract HbufIdentName getIdentName();

    @NotNull
    abstract HbufIdentId getIdentId();

    @Override
    public int getNo() {
        return getIdentId().getNo();
    }

    @Override
    public void navigate(boolean requestFocus) {
        assert this.canNavigate() : getIdentName();

        PsiNavigationSupport.getInstance().getDescriptor(getIdentName()).navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
        return PsiNavigationSupport.getInstance().canNavigate(getIdentName());
    }

    @Override
    public boolean canNavigateToSource() {
        return this.canNavigate();
    }

    @Override
    public ItemPresentation getPresentation() {
        PresentationData data = new PresentationData();
        data.setPresentableText(getName());
        data.setIcon(HbufIcons.SERVER);
        return data;
    }
}
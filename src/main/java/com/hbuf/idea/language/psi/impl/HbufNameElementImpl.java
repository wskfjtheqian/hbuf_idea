package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.HbufIcons;
import com.hbuf.idea.language.psi.HbufNameElement;
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


public abstract class HbufNameElementImpl extends ASTWrapperPsiElement implements HbufNameElement {
    public HbufNameElementImpl(@NotNull ASTNode node) {
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
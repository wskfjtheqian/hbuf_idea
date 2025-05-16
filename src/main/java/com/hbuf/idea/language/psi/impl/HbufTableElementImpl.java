package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufTableElement;
import com.hbuf.idea.language.psi.HbufTypesFactory;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilderFactory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.impl.source.tree.SharedImplUtil;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class HbufTableElementImpl extends ASTWrapperPsiElement implements HbufTableElement {

    public HbufTableElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String toString() {
        return "HbufTableElement[" + getNode().getElementType() + "]";
    }

    @Override
    public void replaceChildInternal(PsiElement child, TreeElement newElement) {
        super.replaceChildInternal(child, newElement);
    }
}


package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.HbufIcons;
import com.hbuf.idea.language.psi.HbufIdElement;
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


public abstract class HbufIdElementImpl extends ASTWrapperPsiElement implements HbufIdElement {
    public HbufIdElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public int getNo() {
        return Integer.parseInt(getNumber().getText());
    }
}
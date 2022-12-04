package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufServerBodyElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class HbufServerBodyElementImpl extends ASTWrapperPsiElement implements HbufServerBodyElement {
    public HbufServerBodyElementImpl(@NotNull ASTNode node) {
        super(node);
    }

}
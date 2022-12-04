package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufAnnotationGroupElement;
import com.hbuf.idea.language.psi.HbufDataElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class HbufAnnotationGroupElementImpl extends ASTWrapperPsiElement implements HbufAnnotationGroupElement {
    public HbufAnnotationGroupElementImpl(@NotNull ASTNode node) {
        super(node);
    }




}
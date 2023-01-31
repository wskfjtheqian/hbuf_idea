package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufAnnotationGroupElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;


public abstract class HbufAnnotationGroupElementImpl extends ASTWrapperPsiElement implements HbufAnnotationGroupElement {
    public HbufAnnotationGroupElementImpl(@NotNull ASTNode node) {
        super(node);
    }


}
package com.hbuf.idea.annotate.psi.impl;

import com.hbuf.idea.annotate.psi.AnnotateItemElement;
import com.hbuf.idea.language.psi.HbufAnnotationElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class  AnnotateItemElementImpl extends ASTWrapperPsiElement implements AnnotateItemElement {
    public AnnotateItemElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}

package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufServerBodyElement;
import com.hbuf.idea.language.psi.HbufServerFuncsElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;


public abstract class HbufServerBodyElementImpl extends ASTWrapperPsiElement implements HbufServerBodyElement {
    public HbufServerBodyElementImpl(@NotNull ASTNode node) {
        super(node);
    }



}

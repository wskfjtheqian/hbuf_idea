package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufDataBodyElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class HbufDataBodyElementImpl extends ASTWrapperPsiElement implements HbufDataBodyElement {

    public HbufDataBodyElementImpl(@NotNull ASTNode node) {
        super(node);
    }

}

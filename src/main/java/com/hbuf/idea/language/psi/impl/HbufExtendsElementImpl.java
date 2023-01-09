package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufExtendsElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class HbufExtendsElementImpl extends ASTWrapperPsiElement implements HbufExtendsElement {
    public HbufExtendsElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}

package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufTypeMapKeyElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class HbufTypeMapKeyElementImpl extends ASTWrapperPsiElement implements HbufTypeMapKeyElement {
    public HbufTypeMapKeyElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}

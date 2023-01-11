package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class HbufTypeStatementElementImpl extends ASTWrapperPsiElement implements HbufTypeStatementElement {
    public HbufTypeStatementElementImpl(@NotNull ASTNode node) {
        super(node);
    }



}

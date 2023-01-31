package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufTypeArrayElement;
import com.hbuf.idea.language.psi.HbufTypeMapElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class HbufTypeArrayElementImpl extends ASTWrapperPsiElement implements HbufTypeArrayElement {

    public HbufTypeArrayElementImpl(@NotNull ASTNode node) {
        super(node);
    }



}

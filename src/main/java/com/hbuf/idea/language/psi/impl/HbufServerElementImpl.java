package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufServerElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class HbufServerElementImpl extends ASTWrapperPsiElement implements HbufServerElement {

    public HbufServerElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }



}

package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufFieldTypeElement;
import com.hbuf.idea.language.psi.HbufTypeElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class HbufFieldTypeElementImpl extends ASTWrapperPsiElement implements HbufFieldTypeElement {

    public HbufFieldTypeElementImpl(@NotNull ASTNode node) {
        super(node);
    }

}

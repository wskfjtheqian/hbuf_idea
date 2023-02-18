package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufEnumBodyElement;
import com.hbuf.idea.language.psi.HbufEnumFieldElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;


public abstract class HbufEnumBodyElementImpl extends ASTWrapperPsiElement implements HbufEnumBodyElement {
    public HbufEnumBodyElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public Collection<HbufEnumFieldElement> getFields() {
        return getEnumFieldList().getFields();
    }
}
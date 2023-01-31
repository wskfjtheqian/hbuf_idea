package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufEnumFieldElement;
import com.hbuf.idea.language.psi.HbufNameElement;
import com.hbuf.idea.language.psi.HbufTypeElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import static com.hbuf.idea.language.psi.HbufTypes.ID;

public abstract class HbufEnumFieldElementImpl extends ASTWrapperPsiElement implements HbufEnumFieldElement {

    public HbufEnumFieldElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }

    @NotNull
    @Override
    public long getNumber() {
        return getId().getId();
    }
}

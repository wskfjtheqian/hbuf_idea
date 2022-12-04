package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufFuncType;
import com.hbuf.idea.language.psi.HbufFuncTypeElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;


public abstract class HbufFuncTypeElementImpl extends ASTWrapperPsiElement implements HbufFuncTypeElement {
    public HbufFuncTypeElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getType() {
        return getId().getText();
    }

    abstract PsiElement getId();




}
package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufServerBodyElement;
import com.hbuf.idea.language.psi.HbufServerFuncElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;


public abstract class HbufServerBodyElementImpl extends ASTWrapperPsiElement implements HbufServerBodyElement {
    public HbufServerBodyElementImpl(@NotNull ASTNode node) {
        super(node);
    }


    @NotNull
    @Override
    public Collection<HbufServerFuncElement> getFuncts() {
        return getFuncList().getFuncts();
    }
}

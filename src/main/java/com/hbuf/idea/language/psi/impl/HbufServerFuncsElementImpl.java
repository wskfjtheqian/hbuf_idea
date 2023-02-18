package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class HbufServerFuncsElementImpl extends ASTWrapperPsiElement implements HbufServerFuncsElement {
    public HbufServerFuncsElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public Collection<HbufServerFuncElement> getFuncts() {
        List<HbufServerFuncElement> list = new ArrayList();
        HbufServerFuncsElementImpl element = this;
        while (null != element) {
            list.add(element.getFuncStatement());
            element = (HbufServerFuncsElementImpl) element.getFuncList();
        }
        return list;
    }

    @Nullable
    abstract HbufServerFuncsElement getFuncList();


    @NotNull
    abstract HbufServerFuncElement getFuncStatement();
}

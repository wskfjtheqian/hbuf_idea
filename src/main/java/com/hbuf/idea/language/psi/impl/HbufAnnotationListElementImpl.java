package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class HbufAnnotationListElementImpl extends ASTWrapperPsiElement implements HbufAnnotationListElement {
    public HbufAnnotationListElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public Collection<HbufAnnotationFieldElement> getFields() {
        List<HbufAnnotationFieldElement> list = new ArrayList();
        HbufAnnotationListElementImpl element = this;
        while (null != element) {
            list.add(element.getAnnotationField());
            element = (HbufAnnotationListElementImpl) element.getAnnotationList();
        }
        return list;
    }

    @NotNull
    abstract HbufAnnotationFieldElement getAnnotationField();

    @Nullable
    abstract HbufAnnotationListElement getAnnotationList();
}
package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufAnnotationElement;
import com.hbuf.idea.language.psi.HbufAnnotationFieldElement;
import com.hbuf.idea.language.psi.HbufAnnotationListElement;
import com.hbuf.idea.language.psi.HbufNameElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public abstract class HbufAnnotationElementImpl extends ASTWrapperPsiElement implements HbufAnnotationElement {

    public HbufAnnotationElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }

    abstract HbufNameElement getIdentName();

    @Override
    public @NotNull Collection<HbufAnnotationFieldElement> getFields() {
        HbufAnnotationListElement listElement = getAnnotationList();
        if (listElement == null) {
            return List.of();
        }
        return listElement.getFields();
    }

}

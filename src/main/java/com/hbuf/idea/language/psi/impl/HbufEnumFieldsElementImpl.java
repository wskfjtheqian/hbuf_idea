package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufEnumFieldElement;
import com.hbuf.idea.language.psi.HbufEnumFieldsElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class HbufEnumFieldsElementImpl extends ASTWrapperPsiElement implements HbufEnumFieldsElement {
    public HbufEnumFieldsElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public Collection<HbufEnumFieldElement> getFields() {
        List<HbufEnumFieldElement> list = new ArrayList();
        HbufEnumFieldsElementImpl element = this;
        while (null != element) {
            list.add(element.getEnumFieldStatement());
            element = (HbufEnumFieldsElementImpl) element.getEnumFieldList();
        }
        return list;
    }

    abstract HbufEnumFieldsElement getEnumFieldList();

    abstract HbufEnumFieldElement getEnumFieldStatement();
}

package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufEnumFieldElement;
import com.hbuf.idea.language.psi.HbufServerFuncsElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class HbufServerFuncsElementImpl extends ASTWrapperPsiElement implements HbufServerFuncsElement {
    public HbufServerFuncsElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public Collection<HbufEnumFieldElement> getFields() {
        List<HbufEnumFieldElement> list = new ArrayList();
        HbufServerFuncsElementImpl element = this;
        while (null != element) {
            list.add(element.getServerFuncstatement());
            element = (HbufServerFuncsElementImpl) element.getEnumFieldList();
        }
        return list;
    }

    abstract HbufServerFuncsElement getEnumFieldList();

    abstract HbufEnumFieldElement getServerFuncstatement();
}

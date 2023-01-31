package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufDataFieldElement;
import com.hbuf.idea.language.psi.HbufDataFieldsElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class HbufDataFieldsElementImpl extends ASTWrapperPsiElement implements HbufDataFieldsElement {
    public HbufDataFieldsElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public Collection<HbufDataFieldElement> getFields() {
        List<HbufDataFieldElement> list = new ArrayList();
        HbufDataFieldsElementImpl element = this;
        while (null != element) {
            list.add(element.getDataFieldStatement());
            element = (HbufDataFieldsElementImpl) element.getDataFieldList();
        }
        return list;
    }

    abstract HbufDataFieldsElement getDataFieldList();

    abstract HbufDataFieldElement getDataFieldStatement();

}

package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufDataBodyElement;
import com.hbuf.idea.language.psi.HbufDataFieldElement;
import com.hbuf.idea.language.psi.HbufDataFieldsElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public abstract class HbufDataBodyElementImpl extends ASTWrapperPsiElement implements HbufDataBodyElement {

    public HbufDataBodyElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public Collection<HbufDataFieldElement> getFields() {
        HbufDataFieldsElement fieldsElement = getDataFieldList();
        if (fieldsElement == null) {
            return new ArrayList<>();
        }
        return fieldsElement.getFields();
    }
}

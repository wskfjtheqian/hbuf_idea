package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufDataFieldElement;
import com.hbuf.idea.language.psi.HbufExtendsElement;
import com.hbuf.idea.language.psi.HbufNameElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public abstract class HbufDataElementImpl extends ASTWrapperPsiElement implements HbufDataElement {

    public HbufDataElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String var1) {
        getIdentName().setName(var1);
        return this;
    }

    abstract HbufExtendsElement getExtends();

    @NotNull
    @Override
    public Collection<HbufExtendsElement> getExtendList() {
        if (null == getExtends()) {
            return new ArrayList<>();
        }
        return getExtends().getExtendList();
    }

    @NotNull
    @Override
    public Collection<HbufDataFieldElement> getFields() {
        return getDataBody().getFields();
    }
}

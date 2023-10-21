package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufExtendsElement;
import com.hbuf.idea.language.psi.HbufIdentName;
import com.hbuf.idea.language.psi.HbufNameElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class HbufExtendsElementImpl extends ASTWrapperPsiElement implements HbufExtendsElement {
    public HbufExtendsElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public Collection<HbufExtendsElement> getExtendList() {
        List<HbufExtendsElement> list = new ArrayList();
        HbufExtendsElementImpl element = this;
        while (null != element) {
            list.add(element);
            element = (HbufExtendsElementImpl) element.getExtends();
        }
        return list;
    }

    
    abstract HbufExtendsElement getExtends();
}
package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufExtendsElement;
import com.hbuf.idea.language.psi.HbufNameElement;
import com.hbuf.idea.language.psi.HbufServerElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public abstract class HbufServerElementImpl extends ASTWrapperPsiElement implements HbufServerElement {

    public HbufServerElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getIdentName().getName();
    }

    @NotNull
    @Override
    public long getNumber() {
        return getId().getId();
    }


    @Nullable
    abstract HbufExtendsElement getExtends();

    @NotNull
    @Override
    public Collection<HbufNameElement> getExtendList() {
        if (null == getExtends()) {
            return new ArrayList<>();
        }
        return getExtends().getExtendList();
    }
}

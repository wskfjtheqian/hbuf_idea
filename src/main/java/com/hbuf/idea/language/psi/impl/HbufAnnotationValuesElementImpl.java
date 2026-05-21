package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufAnnotationValues;
import com.hbuf.idea.language.psi.HbufAnnotationValuesElement;
import com.hbuf.idea.language.psi.HbufString;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class HbufAnnotationValuesElementImpl extends ASTWrapperPsiElement implements HbufAnnotationValuesElement {
    public HbufAnnotationValuesElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public Collection<String> getValues() {
        List<String> list = new ArrayList();
        for (HbufString item : getStringList()) {
            list.add(item.getText());
        }
        return list;
    }

    abstract List<HbufString> getStringList();
}

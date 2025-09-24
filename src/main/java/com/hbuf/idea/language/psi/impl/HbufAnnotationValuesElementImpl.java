package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufAnnotationValues;
import com.hbuf.idea.language.psi.HbufAnnotationValuesElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.hbuf.idea.language.psi.HbufTypes.STRING;

public abstract class HbufAnnotationValuesElementImpl extends ASTWrapperPsiElement implements HbufAnnotationValuesElement {
    public HbufAnnotationValuesElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public Collection<String> getValues() {
        List<String> list = new ArrayList();
        HbufAnnotationValuesElementImpl element = this;
        while (null != element) {
            list.add(element.getString().toString());
            element = (HbufAnnotationValuesElementImpl) element.getAnnotationValues();
        }
        return list;
    }

    public abstract HbufAnnotationValues getAnnotationValues();

    public abstract PsiElement getString();
}

package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface HbufAnnotationFieldElement extends PsiElement {
    String getName();

    @NotNull
    HbufNameElement getIdentName();


    @NotNull
    public HbufAnnotationValues getAnnotationValues();

    @NotNull
    Collection<String> getValues();
}

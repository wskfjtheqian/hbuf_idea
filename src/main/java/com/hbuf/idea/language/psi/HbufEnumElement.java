package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface HbufEnumElement extends PsiNamedElement {
    String getName();

    @NotNull
    HbufNameElement getIdentName();

    
    HbufAnnotationGroup getAnnotationGroup();

    @NotNull
    HbufEnumBodyElement getEnumBody();

    @NotNull
    Collection<HbufEnumFieldElement> getFields();
}

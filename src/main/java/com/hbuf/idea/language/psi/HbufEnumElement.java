package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HbufEnumElement extends PsiNamedElement {
    String getName();

    @NotNull
    HbufNameElement getIdentName();

    @Nullable
    HbufAnnotationGroup getAnnotationGroup();

    @NotNull
    HbufEnumBodyElement getEnumBody();

}

package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HbufEnumElement extends PsiElement {
    String getName();

    @NotNull
    HbufNameElement getIdentName();

    @Nullable
    HbufAnnotationGroup getAnnotationGroup();

    @NotNull
    HbufEnumBodyElement getEnumBody();

}

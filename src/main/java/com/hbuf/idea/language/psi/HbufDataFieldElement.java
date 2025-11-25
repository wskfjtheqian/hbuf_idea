package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HbufDataFieldElement extends PsiElement {
    String getName();

    @NotNull
    HbufNameElement getIdentName();

    long getNumber();

    @NotNull
    HbufIdElement getId();

    @NotNull
    HbufFieldTypeElement getTypeStatement();

    @Nullable
    PsiElement getComment();
}

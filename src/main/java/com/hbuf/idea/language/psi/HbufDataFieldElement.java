package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface HbufDataFieldElement extends PsiElement {
    String getName();

    @NotNull
    HbufNameElement getIdentName();

    long getNumber();

    @NotNull
    HbufFieldTypeElement getTypeStatement();
}

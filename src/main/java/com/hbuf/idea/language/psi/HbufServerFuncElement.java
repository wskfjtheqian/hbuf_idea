package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface HbufServerFuncElement extends PsiElement {
    String getName();

    @NotNull
    HbufNameElement getIdentName();

    long getNumber();

    @NotNull
    HbufIdElement getId();

    @NotNull
    HbufFuncParam getFuncParam();

    @NotNull
    HbufFuncTypeElement getFuncType();
}

package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface HbufServerElement extends PsiElement {
    String getName();

    @NotNull
    HbufNameElement getIdentName();
}

package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface HbufTypeArrayElement extends PsiElement {

    @NotNull
    HbufTypeElement getTypeBase();


}

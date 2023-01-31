package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HbufTypeElement extends PsiElement {
    @NotNull
    String getName();

    boolean isNullable();
}

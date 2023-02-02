package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HbufDataBodyElement extends PsiElement {
    String getName();

    @Nullable
    HbufDataFieldsElement getDataFieldList();
}

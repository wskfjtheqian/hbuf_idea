package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface HbufFieldTypeElement extends PsiElement {

    @Nullable
    HbufTypeArrayElement getTypeArray();

    @Nullable
    HbufTypeElement getTypeBase();

    @Nullable
    HbufTypeMapElement getTypeMap();
}

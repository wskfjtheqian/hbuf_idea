package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.hbuf.idea.language.psi.HbufTypes.TYPES;

public interface HbufTypeMapElement extends PsiElement {

    @NotNull
    HbufTypeElement getTypeBase();

    HbufTypeMapKey getTypeMapKey();

    boolean isNullable();
}

package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface HbufDataElement extends PsiElement {
    String getName();

    @NotNull
    HbufNameElement getIdentName();

    long getNumber();

    @NotNull
    HbufIdElement getId();

    @Nullable
    HbufAnnotationGroupElement getAnnotationGroup();

    @NotNull
    Collection<HbufNameElement> getExtendList();

    @NotNull
    HbufDataBodyElement getDataBody();
}

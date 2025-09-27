package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface HbufAnnotationElement extends PsiElement {
    @Nullable
    HbufAnnotationListElement getAnnotationList();

    @Nullable
    String getName();

    @NotNull
    Collection<HbufAnnotationFieldElement> getFields();
}

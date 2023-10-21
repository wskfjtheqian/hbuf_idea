package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface HbufDataElement extends PsiNamedElement {
    String getName();

    @NotNull
    HbufNameElement getIdentName();


    HbufAnnotationGroupElement getAnnotationGroup();

    @NotNull
    Collection<HbufExtendsElement> getExtendList();


    HbufDataBodyElement getDataBody();

    @NotNull
    Collection<HbufDataFieldElement> getFields();
}

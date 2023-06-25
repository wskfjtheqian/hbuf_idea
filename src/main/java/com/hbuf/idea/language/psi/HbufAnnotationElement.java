package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HbufAnnotationElement extends PsiElement {
    
    HbufAnnotationListElement getAnnotationList();



}

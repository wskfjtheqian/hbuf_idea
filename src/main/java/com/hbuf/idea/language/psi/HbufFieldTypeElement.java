package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface HbufFieldTypeElement extends PsiElement {

    
    HbufTypeArrayElement getTypeArray();

    
    HbufTypeElement getTypeBase();

    
    HbufTypeMapElement getTypeMap();
}

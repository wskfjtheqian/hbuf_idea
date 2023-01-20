package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;

import java.util.Collection;

public interface HbufExtendsElement extends PsiElement {

    Collection<HbufNameElement> getExtendList();
}

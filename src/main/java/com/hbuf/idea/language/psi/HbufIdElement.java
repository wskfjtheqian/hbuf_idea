// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi;

import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;

import static com.hbuf.idea.language.psi.HbufTypes.NUMBER;

public interface HbufIdElement extends PsiElement {
    @NotNull
    PsiElement getNumber();

    int getNo();
}

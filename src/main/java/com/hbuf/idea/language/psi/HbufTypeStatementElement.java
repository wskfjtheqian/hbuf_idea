// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;


public interface HbufTypeStatementElement extends PsiElement {

    @Nullable
    HbufTypeArrayElement getArray();

    @Nullable
    HbufTypeBaseElement getBase();

    @Nullable
    HbufTypeMapElement getMap();
}
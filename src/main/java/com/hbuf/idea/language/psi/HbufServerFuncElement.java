// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi;

import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface HbufServerFuncElement extends PsiNameIdentifierOwner, NavigationItem {

    int getNo();

    HbufFuncTypeElement getType();

    HbufFuncParamElement getParam();
}

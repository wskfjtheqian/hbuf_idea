// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi;

import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface HbufDataElement extends PsiNameIdentifierOwner, NavigationItem {

    int getNo();

    @Nullable
    HbufAnnotationGroup getAnnotationGroup();

    @NotNull
    HbufDataBodyElement getDataBody();

    @NotNull
    Collection<HbufNameElement> getExtendList();

    HbufIdentName getIdentName();

}

// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi;

import com.intellij.psi.tree.IElementType;
import com.hbuf.idea.language.HbufLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class HbufElementType extends IElementType {

  public HbufElementType(@NotNull @NonNls String debugName) {
    super(debugName, HbufLanguage.INSTANCE);
  }

}

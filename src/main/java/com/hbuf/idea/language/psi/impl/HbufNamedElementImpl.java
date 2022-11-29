// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.psi.HbufNamedElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class HbufNamedElementImpl extends ASTWrapperPsiElement implements HbufNamedElement {

  public HbufNamedElementImpl(@NotNull ASTNode node) {
    super(node);
  }

}

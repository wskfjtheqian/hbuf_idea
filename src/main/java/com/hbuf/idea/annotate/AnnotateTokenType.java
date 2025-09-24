// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.annotate;

import com.hbuf.idea.annotate.AnnotateLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class AnnotateTokenType extends IElementType {

    public AnnotateTokenType(@NotNull @NonNls String debugName) {
        super(debugName, AnnotateLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "AnnotateTokenType." + super.toString();
    }

}

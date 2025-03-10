// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.annotate;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class AnnotateLanguage extends Language {

    public static final AnnotateLanguage INSTANCE = new AnnotateLanguage();

    private AnnotateLanguage() {
        super("Annotate");
    }

    public static class AnnotateTokenType extends IElementType {

        public AnnotateTokenType(@NotNull @NonNls String debugName) {
            super(debugName, INSTANCE);
        }

        @Override
        public String toString() {
            return "AnnotateTokenType." + super.toString();
        }

    }

    public static class AnnotateElementType extends IElementType {

        public AnnotateElementType(@NotNull @NonNls String debugName) {
            super(debugName, INSTANCE);
        }

    }
}

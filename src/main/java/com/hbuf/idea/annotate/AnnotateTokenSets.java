// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package com.hbuf.idea.annotate;

import com.hbuf.idea.annotate.psi.AnnotateTypes;
import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.psi.tree.TokenSet;


public interface AnnotateTokenSets {
    TokenSet COMMENTS = TokenSet.create(AnnotateTypes.COMMENT);
    TokenSet IDENTIFIERS = TokenSet.create(AnnotateTypes.IDENT);
}

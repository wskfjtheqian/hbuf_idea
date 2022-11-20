// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package com.hbuf.idea.language.psi;

import com.intellij.psi.tree.TokenSet;

public interface HbufTokenSets {

//  TokenSet IDENTIFIERS = TokenSet.create(HbufTypes.KEY);

  TokenSet COMMENTS = TokenSet.create(HbufTypes.COMMENT);

}

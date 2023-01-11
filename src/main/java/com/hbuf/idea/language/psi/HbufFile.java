// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi;

import com.hbuf.idea.language.HbufFileType;
import com.hbuf.idea.language.HbufLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class HbufFile extends PsiFileBase {

    public HbufFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, HbufLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return HbufFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Hbuf File";
    }

}

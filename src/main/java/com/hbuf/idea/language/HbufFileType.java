// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class HbufFileType extends LanguageFileType {

  public static final HbufFileType INSTANCE = new HbufFileType();

  private HbufFileType() {
    super(HbufLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return "Hbuf File";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Hbuf language file";
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return "hbuf";
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return HbufIcons.FILE;
  }

}

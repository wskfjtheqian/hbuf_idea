// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.formatter;

import com.hbuf.idea.language.HbufLanguage;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;

public class HbufLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

  @NotNull
  @Override
  public Language getLanguage() {
    return HbufLanguage.INSTANCE;
  }

  @Override
  public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
    if (settingsType == SettingsType.SPACING_SETTINGS) {
      consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS");
      consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator");
    } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
      consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
    }
  }

  @Override
  public String getCodeSample(@NotNull SettingsType settingsType) {
    return "package go=\"main\"\n" +
            "\n" +
            "import \"test2.hbuf\"\n" +
            "\n" +
            "//账号状态\n" +
            "[ui:]\n" +
            "enum AccountStatus{\n" +
            "  //账号状态（禁用）\n" +
            "  [lang:zh=\"禁用\";en=\"Disable\"]\n" +
            "  disable = 0\n" +
            "\n" +
            "  //账号状态（启用）\n" +
            "  [lang:zh=\"启用\";en=\"Enable\"]\n" +
            "  enable = 1\n" +
            "}\n";
  }

}

package com.hbuf.idea.language.formatter;

import com.hbuf.idea.language.HbufLanguage;
import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Mikhail Golubev
 */
public class HbufCodeStyleSettingsProvider extends CodeStyleSettingsProvider {

    @Override
    public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
        return new HbufCodeStyleSettings(settings);
    }

    
    @Override
    public String getConfigurableDisplayName() {
        return "Hbuf";
    }

    @NotNull
    public CodeStyleConfigurable createConfigurable(@NotNull CodeStyleSettings settings, @NotNull CodeStyleSettings modelSettings) {
        return new CodeStyleAbstractConfigurable(settings, modelSettings, this.getConfigurableDisplayName()) {
            @Override
            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                return new HbufCodeStyleMainPanel(getCurrentSettings(), settings);
            }
        };
    }

    private static class HbufCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {

        public HbufCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(HbufLanguage.INSTANCE, currentSettings, settings);
        }

    }
}
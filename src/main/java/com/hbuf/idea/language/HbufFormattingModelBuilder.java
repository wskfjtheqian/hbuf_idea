package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.formatting.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class HbufFormattingModelBuilder implements FormattingModelBuilder {
    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, HbufLanguage.INSTANCE)
                .around(HbufTypes.SPACE)
                .spaceIf(settings.getCommonSettings(HbufLanguage.INSTANCE.getID()).ALIGN_MULTILINE_METHOD_BRACKETS)
                .before(HbufTypes.DATA_BODY)
                .none();
    }

    @Override
    public @NotNull
    FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        final CodeStyleSettings codeStyleSettings = formattingContext.getCodeStyleSettings();
        return FormattingModelProvider.createFormattingModelForPsiFile(
                formattingContext.getContainingFile(),
                new HbufBlock(formattingContext.getNode(),
                        Wrap.createWrap(WrapType.NONE, false),
                        Alignment.createAlignment(),
                        createSpaceBuilder(codeStyleSettings)
                ),
                codeStyleSettings
        );
    }
}

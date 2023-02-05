// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.formatter;

import com.hbuf.idea.language.HbufLanguage;
import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.formatting.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;


public class HbufFormattingModelBuilder implements FormattingModelBuilder {

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, HbufLanguage.INSTANCE)
                .before(HbufTypes.PACKAGE).none()
                .after(HbufTypes.PACKAGE).spaces(1)
                .before(HbufTypes.ASSIGN).spaces(1)
                .after(HbufTypes.ASSIGN).spaces(1)
                .after(HbufTypes.PACKAGE_STATEMENT).lineBreakInCode()

                .before(HbufTypes.IMPORT).none()
                .after(HbufTypes.IMPORT).spaces(1)
                .after(HbufTypes.IMPORT_STATEMENT).lineBreakInCode()

                .before(HbufTypes.LBRACK).none()
                .after(HbufTypes.LBRACK).none()
                .before(HbufTypes.RBRACK).none()
                .after(HbufTypes.RBRACK).none()
                .before(HbufTypes.COLON).none()
                .after(HbufTypes.COLON).none()
                .before(HbufTypes.SEMICOLON).none()
                .after(HbufTypes.SEMICOLON).none()
                .after(HbufTypes.ANNOTATION).lineBreakInCode()
                .after(HbufTypes.ANNOTATION_GROUP).lineBreakInCode()

                .before(HbufTypes.ENUM).spaces(1)
                .after(HbufTypes.ENUM).spaces(1)
                .after(HbufTypes.ENUM_FIELD_STATEMENT).blankLines(1)
                .after(HbufTypes.ENUM_STATEMENT).blankLines(1)
                .before(HbufTypes.ENUM_BODY).spaces(1)
                .after(HbufTypes.ENUM_BODY).none()

                .before(HbufTypes.DATA).none()
                .after(HbufTypes.DATA).spaces(1)
                .after(HbufTypes.DATA_FIELD_STATEMENT).blankLines(1)
                .before(HbufTypes.QUESTION).none()
                .after(HbufTypes.QUESTION).none()
                .before(HbufTypes.TYPE_BASE).none()
                .after(HbufTypes.TYPE_BASE).none()
                .before(HbufTypes.TYPE_ARRAY).none()
                .after(HbufTypes.TYPE_ARRAY).none()
                .before(HbufTypes.TYPE_MAP).none()
                .after(HbufTypes.TYPE_MAP).none()
                .before(HbufTypes.TYPE_STATEMENT).none()
                .after(HbufTypes.TYPE_STATEMENT).spaces(1)
                .before(HbufTypes.LSS).none()
                .after(HbufTypes.LSS).none()
                .before(HbufTypes.GTR).none()
                .after(HbufTypes.GTR).none()
                .after(HbufTypes.DATA_STATEMENT).blankLines(1)
                .before(HbufTypes.DATA_BODY).spaces(1)
                .after(HbufTypes.DATA_BODY).none()

                .before(HbufTypes.SERVER).none()
                .after(HbufTypes.SERVER).spaces(1)
                .after(HbufTypes.FUNC_STATEMENT).blankLines(1)
                .before(HbufTypes.LPAREN).none()
                .after(HbufTypes.LPAREN).none()
                .before(HbufTypes.RPAREN).none()
                .after(HbufTypes.RPAREN).none()
                .before(HbufTypes.FUNC_TYPE).none()
                .after(HbufTypes.FUNC_TYPE).spaces(1)
                .before(HbufTypes.COMMA).none()
                .after(HbufTypes.COMMA).spaces(1)
                .after(HbufTypes.SERVER_STATEMENT).blankLines(1)
                .before(HbufTypes.SERVER_BODY).spaces(1)
                .after(HbufTypes.SERVER_BODY).none()
                .before(HbufTypes.LBRACE).none()
                .after(HbufTypes.LBRACE).none()
                ;
    }

    @Override
    public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        final CodeStyleSettings codeStyleSettings = formattingContext.getCodeStyleSettings();
        return FormattingModelProvider
                .createFormattingModelForPsiFile(formattingContext.getContainingFile(),
                        new HbufBlock(formattingContext.getNode(),
                                Wrap.createWrap(WrapType.NONE, false),
                                Alignment.createAlignment(),
                                createSpaceBuilder(codeStyleSettings),
                                Indent.getNoneIndent()
                        ),
                        codeStyleSettings);
    }

}

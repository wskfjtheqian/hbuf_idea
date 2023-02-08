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
                .after(HbufTypes.PACKAGE_STATEMENT).lineBreakInCode()

                .before(HbufTypes.IMPORT).none()
                .after(HbufTypes.IMPORT).spaces(1)
                .after(HbufTypes.IMPORT_STATEMENT).lineBreakInCode()
                .around(HbufTypes.LBRACK).none()
                .around(HbufTypes.RBRACK).none()
                .around(HbufTypes.COLON).none()
                .before(HbufTypes.SEMICOLON).none()
                .after(HbufTypes.SEMICOLON).spaces(1)
                .after(HbufTypes.ANNOTATION).lineBreakInCode()
                .after(HbufTypes.ANNOTATION_GROUP).lineBreakInCode()

                .before(HbufTypes.ENUM).none()
                .after(HbufTypes.ENUM).spaces(1)
                .after(HbufTypes.ENUM_FIELD_STATEMENT).blankLines(1)
                .after(HbufTypes.ENUM_STATEMENT).blankLines(1)
                .before(HbufTypes.ENUM_BODY).spaces(1)
                .after(HbufTypes.ENUM_BODY).none()

                .before(HbufTypes.DATA).none()
                .after(HbufTypes.DATA).spaces(1)
                .after(HbufTypes.DATA_FIELD_STATEMENT).blankLines(1)
                .around(HbufTypes.QUESTION).none()
                .around(HbufTypes.TYPE_BASE).none()
                .around(HbufTypes.TYPE_ARRAY).none()
                .around(HbufTypes.TYPE_MAP).none()
                .before(HbufTypes.TYPE_STATEMENT).none()
                .after(HbufTypes.TYPE_STATEMENT).spaces(1)
                .around(HbufTypes.LSS).none()
                .around(HbufTypes.GTR).none()
                .after(HbufTypes.DATA_STATEMENT).blankLines(1)
                .before(HbufTypes.DATA_BODY).spaces(1)
                .after(HbufTypes.DATA_BODY).none()

                .before(HbufTypes.SERVER).none()
                .after(HbufTypes.SERVER).spaces(1)
                .after(HbufTypes.FUNC_STATEMENT).blankLines(1)
                .around(HbufTypes.LPAREN).none()
                .around(HbufTypes.RPAREN).none()

                .before(HbufTypes.FUNC_TYPE).none()
                .after(HbufTypes.FUNC_TYPE).spaces(1)
                .before(HbufTypes.COMMA).none()
                .after(HbufTypes.COMMA).spaces(1)
                .after(HbufTypes.SERVER_STATEMENT).blankLines(1)
                .before(HbufTypes.SERVER_BODY).spaces(1)
                .after(HbufTypes.SERVER_BODY).none()
                .before(HbufTypes.LBRACE).none()
                .after(HbufTypes.LBRACE).lineBreakInCode()
                .around(HbufTypes.RBRACE).none()

                .aroundInside(HbufTypes.ASSIGN, HbufTypes.PACKAGE).spaces(1)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.ENUM_FIELD_STATEMENT).spaces(1)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.DATA_STATEMENT).spaces(1)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.DATA_FIELD_STATEMENT).spaces(1)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.SERVER_STATEMENT).spaces(1)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.FUNC_STATEMENT).spaces(1)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.ANNOTATION_FIELD).spaces(0)

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

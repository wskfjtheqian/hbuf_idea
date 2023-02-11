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
                .after(HbufTypes.PACKAGE).lineBreakOrForceSpace(false, true)
                .after(HbufTypes.PACKAGE_STATEMENT).lineBreakInCode()

                .before(HbufTypes.IMPORT).none()
                .after(HbufTypes.IMPORT).lineBreakOrForceSpace(false, true)
                .after(HbufTypes.IMPORT_STATEMENT).lineBreakInCode()

                .around(HbufTypes.LBRACK).lineBreakOrForceSpace(false, false)
                .around(HbufTypes.RBRACK).lineBreakOrForceSpace(false, false)
                .around(HbufTypes.COLON).lineBreakOrForceSpace(false, false)
                .before(HbufTypes.SEMICOLON).lineBreakOrForceSpace(false, false)
                .after(HbufTypes.SEMICOLON).lineBreakOrForceSpace(false, true)
                .after(HbufTypes.ANNOTATION).lineBreakInCode()
                .after(HbufTypes.ANNOTATION_GROUP).lineBreakInCode()

                .before(HbufTypes.ENUM).none()
                .after(HbufTypes.ENUM).lineBreakOrForceSpace(false, true)
                .after(HbufTypes.ENUM_FIELD_STATEMENT).blankLines(1)
                .after(HbufTypes.ENUM_STATEMENT).blankLines(1)
                .before(HbufTypes.ENUM_BODY).lineBreakOrForceSpace(false, true)
                .after(HbufTypes.ENUM_BODY).none()

                .before(HbufTypes.DATA).none()
                .after(HbufTypes.DATA).lineBreakOrForceSpace(false, true)
                .after(HbufTypes.DATA_FIELD_STATEMENT).blankLines(1)
                .around(HbufTypes.QUESTION).lineBreakOrForceSpace(false, false)
                .around(HbufTypes.TYPE_BASE).lineBreakOrForceSpace(false, false)
                .around(HbufTypes.TYPE_ARRAY).lineBreakOrForceSpace(false, false)
                .around(HbufTypes.TYPE_MAP).lineBreakOrForceSpace(false, false)
                .before(HbufTypes.TYPE_STATEMENT).lineBreakOrForceSpace(false, false)
                .after(HbufTypes.TYPE_STATEMENT).lineBreakOrForceSpace(false, true)
                .around(HbufTypes.LSS).lineBreakOrForceSpace(false, false)
                .around(HbufTypes.GTR).lineBreakOrForceSpace(false, false)
                .after(HbufTypes.DATA_STATEMENT).blankLines(1)
                .before(HbufTypes.DATA_BODY).lineBreakOrForceSpace(false, true)
                .after(HbufTypes.DATA_BODY).lineBreakOrForceSpace(false, false)

                .before(HbufTypes.SERVER).none()
                .after(HbufTypes.SERVER).lineBreakOrForceSpace(false, true)
                .after(HbufTypes.FUNC_STATEMENT).blankLines(1)
                .around(HbufTypes.LPAREN).lineBreakOrForceSpace(false, false)
                .before(HbufTypes.RPAREN).lineBreakOrForceSpace(false, false)
                .after(HbufTypes.RPAREN).lineBreakOrForceSpace(false, true)

                .before(HbufTypes.FUNC_TYPE).lineBreakOrForceSpace(false, false)
                .after(HbufTypes.FUNC_TYPE).lineBreakOrForceSpace(false, true)
                .before(HbufTypes.COMMA).lineBreakOrForceSpace(false, false)
                .after(HbufTypes.COMMA).lineBreakOrForceSpace(false, true)
                .after(HbufTypes.SERVER_STATEMENT).blankLines(1)
                .before(HbufTypes.SERVER_BODY).lineBreakOrForceSpace(false, true)
                .after(HbufTypes.SERVER_BODY).lineBreakOrForceSpace(false, false)
                .before(HbufTypes.LBRACE).lineBreakOrForceSpace(false, true)
                .after(HbufTypes.LBRACE).lineBreakInCode()

                .aroundInside(HbufTypes.ASSIGN, HbufTypes.PACKAGE_STATEMENT).lineBreakOrForceSpace(false, true)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.ENUM_FIELD_STATEMENT).lineBreakOrForceSpace(false, true)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.DATA_STATEMENT).lineBreakOrForceSpace(false, true)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.DATA_FIELD_STATEMENT).lineBreakOrForceSpace(false, true)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.SERVER_STATEMENT).lineBreakOrForceSpace(false, true)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.FUNC_STATEMENT).lineBreakOrForceSpace(false, true)
                .aroundInside(HbufTypes.ASSIGN, HbufTypes.ANNOTATION_FIELD).lineBreakOrForceSpace(false, false)

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

package com.hbuf.idea.language.template;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Macro;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.macro.MacroBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HbufLiveTemplatesMacro extends MacroBase {
    public HbufLiveTemplatesMacro() {
        super("titleCase", "titleCase(String)");
    }

    @Override
    protected @Nullable Result calculateResult(Expression[] expressions, ExpressionContext expressionContext, boolean b) {
        return null;
    }
}

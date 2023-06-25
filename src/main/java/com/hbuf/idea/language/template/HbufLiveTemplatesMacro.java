package com.hbuf.idea.language.template;

import com.hbuf.idea.language.HbufLanguage;
import com.hbuf.idea.language.psi.HbufUtil;
import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.codeInsight.template.macro.MacroBase;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiUtilCore;
import org.jetbrains.annotations.Nullable;

public class HbufLiveTemplatesMacro {
    public static class GetDataId extends MacroBase {
        public GetDataId() {
            super("getDataId", "getDataId()");
        }

        @Override
        protected  Result calculateResult(Expression[] expressions, ExpressionContext context, boolean b) {
            int id = HbufUtil.getDataNewId(context.getProject());
            return new TextResult("" + id);
        }
    }

    public static class GetServerId extends MacroBase {
        public GetServerId() {
            super("getServerId", "getServerId()");
        }

        @Override
        protected  Result calculateResult(Expression[] expressions, ExpressionContext context, boolean b) {
            int id = HbufUtil.getServerNewId(context.getProject());
            return new TextResult("" + id);
        }
    }
}

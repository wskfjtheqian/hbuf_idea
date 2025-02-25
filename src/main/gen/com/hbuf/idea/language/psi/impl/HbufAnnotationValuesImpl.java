// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.psi.impl;

import java.util.List;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;

import static com.hbuf.idea.language.psi.HbufTypes.*;

import com.hbuf.idea.language.psi.*;

public class HbufAnnotationValuesImpl extends HbufAnnotationValuesElementImpl implements HbufAnnotationValues {

    public HbufAnnotationValuesImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull HbufVisitor visitor) {
        visitor.visitAnnotationValues(this);
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof HbufVisitor) accept((HbufVisitor) visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public HbufAnnotationValues getAnnotationValues() {
        return findChildByClass(HbufAnnotationValues.class);
    }

    @Override
    @NotNull
    public PsiElement getString() {
        return findNotNullChildByType(STRING);
    }

    @Override
    public boolean isValidHost() {
        return true;
    }

    @Override
    public PsiLanguageInjectionHost updateText(@NotNull String s) {
        return this;
    }

    @Override
    public @NotNull LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
        return new LiteralTextEscaper<HbufAnnotationValuesImpl>(this) {

            @Override
            public boolean decode(@NotNull TextRange rangeInsideHost, @NotNull StringBuilder outChars) {
                outChars.append(myHost.getText().substring(rangeInsideHost.getStartOffset(), rangeInsideHost.getEndOffset()));
                return true;
            }

            @Override
            public int getOffsetInHost(int offsetInDecoded, @NotNull TextRange rangeInsideHost) {
                return rangeInsideHost.getStartOffset() + offsetInDecoded;
            }

            @Override
            public boolean isOneLine() {
                return false;
            }
        };
    }
}

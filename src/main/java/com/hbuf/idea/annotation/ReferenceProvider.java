package com.hbuf.idea.annotation;

import com.hbuf.idea.language.psi.HbufUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class ReferenceProvider extends PsiReferenceProvider {
    @Override
    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext processingContext) {
//        if (HbufUtil.checkAnnotationValue(element, "db", "table")) {
//            return new PsiReference[]{new TableReference(element, element.getText().substring(1, element.getText().length() - 1))};
//        }
//        if (HbufUtil.checkAnnotationValue(element, "tag", "power")) {
//            return new PsiReference[]{new TableReference(element, element.getText().substring(1, element.getText().length() - 1))};
//        }
        return PsiReference.EMPTY_ARRAY;
    }
}

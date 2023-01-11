package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufFuncParam;
import com.hbuf.idea.language.psi.HbufFuncType;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class HbufReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(HbufFuncParam.class),
                new PsiReferenceProvider() {
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(HbufFuncType.class),
                new PsiReferenceProvider() {
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }
}

package com.hbuf.idea.language.contributor;

import com.hbuf.idea.language.HbufLanguage;
import com.hbuf.idea.language.psi.HbufStringElement;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class EnumReferenceContributor extends PsiReferenceContributor {
    public EnumReferenceContributor() {
        System.out.println("EnumReferenceContributor LOADED!");
    }

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        System.out.println("registerReferenceProviders CALLED!");

        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(HbufStringElement.class).withLanguage(HbufLanguage.INSTANCE),
                new PsiReferenceProvider() {
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        if (!(element instanceof HbufStringElement)) return PsiReference.EMPTY_ARRAY;

                        HbufStringElement literalExpression = (HbufStringElement) element;
                        Object value = literalExpression.getText();
                        if (value instanceof String && ((String) value).matches("[A-Za-z0-9_]+\\.[A-Za-z0-9_]+")) {
                            return new PsiReference[]{new EnumReference(literalExpression)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                }
        );
    }
}

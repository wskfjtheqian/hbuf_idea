package com.hbuf.idea.language.injection;

import com.hbuf.idea.language.psi.HbufAnnotation;
import com.hbuf.idea.language.psi.HbufAnnotationField;
import com.hbuf.idea.language.psi.HbufAnnotationValues;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.intellij.lang.regexp.RegExpLanguage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HbufLanguageHostInjector implements MultiHostInjector {
    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar multiHostRegistrar, @NotNull PsiElement psiElement) {

        if (shouldInjectRegex(psiElement, "format", "reg")) {
            multiHostRegistrar
                    .startInjecting(RegExpLanguage.INSTANCE)
                    .addPlace(null, null,
                            (PsiLanguageInjectionHost) psiElement,
                            TextRange.from(1, psiElement.getTextLength() - 2))
                    .doneInjecting();
        }
    }

    private boolean shouldInjectRegex(PsiElement psiElement, String annotationName, String fieldName) {
        if (psiElement instanceof HbufAnnotationValues) {
            psiElement = psiElement.getParent();

            if (psiElement instanceof HbufAnnotationField annotationField) {

                if (fieldName.equals(annotationField.getName())) {
                    psiElement = annotationField.getParent().getParent();
                    if (psiElement instanceof HbufAnnotation annotation) {

                        return annotationName.equals(annotation.getName());
                    }
                }
            }
        }
        return false;
    }

    @Override
    public @NotNull List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return List.of(HbufAnnotationValues.class);
    }
}

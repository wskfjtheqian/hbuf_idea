package com.hbuf.idea.language.injection;

import com.hbuf.idea.language.psi.*;
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
        if (HbufUtil.checkAnnotationValue(psiElement, "format", "reg")) {
            multiHostRegistrar
                    .startInjecting(RegExpLanguage.INSTANCE)
                    .addPlace(null, null,
                            (PsiLanguageInjectionHost) psiElement,
                            TextRange.from(1, psiElement.getTextLength() - 2))
                    .doneInjecting();
        }
    }

    @Override
    public @NotNull List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return List.of(HbufStringElement.class);
    }
}

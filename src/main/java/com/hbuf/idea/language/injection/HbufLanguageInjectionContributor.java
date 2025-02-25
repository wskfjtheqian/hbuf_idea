package com.hbuf.idea.language.injection;

import com.hbuf.idea.language.psi.HbufAnnotation;
import com.hbuf.idea.language.psi.HbufAnnotationField;
import com.hbuf.idea.language.psi.HbufAnnotationValues;
import com.intellij.lang.injection.general.Injection;
import com.intellij.lang.injection.general.LanguageInjectionContributor;
import com.intellij.lang.injection.general.SimpleInjection;
import com.intellij.psi.PsiElement;
import org.intellij.lang.regexp.RegExpLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// 这个类用于将正则表达式语言注入到注解值中。
public class HbufLanguageInjectionContributor implements LanguageInjectionContributor {

    // 这个方法用于判断是否应该注入正则表达式语言。
    @Override
    public @Nullable Injection getInjection(@NotNull PsiElement psiElement) {
        if (shouldInjectRegex(psiElement, "format", "ret")) {
            return new SimpleInjection(RegExpLanguage.INSTANCE, "", "", null);
        }
        return null;
    }

    // 这个方法用于判断是否应该注入正则表达式语言。
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


}

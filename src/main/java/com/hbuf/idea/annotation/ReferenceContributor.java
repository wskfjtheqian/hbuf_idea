package com.hbuf.idea.annotation;


import com.hbuf.idea.language.psi.HbufAnnotationValues;
import com.hbuf.idea.language.psi.HbufNameElement;
import com.hbuf.idea.language.psi.HbufStringElement;
import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceRegistrar;
import org.jetbrains.annotations.NotNull;

public class ReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(HbufStringElement.class),
                new ReferenceProvider()
        );
    }
}

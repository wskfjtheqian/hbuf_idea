package com.hbuf.idea.language;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.Nullable;

public class HbufReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    public HbufReference(PsiElement element, TextRange property) {
        super(element, property);
    }

    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        return new ResolveResult[0];
    }

    @Override
    public @Nullable PsiElement resolve() {
        return null;
    }
}

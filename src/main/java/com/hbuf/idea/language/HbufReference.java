package com.hbuf.idea.language;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
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

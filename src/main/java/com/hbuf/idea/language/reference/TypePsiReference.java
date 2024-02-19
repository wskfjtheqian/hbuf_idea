package com.hbuf.idea.language.reference;

import com.hbuf.idea.language.HbufIcons;
import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufEnumElement;
import com.hbuf.idea.language.psi.HbufUtil;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public  class TypePsiReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    private final String name;

    public TypePsiReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        name = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean b) {
        Project project = myElement.getProject();
        List<ResolveResult> results = new ArrayList<>();
        for (HbufDataElement item : HbufUtil.findData(project, name)) {
            results.add(new PsiElementResolveResult(item.getIdentName()));
        }
        for (HbufEnumElement item : HbufUtil.findEnum(project, name)) {
            results.add(new PsiElementResolveResult(item.getIdentName()));
        }
        return results.toArray(new ResolveResult[0]);
    }

    
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @Override
    public Object @NotNull [] getVariants() {
        Project project = myElement.getProject();
        List<LookupElement> variants = new ArrayList<>();
        for (final HbufDataElement item : HbufUtil.findData(project)) {
            if (item.getName() != null && !item.getName().isEmpty()) {
                variants.add(LookupElementBuilder
                        .create(item).withIcon(HbufIcons.FILE)
                        .withTypeText(item.getContainingFile().getName())
                );
            }
        }
        for (final HbufEnumElement item : HbufUtil.findEnum(project)) {
            if (item.getName() != null && !item.getName().isEmpty()) {
                variants.add(LookupElementBuilder
                        .create(item).withIcon(HbufIcons.FILE)
                        .withTypeText(item.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }

    @Override
    public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
        return HbufUtil.createNameElement(myElement.getProject(), newElementName);
    }
}


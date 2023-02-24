package com.hbuf.idea.language.reference;

import com.hbuf.idea.language.HbufIcons;
import com.hbuf.idea.language.psi.HbufNameElement;
import com.hbuf.idea.language.psi.HbufServerElement;
import com.hbuf.idea.language.psi.HbufUtil;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ServerPsiReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    private final String name;

    public ServerPsiReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        name = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @Override
    public ResolveResult[] multiResolve(boolean b) {
        Project project = myElement.getProject();
        List<ResolveResult> results = new ArrayList<>();
        for (HbufServerElement item : HbufUtil.findServer(project, name)) {
            results.add(new PsiElementResolveResult(item.getIdentName()));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<LookupElement> variants = new ArrayList<>();
        for (final HbufServerElement item : HbufUtil.findServer(project)) {
            if (item.getName() != null && item.getName().length() > 0) {
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
        HbufNameElement newElement = HbufUtil.createNameElement(myElement.getProject(), newElementName);
        return newElement;
    }
}

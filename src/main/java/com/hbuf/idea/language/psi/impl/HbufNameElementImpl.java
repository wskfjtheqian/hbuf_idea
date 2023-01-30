package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.HbufIcons;
import com.hbuf.idea.language.psi.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class HbufNameElementImpl extends ASTWrapperPsiElement implements HbufNameElement {
    public HbufNameElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @Nullable PsiElement getNameIdentifier() {
        return this;
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
        HbufNameElement element = HbufUtil.createNameElement(getProject(), s);
        getNode().replaceChild(getIdent().getNode(), element.getFirstChild().getNode());
        return this;
    }

    abstract PsiElement getIdent();

    @Override
    public String getName() {
        return getText();
    }

    @Override
    public PsiReference getReference() {
        if (this.getParent() instanceof HbufFuncTypeElement) {
            return new DataPsiReference(this, TextRange.create(0, getIdent().getTextLength()));
        } else if (this.getParent() instanceof HbufExtendsElement) {
            if (this.getParent().getParent() instanceof HbufEnumElement) {
                return new DataPsiReference(this, TextRange.create(0, getIdent().getTextLength()));
            } else if (this.getParent().getParent() instanceof HbufServerElement) {
                return new ServerPsiReference(this, TextRange.create(0, getIdent().getTextLength()));
            }
        } else if (this.getParent() instanceof HbufTypeElement) {
            return new TypePsiReference(this, TextRange.create(0, getIdent().getTextLength()));
        }
        return null;
    }

    class TypePsiReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

        private final String name;

        public TypePsiReference(@NotNull PsiElement element, TextRange textRange) {
            super(element, textRange);
            name = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
        }

        @Override
        public ResolveResult[] multiResolve(boolean b) {
            Project project = myElement.getProject();
            List<ResolveResult> results = new ArrayList<>();
            for (HbufDataElement item : HbufUtil.findData(project, name)) {
                results.add(new PsiElementResolveResult(item.getIdentName()));
            }
            for (HbufEnumElement item : HbufUtil.findEnum(project, name)) {
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
            for (final HbufDataElement item : HbufUtil.findData(project)) {
                if (item.getName() != null && item.getName().length() > 0) {
                    variants.add(LookupElementBuilder
                            .create(item).withIcon(HbufIcons.FILE)
                            .withTypeText(item.getContainingFile().getName())
                    );
                }
            }
            for (final HbufEnumElement item : HbufUtil.findEnum(project)) {
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
            return super.handleElementRename(newElementName);
        }
    }


    class DataPsiReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

        private final String name;

        public DataPsiReference(@NotNull PsiElement element, TextRange textRange) {
            super(element, textRange);
            name = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
        }

        @Override
        public ResolveResult[] multiResolve(boolean b) {
            Project project = myElement.getProject();
            List<ResolveResult> results = new ArrayList<>();
            for (HbufDataElement item : HbufUtil.findData(project, name)) {
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
            for (final HbufDataElement item : HbufUtil.findData(project)) {
                if (item.getName() != null && item.getName().length() > 0) {
                    variants.add(LookupElementBuilder
                            .create(item).withIcon(HbufIcons.FILE)
                            .withTypeText(item.getContainingFile().getName())
                    );
                }
            }
            return variants.toArray();
        }
    }

    class ServerPsiReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

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
    }


}


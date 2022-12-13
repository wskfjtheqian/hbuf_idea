package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.*;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HbufFindUsagesProvider implements FindUsagesProvider {

    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new HbufLexerAdapter(),
                HbufTokenSets.IDENTIFIERS,
                HbufTokenSets.COMMENTS,
                TokenSet.EMPTY);
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof HbufEnumElement
                || psiElement instanceof HbufDataElement
                || psiElement instanceof HbufFieldStatement
                ;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if(element instanceof HbufEnumElement) return "enum";
        if(element instanceof HbufDataElement) return "data";
        if(element instanceof HbufFieldStatement) return "field";
        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        return ((PsiNamedElement)element).getName();
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement psiElement, boolean useFullName) {
        if(!useFullName){
            return ((PsiNamedElement)psiElement).getName();
        }
        return ((PsiNamedElement)psiElement).getName();
    }

}
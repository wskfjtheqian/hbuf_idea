package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.*;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.openapi.util.NlsSafe;
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
        if (element instanceof HbufEnumElement) return "Hbuf enum";
        if (element instanceof HbufDataElement) return "Hbuf data";
        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        if (element instanceof HbufEnumElement) {
            return ((HbufEnumElement) element).getName();
        }else if (element instanceof HbufDataElement) {
            return ((HbufDataElement) element).getName();
        }
        return "";
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        if (element instanceof HbufEnumElement) {
            return "Enum:" + ((HbufEnumElement) element).getName() ;
        }else if (element instanceof HbufDataElement) {
            return "Data:" +  ((HbufDataElement) element).getName();
        }
        return "";
    }

}
package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufNameElement;
import com.hbuf.idea.language.psi.HbufTokenSets;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
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
                HbufTokenSets.STRING);
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof HbufNameElement;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        PsiElement parent = element.getParent();
        if (parent instanceof HbufDataElement) return "Hbuf data";
        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        PsiElement parent = element.getParent();
        if (parent instanceof HbufDataElement) {
            return ((HbufDataElement) parent).getName();
        }
        return "";
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        PsiElement parent = element.getParent();
        if (parent instanceof HbufDataElement) {
            return "Data:" + ((HbufDataElement) parent).getName();
        }
        return element.getText();
    }

}
package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HbufBraceMatcher implements PairedBraceMatcher {
    //todo [low] what is true and what is false
    private static final BracePair[] PAIRS = {
            new BracePair(HbufTypes.LBRACE,HbufTypes.RBRACE,false),
            new BracePair(HbufTypes.LBRACK, HbufTypes.RBRACK,false),
            new BracePair(HbufTypes.LPAREN, HbufTypes.RPAREN,false),
            new BracePair(HbufTypes.LSS, HbufTypes.GTR,false)
    };


    public BracePair[] getPairs() {
        return PAIRS;
    }

    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType iElementType, @Nullable IElementType iElementType1) {
        return true;
    }

    public int getCodeConstructStart(PsiFile psiFile, int i) {
        return i;
    }
}

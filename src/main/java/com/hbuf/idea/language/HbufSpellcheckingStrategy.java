package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.spellchecker.inspections.CommentSplitter;
import com.intellij.spellchecker.inspections.IdentifierSplitter;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.TokenConsumer;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import org.jetbrains.annotations.NotNull;

//拼写检查(自动检查单词是否拼写错误)
public class HbufSpellcheckingStrategy extends SpellcheckingStrategy {
    @Override
    public @NotNull
    Tokenizer<?> getTokenizer(PsiElement element) {
        if (element instanceof PsiComment) {
            return new SimpleCommentTokenizer();
        }

        if (element instanceof LeafPsiElement) {
            @NotNull IElementType type = ((LeafPsiElement) element).getElementType();
            if (type == HbufTypes.ID) {
                return new PackageElementTokenizer();
            }
        }

        return EMPTY_TOKENIZER;
    }

    private static class SimpleCommentTokenizer extends Tokenizer<PsiComment> {

        @Override
        public void tokenize(@NotNull PsiComment element, TokenConsumer consumer) {
            // Exclude the start of the comment with its # characters from spell checking
            int startIndex = 0;
            for (char c : element.textToCharArray()) {
                if (c == '/' || Character.isWhitespace(c)) {
                    startIndex++;
                } else {
                    break;
                }
            }
            consumer.consumeToken(element, element.getText(), false, 0,
                    TextRange.create(startIndex, element.getTextLength()),
                    CommentSplitter.getInstance());
        }

    }

    private static class PackageElementTokenizer extends Tokenizer<LeafPsiElement> {

        public void tokenize(@NotNull LeafPsiElement element, TokenConsumer consumer) {
            final ASTNode id = element.getNode();
            if (id != null && id.getTextLength() > 0) {
                final PsiElement keyPsi = id.getPsi();
                final String text = id.getText();
                consumer.consumeToken(keyPsi, text, true, 0,
                        TextRange.allOf(text), IdentifierSplitter.getInstance());
            }
        }

    }
}

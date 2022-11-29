package com.hbuf.idea.language;

import com.hbuf.idea.language.parser.HbufParser;
import com.hbuf.idea.language.psi.HbufFile;
import com.hbuf.idea.language.psi.HbufTokenSets;
import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class HbufParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(HbufLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new HbufLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return HbufTokenSets.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new HbufParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new HbufFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return HbufTypes.Factory.createElement(node);
    }

}
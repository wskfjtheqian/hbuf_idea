package com.hbuf.idea.annotate;


import com.hbuf.idea.annotate.AnnotateFile;
import com.hbuf.idea.annotate.AnnotateLanguage;
import com.hbuf.idea.annotate.AnnotateLexerAdapter;
import com.hbuf.idea.annotate.parser.AnnotateParser;
import com.hbuf.idea.annotate.psi.AnnotateTypes;
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

public class AnnotateParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(AnnotateLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new AnnotateLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return AnnotateTokenSets.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new AnnotateParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new AnnotateFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return AnnotateTypes.Factory.createElement(node);
    }

}
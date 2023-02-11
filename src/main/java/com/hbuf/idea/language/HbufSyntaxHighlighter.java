// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class HbufSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    public static final TextAttributesKey COMMENT = createTextAttributesKey(
            "HBUF_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT
    );
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};

    public static final TextAttributesKey STRING = createTextAttributesKey(
            "HBUF_STRING", DefaultLanguageHighlighterColors.STRING
    );
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};

    public static final TextAttributesKey KEY = createTextAttributesKey(
            "HBUF_KEY", DefaultLanguageHighlighterColors.KEYWORD
    );
    private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};

    public static final TextAttributesKey NUMBER = createTextAttributesKey(
            "HBUF_NUMBER", DefaultLanguageHighlighterColors.NUMBER
    );
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};

    public static final TextAttributesKey IDENTIFIER = createTextAttributesKey(
            "HBUF_IDENTIFIER", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
    );
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};


    public static final TextAttributesKey SEMICOLON = createTextAttributesKey(
            "HBUF_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON
    );
    private static final TextAttributesKey[] SEMICOLON_KEYS = new TextAttributesKey[]{SEMICOLON};

    public static final TextAttributesKey METHOD = createTextAttributesKey(
            "HBUF_METHOD", DefaultLanguageHighlighterColors.INSTANCE_METHOD
    );
    private static final TextAttributesKey[] METHOD_KEYS = new TextAttributesKey[]{METHOD};

    public static final TextAttributesKey FIELD = createTextAttributesKey(
            "HBUF_FIELD", DefaultLanguageHighlighterColors.INSTANCE_FIELD
    );
    private static final TextAttributesKey[] FIELD_KEYS = new TextAttributesKey[]{FIELD};


    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new HbufLexerAdapter();
    }

    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(HbufTypes.COMMENT)) {
            return COMMENT_KEYS;
        }
        if (tokenType.equals(HbufTypes.STRING)) {
            return STRING_KEYS;
        }
        if (tokenType.equals(HbufTypes.NUMBER)) {
            return NUMBER_KEYS;
        }

        if (tokenType.equals(HbufTypes.PACKAGE) ||
                tokenType.equals(HbufTypes.IMPORT) ||
                tokenType.equals(HbufTypes.ENUM) ||
                tokenType.equals(HbufTypes.DATA) ||
                tokenType.equals(HbufTypes.SERVER) ||
                tokenType.equals(HbufTypes.TYPES)) {
            return KEY_KEYS;
        }

        if (tokenType.equals(HbufTypes.IDENT_NAME)) {
            return EMPTY_KEYS;
        }

        if (tokenType.equals(HbufTypes.SEMICOLON) || tokenType.equals(HbufTypes.COLON) || tokenType.equals(HbufTypes.COMMA)) {
            return SEMICOLON_KEYS;
        }

        return EMPTY_KEYS;
    }

}


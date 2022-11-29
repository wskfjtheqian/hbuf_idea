package com.hbuf.idea.language;

import com.intellij.lexer.FlexAdapter;

public class HbufLexerAdapter extends FlexAdapter {

    public HbufLexerAdapter() {
        super(new HbufLexer(null));
    }

}

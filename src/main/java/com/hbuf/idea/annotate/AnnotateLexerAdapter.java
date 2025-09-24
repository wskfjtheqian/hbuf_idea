package com.hbuf.idea.annotate;

import com.hbuf.idea.language._HbufLexer;
import com.intellij.lexer.FlexAdapter;

public class AnnotateLexerAdapter extends FlexAdapter {

    public AnnotateLexerAdapter() {
        super(new _AnnotateLexer(null));
    }

}

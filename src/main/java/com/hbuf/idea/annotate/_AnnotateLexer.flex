package com.hbuf.idea.annotate;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.hbuf.idea.annotate.psi.AnnotateTypes.*;

%%

%{
  public _AnnotateLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _AnnotateLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

IDENT=[:letter:][a-zA-Z_0-9]*
COMMENT="//".*

%%
<YYINITIAL> {
  {WHITE_SPACE}       { return WHITE_SPACE; }

  "."                 { return POINT; }

  {IDENT}             { return IDENT; }
  {COMMENT}           { return COMMENT; }

}

[^] { return BAD_CHARACTER; }

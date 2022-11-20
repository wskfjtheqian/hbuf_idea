package com.hbuf.idea.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.hbuf.idea.language.psi.HbufTypes.*;

%%

%{
  public _HbufLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _HbufLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

COMMENT="//".*
NUMBER=[0-9]+(\.[0-9]*)?
ID=[:letter:][a-zA-Z_0-9]*
NAME=[a-zA-Z]
STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")

%%
<YYINITIAL> {
  {WHITE_SPACE}      { return WHITE_SPACE; }

  "="                { return EQ; }
  "import"           { return IMPORT; }
  "data"             { return DATA; }
  "server"           { return SERVER; }
  "CRLF"             { return CRLF; }

  {COMMENT}          { return COMMENT; }
  {NUMBER}           { return NUMBER; }
  {ID}               { return ID; }
  {NAME}             { return NAME; }
  {STRING}           { return STRING; }

}

[^] { return BAD_CHARACTER; }

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

TYPES=int8|int16|int32|int64|uint8|uint16|uint32|uint64|bool|float|double|decimal|string|date
COMMENT="//".*
NUMBER=[0-9]+(\.[0-9]*)?
ID=[:letter:][a-zA-Z_0-9]*
STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")

%%
<YYINITIAL> {
  {WHITE_SPACE}      { return WHITE_SPACE; }

  " "                { return SPACE; }
  "="                { return ASSIGN; }
  "?"                { return QUESTION; }
  "<"                { return LSS; }
  "("                { return LPAREN; }
  "["                { return LBRACK; }
  "{"                { return LBRACE; }
  ","                { return COMMA; }
  ">"                { return GTR; }
  ")"                { return RPAREN; }
  "]"                { return RBRACK; }
  "}"                { return RBRACE; }
  ";"                { return SEMICOLON; }
  ":"                { return COLON; }
  "package"          { return PACKAGE; }
  "import"           { return IMPORT; }
  "enum"             { return ENUM; }
  "server"           { return SERVER; }
  "data"             { return DATA; }
  "CRLF"             { return CRLF; }

  {TYPES}            { return TYPES; }
  {COMMENT}          { return COMMENT; }
  {NUMBER}           { return NUMBER; }
  {ID}               { return ID; }
  {STRING}           { return STRING; }

}

[^] { return BAD_CHARACTER; }

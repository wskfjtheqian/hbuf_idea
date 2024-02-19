// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.hbuf.idea.language.psi.HbufTypes.*;
import static com.hbuf.idea.language.parser.HbufParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class HbufParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return hbufFile(b, l + 1);
  }

  /* ********************************************************** */
  // COMMENT|(LBRACK ident-name COLON [annotation-list] RBRACK)
  public static boolean annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation")) return false;
    if (!nextTokenIs(b, "<annotation>", COMMENT, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION, "<annotation>");
    r = consumeToken(b, COMMENT);
    if (!r) r = annotation_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LBRACK ident-name COLON [annotation-list] RBRACK
  private static boolean annotation_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACK);
    r = r && ident_name(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && annotation_1_3(b, l + 1);
    r = r && consumeToken(b, RBRACK);
    exit_section_(b, m, null, r);
    return r;
  }

  // [annotation-list]
  private static boolean annotation_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_1_3")) return false;
    annotation_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ident-name ASSIGN annotation-values
  public static boolean annotation_field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_field")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ident_name(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    r = r && annotation_values(b, l + 1);
    exit_section_(b, m, ANNOTATION_FIELD, r);
    return r;
  }

  /* ********************************************************** */
  // annotation [annotation-group]
  public static boolean annotation_group(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_group")) return false;
    if (!nextTokenIs(b, "<annotation group>", COMMENT, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_GROUP, "<annotation group>");
    r = annotation(b, l + 1);
    r = r && annotation_group_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group]
  private static boolean annotation_group_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_group_1")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // annotation-field [SEMICOLON annotation-list]
  public static boolean annotation_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_list")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = annotation_field(b, l + 1);
    r = r && annotation_list_1(b, l + 1);
    exit_section_(b, m, ANNOTATION_LIST, r);
    return r;
  }

  // [SEMICOLON annotation-list]
  private static boolean annotation_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_list_1")) return false;
    annotation_list_1_0(b, l + 1);
    return true;
  }

  // SEMICOLON annotation-list
  private static boolean annotation_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    r = r && annotation_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STRING [COMMA annotation-values]
  public static boolean annotation_values(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_values")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    r = r && annotation_values_1(b, l + 1);
    exit_section_(b, m, ANNOTATION_VALUES, r);
    return r;
  }

  // [COMMA annotation-values]
  private static boolean annotation_values_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_values_1")) return false;
    annotation_values_1_0(b, l + 1);
    return true;
  }

  // COMMA annotation-values
  private static boolean annotation_values_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_values_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && annotation_values(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE [data-field-list] RBRACE
  public static boolean data_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_body")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && data_body_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, DATA_BODY, r);
    return r;
  }

  // [data-field-list]
  private static boolean data_body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_body_1")) return false;
    data_field_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (data-field-statement | IDENT) [data-field-list]
  public static boolean data_field_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_field_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, DATA_FIELD_LIST, "<data field list>");
    r = data_field_list_0(b, l + 1);
    r = r && data_field_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // data-field-statement | IDENT
  private static boolean data_field_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_field_list_0")) return false;
    boolean r;
    r = data_field_statement(b, l + 1);
    if (!r) r = consumeToken(b, IDENT);
    return r;
  }

  // [data-field-list]
  private static boolean data_field_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_field_list_1")) return false;
    data_field_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COMMENT|([annotation-group] type-statement ident-name ASSIGN id)
  public static boolean data_field_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_field_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA_FIELD_STATEMENT, "<data field statement>");
    r = consumeToken(b, COMMENT);
    if (!r) r = data_field_statement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group] type-statement ident-name ASSIGN id
  private static boolean data_field_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_field_statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = data_field_statement_1_0(b, l + 1);
    r = r && type_statement(b, l + 1);
    r = r && ident_name(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    r = r && id(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [annotation-group]
  private static boolean data_field_statement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_field_statement_1_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COMMENT|([annotation-group] DATA ident-name [COLON extends] data-body)
  public static boolean data_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA_STATEMENT, "<data statement>");
    r = consumeToken(b, COMMENT);
    if (!r) r = data_statement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group] DATA ident-name [COLON extends] data-body
  private static boolean data_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = data_statement_1_0(b, l + 1);
    r = r && consumeToken(b, DATA);
    r = r && ident_name(b, l + 1);
    r = r && data_statement_1_3(b, l + 1);
    r = r && data_body(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [annotation-group]
  private static boolean data_statement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_statement_1_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  // [COLON extends]
  private static boolean data_statement_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_statement_1_3")) return false;
    data_statement_1_3_0(b, l + 1);
    return true;
  }

  // COLON extends
  private static boolean data_statement_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_statement_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && extends_$(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE [enum-field-list] RBRACE
  public static boolean enum_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_body")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && enum_body_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, ENUM_BODY, r);
    return r;
  }

  // [enum-field-list]
  private static boolean enum_body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_body_1")) return false;
    enum_field_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // enum-field-statement [enum-field-list]
  public static boolean enum_field_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_field_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENUM_FIELD_LIST, "<enum field list>");
    r = enum_field_statement(b, l + 1);
    r = r && enum_field_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [enum-field-list]
  private static boolean enum_field_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_field_list_1")) return false;
    enum_field_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COMMENT|([annotation-group] ident-name ASSIGN id)
  public static boolean enum_field_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_field_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENUM_FIELD_STATEMENT, "<enum field statement>");
    r = consumeToken(b, COMMENT);
    if (!r) r = enum_field_statement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group] ident-name ASSIGN id
  private static boolean enum_field_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_field_statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = enum_field_statement_1_0(b, l + 1);
    r = r && ident_name(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    r = r && id(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [annotation-group]
  private static boolean enum_field_statement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_field_statement_1_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COMMENT|([annotation-group] ENUM ident-name enum-body)
  public static boolean enum_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENUM_STATEMENT, "<enum statement>");
    r = consumeToken(b, COMMENT);
    if (!r) r = enum_statement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group] ENUM ident-name enum-body
  private static boolean enum_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = enum_statement_1_0(b, l + 1);
    r = r && consumeToken(b, ENUM);
    r = r && ident_name(b, l + 1);
    r = r && enum_body(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [annotation-group]
  private static boolean enum_statement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_statement_1_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ident-name ASSIGN id [COMMA extends ]
  public static boolean extends_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "extends_$")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ident_name(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    r = r && id(b, l + 1);
    r = r && extends_3(b, l + 1);
    exit_section_(b, m, EXTENDS, r);
    return r;
  }

  // [COMMA extends ]
  private static boolean extends_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "extends_3")) return false;
    extends_3_0(b, l + 1);
    return true;
  }

  // COMMA extends
  private static boolean extends_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "extends_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && extends_$(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (func-statement | IDENT) [func-list]
  public static boolean func_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, FUNC_LIST, "<func list>");
    r = func_list_0(b, l + 1);
    r = r && func_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // func-statement | IDENT
  private static boolean func_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_list_0")) return false;
    boolean r;
    r = func_statement(b, l + 1);
    if (!r) r = consumeToken(b, IDENT);
    return r;
  }

  // [func-list]
  private static boolean func_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_list_1")) return false;
    func_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (func-type) ident-name
  public static boolean func_param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_param")) return false;
    if (!nextTokenIs(b, "<func param>", IDENT, VOID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNC_PARAM, "<func param>");
    r = func_param_0(b, l + 1);
    r = r && ident_name(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (func-type)
  private static boolean func_param_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_param_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = func_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT|([annotation-group] func-type ident-name LPAREN (func-param|IDENT) RPAREN ASSIGN id)
  public static boolean func_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNC_STATEMENT, "<func statement>");
    r = consumeToken(b, COMMENT);
    if (!r) r = func_statement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group] func-type ident-name LPAREN (func-param|IDENT) RPAREN ASSIGN id
  private static boolean func_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = func_statement_1_0(b, l + 1);
    r = r && func_type(b, l + 1);
    r = r && ident_name(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && func_statement_1_4(b, l + 1);
    r = r && consumeTokens(b, 0, RPAREN, ASSIGN);
    r = r && id(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [annotation-group]
  private static boolean func_statement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_statement_1_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  // func-param|IDENT
  private static boolean func_statement_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_statement_1_4")) return false;
    boolean r;
    r = func_param(b, l + 1);
    if (!r) r = consumeToken(b, IDENT);
    return r;
  }

  /* ********************************************************** */
  // ident-name | void
  public static boolean func_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_type")) return false;
    if (!nextTokenIs(b, "<func type>", IDENT, VOID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNC_TYPE, "<func type>");
    r = ident_name(b, l + 1);
    if (!r) r = consumeToken(b, VOID);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // item_*
  static boolean hbufFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hbufFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "hbufFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // NUMBER
  public static boolean id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "id")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    exit_section_(b, m, ID, r);
    return r;
  }

  /* ********************************************************** */
  // IDENT
  public static boolean ident_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ident_name")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENT);
    exit_section_(b, m, IDENT_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT|(IMPORT STRING)
  public static boolean import_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_statement")) return false;
    if (!nextTokenIs(b, "<import statement>", COMMENT, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_STATEMENT, "<import statement>");
    r = consumeToken(b, COMMENT);
    if (!r) r = import_statement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // IMPORT STRING
  private static boolean import_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IMPORT, STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // package-statement|import-statement|data-statement|server-statement|enum-statement|IDENT|CRLF
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = package_statement(b, l + 1);
    if (!r) r = import_statement(b, l + 1);
    if (!r) r = data_statement(b, l + 1);
    if (!r) r = server_statement(b, l + 1);
    if (!r) r = enum_statement(b, l + 1);
    if (!r) r = consumeToken(b, IDENT);
    if (!r) r = consumeToken(b, CRLF);
    return r;
  }

  /* ********************************************************** */
  // COMMENT|(PACKAGE IDENT ASSIGN STRING)
  public static boolean package_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "package_statement")) return false;
    if (!nextTokenIs(b, "<package statement>", COMMENT, PACKAGE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PACKAGE_STATEMENT, "<package statement>");
    r = consumeToken(b, COMMENT);
    if (!r) r = package_statement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PACKAGE IDENT ASSIGN STRING
  private static boolean package_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "package_statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PACKAGE, IDENT, ASSIGN, STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE [func-list] RBRACE
  public static boolean server_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_body")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && server_body_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, SERVER_BODY, r);
    return r;
  }

  // [func-list]
  private static boolean server_body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_body_1")) return false;
    func_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COMMENT|([annotation-group] SERVER ident-name [COLON extends]  server-body)
  public static boolean server_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SERVER_STATEMENT, "<server statement>");
    r = consumeToken(b, COMMENT);
    if (!r) r = server_statement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group] SERVER ident-name [COLON extends]  server-body
  private static boolean server_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = server_statement_1_0(b, l + 1);
    r = r && consumeToken(b, SERVER);
    r = r && ident_name(b, l + 1);
    r = r && server_statement_1_3(b, l + 1);
    r = r && server_body(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [annotation-group]
  private static boolean server_statement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_statement_1_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  // [COLON extends]
  private static boolean server_statement_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_statement_1_3")) return false;
    server_statement_1_3_0(b, l + 1);
    return true;
  }

  // COLON extends
  private static boolean server_statement_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_statement_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && extends_$(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // type-base LBRACK RBRACK [QUESTION]
  public static boolean type_array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_array")) return false;
    if (!nextTokenIs(b, "<type array>", IDENT, TYPES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_ARRAY, "<type array>");
    r = type_base(b, l + 1);
    r = r && consumeTokens(b, 0, LBRACK, RBRACK);
    r = r && type_array_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [QUESTION]
  private static boolean type_array_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_array_3")) return false;
    consumeToken(b, QUESTION);
    return true;
  }

  /* ********************************************************** */
  // (TYPES|ident-name)[QUESTION]
  public static boolean type_base(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_base")) return false;
    if (!nextTokenIs(b, "<type base>", IDENT, TYPES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_BASE, "<type base>");
    r = type_base_0(b, l + 1);
    r = r && type_base_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // TYPES|ident-name
  private static boolean type_base_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_base_0")) return false;
    boolean r;
    r = consumeToken(b, TYPES);
    if (!r) r = ident_name(b, l + 1);
    return r;
  }

  // [QUESTION]
  private static boolean type_base_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_base_1")) return false;
    consumeToken(b, QUESTION);
    return true;
  }

  /* ********************************************************** */
  // type-base LSS TYPES GTR [QUESTION]
  public static boolean type_map(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_map")) return false;
    if (!nextTokenIs(b, "<type map>", IDENT, TYPES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_MAP, "<type map>");
    r = type_base(b, l + 1);
    r = r && consumeTokens(b, 0, LSS, TYPES, GTR);
    r = r && type_map_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [QUESTION]
  private static boolean type_map_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_map_4")) return false;
    consumeToken(b, QUESTION);
    return true;
  }

  /* ********************************************************** */
  // type-array|type-map|type-base
  public static boolean type_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_statement")) return false;
    if (!nextTokenIs(b, "<type statement>", IDENT, TYPES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_STATEMENT, "<type statement>");
    r = type_array(b, l + 1);
    if (!r) r = type_map(b, l + 1);
    if (!r) r = type_base(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}

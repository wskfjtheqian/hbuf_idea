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
  // LBRACK ID COLON [annotation-list] RBRACK{
  // }
  public static boolean annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACK, ID, COLON);
    r = r && annotation_3(b, l + 1);
    r = r && consumeToken(b, RBRACK);
    r = r && annotation_5(b, l + 1);
    exit_section_(b, m, ANNOTATION, r);
    return r;
  }

  // [annotation-list]
  private static boolean annotation_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_3")) return false;
    annotation_list(b, l + 1);
    return true;
  }

  // {
  // }
  private static boolean annotation_5(PsiBuilder b, int l) {
    return true;
  }

  /* ********************************************************** */
  // annotation [annotation-group]
  public static boolean annotation_group(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_group")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = annotation(b, l + 1);
    r = r && annotation_group_1(b, l + 1);
    exit_section_(b, m, ANNOTATION_GROUP, r);
    return r;
  }

  // [annotation-group]
  private static boolean annotation_group_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_group_1")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ID ASSIGN STRING {
  // }
  public static boolean annotation_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_item")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ID, ASSIGN, STRING);
    r = r && annotation_item_3(b, l + 1);
    exit_section_(b, m, ANNOTATION_ITEM, r);
    return r;
  }

  // {
  // }
  private static boolean annotation_item_3(PsiBuilder b, int l) {
    return true;
  }

  /* ********************************************************** */
  // annotation-item [SEMICOLON annotation-list]  {
  // }
  public static boolean annotation_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_list")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = annotation_item(b, l + 1);
    r = r && annotation_list_1(b, l + 1);
    r = r && annotation_list_2(b, l + 1);
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

  // {
  // }
  private static boolean annotation_list_2(PsiBuilder b, int l) {
    return true;
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
  // data-field-statement [data-field-list]
  public static boolean data_field_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_field_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA_FIELD_LIST, "<data field list>");
    r = data_field_statement(b, l + 1);
    r = r && data_field_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [data-field-list]
  private static boolean data_field_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_field_list_1")) return false;
    data_field_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // [annotation-group] type-statement ident-name ASSIGN NUMBER
  public static boolean data_field_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_field_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA_FIELD_STATEMENT, "<data field statement>");
    r = data_field_statement_0(b, l + 1);
    r = r && type_statement(b, l + 1);
    r = r && ident_name(b, l + 1);
    r = r && consumeTokens(b, 0, ASSIGN, NUMBER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group]
  private static boolean data_field_statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_field_statement_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // [annotation-group] DATA ident-name [COLON extends] ASSIGN NUMBER data-body
  public static boolean data_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_statement")) return false;
    if (!nextTokenIs(b, "<data statement>", DATA, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA_STATEMENT, "<data statement>");
    r = data_statement_0(b, l + 1);
    r = r && consumeToken(b, DATA);
    r = r && ident_name(b, l + 1);
    r = r && data_statement_3(b, l + 1);
    r = r && consumeTokens(b, 0, ASSIGN, NUMBER);
    r = r && data_body(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group]
  private static boolean data_statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_statement_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  // [COLON extends]
  private static boolean data_statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_statement_3")) return false;
    data_statement_3_0(b, l + 1);
    return true;
  }

  // COLON extends
  private static boolean data_statement_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_statement_3_0")) return false;
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
    if (!nextTokenIs(b, "<enum field list>", ID, LBRACK)) return false;
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
  // [annotation-group] ident-name ASSIGN NUMBER
  public static boolean enum_field_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_field_statement")) return false;
    if (!nextTokenIs(b, "<enum field statement>", ID, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENUM_FIELD_STATEMENT, "<enum field statement>");
    r = enum_field_statement_0(b, l + 1);
    r = r && ident_name(b, l + 1);
    r = r && consumeTokens(b, 0, ASSIGN, NUMBER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group]
  private static boolean enum_field_statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_field_statement_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // [annotation-group] ENUM ident-name enum-body
  public static boolean enum_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_statement")) return false;
    if (!nextTokenIs(b, "<enum statement>", ENUM, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENUM_STATEMENT, "<enum statement>");
    r = enum_statement_0(b, l + 1);
    r = r && consumeToken(b, ENUM);
    r = r && ident_name(b, l + 1);
    r = r && enum_body(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group]
  private static boolean enum_statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_statement_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ident-name [COMMA extends]
  public static boolean extends_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "extends_$")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ident_name(b, l + 1);
    r = r && extends_1(b, l + 1);
    exit_section_(b, m, EXTENDS, r);
    return r;
  }

  // [COMMA extends]
  private static boolean extends_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "extends_1")) return false;
    extends_1_0(b, l + 1);
    return true;
  }

  // COMMA extends
  private static boolean extends_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "extends_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && extends_$(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // func-statement [func-list]
  public static boolean func_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_list")) return false;
    if (!nextTokenIs(b, "<func list>", ID, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNC_LIST, "<func list>");
    r = func_statement(b, l + 1);
    r = r && func_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [func-list]
  private static boolean func_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_list_1")) return false;
    func_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // func-type ID
  public static boolean func_param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_param")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = func_type(b, l + 1);
    r = r && consumeToken(b, ID);
    exit_section_(b, m, FUNC_PARAM, r);
    return r;
  }

  /* ********************************************************** */
  // [annotation-group] func-type ident-name LPAREN func-param RPAREN ASSIGN NUMBER
  public static boolean func_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_statement")) return false;
    if (!nextTokenIs(b, "<func statement>", ID, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNC_STATEMENT, "<func statement>");
    r = func_statement_0(b, l + 1);
    r = r && func_type(b, l + 1);
    r = r && ident_name(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && func_param(b, l + 1);
    r = r && consumeTokens(b, 0, RPAREN, ASSIGN, NUMBER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group]
  private static boolean func_statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_statement_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ID
  public static boolean func_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_type")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, FUNC_TYPE, r);
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
  // ID
  public static boolean ident_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ident_name")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, IDENT_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // IMPORT STRING
  public static boolean import_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_statement")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IMPORT, STRING);
    exit_section_(b, m, IMPORT_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // package-statement|import-statement|data-statement|server-statement|enum-statement|COMMENT|CRLF
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = package_statement(b, l + 1);
    if (!r) r = import_statement(b, l + 1);
    if (!r) r = data_statement(b, l + 1);
    if (!r) r = server_statement(b, l + 1);
    if (!r) r = enum_statement(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    return r;
  }

  /* ********************************************************** */
  // PACKAGE ID ASSIGN STRING
  public static boolean package_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "package_statement")) return false;
    if (!nextTokenIs(b, PACKAGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PACKAGE, ID, ASSIGN, STRING);
    exit_section_(b, m, PACKAGE_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE [func-list] RBRACE {
  // }
  public static boolean server_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_body")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && server_body_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    r = r && server_body_3(b, l + 1);
    exit_section_(b, m, SERVER_BODY, r);
    return r;
  }

  // [func-list]
  private static boolean server_body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_body_1")) return false;
    func_list(b, l + 1);
    return true;
  }

  // {
  // }
  private static boolean server_body_3(PsiBuilder b, int l) {
    return true;
  }

  /* ********************************************************** */
  // [annotation-group] SERVER ident-name [COLON extends] ASSIGN NUMBER server-body
  public static boolean server_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_statement")) return false;
    if (!nextTokenIs(b, "<server statement>", LBRACK, SERVER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SERVER_STATEMENT, "<server statement>");
    r = server_statement_0(b, l + 1);
    r = r && consumeToken(b, SERVER);
    r = r && ident_name(b, l + 1);
    r = r && server_statement_3(b, l + 1);
    r = r && consumeTokens(b, 0, ASSIGN, NUMBER);
    r = r && server_body(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annotation-group]
  private static boolean server_statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_statement_0")) return false;
    annotation_group(b, l + 1);
    return true;
  }

  // [COLON extends]
  private static boolean server_statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_statement_3")) return false;
    server_statement_3_0(b, l + 1);
    return true;
  }

  // COLON extends
  private static boolean server_statement_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "server_statement_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && extends_$(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // type-base [QUESTION] LBRACK RBRACK
  public static boolean type_array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_array")) return false;
    if (!nextTokenIs(b, "<type array>", ID, TYPES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_ARRAY, "<type array>");
    r = type_base(b, l + 1);
    r = r && type_array_1(b, l + 1);
    r = r && consumeTokens(b, 0, LBRACK, RBRACK);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [QUESTION]
  private static boolean type_array_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_array_1")) return false;
    consumeToken(b, QUESTION);
    return true;
  }

  /* ********************************************************** */
  // TYPES|ID
  public static boolean type_base(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_base")) return false;
    if (!nextTokenIs(b, "<type base>", ID, TYPES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_BASE, "<type base>");
    r = consumeToken(b, TYPES);
    if (!r) r = consumeToken(b, ID);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // type-base [QUESTION] LSS TYPES GTR
  public static boolean type_map(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_map")) return false;
    if (!nextTokenIs(b, "<type map>", ID, TYPES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_MAP, "<type map>");
    r = type_base(b, l + 1);
    r = r && type_map_1(b, l + 1);
    r = r && consumeTokens(b, 0, LSS, TYPES, GTR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [QUESTION]
  private static boolean type_map_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_map_1")) return false;
    consumeToken(b, QUESTION);
    return true;
  }

  /* ********************************************************** */
  // type-array|type-map|type-base [QUESTION]
  public static boolean type_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_statement")) return false;
    if (!nextTokenIs(b, "<type statement>", ID, TYPES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_STATEMENT, "<type statement>");
    r = type_array(b, l + 1);
    if (!r) r = type_map(b, l + 1);
    if (!r) r = type_statement_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // type-base [QUESTION]
  private static boolean type_statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_statement_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = type_base(b, l + 1);
    r = r && type_statement_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [QUESTION]
  private static boolean type_statement_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_statement_2_1")) return false;
    consumeToken(b, QUESTION);
    return true;
  }

}

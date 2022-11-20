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
  // string
  public static boolean import_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_$")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, IMPORT, r);
    return r;
  }

  /* ********************************************************** */
  // package|import|data|server|CRLF
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = package_$(b, l + 1);
    if (!r) r = import_$(b, l + 1);
    if (!r) r = consumeToken(b, DATA);
    if (!r) r = consumeToken(b, SERVER);
    if (!r) r = consumeToken(b, CRLF);
    return r;
  }

  /* ********************************************************** */
  // id + EQ + string
  public static boolean package_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "package_$")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = package_0(b, l + 1);
    r = r && package_1(b, l + 1);
    r = r && consumeToken(b, STRING);
    exit_section_(b, m, PACKAGE, r);
    return r;
  }

  // id +
  private static boolean package_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "package_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "package_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // EQ +
  private static boolean package_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "package_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQ);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, EQ)) break;
      if (!empty_element_parsed_guard_(b, "package_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

}

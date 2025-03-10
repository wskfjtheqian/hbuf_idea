// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.annotate.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.hbuf.idea.annotate.psi.AnnotateTypes.*;
import static com.hbuf.idea.annotate.parser.AnnotateParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class AnnotateParser implements PsiParser, LightPsiParser {

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
    return annotateFile(b, l + 1);
  }

  /* ********************************************************** */
  // item*
  static boolean annotateFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotateFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annotateFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IDENT|(POINT IDENT)
  public static boolean item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item")) return false;
    if (!nextTokenIs(b, "<item>", IDENT, POINT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ITEM, "<item>");
    r = consumeToken(b, IDENT);
    if (!r) r = item_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // POINT IDENT
  private static boolean item_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, POINT, IDENT);
    exit_section_(b, m, null, r);
    return r;
  }

}

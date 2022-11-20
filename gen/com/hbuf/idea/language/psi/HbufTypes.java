// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.hbuf.idea.language.psi.impl.*;

public interface HbufTypes {

  IElementType IMPORT = new HbufElementType("IMPORT");
  IElementType PACKAGE = new HbufElementType("PACKAGE");

  IElementType COMMENT = new HbufTokenType("comment");
  IElementType CRLF = new HbufTokenType("CRLF");
  IElementType DATA = new HbufTokenType("data");
  IElementType EQ = new HbufTokenType("=");
  IElementType ID = new HbufTokenType("id");
  IElementType LP = new HbufTokenType("(");
  IElementType NUMBER = new HbufTokenType("number");
  IElementType RP = new HbufTokenType(")");
  IElementType SEMI = new HbufTokenType(";");
  IElementType SERVER = new HbufTokenType("server");
  IElementType STRING = new HbufTokenType("string");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == IMPORT) {
        return new HbufImportImpl(node);
      }
      else if (type == PACKAGE) {
        return new HbufPackageImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}

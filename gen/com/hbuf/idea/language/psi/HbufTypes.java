// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.hbuf.idea.language.psi.impl.*;

public interface HbufTypes {

  IElementType PROPERTY = new HbufElementType("PROPERTY");

  IElementType COMMENT = new HbufTokenType("COMMENT");
  IElementType CRLF = new HbufTokenType("CRLF");
  IElementType KEY = new HbufTokenType("KEY");
  IElementType SEPARATOR = new HbufTokenType("SEPARATOR");
  IElementType VALUE = new HbufTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new HbufPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}

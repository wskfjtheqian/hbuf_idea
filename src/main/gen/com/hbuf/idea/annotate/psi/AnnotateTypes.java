// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.annotate.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.hbuf.idea.annotate.AnnotateLanguage.AnnotateElementType;
import com.hbuf.idea.annotate.AnnotateLanguage.AnnotateTokenType;
import com.hbuf.idea.annotate.psi.impl.*;

public interface AnnotateTypes {

  IElementType ITEM = new AnnotateElementType("ITEM");

  IElementType COMMENT = new AnnotateTokenType("COMMENT");
  IElementType IDENT = new AnnotateTokenType("IDENT");
  IElementType POINT = new AnnotateTokenType(".");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ITEM) {
        return new AnnotateItemImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}

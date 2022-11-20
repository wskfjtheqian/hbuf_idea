// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class HbufVisitor extends PsiElementVisitor {

  public void visitProperty(@NotNull HbufProperty o) {
    visitNamedElement(o);
  }

  public void visitNamedElement(@NotNull HbufNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}

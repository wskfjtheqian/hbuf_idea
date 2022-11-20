// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class HbufVisitor extends PsiElementVisitor {

  public void visitImport(@NotNull HbufImport o) {
    visitImportElement(o);
  }

  public void visitPackage(@NotNull HbufPackage o) {
    visitPackageElement(o);
  }

  public void visitImportElement(@NotNull HbufImportElement o) {
    visitPsiElement(o);
  }

  public void visitPackageElement(@NotNull HbufPackageElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}

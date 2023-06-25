// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.hbuf.idea.language.psi.HbufTypes.*;
import com.hbuf.idea.language.psi.*;

public class HbufPackageStatementImpl extends HbufPackageElementImpl implements HbufPackageStatement {

  public HbufPackageStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HbufVisitor visitor) {
    visitor.visitPackageStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HbufVisitor) accept((HbufVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  
  public PsiElement getComment() {
    return findChildByType(COMMENT);
  }

  @Override
  
  public PsiElement getIdent() {
    return findChildByType(IDENT);
  }

  @Override
  
  public PsiElement getString() {
    return findChildByType(STRING);
  }

}

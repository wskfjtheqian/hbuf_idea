// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.annotate.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.hbuf.idea.annotate.psi.AnnotateTypes.*;
import com.hbuf.idea.annotate.psi.*;

public class AnnotateItemImpl extends AnnotateItemElementImpl implements AnnotateItem {

  public AnnotateItemImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AnnotateVisitor visitor) {
    visitor.visitItem(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AnnotateVisitor) accept((AnnotateVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getIdent() {
    return findNotNullChildByType(IDENT);
  }

}

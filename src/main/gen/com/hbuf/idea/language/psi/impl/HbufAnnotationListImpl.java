// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.hbuf.idea.language.psi.HbufTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.hbuf.idea.language.psi.*;

public class HbufAnnotationListImpl extends ASTWrapperPsiElement implements HbufAnnotationList {

  public HbufAnnotationListImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HbufVisitor visitor) {
    visitor.visitAnnotationList(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HbufVisitor) accept((HbufVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public HbufAnnotationItem getAnnotationItem() {
    return findNotNullChildByClass(HbufAnnotationItem.class);
  }

  @Override
  @Nullable
  public HbufAnnotationList getAnnotationList() {
    return findChildByClass(HbufAnnotationList.class);
  }

}

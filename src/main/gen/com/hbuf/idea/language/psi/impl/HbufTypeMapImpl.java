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

public class HbufTypeMapImpl extends HbufTypeMapElementImpl implements HbufTypeMap {

  public HbufTypeMapImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HbufVisitor visitor) {
    visitor.visitTypeMap(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HbufVisitor) accept((HbufVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public HbufTypeBase getTypeBase() {
    return findNotNullChildByClass(HbufTypeBase.class);
  }

  @Override
  @NotNull
  public PsiElement getTypes() {
    return findNotNullChildByType(TYPES);
  }

}

// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface HbufFuncStatement extends HbufServerFuncElement {

  @Nullable
  HbufAnnotationGroup getAnnotationGroup();

  @Nullable
  HbufFuncParam getFuncParam();

  @Nullable
  HbufFuncType getFuncType();

  @Nullable
  HbufId getId();

  @Nullable
  HbufIdentName getIdentName();

  @Nullable
  PsiElement getComment();

  @Nullable
  PsiElement getIdent();

}

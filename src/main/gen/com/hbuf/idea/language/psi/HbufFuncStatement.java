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

  @NotNull
  HbufFuncType getFuncType();

  @NotNull
  HbufId getId();

  @NotNull
  HbufIdentName getIdentName();

  @Nullable
  PsiElement getIdent();

}

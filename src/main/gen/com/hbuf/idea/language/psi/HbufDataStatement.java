// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface HbufDataStatement extends HbufDataElement {

  @Nullable
  HbufAnnotationGroup getAnnotationGroup();

  @NotNull
  HbufDataBody getDataBody();

  @Nullable
  HbufExtends getExtends();

  @NotNull
  HbufIdentName getIdentName();

  @NotNull
  PsiElement getNumber();

}

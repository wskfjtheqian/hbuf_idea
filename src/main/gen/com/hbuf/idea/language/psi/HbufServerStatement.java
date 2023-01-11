// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface HbufServerStatement extends HbufServerElement {

  @Nullable
  HbufAnnotationGroup getAnnotationGroup();

  @Nullable
  HbufExtends getExtends();

  @NotNull
  HbufIdentName getIdentName();

  @NotNull
  HbufServerBody getServerBody();

  @NotNull
  PsiElement getNumber();

}

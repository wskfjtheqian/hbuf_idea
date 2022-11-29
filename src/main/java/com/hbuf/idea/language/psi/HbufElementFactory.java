// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi;

import com.hbuf.idea.language.HbufFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;


public class HbufElementFactory {

  public static HbufProperty createProperty(Project project, String name) {
    final HbufFile file = createFile(project, name);
    return (HbufProperty) file.getFirstChild();
  }

  public static HbufFile createFile(Project project, String text) {
    String name = "dummy.hbuf";
    return (HbufFile) PsiFileFactory.getInstance(project).createFileFromText(name, HbufFileType.INSTANCE, text);
  }

  public static HbufProperty createProperty(Project project, String name, String value) {
    final HbufFile file = createFile(project, name + " = " + value);
    return (HbufProperty) file.getFirstChild();
  }

  public static PsiElement createCRLF(Project project) {
    final HbufFile file = createFile(project, "\n");
    return file.getFirstChild();
  }

}

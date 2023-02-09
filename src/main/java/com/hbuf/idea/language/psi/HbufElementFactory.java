// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi;

import com.hbuf.idea.language.HbufFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HbufElementFactory {

    public static HbufFile createFile(Project project, String text) {
        String name = "dummy.hbuf";
        return (HbufFile) PsiFileFactory.getInstance(project).createFileFromText(name, HbufFileType.INSTANCE, text);
    }

    public static PsiElement createCRLF(Project project) {
        final HbufFile file = createFile(project, "\n");
        return file.getFirstChild();
    }

    public static HbufImportElement createImport(Project project, String path) {
        final HbufFile file = createFile(project, "import \"" + path + "\"");
        return (HbufImportElement) file.getFirstChild();
    }

    public static HbufIdElement createId(Project project, int id) {
        final HbufFile file = createFile(project, "data D=" + id + "{}");
        @NotNull List<HbufDataElement> elements = new ArrayList<>(PsiTreeUtil.findChildrenOfAnyType(file, HbufDataElement.class));
        return elements.get(0).getId();
    }

    public static HbufDataElement createData(Project project, String name, int id) {
        final HbufFile file = createFile(project, "data " + name + " = " + id + " {\n}");
        return (HbufDataElement) file.getFirstChild();
    }
}

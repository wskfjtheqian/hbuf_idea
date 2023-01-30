// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi;

import com.hbuf.idea.language.HbufFileType;
import com.hbuf.idea.language.HbufLanguage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HbufUtil {
    public static HbufNameElement createNameElement(Project project, String name) {
        PsiFile file = PsiFileFactory.getInstance(project).createFileFromText("fileName", HbufLanguage.INSTANCE, "enum " + name + "{}");
        if (file instanceof HbufFile) {
            PsiElement first = file.getFirstChild();
            if (first instanceof HbufEnumElement) {
                return ((HbufEnumElement) first).getIdentName();
            }
        }
        return null;
    }

    public static List<HbufDataElement> findData(Project project, String name) {
        List<HbufDataElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<HbufDataElement> dataElements = PsiTreeUtil.findChildrenOfAnyType(hbufFile, HbufDataElement.class);
                for (HbufDataElement element : dataElements) {
                    if (element.getName().equals(name)) {
                        result.add(element);
                    }
                }
            }
        }
        return result;
    }

    public static List<HbufDataElement> findData(Project project) {
        List<HbufDataElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<HbufDataElement> dataElements = PsiTreeUtil.findChildrenOfAnyType(hbufFile, HbufDataElement.class);
                result.addAll(dataElements);
            }
        }
        return result;
    }

    public static List<HbufEnumElement> findEnum(Project project, String name) {
        List<HbufEnumElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<HbufEnumElement> enumElements = PsiTreeUtil.findChildrenOfAnyType(hbufFile, HbufEnumElement.class);
                for (HbufEnumElement element : enumElements) {
                    if (element.getName().equals(name)) {
                        result.add(element);
                    }
                }
            }
        }
        return result;
    }

    public static List<HbufEnumElement> findEnum(Project project) {
        List<HbufEnumElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<HbufEnumElement> enumElements = PsiTreeUtil.findChildrenOfAnyType(hbufFile, HbufEnumElement.class);
                result.addAll(enumElements);
            }
        }
        return result;
    }

    public static List<HbufServerElement> findServer(Project project, String name) {
        List<HbufServerElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<HbufServerElement> serverElements = PsiTreeUtil.findChildrenOfAnyType(hbufFile, HbufServerElement.class);
                for (HbufServerElement element : serverElements) {
                    if (element.getName().equals(name)) {
                        result.add(element);
                    }
                }
            }
        }
        return result;
    }

    public static List<HbufServerElement> findServer(Project project) {
        List<HbufServerElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<HbufServerElement> serverElements = PsiTreeUtil.findChildrenOfAnyType(hbufFile, HbufServerElement.class);
                result.addAll(serverElements);
            }
        }
        return result;
    }


}
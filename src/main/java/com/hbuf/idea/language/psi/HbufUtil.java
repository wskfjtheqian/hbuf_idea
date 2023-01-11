// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi;

import com.hbuf.idea.language.HbufFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class HbufUtil {

    public static List<PsiNameIdentifierOwner> findProperties(Project project, String key) {
        List<PsiNameIdentifierOwner> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<PsiNameIdentifierOwner> properties = PsiTreeUtil.findChildrenOfAnyType(
                        hbufFile,
                        HbufDataElement.class,
                        HbufEnumElement.class
                );
                for (PsiNameIdentifierOwner item : properties) {
                    if (Objects.equals(item.getName(), key)) {
                        result.add(item);
                    }
                }
            }
        }
        return result;
    }

    public static boolean isData(Project project, String key) {
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<PsiNameIdentifierOwner> properties = PsiTreeUtil.findChildrenOfAnyType(
                        hbufFile,
                        HbufDataElement.class
                );
                for (PsiNameIdentifierOwner item : properties) {
                    if (Objects.equals(item.getName(), key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isEnum(Project project, String key) {
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<PsiNameIdentifierOwner> properties = PsiTreeUtil.findChildrenOfAnyType(
                        hbufFile,
                        HbufEnumElement.class
                );
                for (PsiNameIdentifierOwner item : properties) {
                    if (Objects.equals(item.getName(), key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isServer(Project project, String key) {
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<PsiNameIdentifierOwner> properties = PsiTreeUtil.findChildrenOfAnyType(
                        hbufFile,
                        HbufServerElement.class
                );
                for (PsiNameIdentifierOwner item : properties) {
                    if (Objects.equals(item.getName(), key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static List<PsiNameIdentifierOwner> findProperties(Project project) {
        List<PsiNameIdentifierOwner> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<PsiNameIdentifierOwner> properties = PsiTreeUtil.findChildrenOfAnyType(
                        hbufFile,
                        HbufDataElement.class,
                        HbufEnumElement.class
                );
                result.addAll(properties);
            }
        }
        return result;
    }

    public static  HbufEnumElement getEnumByChild(PsiElement element){
        while (null != element && !(element instanceof HbufEnumElement)){
            element = element.getParent();
        }
        return (HbufEnumElement) element;
    }

    public static  HbufDataElement getDataByChild(PsiElement element){
        while (null != element && !(element instanceof HbufDataElement)){
            element = element.getParent();
        }
        return (HbufDataElement) element;
    }


    public static  HbufServerElement getServerByChild(PsiElement element){
        while (null != element && !(element instanceof HbufServerElement)){
            element = element.getParent();
        }
        return (HbufServerElement) element;
    }
}

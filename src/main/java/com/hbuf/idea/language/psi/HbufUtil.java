// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi;

import com.hbuf.idea.language.HbufFileType;
import com.hbuf.idea.language.HbufLanguage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.Element;
import java.util.*;

public class HbufUtil {
    public static Set<String> DataBaseType = Set.of(
            "int8",
            "int16",
            "int32",
            "int64",
            "uint8",
            "uint16",
            "uint32",
            "uint64",
            "bool",
            "float",
            "double",
            "decimal",
            "string",
            "date"
    );


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

    public static <T extends PsiElement> Collection<T> findChildrenOfAnyType(Project project, Class<? extends T>... classes) {
        List<T> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<T> properties = PsiTreeUtil.findChildrenOfAnyType(hbufFile, classes);
                result.addAll(properties);
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
                @NotNull Collection<HbufDataElement> properties = PsiTreeUtil.findChildrenOfAnyType(
                        hbufFile,
                        HbufDataElement.class
                );
                for (HbufDataElement item : properties) {
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
                @NotNull Collection<HbufEnumElement> properties = PsiTreeUtil.findChildrenOfAnyType(
                        hbufFile,
                        HbufEnumElement.class
                );
                for (HbufEnumElement item : properties) {
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
                @NotNull Collection<HbufServerElement> properties = PsiTreeUtil.findChildrenOfAnyType(
                        hbufFile,
                        HbufServerElement.class
                );
                for (HbufServerElement item : properties) {
                    if (Objects.equals(item.getName(), key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static HbufEnumElement getEnumByChild(PsiElement element) {
        while (null != element && !(element instanceof HbufEnumElement)) {
            element = element.getParent();
        }
        return (HbufEnumElement) element;
    }

    public static HbufAnnotationElement getAnnotationByChild(PsiElement element) {
        while (null != element && !(element instanceof HbufAnnotationElement)) {
            element = element.getParent();
        }
        return (HbufAnnotationElement) element;
    }

    public static HbufDataElement getDataByChild(PsiElement element) {
        while (null != element && !(element instanceof HbufDataElement)) {
            element = element.getParent();
        }
        return (HbufDataElement) element;
    }


    public static HbufServerElement getServerByChild(PsiElement element) {
        while (null != element && !(element instanceof HbufServerElement)) {
            element = element.getParent();
        }
        return (HbufServerElement) element;
    }

    public static Collection<HbufDataElement> getDataSub(HbufDataElement element) {
        List<HbufDataElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(element.getProject()));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(element.getProject()).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<HbufDataElement> properties = PsiTreeUtil.findChildrenOfAnyType(hbufFile, HbufDataElement.class);
                for (HbufDataElement data : properties) {
                    if (data == element) {
                        continue;
                    }
                    for (HbufExtendsElement item : data.getExtendList()) {
                        if (Objects.equals(item.getIdentName().getName(), element.getName())) {
                            result.add(data);
                            result.addAll(getDataSub(data));
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static Collection<HbufServerElement> getServerSub(HbufServerElement element) {
        List<HbufServerElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(element.getProject()));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(element.getProject()).findFile(virtualFile);
            if (hbufFile != null) {
                @NotNull Collection<HbufServerElement> properties = PsiTreeUtil.findChildrenOfAnyType(hbufFile, HbufServerElement.class);
                for (HbufServerElement data : properties) {
                    if (data == element) {
                        continue;
                    }
                    for (HbufExtendsElement item : data.getExtendList()) {
                        if (Objects.equals(item.getIdentName().getName(), element.getName())) {
                            result.add(data);
                            result.addAll(getServerSub(data));
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static String getString(String text) {
        if (text.isEmpty()) {
            return "";
        }
        int start = text.indexOf("\"");
        start = 0 != start ? 0 : 1;

        int end = text.lastIndexOf("\"");
        end = (end != text.length() - 1) ? text.length() : (text.length() - 1);

        return text.substring(start, end);
    }


    public static boolean isAtImportFile(PsiFile file, HbufDataElement dataElement) {
        if (file != null) {
            @NotNull Collection<HbufImportElement> elements = PsiTreeUtil.findChildrenOfAnyType(file, HbufImportElement.class);
            for (HbufImportElement element : elements) {
                @NlsSafe String path = HbufUtil.getString(element.getString().getText());
                VirtualFile f = element.getContainingFile().getVirtualFile().getParent().findFileByRelativePath(path);
                if (null != f && dataElement.getContainingFile().getVirtualFile().getPath().equals(f.getPath())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static PsiElement getImportLastNode(HbufFile hbufFile) {
        @NotNull List<HbufImportElement> importElements = new ArrayList<>(PsiTreeUtil.findChildrenOfAnyType(hbufFile, HbufImportElement.class));
        if (!importElements.isEmpty()) {
            return importElements.get(importElements.size() - 1);
        }

        @NotNull List<HbufPackageElement> packageElements = new ArrayList<>(PsiTreeUtil.findChildrenOfAnyType(hbufFile, HbufPackageElement.class));
        if (!packageElements.isEmpty()) {
            return packageElements.get(packageElements.size() - 1);
        }
        return hbufFile.getLastChild();
    }

    public static int getFuncNewId(Project project) {
        return 0;
    }

    public static PsiElement getExtendsRoot(PsiElement element) {
        while (element instanceof HbufExtendsElement) {
            element = element.getParent();
        }
        return element;
    }

    public static int getExtendsNewId(Project project, Collection<HbufExtendsElement> elements) {
        for (int i = 0; i < elements.size(); i++) {
            if (!checkExtendId(i, elements)) {
                return i;
            }
        }
        return elements.size();
    }

    private static boolean checkExtendId(int id, Collection<HbufExtendsElement> elements) {
        for (HbufExtendsElement item : elements) {
            if (item.getId().getId() == id) {
                return true;
            }
        }
        return false;
    }
}
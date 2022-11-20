// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language;

import com.google.common.collect.Lists;
import com.hbuf.idea.language.psi.HbufFile;
import com.hbuf.idea.language.psi.HbufPropertyElement;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jsoup.internal.StringUtil;

import java.util.*;

public class HbufUtil {

    /**
     * Searches the entire project for Hbuf language files with instances of the Hbuf property with the given key.
     *
     * @param project current project
     * @param key     to check
     * @return matching properties
     */
    public static List<HbufPropertyElement> findProperties(Project project, String key) {
        List<HbufPropertyElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                HbufPropertyElement[] properties = PsiTreeUtil.getChildrenOfType(hbufFile, HbufPropertyElement.class);
                if (properties != null) {
                    for (HbufPropertyElement property : properties) {
                        if (key.equals(property.getKey())) {
                            result.add(property);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static List<HbufPropertyElement> findProperties(Project project) {
        List<HbufPropertyElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(HbufFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            HbufFile hbufFile = (HbufFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hbufFile != null) {
                HbufPropertyElement[] properties = PsiTreeUtil.getChildrenOfType(hbufFile, HbufPropertyElement.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }

    /**
     * Attempts to collect any comment elements above the Hbuf key/value pair.
     */
    public static @NotNull
    String findDocumentationComment(HbufPropertyElement property) {
        List<String> result = new LinkedList<>();
        PsiElement element = property.getPrevSibling();
        while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
            if (element instanceof PsiComment) {
                String commentText = element.getText().replaceFirst("[!# ]+", "");
                result.add(commentText);
            }
            element = element.getPrevSibling();
        }
        return StringUtil.join(Lists.reverse(result), "\n ");
    }

}

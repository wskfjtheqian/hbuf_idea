// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.HbufIcons;
import com.hbuf.idea.language.psi.HbufElementFactory;
import com.hbuf.idea.language.psi.HbufProperty;
import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class HbufPsiImplUtil {

    public static String getKey(HbufProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(HbufTypes.KEY);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to hbuf spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(HbufProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(HbufTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(HbufProperty element) {
        return getKey(element);
    }

    public static PsiElement setName(HbufProperty element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(HbufTypes.KEY);
        if (keyNode != null) {
            HbufProperty property = HbufElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(HbufProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(HbufTypes.KEY);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    public static ItemPresentation getPresentation(final HbufProperty element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getKey();
            }

            @Nullable
            @Override
            public String getLocationString() {
                PsiFile containingFile = element.getContainingFile();
                return containingFile == null ? null : containingFile.getName();
            }

            @Override
            public Icon getIcon(boolean unused) {
                return HbufIcons.FILE;
            }
        };
    }

}

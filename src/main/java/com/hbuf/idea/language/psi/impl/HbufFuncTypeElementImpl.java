package com.hbuf.idea.language.psi.impl;

import com.hbuf.idea.language.HbufIcons;
import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufFuncTypeElement;
import com.hbuf.idea.language.psi.HbufNameElement;
import com.hbuf.idea.language.psi.HbufUtil;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public abstract class HbufFuncTypeElementImpl extends ASTWrapperPsiElement implements HbufFuncTypeElement {
    public HbufFuncTypeElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        if (null == getIdentName()) {
            return null;
        }
        return getIdentName().getName();
    }
}
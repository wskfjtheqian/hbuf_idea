package com.hbuf.idea.language.psi;

import com.hbuf.idea.language.psi.impl.HbufTableElementImpl;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public class HbufTypesFactory implements HbufTypes {
    public static final IElementType POINT = new HbufTokenType(".");
    public static final IElementType QUOTATION = new HbufTokenType("\"");

    public static PsiElement createElement(ASTNode node) {
        IElementType type = node.getElementType();
        if (type == STRING) {
            return new HbufTableElementImpl(node);
        }
        return Factory.createElement(node);
    }
}

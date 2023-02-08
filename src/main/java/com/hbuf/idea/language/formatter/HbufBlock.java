// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.hbuf.idea.language.formatter;

import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HbufBlock extends AbstractBlock {
    private final SpacingBuilder spacingBuilder;
    private final Indent mIndent;

    protected HbufBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
                        SpacingBuilder spacingBuilder, Indent indent) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
        this.mIndent = indent;
        if (node.getElementType() == HbufTypes.LBRACE) {
            return;
        }
        if (node.getElementType() == HbufTypes.RBRACE) {
            return;
        }

    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            @NotNull IElementType type = child.getElementType();
            if (type != TokenType.WHITE_SPACE) {
                Indent indent = Indent.getNoneIndent();

                if (type == HbufTypes.ENUM_FIELD_STATEMENT
                        || type == HbufTypes.DATA_FIELD_STATEMENT
                        || type == HbufTypes.FUNC_STATEMENT
                ) {
                    indent = Indent.getSpaceIndent(4);
                } else if (type == HbufTypes.COMMENT) {
                    @NotNull IElementType parentType = child.getTreeParent().getElementType();
                    if (parentType == HbufTypes.ENUM_BODY
                            || parentType == HbufTypes.ENUM_FIELD_LIST ||
                            parentType == HbufTypes.DATA_BODY
                            || parentType == HbufTypes.DATA_FIELD_LIST ||
                            parentType == HbufTypes.SERVER_BODY
                            || parentType == HbufTypes.FUNC_LIST
                    ) {
                        indent = Indent.getSpaceIndent(4);
                    }
                }

                Block block = new HbufBlock(
                        child,
                        Wrap.createWrap(WrapType.NONE, false),
                        null,
                        spacingBuilder,
                        indent
                );
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        return mIndent;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }

}

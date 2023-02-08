package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.*;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HbufFoldingBuilder extends FoldingBuilderEx implements DumbAware {

    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        List<FoldingDescriptor> descriptors = new ArrayList<>();

        Collection<PsiElement> elements = PsiTreeUtil.findChildrenOfAnyType(root,
                HbufAnnotationGroupElement.class,
                HbufDataBodyElement.class,
                HbufEnumBodyElement.class,
                HbufServerBodyElement.class
        );
        for (final PsiElement element : elements) {
            int start = element.getTextRange().getStartOffset() + 1;
            int end = element.getTextRange().getEndOffset() - 1;
            if (start < end) {
                FoldingGroup group = FoldingGroup.newGroup(elements.toString());
                descriptors.add(new FoldingDescriptor(
                        element.getNode(),
                        new TextRange(start, end),
                        group
                ));
            }
        }
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }


    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        String retTxt = "...";
        if (node.getElementType() == HbufTypes.ANNOTATION_GROUP) {
            return "override";
        }
        return retTxt;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }

}

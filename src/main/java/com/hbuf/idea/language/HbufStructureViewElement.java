package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.*;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class HbufStructureViewElement {
    HbufStructureFileElement create(HbufFile hbufFile) {
        return new HbufStructureFileElement(hbufFile);
    }

    class HbufStructureFileElement extends PsiTreeElementBase<HbufFile> {
        public HbufStructureFileElement(HbufFile element) {
            super(element);
        }

        @Override
        public @NotNull
        Collection<StructureViewTreeElement> getChildrenBase() {
            Collection<PsiElement> elements = PsiTreeUtil.findChildrenOfAnyType(getElement(),
                    HbufDataElement.class,
                    HbufEnumElement.class,
                    HbufServerElement.class
            );

            List<StructureViewTreeElement> treeElements = new ArrayList<>(elements.size());
            for (PsiElement element : elements) {
                if (element instanceof HbufEnumElement) {
                    treeElements.add(new HbufTreeEnumElement((HbufEnumElement) element));
                } else if (element instanceof HbufDataElement) {
                    treeElements.add(new HbufTreeDataElement((HbufDataElement) element));
                } else if (element instanceof HbufServerElement) {
                    treeElements.add(new HbufTreeServerElement((HbufServerElement) element));
                }

            }
            return treeElements;
        }

        @Override
        public @NlsSafe
        @Nullable
        String getPresentableText() {
            return getElement().getPresentation().getPresentableText();
        }

    }

    class HbufTreeEnumElement extends PsiTreeElementBase<HbufEnumElement> {
        protected HbufTreeEnumElement(HbufEnumElement psiElement) {
            super(psiElement);
        }

        @Override
        public @NotNull
        Collection<StructureViewTreeElement> getChildrenBase() {
            Collection<PsiElement> elements = PsiTreeUtil.findChildrenOfAnyType(getElement(),
                    HbufEnumFieldElement.class
            );

            List<StructureViewTreeElement> treeElements = new ArrayList<>(elements.size());
            for (PsiElement element : elements) {
                treeElements.add(new HbufTreeEnumFieldElement((HbufEnumFieldElement) element));
            }
            return treeElements;
        }

        @Override
        public @NlsSafe
        @Nullable
        String getPresentableText() {
            return getElement().getPresentation().getPresentableText();
        }

        @Override
        public Icon getIcon(boolean open) {
            return getElement().getPresentation().getIcon(open);
        }
    }

    private class HbufTreeEnumFieldElement extends PsiTreeElementBase<HbufEnumFieldElement> {
        public HbufTreeEnumFieldElement(HbufEnumFieldElement element) {
            super(element);
        }

        @Override
        public @NotNull
        Collection<StructureViewTreeElement> getChildrenBase() {
            List<StructureViewTreeElement> treeElements = new ArrayList<>();
            return treeElements;
        }

        @Override
        public @NlsSafe
        @Nullable
        String getPresentableText() {
            return getElement().getPresentation().getPresentableText() + "=" + getElement().getNo();
        }

        @Override
        public Icon getIcon(boolean open) {
            return getElement().getPresentation().getIcon(open);
        }
    }

    class HbufTreeDataElement extends PsiTreeElementBase<HbufDataElement> {

        protected HbufTreeDataElement(HbufDataElement psiElement) {
            super(psiElement);
        }

        @Override
        public @NotNull
        Collection<StructureViewTreeElement> getChildrenBase() {
            Collection<PsiElement> elements = PsiTreeUtil.findChildrenOfAnyType(getElement(),
                    HbufDataFieldElement.class
            );

            List<StructureViewTreeElement> treeElements = new ArrayList<>(elements.size());
            for (PsiElement element : elements) {
                treeElements.add(new HbufTreeDataFieldElement((HbufDataFieldElement) element));
            }
            return treeElements;
        }

        @Override
        public @NlsSafe
        @Nullable
        String getPresentableText() {
            return getElement().getPresentation().getPresentableText();
        }

        @Override
        public Icon getIcon(boolean open) {
            return getElement().getPresentation().getIcon(open);
        }
    }

    private class HbufTreeDataFieldElement extends PsiTreeElementBase<HbufDataFieldElement> {
        public HbufTreeDataFieldElement(HbufDataFieldElement element) {
            super(element);
        }

        @Override
        public @NotNull
        Collection<StructureViewTreeElement> getChildrenBase() {
            List<StructureViewTreeElement> treeElements = new ArrayList<>();
            return treeElements;
        }

        @Override
        public @NlsSafe
        @Nullable
        String getPresentableText() {
            HbufTypeStatementElement type = getElement().getType();
            String text = "";
            if (null != type.getMap()) {
                text = type.getMap().getBase().getName() + "<" + type.getMap().getKey().getText() + ">";
            } else if (null != type.getArray()) {
                text = type.getArray().getBase().getName() + "[]";
            } else if (null != type.getBase()) {
                text = type.getBase().getName();
            }
            return getElement().getPresentation().getPresentableText() +
                    ": " + text +
                    "=" + getElement().getNo();
        }

        @Override
        public Icon getIcon(boolean open) {
            return getElement().getPresentation().getIcon(open);
        }
    }

    class HbufTreeServerElement extends PsiTreeElementBase<HbufServerElement> {

        protected HbufTreeServerElement(HbufServerElement psiElement) {
            super(psiElement);
        }

        @Override
        public @NotNull
        Collection<StructureViewTreeElement> getChildrenBase() {
            Collection<PsiElement> elements = PsiTreeUtil.findChildrenOfAnyType(getElement(),
                    HbufServerFuncElement.class
            );

            List<StructureViewTreeElement> treeElements = new ArrayList<>(elements.size());
            for (PsiElement element : elements) {
                treeElements.add(new HbufTreeServerFuncElement((HbufServerFuncElement) element));
            }
            return treeElements;
        }

        @Override
        public @NlsSafe
        @Nullable
        String getPresentableText() {
            return getElement().getPresentation().getPresentableText() + " = " + getElement().getNo();
        }

        @Override
        public Icon getIcon(boolean open) {
            return getElement().getPresentation().getIcon(open);
        }
    }

    private class HbufTreeServerFuncElement extends PsiTreeElementBase<HbufServerFuncElement> {
        public HbufTreeServerFuncElement(HbufServerFuncElement element) {
            super(element);
        }

        @Override
        public @NotNull
        Collection<StructureViewTreeElement> getChildrenBase() {
            List<StructureViewTreeElement> treeElements = new ArrayList<>();
            return treeElements;
        }

        @Override
        public @NlsSafe
        @Nullable
        String getPresentableText() {
            return getElement().getPresentation().getPresentableText() +
                    "(" + getElement().getParam().getFuncType().getType() +
                    "): " + getElement().getType().getType() +
                    " = " + getElement().getNo();
        }

        @Override
        public Icon getIcon(boolean open) {
            return getElement().getPresentation().getIcon(open);
        }
    }

}
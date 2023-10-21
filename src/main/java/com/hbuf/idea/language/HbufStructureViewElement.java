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
        
        String getPresentableText() {
            return getElement().getName();
        }

        @Override
        public Icon getIcon(boolean open) {
            return HbufIcons.ENUM;
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
        
        String getPresentableText() {
            return getElement().getName() + " = " + getElement().getNumber();
        }

        @Override
        public Icon getIcon(boolean open) {
            return HbufIcons.FIELD;
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
        
        String getPresentableText() {
            return getElement().getName() ;
        }

        @Override
        public Icon getIcon(boolean open) {
            return HbufIcons.DATA;
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
        
        String getPresentableText() {
            HbufFieldTypeElement type = getElement().getTypeStatement();
            String text = "";
            if (null != type.getTypeMap()) {
                text = type.getTypeMap().getTypeBase().getName() + (type.getTypeMap().getTypeBase().isNullable() ? "?" : "") + "<" + type.getTypeMap().getKey() + ">" + (type.getTypeMap().isNullable() ? "?" : "");
            } else if (null != type.getTypeArray()) {
                text = type.getTypeArray().getTypeBase().getName() + (type.getTypeArray().getTypeBase().isNullable() ? "?" : "") + "[]" + (type.getTypeArray().isNullable() ? "?" : "");
            } else if (null != type.getTypeBase()) {
                text = type.getTypeBase().getName() + (type.getTypeBase().isNullable() ? "?" : "");
            }
            return getElement().getName() +
                    ": " + text +
                    "=" + getElement().getNumber();
        }

        @Override
        public Icon getIcon(boolean open) {
            return HbufIcons.FIELD;
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
        
        String getPresentableText() {
            return getElement().getName();
        }

        @Override
        public Icon getIcon(boolean open) {
            return HbufIcons.SERVER;
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
        
        String getPresentableText() {
            return getElement().getName() +
                    "(" + getElement().getFuncParam().getFuncType().getName() +
                    "): " + getElement().getFuncType().getName() +
                    " = " + getElement().getNumber();
        }

        @Override
        public Icon getIcon(boolean open) {
            return HbufIcons.FUNC;
        }
    }

}

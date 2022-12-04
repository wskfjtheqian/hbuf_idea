package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufEnumElement;
import com.hbuf.idea.language.psi.HbufFile;
import com.hbuf.idea.language.psi.HbufServerElement;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HbufStructureViewElement implements StructureViewTreeElement, SortableTreeElement {
    private final NavigatablePsiElement myElement;

    public HbufStructureViewElement(NavigatablePsiElement element) {
        this.myElement = element;
    }

    @Override
    public Object getValue() {
        return myElement;
    }

    @Override
    public void navigate(boolean requestFocus) {
        myElement.navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
        return myElement.canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return myElement.canNavigateToSource();
    }

    @NotNull
    @Override
    public String getAlphaSortKey() {
        String name = myElement.getName();
        return name != null ? name : "";
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        ItemPresentation presentation = myElement.getPresentation();
        return presentation != null ? presentation : new PresentationData();
    }

    @Override
    public TreeElement[] getChildren() {
        if (myElement instanceof HbufFile) {
            Collection<PsiElement> elements = PsiTreeUtil.findChildrenOfAnyType(myElement,
                    HbufDataElement.class,
                    HbufEnumElement.class,
                    HbufServerElement.class
            );

            List<TreeElement> treeElements = new ArrayList<>(elements.size());
            for (PsiElement element : elements) {
                if (element instanceof HbufEnumElement) {
                    treeElements.add(new HbufTreeEnumElement((HbufEnumElement) element));
                } else if (element instanceof HbufDataElement) {
                    treeElements.add(new HbufTreeDataElement((HbufDataElement) element));
                } else if (element instanceof HbufServerElement) {
                    treeElements.add(new HbufTreeServerElement((HbufServerElement) element));
                }

            }
            return treeElements.toArray(new TreeElement[0]);
        }
        return EMPTY_ARRAY;
    }

    private class HbufTreeEnumElement implements StructureViewTreeElement, SortableTreeElement {
        private final HbufEnumElement enumElement;

        private HbufTreeEnumElement(HbufEnumElement enumElement) {
            this.enumElement = enumElement;
        }

        @Override
        public Object getValue() {
            return enumElement;
        }

        @Override
        public @NotNull
        String getAlphaSortKey() {
            String name = enumElement.getName();
            return name != null ? "e" + name : "";
        }

        @Override
        public @NotNull
        ItemPresentation getPresentation() {
            PresentationData data = new PresentationData();
            data.setPresentableText(enumElement.getName());
            return data;
        }

        @Override
        public TreeElement[] getChildren() {
            return new TreeElement[0];
        }

        @Override
        public void navigate(boolean b) {
            myElement.navigate(b);
        }

        @Override
        public boolean canNavigate() {
            return myElement.canNavigate();
        }

        @Override
        public boolean canNavigateToSource() {
            return myElement.canNavigateToSource();
        }
    }

    private class HbufTreeDataElement implements StructureViewTreeElement, SortableTreeElement {
        private final HbufDataElement enumElement;

        private HbufTreeDataElement(HbufDataElement enumElement) {
            this.enumElement = enumElement;
        }

        @Override
        public Object getValue() {
            return enumElement;
        }

        @Override
        public @NotNull
        String getAlphaSortKey() {
            String name = enumElement.getName();
            return name != null ? "d" + name : "";
        }

        @Override
        public @NotNull
        ItemPresentation getPresentation() {
            PresentationData data = new PresentationData();
            data.setPresentableText(enumElement.getName());
            return data;
        }

        @Override
        public TreeElement[] getChildren() {
            return new TreeElement[0];
        }

        @Override
        public void navigate(boolean b) {
            enumElement.navigate(b);
        }

        @Override
        public boolean canNavigate() {
            return enumElement.canNavigate();
        }

        @Override
        public boolean canNavigateToSource() {
            return enumElement.canNavigateToSource();
        }
    }

    private class HbufTreeServerElement implements StructureViewTreeElement, SortableTreeElement {
        private final HbufServerElement enumElement;

        private HbufTreeServerElement(HbufServerElement enumElement) {
            this.enumElement = enumElement;
        }

        @Override
        public Object getValue() {
            return enumElement;
        }

        @Override
        public @NotNull
        String getAlphaSortKey() {
            String name = enumElement.getName();
            return name != null ? "e" + name : "";
        }

        @Override
        public @NotNull
        ItemPresentation getPresentation() {
            PresentationData data = new PresentationData();
            data.setPresentableText(enumElement.getName());
            return data;
        }

        @Override
        public TreeElement[] getChildren() {
            return new TreeElement[0];
        }

        @Override
        public void navigate(boolean b) {
            enumElement.navigate(b);
        }

        @Override
        public boolean canNavigate() {
            return enumElement.canNavigate();
        }

        @Override
        public boolean canNavigateToSource() {
            return enumElement.canNavigateToSource();
        }
    }


}
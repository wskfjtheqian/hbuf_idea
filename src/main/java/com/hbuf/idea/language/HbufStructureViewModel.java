package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufFile;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.TextEditorBasedStructureViewModel;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;


public class HbufStructureViewModel extends TextEditorBasedStructureViewModel implements StructureViewModel.ElementInfoProvider {
    @NotNull
    private final StructureViewTreeElement myRootElement;

    HbufStructureViewModel( final Editor editor, @NotNull final PsiFile psiFile) {
        super(editor, psiFile);
        myRootElement = new DartStructureViewRootElement(psiFile);
    }

    @NotNull
    @Override
    public StructureViewTreeElement getRoot() {
        return myRootElement;
    }

    @Override
    
    public PsiElement getCurrentEditorElement() {
        if (getEditor() == null) return null;

        return (PsiElement) super.getCurrentEditorElement();
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public Sorter[] getSorters() {
        return new Sorter[]{Sorter.ALPHA_SORTER};
    }

    private static class DartStructureViewRootElement extends PsiTreeElementBase<PsiFile> {

        DartStructureViewRootElement(PsiFile file) {
            super(file);
        }

        
        @Override
        public String getPresentableText() {
            return getElement().getName();
        }

        @NotNull
        @Override
        public Collection<StructureViewTreeElement> getChildrenBase() {
            return Arrays.asList(new HbufStructureViewElement().create((HbufFile) getValue()).getChildren());
        }
    }
}


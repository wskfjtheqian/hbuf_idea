package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufEnumElement;
import com.hbuf.idea.language.psi.HbufServerElement;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HbufStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {
    public HbufStructureViewModel(@NotNull PsiFile psiFile, @Nullable Editor editor) {
        super(psiFile, editor, new HbufStructureViewElement(psiFile));
    }

    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[]{Sorter.ALPHA_SORTER};
    }


    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return element.getValue() instanceof HbufEnumElement ||
                element.getValue() instanceof HbufDataElement ||
                element.getValue() instanceof HbufServerElement;
    }

    @Override
    protected Class<?>[] getSuitableClasses() {
        return new Class[]{HbufEnumElement.class, HbufDataElement.class, HbufServerElement.class};
    }
}

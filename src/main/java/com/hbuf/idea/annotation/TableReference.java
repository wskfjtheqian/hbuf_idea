package com.hbuf.idea.annotation;

import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TableReference extends PsiReferenceBase<PsiElement> {
    private final String className;

    public TableReference(@NotNull PsiElement element, String className) {
        super(element);
        this.className = className;
    }

    @Override
    public PsiElement resolve() {
        List<HbufDataElement> list = HbufUtil.findData(myElement.getProject(), className);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean isReferenceTo(@NotNull PsiElement element) {
        return element.equals(resolve());
    }
}

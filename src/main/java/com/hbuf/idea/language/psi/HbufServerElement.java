package com.hbuf.idea.language.psi;

import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface HbufServerElement extends PsiNamedElement {
    String getName();

    @NotNull
    HbufNameElement getIdentName();

    HbufAnnotationGroupElement getAnnotationGroup();

    @NotNull
    Collection<HbufExtendsElement> getExtendList();

    @NotNull
    HbufServerBodyElement getServerBody();

    @NotNull
    Collection<HbufServerFuncElement> getFuncts();
}

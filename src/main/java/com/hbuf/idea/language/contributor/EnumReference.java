package com.hbuf.idea.language.contributor;

import com.hbuf.idea.language.psi.HbufStringElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
public class EnumReference extends PsiReferenceBase<HbufStringElement> implements PsiPolyVariantReference {

    public EnumReference(@NotNull HbufStringElement element) {
        super(element, new TextRange(1, element.getTextLength() - 1));
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        String enumFullName = getValue();
        String[] parts = enumFullName.split("\\.");

        if (parts.length != 2) return ResolveResult.EMPTY_ARRAY;

        String enumClassName = parts[0];
        String enumConstant = parts[1];

//        // 找到对应的 Enum 类
//        PsiClass enumClass = JavaPsiFacade.getInstance(myElement.getProject())
//                .findClass(enumClassName, GlobalSearchScope.allScope(myElement.getProject()));
//
//        if (enumClass == null || !enumClass.isEnum()) return ResolveResult.EMPTY_ARRAY;
//
//        for (PsiField field : enumClass.getFields()) {
//            if (field instanceof PsiEnumConstant && field.getName().equals(enumConstant)) {
//                return new ResolveResult[]{new PsiElementResolveResult(field)};
//            }
//        }
        return ResolveResult.EMPTY_ARRAY;
    }

    @Override
    public PsiElement resolve() {
        ResolveResult[] results = multiResolve(false);
        return results.length > 0 ? results[0].getElement() : null;
    }

    @Override
    public @NotNull Object @NotNull [] getVariants() {
        return new Object[0];
    }
}

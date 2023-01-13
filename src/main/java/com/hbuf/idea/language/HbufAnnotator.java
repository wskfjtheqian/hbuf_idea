package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.*;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class HbufAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof HbufTypeStatementElement) {
            HbufTypeStatementElement type = ((HbufTypeStatementElement) element);
            HbufTypeBaseElement base = type.getTypeBase();
            if (null != base) {
                checkType(holder, base, CheckType.Base, CheckType.Data, CheckType.Enum);
                return;
            }
            HbufTypeArrayElement array = type.getTypeArray();
            if (null != array) {
                checkType(holder, array.getTypeBase(), CheckType.Base, CheckType.Data, CheckType.Enum);
                return;
            }
            HbufTypeMapElement map = type.getTypeMap();
            if (null != map) {
                checkType(holder, map.getKey(), CheckType.Base);
                checkType(holder, map.getBase(), CheckType.Base, CheckType.Data, CheckType.Enum);
                return;
            }
        }
        if (element instanceof HbufFuncType) {
            HbufFuncType func = (HbufFuncType) element;
            checkType(holder, func, CheckType.Data);
            return;
        }
        if (element instanceof HbufFuncParam) {
            HbufFuncParam param = (HbufFuncParam) element;
            checkType(holder, param.getFuncType(), CheckType.Data);
            return;
        }

        if (element instanceof HbufNameElement) {
            PsiElement parent = element.getParent();
            if (parent instanceof HbufEnumElement) {
                return;
            }
            if (parent instanceof HbufDataElement) {
                return;
            }
            if (parent instanceof HbufServerElement) {
                return;
            }
            if (parent instanceof HbufDataFieldElement) {
                checkDataFieldName(holder, (HbufDataFieldElement) parent, element);
                return;
            }
            if (parent instanceof HbufEnumFieldElement) {
                checkEnumFieldName(holder, (HbufEnumFieldElement) parent, element);
                return;
            }
            if (parent instanceof HbufServerFuncElement) {
                checkServerFuncName(holder, (HbufServerFuncElement) parent, element);
                return;
            }
            if (parent instanceof HbufExtendsElement) {
                parent = parent.getParent();
                if (parent instanceof HbufDataElement) {
                    checkDataExtends(holder, (HbufDataElement) parent, element);
                    return;
                }
                if (parent instanceof HbufServerElement) {
                    checkServerExtends(holder, (HbufServerElement) parent, element);
                    return;
                }
            }
        }

        if (element instanceof HbufIdElement) {
            PsiElement parent = element.getParent();
            if (parent instanceof HbufEnumElement) {
                return;
            }
            if (parent instanceof HbufDataElement) {
                return;
            }
            if (parent instanceof HbufServerElement) {
                return;
            }
            if (parent instanceof HbufDataFieldElement) {
                checkDataFieldId(holder, (HbufDataFieldElement) parent, (HbufIdElement) element);
                return;
            }
            if (parent instanceof HbufEnumFieldElement) {
                checkEnumFieldId(holder, (HbufEnumFieldElement) parent, (HbufIdElement) element);
                return;
            }
            if (parent instanceof HbufServerFuncElement) {
                checkServerFuncId(holder, (HbufServerFuncElement) parent, (HbufIdElement) element);
                return;
            }
            if (parent instanceof HbufExtendsElement) {
                parent = parent.getParent();
                if (parent instanceof HbufDataElement) {
                    checkDataExtends(holder, (HbufDataElement) parent, element);
                    return;
                }
                if (parent instanceof HbufServerElement) {
                    checkServerExtends(holder, (HbufServerElement) parent, element);
                    return;
                }
            }
        }
    }

    private void checkServerFuncId(AnnotationHolder holder, HbufServerFuncElement parent, HbufIdElement element) {
        HbufServerElement hse = HbufUtil.getServerByChild(element);
        if (null == hse) {
            return;
        }
        for (HbufServerFuncElement item : hse.getServerBody().getFuncList().getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getNo() == element.getNo()) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Func Id'" + element.getNo() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkEnumFieldId(AnnotationHolder holder, HbufEnumFieldElement parent, HbufIdElement element) {
        HbufEnumElement hee = HbufUtil.getEnumByChild(element);
        if (null == hee) {
            return;
        }
        for (HbufEnumFieldElement item : hee.getEnumBody().getEnumFieldList().getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getNo() == element.getNo()) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Field Id'" + element.getNo() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkDataFieldId(AnnotationHolder holder, HbufDataFieldElement parent, HbufIdElement element) {
        HbufDataElement hee = HbufUtil.getDataByChild(element);
        if (null == hee) {
            return;
        }
        for (HbufDataFieldElement item : hee.getDataBody().getDataFieldList().getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getNo() == element.getNo()) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Field Id'" + element.getNo() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkServerExtends(AnnotationHolder holder, HbufServerElement data, PsiElement element) {
        if (data.getName().equals(element.getText())) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Cyclic inheritance involving '" + element.getText() + "'")
                    .range(element)
                    .highlightType(ProblemHighlightType.GENERIC_ERROR)
                    .create();
            return;
        }
        if (HbufUtil.isData(element.getProject(), element.getText())) {
            return;
        }
        holder.newAnnotation(HighlightSeverity.ERROR, element.getText() + " undefined symbol")
                .range(element)
                .highlightType(ProblemHighlightType.GENERIC_ERROR)
                .create();
    }

    private void checkDataExtends(AnnotationHolder holder, HbufDataElement data, PsiElement element) {
        if (data.getName().equals(element.getText())) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Cyclic inheritance involving '" + element.getText() + "'")
                    .range(element)
                    .highlightType(ProblemHighlightType.GENERIC_ERROR)
                    .create();
            return;
        }
        if (HbufUtil.isData(element.getProject(), element.getText())) {
            return;
        }
        holder.newAnnotation(HighlightSeverity.ERROR, element.getText() + " undefined symbol")
                .range(element)
                .highlightType(ProblemHighlightType.GENERIC_ERROR)
                .create();
    }


    private void checkEnumFieldName(AnnotationHolder holder, HbufEnumFieldElement parent, PsiElement element) {
        HbufEnumElement hee = HbufUtil.getEnumByChild(element);
        if (null == hee) {
            return;
        }
        for (HbufEnumFieldElement item : hee.getEnumBody().getEnumFieldList().getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getName().equals(element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Field '" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkServerFuncName(AnnotationHolder holder, HbufServerFuncElement parent, PsiElement element) {
        HbufServerElement hee = HbufUtil.getServerByChild(element);
        if (null == hee) {
            return;
        }
        for (HbufServerFuncElement item : hee.getServerBody().getFuncList().getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getName().equals(element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Func '" + element.getText() + "' is already defined in the scope")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }

    private void checkDataFieldName(AnnotationHolder holder, HbufDataFieldElement parent, PsiElement element) {
        HbufDataElement hde = HbufUtil.getDataByChild(element);
        if (null == hde) {
            return;
        }
        for (HbufDataFieldElement item : hde.getDataBody().getDataFieldList().getFields()) {
            if (item == parent) {
                continue;
            }
            if (item.getName().equals(element.getText())) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Field '" + element.getText() + "' is already defined in the scope element.getText()")
                        .range(element)
                        .highlightType(ProblemHighlightType.GENERIC_ERROR)
                        .create();
                return;
            }
        }
    }


    enum CheckType {
        Base,
        Data,
        Enum,
        Server,
    }

    private void checkType(@NotNull AnnotationHolder holder, PsiElement base, CheckType... types) {
        List<CheckType> list = Arrays.asList(types);
        if (list.contains(CheckType.Base)) {
            switch (base.getText()) {
                case "int8":
                case "int16":
                case "int32":
                case "int64":
                case "uint8":
                case "uint16":
                case "uint32":
                case "uint64":
                case "bool":
                case "float":
                case "double":
                case "decimal":
                case "string":
                    return;
            }
        }
        if (list.contains(CheckType.Data)) {
            if (HbufUtil.isData(base.getProject(), base.getText())) {
                return;
            }
        }
        if (list.contains(CheckType.Enum)) {
            if (HbufUtil.isEnum(base.getProject(), base.getText())) {
                return;
            }
        }
        if (list.contains(CheckType.Server)) {
            if (HbufUtil.isServer(base.getProject(), base.getText())) {
                return;
            }
        }
        holder.newAnnotation(HighlightSeverity.ERROR, base.getText() + " undefined symbol")
                .range(base)
                .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                .create();
        return;
    }
}

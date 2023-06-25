package com.hbuf.idea.language.template;

import com.hbuf.idea.language.HbufLanguage;
import com.hbuf.idea.language.psi.*;
import com.intellij.codeInsight.template.TemplateActionContext;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiUtilCore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class HbufLiveTemplatesContext extends TemplateContextType {
    protected HbufLiveTemplatesContext(@NotNull String id,
                                       @NlsContexts.Label @NotNull String presentableName,
                                        Class<? extends TemplateContextType> baseContextType) {
        super(id, presentableName, baseContextType);
    }

    @Override
    public boolean isInContext(@NotNull TemplateActionContext templateActionContext) {
        PsiFile file = templateActionContext.getFile();
        int startOffset = templateActionContext.getStartOffset();
        if (PsiUtilCore.getLanguageAtOffset(file, startOffset).isKindOf(HbufLanguage.INSTANCE)) {
            PsiElement element = file.findElementAt(startOffset);
            if (element instanceof PsiWhiteSpace) {
                return false;
            } else {
                return element != null && this.isInContext(element);
            }
        } else {
            return false;
        }
    }

    protected abstract boolean isInContext(@NotNull PsiElement element);

    public static class File extends HbufLiveTemplatesContext {
        protected File() {
            super("HBUF_FILE", "File", HbufEverywhereContext.class);
        }

        @Override
        protected boolean isInContext(@NotNull PsiElement element) {
            return null != element.getParent() && element.getParent() instanceof HbufFile;
        }
    }

    public static class Enum extends HbufLiveTemplatesContext {
        protected Enum() {
            super("HBUF_ENUM", "Enum", HbufEverywhereContext.class);
        }

        @Override
        protected boolean isInContext(@NotNull PsiElement element) {
            PsiElement parent = element.getParent();
            if (null != parent) {
                return parent instanceof HbufEnumFieldsElement || parent instanceof HbufEnumBodyElement;
            }
            return false;
        }
    }

    public static class Data extends HbufLiveTemplatesContext {
        protected Data() {
            super("HBUF_DATA", "Data", HbufEverywhereContext.class);
        }

        @Override
        protected boolean isInContext(@NotNull PsiElement element) {
            PsiElement parent = element.getParent();
            if (null != parent) {
                return parent instanceof HbufDataFieldsElement || parent instanceof HbufDataBodyElement;
            }
            return false;
        }
    }

    public static class Server extends HbufLiveTemplatesContext {
        protected Server() {
            super("HBUF_SERVER", "Server", HbufEverywhereContext.class);
        }

        @Override
        protected boolean isInContext(@NotNull PsiElement element) {
            PsiElement parent = element.getParent();
            if (null != parent) {
                return parent instanceof HbufServerFuncsElement || parent instanceof HbufServerBodyElement;
            }
            return false;
        }
    }
}

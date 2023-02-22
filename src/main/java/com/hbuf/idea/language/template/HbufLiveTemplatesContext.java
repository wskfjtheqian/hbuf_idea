package com.hbuf.idea.language.template;

import com.intellij.codeInsight.template.TemplateActionContext;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NotNull;

public class HbufLiveTemplatesContext extends TemplateContextType {
    protected HbufLiveTemplatesContext() {
        super("HBUF", "Hbuf");
    }

    @Override
    public boolean isInContext(@NotNull TemplateActionContext templateActionContext) {
        return templateActionContext.getFile().getName().endsWith(".hbuf");
    }

}

package com.hbuf.idea.language.template;

import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NotNull;

public class HbufLiveTemplatesContext extends TemplateContextType {
    protected HbufLiveTemplatesContext() {
        super("MARKDOWN", "Markdown");
    }

}

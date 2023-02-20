package com.hbuf.idea.language.completion;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import org.jetbrains.annotations.NotNull;

public class HbufEnumFieldLookupElement extends LookupElement {
    @Override
    public @NotNull String getLookupString() {
        return "null";
    }

    public void renderElement(LookupElementPresentation presentation) {
        presentation.setItemText("this.getLookupString()");
    }
}

package com.hbuf.idea.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class HbufFileType extends LanguageFileType {
    public static final HbufFileType INSTANCE = new HbufFileType();

    protected HbufFileType() {
        super(HbufLanguage.INSTANCE);
    }

    @Override
    public @NonNls
    @NotNull
    String getName() {
        return "Hbuf";
    }

    @Override
    public @NlsContexts.Label
    @NotNull
    String getDescription() {
        return "Hbuf file";
    }

    @Override
    public @NlsSafe
    @NotNull
    String getDefaultExtension() {
        return "hbuf";
    }

    @Override
    public 
    Icon getIcon() {
        return HbufIcons.FILE;
    }


}




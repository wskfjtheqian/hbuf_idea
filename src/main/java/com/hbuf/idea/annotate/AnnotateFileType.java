package com.hbuf.idea.annotate;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AnnotateFileType extends LanguageFileType {
    public static final AnnotateFileType INSTANCE = new AnnotateFileType();

    protected AnnotateFileType() {
        super(AnnotateLanguage.INSTANCE);
    }

    @Override
    public @NonNls
    @NotNull
    String getName() {
        return "Annotate";
    }

    @Override
    public @NlsContexts.Label
    @NotNull
    String getDescription() {
        return "Annotate file";
    }

    @Override
    public @NlsSafe
    @NotNull
    String getDefaultExtension() {
        return "annotate";
    }

    @Override
    public 
    Icon getIcon() {
//        return AnnotateIcons.FILE;
        return null;
    }


}




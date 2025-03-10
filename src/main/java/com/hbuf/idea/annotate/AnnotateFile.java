package com.hbuf.idea.annotate;



import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class AnnotateFile  extends PsiFileBase {

    public AnnotateFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, AnnotateLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return AnnotateFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Annotate File";
    }

}
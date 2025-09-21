package com.hbuf.idea.language.editor;

import com.hbuf.idea.language.HbufFileType;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class HbufEditorProvider implements FileEditorProvider {
    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return file.getFileType() instanceof HbufFileType;
    }

    @Override
    public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        return new HbufFileEditor(project, file);
    }

    @Override
    public @NotNull @NonNls String getEditorTypeId() {
        return "hbuf-editor";
    }

    @Override
    public @NotNull FileEditorPolicy getPolicy() {
        return FileEditorPolicy.NONE;
    }

}

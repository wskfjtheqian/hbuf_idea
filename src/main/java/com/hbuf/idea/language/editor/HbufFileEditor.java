package com.hbuf.idea.language.editor;

import com.hbuf.idea.language.psi.HbufFile;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.fileEditor.FileEditorStateLevel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class HbufFileEditor extends UserDataHolderBase implements FileEditor {

    private final Project project;
    private final VirtualFile file;
    private final HbufEditerGui editer;

    public HbufFileEditor(Project project, VirtualFile file) {
        this.project = project;
        this.file = file;

        this.editer = new HbufEditerGui((HbufFile) PsiManager.getInstance(project).findFile(file),project);
    }

    @Override
    public @NotNull JComponent getComponent() {
        return editer;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return editer;
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) @NotNull String getName() {
        return "Generate Code";
    }

    @Override
    public void setState(@NotNull FileEditorState fileEditorState) {

    }

    @Override
    public @NotNull FileEditorState getState(@NotNull FileEditorStateLevel level) {
        // 返回文件编辑器的状态
        return new FileEditorState() {
            @Override
            public boolean canBeMergedWith(@NotNull FileEditorState fileEditorState, @NotNull FileEditorStateLevel fileEditorStateLevel) {
                return false;
            }
        };
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Override
    public void dispose() {
if (editer != null) {
        editer.dispose();
}
    }

    @Override
    public VirtualFile getFile() {
        // 确保返回的文件对象不是 null
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        return file;
    }
}

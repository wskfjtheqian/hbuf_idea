package com.hbuf.idea.language;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class HbufCreateAction extends CreateFileFromTemplateAction {
    public HbufCreateAction() {
        super("Hbuf", "create new hbuf file", HbufIcons.FILE);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory,
                               CreateFileFromTemplateDialog.Builder builder) {
        builder
                .setTitle("New Hbuf File")
                .addKind("File", HbufIcons.FILE, "Hbuf.ft");
    }

    @Override
    protected String getActionName(PsiDirectory directory, @NotNull String newName, String templateName) {
        return "Create My Class: " + newName;
    }
}

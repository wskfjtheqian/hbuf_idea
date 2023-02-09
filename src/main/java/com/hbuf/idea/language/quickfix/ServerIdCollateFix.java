package com.hbuf.idea.language.quickfix;

import com.hbuf.idea.language.psi.*;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ServerIdCollateFix extends BaseIntentionAction {
    private final HbufIdElement element;

    public ServerIdCollateFix(HbufIdElement element) {
        this.element = element;
    }

    @Override
    public @NotNull
    @IntentionFamilyName String getFamilyName() {
        return "Data id";
    }

    @Override
    public @IntentionName
    @NotNull String getText() {
        return "Collate data id";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        return true;
    }


    @Override
    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
        WriteCommandAction.writeCommandAction(project).run(() -> {
            @NotNull List<HbufServerElement> elements = new ArrayList<>(HbufUtil.findServer(element.getProject()));
            for (int i = 0; i < elements.size(); i++) {
                HbufIdElement id = HbufElementFactory.createId(project, i);
                HbufIdElement idElement = elements.get(i).getId();
                idElement.getParent().getNode().replaceChild(idElement.getNode(), id.getNode());
            }
            FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
        });
    }
}


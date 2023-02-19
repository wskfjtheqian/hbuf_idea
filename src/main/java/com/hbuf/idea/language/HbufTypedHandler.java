package com.hbuf.idea.language;

import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.template.impl.editorActions.TypedActionHandlerBase;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class HbufTypedHandler extends TypedActionHandlerBase {
    public HbufTypedHandler(TypedActionHandler originalHandler) {
        super(originalHandler);
    }

    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
        if (myOriginalHandler != null) myOriginalHandler.execute(editor, c, dataContext);
        if (c != '=') return;
        Project project = editor.getProject();
        if (project == null) return;
//        int offset = editor.getCaretModel().getOffset();
//        if (offset < 4) return;
//        TextRange from = TextRange.from(offset - 4, 4);
//        String text = editor.getDocument().getText(from);
//        if ("case".equals(text)) {

        ApplicationManager.getApplication().runWriteAction(() -> {
            AutoPopupController.getInstance(project).autoPopupMemberLookup(editor, null);
        });
//        }
    }


}

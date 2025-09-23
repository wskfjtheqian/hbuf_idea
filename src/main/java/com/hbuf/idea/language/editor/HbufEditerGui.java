package com.hbuf.idea.language.editor;

import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufFile;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.ui.OnePixelSplitter;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import org.intellij.plugins.markdown.lang.MarkdownFileType;
import org.intellij.plugins.markdown.ui.preview.MarkdownEditorWithPreview;
import org.intellij.plugins.markdown.ui.preview.MarkdownSplitEditorProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Collection;

public class HbufEditerGui extends JPanel {
    private static final Logger log = LoggerFactory.getLogger(HbufEditerGui.class);
    private final HbufFile file;
    private final Project project;
    private final Parser parser = Parser.builder().build();
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();

    private JList<String> dataList;
    private MarkdownEditorWithPreview mdEditor;
    private LightVirtualFile vFile;

    DefaultListModel<String> listModel = new DefaultListModel<>();

    public HbufEditerGui(@Nullable HbufFile file, Project project) {
        this.file = file;
        this.project = project;
        add(createComponent());
        //设置布局为填充父容器
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


//        dataList.setModel(listModel);
//        dataList.addListSelectionListener(e -> {
//
//        });

//        setDataList();
    }

    public JComponent createComponent() {
        dataList = new JBList<>(listModel);
        JBScrollPane leftPanel = new JBScrollPane(dataList);

        // 创建虚拟文件
        this.vFile = new LightVirtualFile("temp.md", MarkdownFileType.INSTANCE, "");
        vFile.setWritable(true);

        MarkdownSplitEditorProvider provider = new MarkdownSplitEditorProvider();
        mdEditor = (MarkdownEditorWithPreview) provider.createEditor(project, vFile);

        JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // 水平排列，左对齐
        JBCheckBox check1 = new JBCheckBox("Option 1");
        JBCheckBox check2 = new JBCheckBox("Option 2");
        JBCheckBox check3 = new JBCheckBox("Option 3");
        checkBoxPanel.add(check1);
        checkBoxPanel.add(check2);
        checkBoxPanel.add(check3);

        // 右边面板：上方复选框，下面 MarkdownEditor
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(checkBoxPanel, BorderLayout.NORTH);
        rightPanel.add(mdEditor.getComponent(), BorderLayout.CENTER);

        OnePixelSplitter splitter = new OnePixelSplitter(false, 0.3f);
        splitter.setFirstComponent(leftPanel);
        splitter.setSecondComponent(rightPanel);

        // 监听 JBList 选择事件
        dataList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // 避免重复触发
                    String selected = dataList.getSelectedValue();
                    if (selected != null) {
                        // 生成代码内容（示例）
                        StringBuilder code = new StringBuilder();
                        code.append("```java\n");
                        code.append("// 生成的代码示例: ").append(selected).append("\n");
                        code.append("public class ").append(selected.replace(" ", "")).append(" {\n");
                        code.append("    public void run() {\n");
                        code.append("        System.out.println(\"Hello ").append(selected).append("\");\n");
                        code.append("    }\n");
                        code.append("}\n");
                        code.append("```\n");

                        mdEditor.getEditor().getDocument().setText(code.toString());
                    }
                }
            }
        });
        return splitter;
    }

    private void setDataList() {
        @NotNull Collection<HbufDataElement> dataElements = PsiTreeUtil.findChildrenOfAnyType(file, HbufDataElement.class);

        for (HbufDataElement element : dataElements) {
            listModel.addElement(element.getName());
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        listModel.clear();
        setDataList();
    }


    public void dispose() {
        this.mdEditor.dispose();
      ;
    }
}

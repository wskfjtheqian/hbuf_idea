package com.hbuf.idea.language.editor;

import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufFile;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.ui.OnePixelSplitter;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import org.intellij.plugins.markdown.lang.MarkdownFileType;
import org.intellij.plugins.markdown.ui.preview.MarkdownEditorWithPreview;
import org.intellij.plugins.markdown.ui.preview.MarkdownSplitEditorProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.Collection;


public class HbufEditerGui extends JPanel {
    private static final Logger log = LoggerFactory.getLogger(HbufEditerGui.class);
    private final HbufFile file;
    private final Project project;

    DefaultListModel<String> listModel = new DefaultListModel<>();
    private LightVirtualFile vFile;
    private String listSelected;

    private JList<String> dataList;
    private MarkdownEditorWithPreview mdEditor;
    private CollapsibleCheckBoxPanel checkList;
    private CollapsibleCheckBoxPanel checkGet;
    private CollapsibleCheckBoxPanel checkAdd;
    private CollapsibleCheckBoxPanel checkSet;
    private CollapsibleCheckBoxPanel checkDel;
    private CollapsibleCheckBoxPanel checkStatus;
    private CollapsibleCheckBoxPanel checkExport;

    public HbufEditerGui(@Nullable HbufFile file, Project project) {
        this.file = file;
        this.project = project;
        add(createComponent());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    public JComponent createComponent() {
        dataList = new JBList<>(listModel);
        JBScrollPane leftPanel = new JBScrollPane(dataList);

        this.vFile = new LightVirtualFile("temp.md", MarkdownFileType.INSTANCE, "");
        vFile.setWritable(true);

        MarkdownSplitEditorProvider provider = new MarkdownSplitEditorProvider();
        mdEditor = (MarkdownEditorWithPreview) provider.createEditor(project, vFile);

        CollapsibleCheckBoxGroup group = new CollapsibleCheckBoxGroup(true);
        group.addPanel(this.checkList = bindEvent(new CollapsibleCheckBoxPanel("List", new JBLabel(""), false)));
        group.addPanel(this.checkExport = bindEvent(new CollapsibleCheckBoxPanel("Export", new JBLabel(""), false)));
        group.addPanel(this.checkAdd = bindEvent(new CollapsibleCheckBoxPanel("Add", new JBLabel(""), false)));
        group.addPanel(this.checkGet = bindEvent(new CollapsibleCheckBoxPanel("Get", new JBLabel(""), false)));
        group.addPanel(this.checkSet = bindEvent(new CollapsibleCheckBoxPanel("Set", new JBLabel(""), false)));
        group.addPanel(this.checkDel = bindEvent(new CollapsibleCheckBoxPanel("Del", new JBLabel(""), false)));
        group.addPanel(this.checkStatus = bindEvent(new CollapsibleCheckBoxPanel("Status", new JBLabel(""), false)));


        OnePixelSplitter rightSplitter = new OnePixelSplitter(false, 0.3f);
        rightSplitter.setFirstComponent(group);
        rightSplitter.setSecondComponent(mdEditor.getComponent());


        OnePixelSplitter leftSplitter = new OnePixelSplitter(false, 0.3f);
        leftSplitter.setFirstComponent(leftPanel);
        leftSplitter.setSecondComponent(rightSplitter);

        dataList.addListSelectionListener(this::onListSelection);
        return leftSplitter;
    }

    private CollapsibleCheckBoxPanel bindEvent(CollapsibleCheckBoxPanel panel) {
        panel.getCheckBox().addChangeListener(e -> {
            generateCode();
        });
        return panel;
    }

    private void onListSelection(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            this.listSelected = dataList.getSelectedValue();
            if (listSelected != null) {
                this.generateCode();
            }
        }
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
    }

    public void generateCode() {
        StringBuilder code = new StringBuilder();
        code.append(generateHbufServerCode(listSelected));
        code.append(generateHbufManageCode(listSelected));
        code.append(generateHbufPowerCode(listSelected));
        code.append(generateGoServerCode(listSelected));
        code.append(generateVueListViewCode(listSelected));
        code.append(generateVueInfoViewCode(listSelected));
        code.append(generateVueRouteCode(listSelected));

        ApplicationManager.getApplication().runWriteAction(() -> {
            mdEditor.getEditor().getDocument().setText(code.toString());
        });
    }

    private StringBuilder generateHbufServerCode(String name) {
        StringBuilder code = new StringBuilder();
        code.append("### Hbuf Server Code\n")
                .append("```hbuf\n")
                .append("server Server {\n");
        if (checkList.getCheckBox().isSelected()) {
            code.append("\tList").append(name).append("Resp List").append(name).append("(List").append(name).append("Req req) = 1 \n\n");
        }
        if (checkAdd.getCheckBox().isSelected()) {
            code.append("\tAdd").append(name).append("Resp Add").append(name).append("(Add").append(name).append("Req req) = 2  \n\n");
        }
        if (checkGet.getCheckBox().isSelected()) {
            code.append("\tGet").append(name).append("Resp Get").append(name).append("(Get").append(name).append("Req req) = 3  \n\n");
        }
        if (checkSet.getCheckBox().isSelected()) {
            code.append("\tSet").append(name).append("Resp Set").append(name).append("(Set").append(name).append("Req req) = 4  \n\n");
        }
        if (checkDel.getCheckBox().isSelected()) {
            code.append("\tDel").append(name).append("Resp Del").append(name).append("(Del").append(name).append("Req req) = 5  \n\n");
        }
        if (checkStatus.getCheckBox().isSelected()) {
            code.append("\tStatus").append(name).append("Resp Status").append(name).append("(Status").append(name).append("Req req) = 6  \n\n");
        }
        if (checkExport.getCheckBox().isSelected()) {
            code.append("\tExport").append(name).append("Resp Export").append(name).append("(Export").append(name).append("Req req) = 7  \n\n");
        }

        code.append("}\n").append("```\n\n");
        return code;
    }

    private StringBuilder generateHbufManageCode(String name) {
        StringBuilder code = new StringBuilder();
        code.append("### Hbuf Manage Code\n")
                .append("```hbuf\n")
                .append("server Server {\n");
        if (checkList.getCheckBox().isSelected()) {
            code.append("\t[tag:auth=\"manageApi\"; power=\"AdminPower.list").append(name).append("\"]\n");
            code.append("\t[bind:value=\"BasicServer.List").append(name).append("\"]\n");
            code.append("\tMgList").append(name).append("Resp MgList").append(name).append("(MgList").append(name).append("Req req) = 1 \n\n");
        }
        if (checkAdd.getCheckBox().isSelected()) {
            code.append("\t[tag:auth=\"manageApi\"; power=\"AdminPower.add").append(name).append("\"]\n");
            code.append("\t[bind:value=\"BasicServer.Add").append(name).append("\"]\n");
            code.append("\tMgAdd").append(name).append("Resp MgAdd").append(name).append("(MgAdd").append(name).append("Req req) = 2  \n\n");
        }
        if (checkGet.getCheckBox().isSelected()) {
            code.append("\t[tag:auth=\"manageApi\"; power=\"AdminPower.get").append(name).append("\",\"AdminPower.set").append(name).append("\"]\n");
            code.append("\t[bind:value=\"BasicServer.Get").append(name).append("\"]\n");
            code.append("\tMgGet").append(name).append("Resp MgGet").append(name).append("(MgGet").append(name).append("Req req) = 3  \n\n");
        }
        if (checkSet.getCheckBox().isSelected()) {
            code.append("\t[tag:auth=\"manageApi\"; power=\"AdminPower.set").append(name).append("\"]\n");
            code.append("\t[bind:value=\"BasicServer.set").append(name).append("\"]\n");
            code.append("\tMgSet").append(name).append("Resp MgSet").append(name).append("(MgSet").append(name).append("Req req) = 4  \n\n");
        }
        if (checkDel.getCheckBox().isSelected()) {
            code.append("\t[tag:auth=\"manageApi\"; power=\"AdminPower.del").append(name).append("\"]\n");
            code.append("\t[bind:value=\"BasicServer.Del").append(name).append("\"]\n");
            code.append("\tMgDel").append(name).append("Resp MgDel").append(name).append("(MgDel").append(name).append("Req req) = 5  \n\n");
        }
        if (checkStatus.getCheckBox().isSelected()) {
            code.append("\t[tag:auth=\"manageApi\"; power=\"AdminPower.status").append(name).append("\"]\n");
            code.append("\t[bind:value=\"BasicServer.Status").append(name).append("\"]\n");
            code.append("\tMgStatus").append(name).append("Resp MgStatus").append(name).append("(MgStatus").append(name).append("Req req) = 6  \n\n");
        }
        if (checkExport.getCheckBox().isSelected()) {
            code.append("\t[tag:auth=\"manageApi\"; power=\"AdminPower.export").append(name).append("\"]\n");
            code.append("\t[bind:value=\"BasicServer.Export").append(name).append("\"]\n");
            code.append("\tMgExport").append(name).append("Resp MgExport").append(name).append("(MgExport").append(name).append("Req req) = 7  \n\n");
        }

        code.append("}\n").append("```\n\n");
        return code;
    }


    private StringBuilder generateHbufPowerCode(String name) {
        StringBuilder code = new StringBuilder();
        code.append("### Hbuf Power Code\n")
                .append("```hbuf\n")
                .append("enum AdminPower{\n");
        if (checkList.getCheckBox().isSelected()) {
            code.append("\tlist").append(name).append(" = 1 \n\n");
        }
        if (checkAdd.getCheckBox().isSelected()) {
            code.append("\tadd").append(name).append(" = 2  \n\n");
        }
        if (checkGet.getCheckBox().isSelected()) {
            code.append("\tget").append(name).append(" = 3  \n\n");
        }
        if (checkSet.getCheckBox().isSelected()) {
            code.append("\tset").append(name).append(" = 4  \n\n");
        }
        if (checkDel.getCheckBox().isSelected()) {
            code.append("\tdel").append(name).append(" = 5  \n\n");
        }
        if (checkStatus.getCheckBox().isSelected()) {
            code.append("\tstatus").append(name).append(" = 6  \n\n");
        }
        if (checkExport.getCheckBox().isSelected()) {
            code.append("\texport").append(name).append(" = 7  \n\n");
        }

        code.append("}\n").append("```\n\n");
        return code;
    }

    private StringBuilder generateGoServerCode(String name) {
        StringBuilder code = new StringBuilder();
        code.append("### Golang Server Code\n")
                .append("```go\n");

        if (checkList.getCheckBox().isSelected()) {
            code.append("(s *Server) List").append(name).append("(ctx context.Context, req *List").append(name).append("Req) (*List").append(name).append("Resp, error) {\n");
            code.append("\treturn &List").append(name).append("{}, nil\n");
            code.append("}\n\n");
        }
        if (checkAdd.getCheckBox().isSelected()) {
            code.append("(s *Server) Add").append(name).append("(ctx context.Context, req *Add").append(name).append("Req) (*Add").append(name).append("Resp, error) {\n");
            code.append("\treturn &Add").append(name).append("{}, nil\n");
            code.append("}\n\n");
        }
        if (checkGet.getCheckBox().isSelected()) {
            code.append("(s *Server) Get").append(name).append("(ctx context.Context, req *Get").append(name).append("Req) (*Get").append(name).append("Resp, error) {\n");
            code.append("\treturn &Get").append(name).append("{}, nil\n");
            code.append("}\n\n");
        }
        if (checkSet.getCheckBox().isSelected()) {
            code.append("(s *Server) Set").append(name).append("(ctx context.Context, req *Set").append(name).append("Req) (*Set").append(name).append("Resp, error) {\n");
            code.append("\treturn &Set").append(name).append("{}, nil\n");
            code.append("}\n\n");
        }
        if (checkDel.getCheckBox().isSelected()) {
            code.append("(s *Server) Del").append(name).append("(ctx context.Context, req *Del").append(name).append("Req) (*Del").append(name).append("Resp, error) {\n");
            code.append("\treturn &Del").append(name).append("{}, nil\n");
            code.append("}\n\n");
        }
        if (checkStatus.getCheckBox().isSelected()) {
            code.append("(s *Server) Status").append(name).append("(ctx context.Context, req *Status").append(name).append("Req) (*Status").append(name).append("Resp, error) {\n");
            code.append("\treturn &Status").append(name).append("{}, nil\n");
            code.append("}\n\n");
        }
        if (checkExport.getCheckBox().isSelected()) {
            code.append("(s *Server) Export").append(name).append("(ctx context.Context, req *Export").append(name).append("Req) (*Export").append(name).append("Resp, error) {\n");
            code.append("\treturn &Export").append(name).append("{}, nil\n");
            code.append("}\n\n");
        }
        code.append("```\n\n");
        return code;
    }

    private StringBuilder generateVueListViewCode(String name) {
        StringBuilder code = new StringBuilder();
        code.append("### Vue List Page Code\n")
                .append("```vue\n")
                .append(
                        "<script setup lang=\"ts\">\n" +
                                "\n" +
                                "</script>\n" +
                                "\n" +
                                "<template>\n" +
                                "\n" +
                                "</template>\n" +
                                "\n" +
                                "<style scoped>\n" +
                                "\n" +
                                "</style>\n"
                );

        code.append("```\n\n");
        return code;
    }

    private StringBuilder generateVueInfoViewCode(String name) {
        StringBuilder code = new StringBuilder();
        code.append("### Vue Info Page Code\n")
                .append("```vue\n").append(
                        "<script setup lang=\"ts\">\n" +
                                "\n" +
                                "</script>\n" +
                                "\n" +
                                "<template>\n" +
                                "\n" +
                                "</template>\n" +
                                "\n" +
                                "<style scoped>\n" +
                                "\n" +
                                "</style>\n"
                );

        code.append("```\n\n");
        return code;
    }

    private StringBuilder generateVueRouteCode(String name) {
        StringBuilder code = new StringBuilder();
        code.append("### Vue Route Code\n")
                .append("```vue\n")
                .append(
                        "<script setup lang=\"ts\">\n" +
                                "\n" +
                                "</script>\n" +
                                "\n" +
                                "<template>\n" +
                                "\n" +
                                "</template>\n" +
                                "\n" +
                                "<style scoped>\n" +
                                "\n" +
                                "</style>\n"
                );
        code.append("```\n\n");
        return code;
    }
}

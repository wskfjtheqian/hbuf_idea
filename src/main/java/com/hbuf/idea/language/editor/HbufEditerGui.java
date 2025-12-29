package com.hbuf.idea.language.editor;

import com.hbuf.idea.language.psi.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.TextEditorWithPreview;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.ui.OnePixelSplitter;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.intellij.plugins.markdown.lang.MarkdownFileType;
import org.intellij.plugins.markdown.ui.preview.MarkdownEditorWithPreview;
import org.intellij.plugins.markdown.ui.preview.MarkdownSplitEditorProvider;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;


public class HbufEditerGui extends JPanel {
    private static final Logger log = LoggerFactory.getLogger(HbufEditerGui.class);
    private final HbufFile file;
    private final Project project;
    private static final VelocityEngine engine;

    static {
        Properties props = new Properties();
        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        engine = new VelocityEngine(props);
        engine.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        engine.setProperty("file.resource.loader.unicode", "true");
        engine.init();
    }

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
        mdEditor.setLayout(TextEditorWithPreview.Layout.SHOW_PREVIEW);

        CollapsibleCheckBoxGroup group = new CollapsibleCheckBoxGroup(true);
        group.addPanel(this.checkList = bindEvent(new CollapsibleCheckBoxPanel("List", new JBLabel(""), true)));
        group.addPanel(this.checkExport = bindEvent(new CollapsibleCheckBoxPanel("Export", new JBLabel(""), true)));
        group.addPanel(this.checkAdd = bindEvent(new CollapsibleCheckBoxPanel("Add", new JBLabel(""), true)));
        group.addPanel(this.checkGet = bindEvent(new CollapsibleCheckBoxPanel("Get", new JBLabel(""), true)));
        group.addPanel(this.checkSet = bindEvent(new CollapsibleCheckBoxPanel("Set", new JBLabel(""), true)));
        group.addPanel(this.checkDel = bindEvent(new CollapsibleCheckBoxPanel("Del", new JBLabel(""), true)));
        group.addPanel(this.checkStatus = bindEvent(new CollapsibleCheckBoxPanel("Status", new JBLabel(""), true)));


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
        code.append(generateHbufIdCode(listSelected));
        code.append(generateGoServerCode(listSelected));
        code.append(generateGoManageCode(listSelected));
        code.append(generateVueListViewCode(listSelected));
        code.append(generateVueInfoViewCode(listSelected));
        code.append(generateVueRouteCode(listSelected));
        code.append(generateVueLangCode(listSelected));
        code.append(generateSqlCode(listSelected));

        ApplicationManager.getApplication().runWriteAction(() -> {
            String fixedTemplate = StringUtil.convertLineSeparators(code.toString());
            mdEditor.getEditor().getDocument().setText(fixedTemplate);
        });
    }

    private @NotNull VelocityContext getVelocityContext(String name) {
        VelocityContext context = new VelocityContext();
        context.put("util", new VelocityUtil());
        this.addPackageName(context);
        this.addDataInfo(context, name);
        context.put("dataName", name);
        context.put("genList", checkList.getCheckBox().isSelected());
        context.put("genAdd", checkAdd.getCheckBox().isSelected());
        context.put("genGet", checkGet.getCheckBox().isSelected());
        context.put("genSet", checkSet.getCheckBox().isSelected());
        context.put("genDel", checkDel.getCheckBox().isSelected());
        context.put("genStatus", checkStatus.getCheckBox().isSelected());
        context.put("genExport", checkExport.getCheckBox().isSelected());
        return context;
    }

    private void addDataInfo(VelocityContext context, String name) {
        @NotNull Collection<HbufDataElement> dataElements = PsiTreeUtil.findChildrenOfAnyType(file, HbufDataElement.class);
        HbufDataElement dataElement = null;
        for (HbufDataElement element : dataElements) {
            if (element.getName().equals(name)) {
                dataElement = element;
                break;
            }
        }
        if (dataElement == null) {
            return;
        }

        @Nullable PsiElement comment = dataElement.getComment();
        if (comment != null) {
            context.put("comment", comment.getText().replaceFirst("//", "").trim());
        }

        ArrayList<HbufDataField> fields = new ArrayList<>();
        for (HbufDataFieldElement element : dataElement.getFields()) {
            @NotNull Collection<HbufAnnotationElement> annotations = PsiTreeUtil.findChildrenOfAnyType(element, HbufAnnotationElement.class);
            boolean hasDbIndex = false;
            for (HbufAnnotationElement annotation : annotations) {
                if ("db".equals(annotation.getName())) {
                    for (HbufAnnotationFieldElement fieldElement : annotation.getFields()) {
                        if ("key".equals(fieldElement.getIdentName().getName())) {
                            for (String value : fieldElement.getValues()) {
                                if ("\"true\"".equals(value)) {
                                    context.put("dbIndex", element.getName());
                                    hasDbIndex = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            HbufDataField field = new HbufDataField();
            if (hasDbIndex) {
                field.setName("id");
            } else {
                field.setName(element.getName());
            }
            field.setType(element.getTypeStatement().getText());

            comment = element.getComment();
            if (comment != null) {
                field.setComment(comment.getText().replaceFirst("//", "").trim());
            } else {
                field.setComment("");
            }

            field.setSqlType(getSqlType(field.getType()));
            field.setDefaultValue(getDefaultValue(field.getType()));

            fields.add(field);
        }

        context.put("fields", fields);
    }

    private String getDefaultValue(String type) {
        type = type.replace("?", "");
        return switch (type) {
            case "int8", "int16", "int32", "uint8", "uint16", "int64", "uint32", "uint64", "float", "double",
                 "decimal" -> "NOT NULL DEFAULT 0";
            case "bool" -> "NOT NULL DEFAULT false";
            default -> "NULL";
        };
    }

    private String getSqlType(String type) {
        type = type.replace("?", "");
        return switch (type) {
            case "int8" -> "INT8";
            case "int16", "int32", "uint8", "uint16" -> "INT";
            case "int64", "uint32", "uint64" -> "BIGINT";
            case "bool" -> "BOOLEAN";
            case "float" -> "FLOAT";
            case "double" -> "DOUBLE";
            case "bytes" -> "BLOB";
            case "string" -> "VARCHAR(200)";
            case "date" -> "TIMESTAMP(3)";
            case "decimal" -> "DECIMAL(22,6)";
            default -> "TEXT";
        };
    }

    private void addPackageName(VelocityContext context) {
        @NotNull Collection<HbufPackageElement> elements = PsiTreeUtil.findChildrenOfAnyType(file, HbufPackageElement.class);
        for (HbufPackageElement element : elements) {
            @NonNls String lang = element.getIdent().getText();
            @NlsSafe String pkg = element.getString().getText().replace("\"", "");
            context.put(lang + "Package", pkg);
        }
    }

    private String evaluateVelocity(VelocityContext context, String templateName) {
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(Objects.requireNonNull(HbufEditerGui.class.getResourceAsStream("/templates/" + templateName)), StandardCharsets.UTF_8);
            StringWriter writer = new StringWriter();
            engine.evaluate(context, writer, templateName, reader);
            return writer.toString();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private StringBuilder generateHbufServerCode(String name) {
        VelocityContext context = getVelocityContext(name);

        StringBuilder code = new StringBuilder();
        code.append("### Hbuf Server Code\n").append("```hbuf\n");
        code.append(evaluateVelocity(context, "Server.hbuf.vm"));
        code.append("```\n\n");
        return code;
    }

    private StringBuilder generateHbufManageCode(String name) {
        VelocityContext context = getVelocityContext(name);

        StringBuilder code = new StringBuilder();
        code.append("### Hbuf Manage Code\n").append("```hbuf\n");
        code.append(evaluateVelocity(context, "Manage.hbuf.vm"));
        code.append("```\n\n");
        return code;
    }


    private StringBuilder generateHbufPowerCode(String name) {
        VelocityContext context = getVelocityContext(name);

        StringBuilder code = new StringBuilder();
        code.append("### Hbuf Power Code\n").append("```hbuf\n");
        code.append(evaluateVelocity(context, "Power.hbuf.vm"));
        code.append("```\n\n");
        return code;
    }

    private StringBuilder generateHbufIdCode(String name) {
        VelocityContext context = getVelocityContext(name);

        StringBuilder code = new StringBuilder();
        code.append("### Hbuf Id Code\n").append("```hbuf\n");
        code.append(evaluateVelocity(context, "Id.hbuf.vm"));
        code.append("```\n\n");
        return code;
    }

    private StringBuilder generateGoServerCode(String name) {
        VelocityContext context = getVelocityContext(name);

        StringBuilder code = new StringBuilder();
        code.append("### Golang Server Code\n").append("```go\n");
        code.append(evaluateVelocity(context, "Server.go.vm"));
        code.append("```\n\n");
        return code;
    }

    private StringBuilder generateGoManageCode(String name) {
        VelocityContext context = getVelocityContext(name);

        StringBuilder code = new StringBuilder();
        code.append("### Golang Manage Code\n").append("```go\n");
        code.append(evaluateVelocity(context, "Manage.go.vm"));
        code.append("```\n\n");
        return code;
    }


    private StringBuilder generateVueListViewCode(String name) {
        VelocityContext context = getVelocityContext(name);

        StringBuilder code = new StringBuilder();
        code.append("### Vue ListPage Code\n").append("```vue\n");
        code.append(evaluateVelocity(context, "ListPage.vue.vm"));
        code.append("```\n\n");
        return code;
    }


    private StringBuilder generateVueInfoViewCode(String name) {
        VelocityContext context = getVelocityContext(name);

        StringBuilder code = new StringBuilder();
        code.append("### Vue InfoPage Code\n").append("```vue\n");
        code.append(evaluateVelocity(context, "InfoPage.vue.vm"));
        code.append("```\n\n");
        return code;
    }

    private StringBuilder generateVueRouteCode(String name) {
        VelocityContext context = getVelocityContext(name);

        StringBuilder code = new StringBuilder();
        code.append("### Vue ListInfo Code\n").append("```ts\n");
        code.append(evaluateVelocity(context, "Router.ts.vm"));
        code.append("```\n\n");
        return code;
    }

    private StringBuilder generateVueLangCode(String name) {
        VelocityContext context = getVelocityContext(name);

        StringBuilder code = new StringBuilder();
        code.append("### Vue Lang Code\n").append("```json\n");
        code.append(evaluateVelocity(context, "Lang.json.vm"));
        code.append("```\n\n");
        return code;
    }

    private StringBuilder generateSqlCode(String name) {
        VelocityContext context = getVelocityContext(name);

        StringBuilder code = new StringBuilder();
        code.append("### Create Table SQL Code\n").append("```sql\n");
        code.append(evaluateVelocity(context, "Sql.sql.vm"));
        code.append("```\n\n");
        return code;
    }

}

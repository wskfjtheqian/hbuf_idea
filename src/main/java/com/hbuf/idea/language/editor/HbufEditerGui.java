package com.hbuf.idea.language.editor;

import javax.swing.*;

public class HbufEditerGui extends JPanel {
    private JPanel panel1;
    private JList list1;
    private JCheckBox listCheckBox;
    private JCheckBox addCheckBox;
    private JCheckBox getCheckBox;
    private JCheckBox editCheckBox;
    private JCheckBox deleteCheckBox;
    private JCheckBox statusCheckBox;
    private JCheckBox exportCheckBox;

    public HbufEditerGui() {
        add(panel1);
        //设置布局为填充父容器
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }


}

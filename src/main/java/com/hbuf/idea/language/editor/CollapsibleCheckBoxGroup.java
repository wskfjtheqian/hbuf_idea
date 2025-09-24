package com.hbuf.idea.language.editor;

import com.intellij.ui.components.JBScrollPane;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CollapsibleCheckBoxGroup extends JPanel {
    private final boolean singleExpand;
    private final List<CollapsibleCheckBoxPanel> panels = new ArrayList<>();

    public CollapsibleCheckBoxGroup(boolean singleExpand) {
        super();
        this.singleExpand = singleExpand;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addPanel(CollapsibleCheckBoxPanel panel) {
        panels.add(panel);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (singleExpand) {
            panel.getCheckBox().addActionListener(e -> {
                if (panel.getContent().isVisible()) {
                    for (CollapsibleCheckBoxPanel p : panels) {
                        if (p != panel) p.getContent().setVisible(false);
                    }
                }
            });
        }

        add(panel);
    }

    public JScrollPane withScroll() {
        JBScrollPane scrollPane = new JBScrollPane(this);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }
}

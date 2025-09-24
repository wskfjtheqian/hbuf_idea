package com.hbuf.idea.language.editor;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;

public class CollapsibleCheckBoxPanel extends JPanel {
    private final JBCheckBox checkBox;
    private final JPanel content;
    private boolean expanded;
    private Timer animationTimer;
    private int animationStep;
    private int targetHeight;

    public CollapsibleCheckBoxPanel(String title, JComponent inner, boolean expanded) {
        super(new BorderLayout());
        this.expanded = expanded;

        checkBox = new JBCheckBox(title, expanded);
        checkBox.setOpaque(false);

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(JBUI.Borders.empty(2, 4));
        header.setBackground(getHeaderBackground(expanded));
        header.add(checkBox, BorderLayout.WEST);

        content = new JPanel(new BorderLayout());
        content.add(inner, BorderLayout.CENTER);
        content.setBackground(UIUtil.getPanelBackground());
        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

        setAlignmentX(Component.LEFT_ALIGNMENT);

        content.setVisible(expanded);
        content.setPreferredSize(new Dimension(content.getPreferredSize().width, expanded ? inner.getPreferredSize().height : 0));

        checkBox.addActionListener(e -> toggle());
    }

    private Color getHeaderBackground(boolean expanded) {
        return expanded ? UIUtil.getPanelBackground().brighter() : UIUtil.getPanelBackground();
    }

    private void toggle() {
        if (animationTimer != null && animationTimer.isRunning()) return;

        expanded = !expanded;
        checkBox.getParent().setBackground(getHeaderBackground(expanded));

        int startHeight = content.getHeight();
        targetHeight = expanded ? content.getComponent(0).getPreferredSize().height : 0;
        int steps = 10;
        animationStep = (targetHeight - startHeight) / steps;

        animationTimer = new Timer(15, e -> {
            int newHeight = content.getHeight() + animationStep;
            if ((animationStep > 0 && newHeight >= targetHeight) || (animationStep < 0 && newHeight <= targetHeight)) {
                newHeight = targetHeight;
                animationTimer.stop();
            }
            content.setPreferredSize(new Dimension(content.getWidth(), newHeight));
            content.revalidate();
            content.repaint();
        });
        animationTimer.start();
    }

    public JBCheckBox getCheckBox() {
        return checkBox;
    }

    public JPanel getContent() {
        return content;
    }
}

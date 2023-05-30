/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomizedClasses;

/**
 *
 * @author Aesthetics
 */

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Objects;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

/**
 *
 * @author Raven
 */
public class ScrollPaneWin11 extends JScrollPane {

    private int scrollSpeed = 1; // default scroll speed is 1

    public ScrollPaneWin11() {
        getVerticalScrollBar().setUI(new ScrollBarWin11UI());
        getHorizontalScrollBar().setUI(new ScrollBarWin11UI());
        setLayout(new ScrollLayout());

        // Add mouse wheel listener to adjust scroll speed
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int rotation = e.getWheelRotation();
                int amount = e.getScrollAmount();

                // Multiply scroll amount by scroll speed
                getVerticalScrollBar().setValue(getVerticalScrollBar().getValue() + (amount * rotation * scrollSpeed));
            }
        });
    }

    /**
     * Sets the scroll speed for mouse wheel scrolling.
     * @param speed The scroll speed to set.
     */
    public void setScrollSpeed(int speed) {
        this.scrollSpeed = speed;
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    @Override
    public void updateUI() {
        super.updateUI();
        EventQueue.invokeLater(() -> {
            setComponentZOrder(getVerticalScrollBar(), 0);
            setComponentZOrder(getHorizontalScrollBar(), 1);
            setComponentZOrder(getViewport(), 2);
            getVerticalScrollBar().setOpaque(false);
            getHorizontalScrollBar().setOpaque(false);
        });
    }

    private class ScrollLayout extends ScrollPaneLayout {

        @Override
        public void layoutContainer(Container parent) {
            super.layoutContainer(parent);
            if (parent instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) parent;
                Rectangle rec = scroll.getViewport().getBounds();
                Insets insets = parent.getInsets();
                int rhHeight = 0;
                if (scroll.getColumnHeader() != null) {
                    Rectangle rh = scroll.getColumnHeader().getBounds();
                    rhHeight = rh.height;
                }
                rec.width = scroll.getBounds().width - (insets.left + insets.right);
                rec.height = scroll.getBounds().height - (insets.top + insets.bottom) - rhHeight;
                if (Objects.nonNull(viewport)) {
                    viewport.setBounds(rec);
                }
                if (!Objects.isNull(hsb)) {
                    Rectangle hrc = hsb.getBounds();
                    hrc.width = rec.width;
                    hsb.setBounds(hrc);
                }
            }
        }
    }
}
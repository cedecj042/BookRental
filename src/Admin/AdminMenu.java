
package Admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import static javax.swing.SwingConstants.CENTER;


public class AdminMenu extends javax.swing.JPanel {
    private ActionListener act;

    public void setAct(ActionListener act) {
        if (act != null) {
            this.act = act;
        }
    }

    private ImageIcon activeIcon,inactiveIcon;

    public ImageIcon getActiveIcon() {
        return activeIcon;
    }

    public void setActiveIcon(ImageIcon activeIcon) {
        this.activeIcon = activeIcon;
    }

    public ImageIcon getInactiveIcon() {
        return inactiveIcon;
    }

    public void setInactiveIcon(ImageIcon inactiveIcon) {
        this.inactiveIcon = inactiveIcon;
    }
    
    
    public void setClicked(Color color1, Color color2,Icon icon){
        this.setBackground(color1);
        this.border.setBackground(color2);
        this.lbName.setForeground(color2);
        this.lbIcon.setIcon(icon);
    }
    public void setDefault(Color color,Icon icon){
        this.setBackground(Color.white);
        this.border.setBackground(Color.white);
        this.lbName.setForeground(color);
        this.lbIcon.setIcon(icon);
    }

    
    public AdminMenu(Icon icon, String menuName,ActionListener act) {
        initComponents();
        lbIcon.setIcon(icon);
        lbName.setText(menuName);
        lbIcon.setHorizontalAlignment(CENTER);
        lbIcon.setHorizontalTextPosition(CENTER);
        this.setSize(new Dimension(Integer.MAX_VALUE, 70));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        this.setMinimumSize(new Dimension(Integer.MAX_VALUE, 70));
        if (act != null) {
            this.act = act;
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();
        border = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbName.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lbName.setText("Menu Name Here ...");
        add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 210, 70));
        add(lbIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 40, 70));

        border.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout borderLayout = new javax.swing.GroupLayout(border);
        border.setLayout(borderLayout);
        borderLayout.setHorizontalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        borderLayout.setVerticalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        add(border, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 0, 5, 70));
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        
        if (act != null) {
            act.actionPerformed(null);
        }
    }//GEN-LAST:event_formMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel border;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}

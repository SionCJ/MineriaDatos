/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineriadatos;

/**
 *
 * @author Sion
 */
public class DialogClasificador extends javax.swing.JDialog {
    Ventana padre;
    
    public DialogClasificador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        padre = (Ventana) parent;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmboxPodado = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        spinConfFactor = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        spinMinInst = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        spinSemilla = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Opciones clasificador");
        setPreferredSize(new java.awt.Dimension(270, 190));
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5));

        jLabel1.setText("Tipo de arbol");
        jLabel1.setPreferredSize(new java.awt.Dimension(95, 14));
        getContentPane().add(jLabel1);

        cmboxPodado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "podado", "sin podar" }));
        cmboxPodado.setPreferredSize(new java.awt.Dimension(70, 20));
        getContentPane().add(cmboxPodado);

        jLabel3.setText("Factor de confianza");
        getContentPane().add(jLabel3);

        spinConfFactor.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.25f), Float.valueOf(0.0f), Float.valueOf(1.0f), Float.valueOf(0.05f)));
        spinConfFactor.setPreferredSize(new java.awt.Dimension(70, 20));
        getContentPane().add(spinConfFactor);

        jLabel4.setText("Instancias minimas");
        jLabel4.setPreferredSize(new java.awt.Dimension(95, 14));
        getContentPane().add(jLabel4);

        spinMinInst.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(2), Integer.valueOf(1), null, Integer.valueOf(1)));
        spinMinInst.setPreferredSize(new java.awt.Dimension(70, 20));
        getContentPane().add(spinMinInst);

        jLabel5.setText("semilla");
        jLabel5.setPreferredSize(new java.awt.Dimension(95, 14));
        getContentPane().add(jLabel5);

        spinSemilla.setPreferredSize(new java.awt.Dimension(70, 20));
        getContentPane().add(spinSemilla);

        jLabel2.setPreferredSize(new java.awt.Dimension(200, 20));
        getContentPane().add(jLabel2);

        jButton1.setText("Default");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        boolean podado = cmboxPodado.getSelectedIndex()==0;
        float factor = (float) spinConfFactor.getValue();
        int minInstancias = (int) spinMinInst.getValue();
        int semilla = (int) spinSemilla.getValue();
        padre.setOpcionesClasificador(new OpcionesClasificador(podado, factor, minInstancias, semilla) );
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cmboxPodado.setSelectedIndex(0);
        spinConfFactor.setValue( (float)0.25 );
        spinMinInst.setValue(2);
        spinSemilla.setValue(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogClasificador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogClasificador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogClasificador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogClasificador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogClasificador dialog = new DialogClasificador(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JComboBox cmboxPodado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSpinner spinConfFactor;
    private javax.swing.JSpinner spinMinInst;
    private javax.swing.JSpinner spinSemilla;
    // End of variables declaration//GEN-END:variables
}

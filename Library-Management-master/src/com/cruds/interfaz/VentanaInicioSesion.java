package com.cruds.interfaz;

import com.cruds.bd.AdministradorDAO;
import com.cruds.modelo.Administrador;
import javax.swing.JOptionPane;


public class VentanaInicioSesion extends javax.swing.JFrame {

    public VentanaInicioSesion() {
        // Usa la imagen de la biblioteca como fondo (antes de crear los componentes)
        setContentPane(new PanelFondo("/com/cruds/imagenes/fondo.png"));
        ((PanelFondo) getContentPane()).setVelo(140); // capa clara para que se lean los textos
        initComponents();
        // Panel transparente para que se vea la imagen detrás
        panelInicioSesion.setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiquetaBienvenida = new javax.swing.JLabel();
        panelInicioSesion = new javax.swing.JPanel();
        etiquetaAdmin = new javax.swing.JLabel();
        etiquetaContrasena = new javax.swing.JLabel();
        txtIdAdmin = new javax.swing.JTextField();
        btnIniciarSesion = new javax.swing.JButton();
        txtContrasena = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bienvenido a la Biblioteca SIT");

        etiquetaBienvenida.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        etiquetaBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaBienvenida.setText("Bienvenido a la Biblioteca SIT");

        etiquetaAdmin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        etiquetaAdmin.setText("ID de administrador:");

        etiquetaContrasena.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        etiquetaContrasena.setText("Contraseña:");

        txtIdAdmin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIdAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAdminActionPerformed(evt);
            }
        });

        btnIniciarSesion.setBackground(new java.awt.Color(153, 153, 255));
        btnIniciarSesion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnIniciarSesion.setText("Iniciar sesión");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        txtContrasena.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelInicioSesionLayout = new javax.swing.GroupLayout(panelInicioSesion);
        panelInicioSesion.setLayout(panelInicioSesionLayout);
        panelInicioSesionLayout.setHorizontalGroup(
            panelInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInicioSesionLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(panelInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etiquetaAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(panelInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(txtContrasena))
                .addGap(167, 167, 167))
            .addGroup(panelInicioSesionLayout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInicioSesionLayout.setVerticalGroup(
            panelInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioSesionLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(panelInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etiquetaAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIdAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(panelInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(etiquetaBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(panelInicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(etiquetaBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelInicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        panelInicioSesion.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdAdminActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        String idAdmin = txtIdAdmin.getText().trim();
        String contrasena = String.valueOf(txtContrasena.getPassword()).trim();
        if(idAdmin.length() == 0 || contrasena.length() == 0)
        {
            JOptionPane.showMessageDialog(panelInicioSesion, "Por favor ingrese el ID y la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        AdministradorDAO administradorDAO = new AdministradorDAO();
        if(administradorDAO.esAdministradorValido(new Administrador(idAdmin, contrasena)))
        {
            sesionIniciada = true;
            System.out.println("Inicio de sesión correcto");
            new VentanaPrincipal().setVisible(true);
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(panelInicioSesion, "¡Inicio de sesión inválido!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaInicioSesion LF = new VentanaInicioSesion();
                LF.setVisible(true);
                
            }
        });
    }
    
    public static boolean sesionIniciada = false;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel etiquetaAdmin;
    private javax.swing.JLabel etiquetaContrasena;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JLabel etiquetaBienvenida;
    private javax.swing.JPanel panelInicioSesion;
    private javax.swing.JTextField txtIdAdmin;
    private javax.swing.JPasswordField txtContrasena;
    // End of variables declaration//GEN-END:variables
}

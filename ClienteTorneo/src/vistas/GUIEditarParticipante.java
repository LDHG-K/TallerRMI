/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Models.Competitor;
import Services.Interfaces.IServiceCompetitor;
import controllers.CompetitorController;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Luis
 */
public class GUIEditarParticipante extends javax.swing.JFrame {

   private IServiceCompetitor servicioCompetidor;
   private CompetitorController competitorController;
   private Competitor participante;
   
   /**
     * Creates new form GUIEditarParticipante
     */
    public GUIEditarParticipante(IServiceCompetitor ser) {
        initComponents();
        servicioCompetidor = ser;
        setLocationRelativeTo(null);
        competitorController = new CompetitorController(ser);
        
        txtApodo.setEnabled(false);
       jDateChooser1.setEnabled(false);
       jDateChooser2.setEnabled(false);
       btnAdicionar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtApodo = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnAdicionar1 = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Participante");

        jLabel2.setText("Fecha Ingreso: ");

        jLabel3.setText("Fecha Caducidad:");

        btnAdicionar.setText("Editar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        jLabel1.setText("Apodo: ");

        btnAdicionar1.setText("Buscar");
        btnAdicionar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionar1ActionPerformed(evt);
            }
        });

        jLabel4.setText("ID:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(btnAdicionar1)
                                .addGap(93, 93, 93)
                                .addComponent(btnAdicionar)
                                .addGap(0, 63, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtID))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtApodo, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtApodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnAdicionar1))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        
        String apodo = txtApodo.getText();
        
        Date fechaInscripcion = jDateChooser1.getDate(); //ic es la interfaz, jDate el JDatechooser
        long d = fechaInscripcion.getTime(); //guardamos en un long el tiempo
        java.sql.Date fecha1 = new java.sql.Date(d);// parseamos al formato del sql
        
        Date fechaExpiracion = jDateChooser2.getDate(); //ic es la interfaz, jDate el JDatechooser
        long e = fechaExpiracion.getTime(); //guardamos en un long el tiempo
        java.sql.Date fecha2 = new java.sql.Date(e);// parseamos al formato del sql
        
        
       try {
           competitorController.editarParticipante(new Competitor(Long.parseLong(txtID.getText()),apodo,fecha1,fecha2));
           btnAdicionar.setEnabled(false);
           JOptionPane.showMessageDialog(this, "El competidor ha sido adicionado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
       } catch (RemoteException ex) {
           JOptionPane.showMessageDialog(this, "El competidor no pudo ser adicionado" + ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
           //Logger.getLogger(GUIEditarParticipante.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        
       
       txtApodo.setText("");
       txtApodo.setEnabled(false);
       
       jDateChooser1.setDate(null);
       jDateChooser1.setEnabled(false);
       jDateChooser2.setDate(null);
       jDateChooser2.setEnabled(false);
       txtID.setEnabled(true);
        txtApodo.grabFocus();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAdicionar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionar1ActionPerformed
         Long id = Long.parseLong(txtID.getText());
       try {
           participante = competitorController.buscarParticipante(id);
            txtApodo.setEnabled(true);
            jDateChooser1.setEnabled(true);
            jDateChooser2.setEnabled(true);
            btnAdicionar.setEnabled(true);
       } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "El participante no fue encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
       
         //  Logger.getLogger(GUIEditarParticipante.class.getName()).log(Level.SEVERE, null, ex);
       } catch (Exception ex) {
       //    Logger.getLogger(GUIEditarParticipante.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       txtApodo.setText(participante.getApodo());
       txtApodo.setEnabled(true);
       
       jDateChooser1.setDate(participante.getFechaInscripcion());
       jDateChooser1.setEnabled(true);
       jDateChooser2.setDate(participante.getFechaCaducidad());
       jDateChooser2.setEnabled(true);
       txtID.setEnabled(false);
    }//GEN-LAST:event_btnAdicionar1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAdicionar1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtApodo;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}

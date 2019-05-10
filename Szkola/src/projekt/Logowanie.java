/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import hibernate.NauczycieleQuery;
import hibernate.Uczniowie;
import hibernate.UczniowieQuery;

/**
 *
 * @author Natalia
 */
public class Logowanie extends javax.swing.JFrame {

    private UczniowieQuery queryUczen;
    private NauczycieleQuery queryNauczyciel;
    
    public Logowanie() {
        setTitle("Logowanie");
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

        wybNauczyciel = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        Zaloguj = new javax.swing.JButton();
        haslo = new javax.swing.JPasswordField();
        UczenRB = new javax.swing.JRadioButton();
        NauczycielRB = new javax.swing.JRadioButton();
        alert = new javax.swing.JLabel();

        wybNauczyciel.setText("Uczeń");
        wybNauczyciel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wybNauczycielActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID: ");

        jLabel2.setText("Hasło: ");

        Zaloguj.setText("Zaloguj");
        Zaloguj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZalogujActionPerformed(evt);
            }
        });

        UczenRB.setText("Uczeń");
        UczenRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UczenRBActionPerformed(evt);
            }
        });

        NauczycielRB.setText("Nauczyciel");
        NauczycielRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NauczycielRBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(alert)
                            .addComponent(UczenRB))
                        .addGap(18, 18, 18)
                        .addComponent(NauczycielRB)
                        .addGap(32, 32, 32)
                        .addComponent(Zaloguj))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ID, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(haslo))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(haslo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UczenRB)
                    .addComponent(NauczycielRB)
                    .addComponent(Zaloguj))
                .addGap(30, 30, 30)
                .addComponent(alert)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wybNauczycielActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wybNauczycielActionPerformed
        
    }//GEN-LAST:event_wybNauczycielActionPerformed

    private void UczenRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UczenRBActionPerformed
       if(evt.getSource().equals(UczenRB)){
           NauczycielRB.setSelected(false);
       }
    }//GEN-LAST:event_UczenRBActionPerformed

    private void ZalogujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZalogujActionPerformed
if(evt.getSource().equals(Zaloguj))  {
    alert.setText("");
    String id = ID.getText();
    char[] passw = haslo.getPassword();
    String password = new String(passw);
    queryNauczyciel = new NauczycieleQuery();
    queryUczen = new UczniowieQuery();
    
    if(!(id.equals("")) && !(password.equals("")) && (NauczycielRB.isSelected() || UczenRB.isSelected() ) ){
        if(UczenRB.isSelected()){
            boolean test  = queryUczen.selecyByIDandPassword(id, password);
            
            if(test){
                Uczniowie u = queryUczen.selectByIDandPassword(id, password);
                new Uczen().setVisible(true);
                this.setVisible(false);
            }else{
                alert.setText("Zły id lub hasło");
            }
        }
       if(NauczycielRB.isSelected()){
           new Nauczyciel().setVisible(true);
           this.setVisible(false);
       }
       else{
           alert.setText("Zły id lub hasło");
       }
    }else{
        alert.setText("Błąd");
    }

}     
    }//GEN-LAST:event_ZalogujActionPerformed

    private void NauczycielRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NauczycielRBActionPerformed
       if(evt.getSource().equals(NauczycielRB)){
           UczenRB.setSelected(false);
       }
    }//GEN-LAST:event_NauczycielRBActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID;
    private javax.swing.JRadioButton NauczycielRB;
    private javax.swing.JRadioButton UczenRB;
    private javax.swing.JButton Zaloguj;
    private javax.swing.JLabel alert;
    private javax.swing.JPasswordField haslo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox wybNauczyciel;
    // End of variables declaration//GEN-END:variables
}


package administrador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class administrador_de_tareas extends javax.swing.JFrame {
 
    private DefaultTableModel modelo;
    
    public administrador_de_tareas() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        No_procesos.setFocusable(false);
        mostrar_procesos();
    }

   
    /*   public static List listRunningProcesses() {
    List<String> processes = new ArrayList<String>();
    List<String> processes2 = new ArrayList<String>();
    try {
    String line;
    Process p = Runtime.getRuntime().exec("tasklist.exe /nh");
    BufferedReader input = new BufferedReader
    (       new InputStreamReader(p.getInputStream()));
    while ((line = input.readLine()) != null)
    {
    if (!line.trim().equals("")) {
    
    processes.add(line.substring(0, line.indexOf(" ")));
    }
    }
    input.close();
    }
    catch (Exception err) {
    err.printStackTrace();
    }
    return processes;
    }*/
    private void mostrar_procesos(){
    int ICol=0,ICont=0;
        modelo = (DefaultTableModel) jtabla_datos.getModel();
        Object[] Fila = new  Object[5];
        int i=0;
        String StrAuxi = "";
            
            
                try {
            String line;
            Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                    System.out.println(line);
                if(i>=4){
                    ICont=0;
                   while(ICont<=4){
                    String[] sep = line.split("\\s+");
                    if(ICont!=4){
                    Fila[ICont] = sep[ICont];
                    }else{
                       Fila[ICont] = sep[ICont]+" "+sep[ICont+1]; 
                    }
                    ICont++;
                   }
                //agregamos el arreglo de la cada fila a la tabla
                modelo.addRow(Fila);
                //Asignamos y/o aplicamos el modelo a nuestra tabla
                jtabla_datos.setModel(modelo);
                //DefaulTableCellRenderer Alinear = new DefaulTableCellRenderer();
                
                }
        i++;
            }
            input.close();
            No_procesos.setText(String.valueOf(i));
        } catch (Exception err) {
            err.printStackTrace();
        }
        
    }
    

    void LimpiarTabla(){
        jtabla_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "PID", "Tipo de sesión ", "Número de sesión", "Uso de memoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    public void terminarP(){
        modelo = (DefaultTableModel) jtabla_datos.getModel();
        String StrCelda = String.valueOf(modelo.getValueAt(jtabla_datos.getSelectedRow(), 0));
        if(StrCelda==""){ 
          JOptionPane.showMessageDialog(null, "ERROR, No se ha selecionado ningun proceso","Error", JOptionPane.INFORMATION_MESSAGE); 
        }else{
        try {
          Process hijo;
          hijo = Runtime.getRuntime().exec("taskkill /F /IM "+StrCelda);
          hijo.waitFor();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(administrador_de_tareas.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
                    
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla_datos = new javax.swing.JTable();
        jIniciar_procesos = new javax.swing.JButton();
        jterminar_procesos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        No_procesos = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador de tareas");

        jtabla_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "PID", "Tipo de sesión ", "Número de sesión", "Uso de memoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtabla_datos);

        jIniciar_procesos.setText("PROCESOS");
        jIniciar_procesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIniciar_procesosActionPerformed(evt);
            }
        });

        jterminar_procesos.setText("MATAR PROCESO");
        jterminar_procesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jterminar_procesosActionPerformed(evt);
            }
        });

        jLabel2.setText("TOTAL DE PROCESOS: ");

        No_procesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                No_procesosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addComponent(No_procesos, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jIniciar_procesos, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jterminar_procesos, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(337, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jIniciar_procesos)
                    .addComponent(jterminar_procesos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(No_procesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jIniciar_procesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIniciar_procesosActionPerformed
        
        LimpiarTabla();
        mostrar_procesos();
        /*
        List<String> processes = listRunningProcesses();
        String result = "";
        String result2 = "";
        Iterator<String> it = processes.iterator();
        int i = 0;
        this.No_procesos.setText(String.valueOf(processes.size()));
        while (it.hasNext()) {
        result += it.next();
        i++;
        if (i==1) {
        Object[] s={result};
        mt.addRow(s);
        result="";
        i = 0;
        }
        }*/
   
    }//GEN-LAST:event_jIniciar_procesosActionPerformed
    // Boton de cerrar o matar procesos
    private void jterminar_procesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jterminar_procesosActionPerformed
       
        terminarP();
        LimpiarTabla();
        mostrar_procesos();
      /* String cm=JOptionPane.showInputDialog("Nombre del Proceso:");
 String osName = System.getProperty("os.name");
 String cmd="";
 if(osName.toUpperCase().contains("WIN")){
     
	cmd+="TASKKILL /F /IM "+cm+".exe"+" /T";
 }else{
	//cmd+="TASKKILL /F /IM "+cm+".exe"+" /T";
 }
 Process hijo;
 try {
	hijo = Runtime.getRuntime().exec(cmd);
	hijo.waitFor();
        JOptionPane.showMessageDialog(null,"Proceso cerrado de exitosamente");
 } catch (IOException e) {
	JOptionPane.showMessageDialog(null,"Incapaz de matar Proceso");
 } catch (InterruptedException e) {
	JOptionPane.showMessageDialog(null,"Incapaz de matar Proceso");
    }//GEN-LAST:event_jterminar_procesosActionPerformed
    
    */}
    private void No_procesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_No_procesosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_No_procesosActionPerformed

       

  
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
            java.util.logging.Logger.getLogger(administrador_de_tareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(administrador_de_tareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(administrador_de_tareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(administrador_de_tareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new administrador_de_tareas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField No_procesos;
    private javax.swing.JButton jIniciar_procesos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtabla_datos;
    private javax.swing.JButton jterminar_procesos;
    // End of variables declaration//GEN-END:variables
}

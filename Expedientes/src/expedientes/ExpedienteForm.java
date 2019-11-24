package expedientes;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import java.util.List;

public class ExpedienteForm extends javax.swing.JFrame {
    String paraExpediente = null;
    ArrayList paraArbol = new ArrayList<File>();
    ArrayList probarMilisegundos = new ArrayList<Long>();
    BinaryTree arbolExpedientes;
    ArrayList paraNombre = new ArrayList<String>();
    ArrayList paraResto = new ArrayList<String>();
    
    public ExpedienteForm() {
        initComponents();
    }

    public void agregar(){
        if(txtNombre.getText().isEmpty() || txtEdad.getText().isEmpty() || txtExpediente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Faltan campos por llenar");
            return;
        }
        
        File toSave = new File("FolderExpedientes\\" + txtNombre.getText() + ".txt");
        
        paraExpediente = txtNombre.getText() + "\r\n" + txtEdad.getText() + "\r\n" + txtExpediente.getText();
        System.out.println(paraExpediente);
        if(!toSave.exists()){
            try {
                toSave.createNewFile();
                
                PrintWriter archivoWrite = new PrintWriter(toSave);
                archivoWrite.println(paraExpediente);
                archivoWrite.close();

                JOptionPane.showMessageDialog(this, "Expediente de " + txtNombre.getText() + " guardado con exito.");
            
                txtNombre.setText("");
                txtEdad.setText("");
                txtExpediente.setText("");
            } catch (IOException ex) {
                System.err.println("Error de entrada o salida de archivos.");
                System.err.println(ex.toString());
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Ya existe un archivo con ese nombre.");
        }
    }
    
    public void probarArchivos() throws IOException{
        Stream<Path> paths = Files.walk(Paths.get("FolderExpedientes\\"));
        
        paths.forEach(filePath -> {
            if(Files.isRegularFile(filePath)){
                try{
                    leerContenido(filePath);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void leerContenido(Path filePath) throws IOException{
        File prueba = new File(filePath.toString());
        
        System.out.println(prueba.toString());

        BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
        System.out.println(attr.creationTime().toMillis());
        System.out.println("read file: " + filePath);
        List<String> fileList = Files.readAllLines(filePath);
        
        String mostrar = fileList.get(0);
        String restoExpediente = fileList.get(1);
        for(int i=1; i<fileList.size()-1; i++){
            if(i>1){
                restoExpediente += " ";
                restoExpediente += fileList.get(i);
            }
                
            mostrar += " ";
            mostrar += fileList.get(i);
        }
        
        System.out.println(" " + mostrar);
        paraNombre.add(fileList.get(0));
        paraResto.add(restoExpediente);
        paraArbol.add(prueba);
        probarMilisegundos.add(attr.creationTime().toMillis());
    }
    
    public void saveToTree(){
        int toRemove = 0;
        
        if(!probarMilisegundos.isEmpty()){
            arbolExpedientes = new BinaryTree();
            Long menor = (Long) probarMilisegundos.get(0);
            while(!probarMilisegundos.isEmpty()){
                for(int i=0; i<probarMilisegundos.size(); i++){
                    toRemove=0;
                    if((Long) probarMilisegundos.get(i) < menor){
                        menor = (Long)probarMilisegundos.get(i);
                        toRemove = i;
                    }

                }
                if(!probarMilisegundos.isEmpty()){
                    arbolExpedientes.addNode((Long) probarMilisegundos.get(toRemove), paraNombre.get(toRemove).toString(), paraResto.get(toRemove).toString());
                    probarMilisegundos.remove(toRemove);
                    paraNombre.remove(toRemove);
                    paraResto.remove(toRemove);
                }
            }
        }
        else{
            System.err.println("No hay nada para agregar al arbol.");
        }


    }
    /*
    public void load() {
        Path path = Paths.get(base_dir, data_dir);
        try (Stream<Path> walk = Files.walk("FolderExpedientes\\")) {
            List<String> result = walk.filter(Files::isRegularFile)
                    .sorted((path1, path2) -> {
                        int res = -1;
                        try {
                            BasicFileAttributes file1 = Files.getFileAttributeView(path1, BasicFileAttributeView.class).readAttributes();
                            BasicFileAttributes file2 = Files.getFileAttributeView(path2, BasicFileAttributeView.class).readAttributes();

                            res = file1.lastModifiedTime().compareTo(file2.lastModifiedTime());
                        } catch (IOException ex) {
                            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            return res;
                        }
                    })
                    .map(x -> x.toString())
                    .collect(Collectors.toList());

            result.forEach(filePath -> {
                Patient patient = new Patient();
                patient.load(filePath);
                insert(patient, false);
            });
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnRecargar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtExpediente = new javax.swing.JTextArea();
        toDebug = new javax.swing.JButton();
        toCheckArrayList = new javax.swing.JButton();
        btnArbol = new javax.swing.JButton();
        btnVerArbol = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Edad:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Expediente:");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnRecargar.setText("Recargar");

        txtExpediente.setColumns(20);
        txtExpediente.setRows(5);
        jScrollPane1.setViewportView(txtExpediente);

        toDebug.setText("Debug");
        toDebug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toDebugActionPerformed(evt);
            }
        });

        toCheckArrayList.setText("Revisar ArrayList");
        toCheckArrayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toCheckArrayListActionPerformed(evt);
            }
        });

        btnArbol.setText("Crear Arbol");
        btnArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArbolActionPerformed(evt);
            }
        });

        btnVerArbol.setText("Ver Arbol");
        btnVerArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerArbolActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(toDebug)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(toCheckArrayList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnArbol)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerArbol)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnBuscar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnRecargar)
                                    .addComponent(btnAgregar))))
                        .addGap(66, 66, 66))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRecargar)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArbol)
                    .addComponent(btnVerArbol)
                    .addComponent(toDebug)
                    .addComponent(toCheckArrayList))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void toDebugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toDebugActionPerformed
        paraArbol.clear();
        try {
            probarArchivos();
        } catch (IOException ex) {
            System.err.println("Fallo este pedo :'c");
        }
    }//GEN-LAST:event_toDebugActionPerformed

    private void toCheckArrayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toCheckArrayListActionPerformed
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Para arbol array: ");
        
        for(int i=0; i<paraArbol.size(); i++){
            System.out.println(paraArbol.get(i).toString());
        }
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Probar milisegundos: ");
        
        for(int i=0; i<probarMilisegundos.size(); i++){
            System.out.println(probarMilisegundos.get(i).toString());
        }
        
    }//GEN-LAST:event_toCheckArrayListActionPerformed

    private void btnArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArbolActionPerformed
        saveToTree();
    }//GEN-LAST:event_btnArbolActionPerformed

    private void btnVerArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerArbolActionPerformed
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("In order:");
        arbolExpedientes.inOrderTraverseTree(arbolExpedientes.root);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Pre order:");
        
        arbolExpedientes.preorderTraverseTree(arbolExpedientes.root);
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Post order:");
        
        arbolExpedientes.postOrderTraverseTree(arbolExpedientes.root);
    }//GEN-LAST:event_btnVerArbolActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ExpedienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ExpedienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ExpedienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ExpedienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
////        java.awt.EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                new ExpedienteForm().setVisible(true);
////            }
////        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnArbol;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnRecargar;
    private javax.swing.JButton btnVerArbol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton toCheckArrayList;
    private javax.swing.JButton toDebug;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextArea txtExpediente;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

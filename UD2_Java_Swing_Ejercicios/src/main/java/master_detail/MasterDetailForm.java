/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package master_detail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.RollbackException;
import java.beans.Beans;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author gag
 */
public class MasterDetailForm extends javax.swing.JFrame implements ListSelectionListener {

    private EntityManager entityManager;
    private Query queryMaster;

    private ListTableModel<Contactos> modelMaster;
    private ListTableModel<Correos> modelDetail;

    private Contactos lastSelected;
    private List<Correos> lastDetailData = Collections.emptyList();

    /**
     * Creates new form MasterDetail
     */
    public MasterDetailForm() {
        initComponents();
        if (Beans.isDesignTime()) {
            return;
        }
        // Set listeners
        btnDeleteDetail.addActionListener(((e) -> onClickDeleteDetail(e)));
        btnDeleteMaster.addActionListener(((e) -> onClickeDeleteMaster(e)));
        btnNewDetail.addActionListener(((e) -> onClickNewDetail(e)));
        btnNewMaster.addActionListener(((e) -> onClickNewMaster(e)));
        btnRefresh.addActionListener(((e) -> onClickRefresh(e)));
        btnSave.addActionListener(((e) -> onClickSave(e)));

        // Needed to not break table when deleting or refreshing while editing a cell
        // Other option is to call "table.getCellEditor().stopCellEditing()"
        tableMaster.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tableDetail.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        // Init entities
        entityManager = Persistence.createEntityManagerFactory("io.gongarce.agenda").createEntityManager();
        queryMaster = entityManager.createQuery("SELECT C FROM Contactos C");
        List<Contactos> list = queryMaster.getResultList();
        entityManager.getTransaction().begin();

        // Configure Master Table
        List<ListTableModel.TableColum<Contactos, ?>> columns = new ArrayList<>(3);
        columns.add(new ListTableModel.TableColum<>("Id", Integer.class, Contactos::getId, Contactos::setId, false));
        columns.add(new ListTableModel.TableColum<>("Nombre", String.class, Contactos::getNombre, Contactos::setNombre));
        columns.add(new ListTableModel.TableColum<>("Ciudad", String.class, Contactos::getCiudad, Contactos::setCiudad));
        modelMaster = new ListTableModel<>(columns, list);
        tableMaster.setModel(modelMaster);

        // Configure Detail Table
        List<ListTableModel.TableColum<Correos, ?>> detailCols = new ArrayList<>(2);
        detailCols.add(new ListTableModel.TableColum<>("Id", Integer.class, Correos::getCorreoId, Correos::setCorreoId, false));
        detailCols.add(new ListTableModel.TableColum<>("Correo", String.class, Correos::getCorreo, Correos::setCorreo));
        modelDetail = new ListTableModel<>(detailCols);
        tableDetail.setModel(modelDetail);

        // Table listeners
        tableMaster.getSelectionModel().addListSelectionListener(this);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (tableMaster.getSelectedRowCount() != 1) {
            modelDetail.setData(Collections.emptyList());
        } else {
            Contactos c = modelMaster.getRowValue(tableMaster.convertRowIndexToModel(tableMaster.getSelectedRow()));
            if (Objects.nonNull(c) && !c.equals(lastSelected)) {
                lastSelected = c;
                Query lastDetailQuery = entityManager.createNamedQuery("Correos.findByContact");
                lastDetailQuery.setParameter("contacto", c);
                lastDetailData = lastDetailQuery.getResultList();
            }
            modelDetail.setData(lastDetailData);
        }
    }

    private void onClickDeleteDetail(java.awt.event.ActionEvent evt) {
        // TODO: Delete Detail Row
    }

    private void onClickNewDetail(java.awt.event.ActionEvent evt) {
        // TODO: Create Detail Row
    }

    @SuppressWarnings("unchecked")
    private void onClickRefresh(java.awt.event.ActionEvent evt) {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        List<Contactos> data = queryMaster.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        modelMaster.setData(data);
    }

    private void onClickeDeleteMaster(java.awt.event.ActionEvent evt) {
        int[] selected = tableMaster.getSelectedRows();
        List<Contactos> toRemove = new ArrayList<>(selected.length);
        for (int i = 0; i < selected.length; i++) {
            Contactos C = modelMaster.getRowValue(tableMaster.convertRowIndexToModel(selected[i]));
            toRemove.add(C);
            entityManager.remove(C);
        }
        modelMaster.remove(toRemove);
    }

    private void onClickNewMaster(java.awt.event.ActionEvent evt) {
        Contactos contact = new Contactos();
        entityManager.persist(contact);
        int row = modelMaster.add(contact);
        tableMaster.setRowSelectionInterval(row, row);
        tableMaster.scrollRectToVisible(tableMaster.getCellRect(row, 0, true));
        tableMaster.editCellAt(row, 1);
    }

    private void onClickSave(java.awt.event.ActionEvent evt) {
        List<Contactos> list = modelMaster.getData();
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<Contactos> merged = new ArrayList<Contactos>(list.size());
            for (Contactos c : list) {
                merged.add(entityManager.merge(c));
            }
            modelMaster.setData(merged);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMaster = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnNewMaster = new javax.swing.JButton();
        btnDeleteMaster = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDetail = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnNewDetail = new javax.swing.JButton();
        btnDeleteDetail = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 200));

        jScrollPane1.setViewportView(tableMaster);

        jPanel1.add(jScrollPane1);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnNewMaster.setText("New");
        jPanel2.add(btnNewMaster);

        btnDeleteMaster.setText("Delete");
        jPanel2.add(btnDeleteMaster);

        jPanel1.add(jPanel2);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(400, 200));

        jScrollPane2.setViewportView(tableDetail);

        jPanel1.add(jScrollPane2);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnNewDetail.setText("New");
        jPanel3.add(btnNewDetail);

        btnDeleteDetail.setText("Delete");
        jPanel3.add(btnDeleteDetail);

        btnRefresh.setText("Refresh");
        jPanel3.add(btnRefresh);

        btnSave.setText("Save");
        jPanel3.add(btnSave);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MasterDetailForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MasterDetailForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MasterDetailForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MasterDetailForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MasterDetailForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteDetail;
    private javax.swing.JButton btnDeleteMaster;
    private javax.swing.JButton btnNewDetail;
    private javax.swing.JButton btnNewMaster;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableDetail;
    private javax.swing.JTable tableMaster;
    // End of variables declaration//GEN-END:variables

}

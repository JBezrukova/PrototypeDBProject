import DAO.DBDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by bezru on 23.10.2017.
 */
public class Predict extends javax.swing.JFrame {

    private static DefaultTableModel tableModelPrevious;
    private static DefaultTableModel tableModelPredict;
    private ArrayList codes;

    /**
     * Creates new form Predict
     */
    public Predict() {
        initComponents();
        tableModelPrevious = (DefaultTableModel) jTable2.getModel();
        tableModelPredict = (DefaultTableModel) jTable3.getModel();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Прогнозиорвание объемов");
        pack();

        getPreviousValues();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();

        jPanel1 = new javax.swing.JPanel();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        scrollPane2 = new java.awt.ScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();




        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Код товарае", "Наименование", "Месяц", "Количество"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        scrollPane1.add(jScrollPane2);

        jLabel1.setText("Прошлые периоды");

        jLabel2.setText("Прогноз");

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Код товара", "Наименование", "Месяц", "Прогноз"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        scrollPane2.add(jScrollPane3);

        jButton3.setText("Рассчитать");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton3))
                                        .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(4, 4, 4)
                                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jButton3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        jButton1.setText("Редактировать");

        jButton2.setText("Утвердить");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 385, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    /**
     * putting data into table of prediction
     * @param evt
     */

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        createPredictionTable();
    }

    /**
     * Check that the person is a manager. Allowing correction.
     * @param evt
     */

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Object result = JOptionPane.showInputDialog(this,
                "Подтвердите пароль менеджера группы логистики",
                "пароль",
                JOptionPane.QUESTION_MESSAGE);

        if (result.equals("password")){

            JOptionPane.showMessageDialog(this, "Разрешено исправление данных в таблице");
        }
    }

    /**
     * Method to correct volume
     * @param evt
     */

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        Map<Object, Object> codeCountMap = new HashMap<>();
        for (int i = 0; i < tableModelPredict.getRowCount(); i++){
            codeCountMap.put(tableModelPredict.getValueAt(i, 0), tableModelPredict.getValueAt(i, 3));
        }
        boolean result = DBDAO.getInstance().put(codeCountMap, codes);
        if (result == true){
            JOptionPane.showMessageDialog(this, "Данные обновлены");
        }

    }

    /**
     * Putting data into table. PREVIOUS ACTION : counting of volume
     */

    private void createPredictionTable() {
        codes = new ArrayList();
        int code;
        ResultSet resultSet = DBDAO.getInstance().getProducts();
        tableModelPredict.setRowCount(0);

        try {
            while (resultSet.next()) {

                Vector vector = new Vector<>(4);
                code = resultSet.getInt("code");
                codes.add(code);
                vector.add(code);
                vector.add(resultSet.getString("prodName"));
                vector.add(new Date());
                vector.add(predict(code));
                tableModelPredict.addRow(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method for getting all previous values of sells
     */

    private static void getPreviousValues() {
        ResultSet resultSet = DBDAO.getInstance().getListOfPreviousPeriod();
        tableModelPrevious.setRowCount(0);
        try {
            while (resultSet.next()) {
                Vector vector = new Vector<>(4);
                vector.add(resultSet.getInt("code"));
                vector.add(resultSet.getString("prodName"));
                vector.add(resultSet.getString("dateOfSell"));
                vector.add(resultSet.getString("countOfProd"));
                tableModelPrevious.addRow(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Countion of future volume
     * @param code code of a product
     * @return value of future volume
     */

    private static int predict(int code) {

        ResultSet result = DBDAO.getInstance().getListOfPreviousPeriodByCode(code);
        ArrayList<Integer> counts = new ArrayList<>();
        try {
            while (result.next()) {

                counts.add(result.getInt("countOfProd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int sum = 0;
        for (int i = 0; i < counts.size(); i++) {
            sum += counts.get(i);
        }
        sum /= counts.size();

        int now = counts.get(0);
        System.out.println(now);
        int average = sum;
        System.out.println(average);
        double maxValue = average * 1.1;
        System.out.println(maxValue);

        int predict = (int) maxValue - now + average;
        return predict;
    }


    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;

    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private java.awt.ScrollPane scrollPane1;
    private java.awt.ScrollPane scrollPane2;
    // End of variables declaration
}

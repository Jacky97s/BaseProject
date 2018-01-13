package demo.calories;

import demo.calories.DeleteCalories;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteCalories extends JFrame {
    private static DeleteCalories _instance;
    private JPanel deletePanel;
    private JButton backButton;
    private JButton refreshButton;
    private JButton deleteButton;
    private JTable table1;
    private JTextField selectToDelete;

    public DeleteCalories() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DeleteCalories.getInstance().setVisible(false);
                CaloriesMain.getInstance().setVisible(true);
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Class.forName("org.h2.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:h2:~/JIAJINZUAN", "root", "");
                    Statement st = conn.createStatement();
                    String statement = ("SELECT * FROM CALORIES");
                    ResultSet rs = st.executeQuery(statement);
                    DefaultTableModel model = new DefaultTableModel(new String[]{"No.", "DATE", "TIME", "CALORIES", "FOOD"}, 0) {
                        public boolean isCellEditable(int row, int column) {
                            return false;//This causes all cells to be not editable
                        }
                    };
                    int index = 0;
                    while (rs.next()) {
                        ++index;
                        String d = rs.getString("DATE");
                        String e = rs.getString("TIME");
                        int f = rs.getInt("CALORIES");
                        String g = rs.getString("FOOD");
                        model.addRow(new Object[]{index, d, e, f, g});
                    }
                    table1.setModel(model);
                    rs.close();
                    conn.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Class.forName("org.h2.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:h2:~/JIAJINZUAN", "root", "");
                    Statement st = conn.createStatement();
                    String statement = ("SELECT * FROM CALORIES");
                    ResultSet rs = st.executeQuery(statement);
                    int index = 0;
                    int select = Integer.parseInt(selectToDelete.getText());
                    while (rs.next()) {
                        ++index;
                        if (index == select) {
                            String d = rs.getString("DATE");
                            String e = rs.getString("TIME");
                            int f = rs.getInt("CALORIES");
                            String g = rs.getString("FOOD");
                            statement = ("DELETE FROM CALORIES WHERE DATE = \'" + d + "\' AND TIME = \'" + e + "\' AND CALORIES = " + f + " AND FOOD = \'" + g + "\'");
                            st.executeUpdate(statement);
                            break;
                        }
                    }
                    selectToDelete.setText("");
                    statement = ("SELECT * FROM CALORIES");
                    rs = st.executeQuery(statement);
                    DefaultTableModel model = new DefaultTableModel(new String[]{"No.", "DATE", "TIME", "CALORIES", "FOOD"}, 0) {
                        public boolean isCellEditable(int row, int column) {
                            return false;//This causes all cells to be not editable
                        }
                    };
                    index = 0;
                    while (rs.next()) {
                        ++index;
                        String d = rs.getString("DATE");
                        String e = rs.getString("TIME");
                        int f = rs.getInt("CALORIES");
                        String g = rs.getString("FOOD");
                        model.addRow(new Object[]{index, d, e, f, g});
                    }
                    table1.setModel(model);
                    rs.close();
                    conn.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static DeleteCalories getInstance() {
        if (_instance == null) {
            _instance = new DeleteCalories();
            _instance.setContentPane(new DeleteCalories().deletePanel);
            _instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            _instance.setBounds(400, 60, 400, 400);
            _instance.setPreferredSize(new Dimension(650, 700));
            _instance.pack();
        }
        return _instance;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        deletePanel = new JPanel();
        deletePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        deletePanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 36, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Delete History");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        deletePanel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        backButton = new JButton();
        Font backButtonFont = this.$$$getFont$$$(null, -1, 20, backButton.getFont());
        if (backButtonFont != null) backButton.setFont(backButtonFont);
        backButton.setText("Back");
        panel2.add(backButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        refreshButton = new JButton();
        Font refreshButtonFont = this.$$$getFont$$$(null, -1, 20, refreshButton.getFont());
        if (refreshButtonFont != null) refreshButton.setFont(refreshButtonFont);
        refreshButton.setText("Refresh");
        panel2.add(refreshButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        Font deleteButtonFont = this.$$$getFont$$$(null, -1, 20, deleteButton.getFont());
        if (deleteButtonFont != null) deleteButton.setFont(deleteButtonFont);
        deleteButton.setText("Delete");
        panel2.add(deleteButton, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        Font scrollPane1Font = this.$$$getFont$$$(null, -1, 20, scrollPane1.getFont());
        if (scrollPane1Font != null) scrollPane1.setFont(scrollPane1Font);
        deletePanel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        Font table1Font = this.$$$getFont$$$(null, -1, -1, table1.getFont());
        if (table1Font != null) table1.setFont(table1Font);
        scrollPane1.setViewportView(table1);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        deletePanel.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        selectToDelete = new JTextField();
        Font selectToDeleteFont = this.$$$getFont$$$(null, -1, 20, selectToDelete.getFont());
        if (selectToDeleteFont != null) selectToDelete.setFont(selectToDeleteFont);
        panel3.add(selectToDelete, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 20, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Insert row number to Delete");
        panel3.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panel3.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return deletePanel;
    }
}

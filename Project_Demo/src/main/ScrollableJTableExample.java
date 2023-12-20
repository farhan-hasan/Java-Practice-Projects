package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ScrollableJTableExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Scrollable JTable Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create DefaultTableModel with sample data
            DefaultTableModel model = new DefaultTableModel(
                    new Object[][]{
                            {1, "Person 1", 21},
                            {2, "Person 2", 22},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23},
                            {3, "Person 3", 23}
                            // Add more rows as needed
                    },
                    new Object[]{"ID", "Name", "Age"}
            );

            // Create JTable with the DefaultTableModel
            JTable table = new JTable(model);

            // Create JScrollPane and add JTable to it
            JScrollPane scrollPane = new JScrollPane(table);

            // Create JPanel and add JScrollPane to it
            JPanel panel = new JPanel();
            panel.add(scrollPane);

            // Add JPanel to the JFrame
            frame.getContentPane().add(panel);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

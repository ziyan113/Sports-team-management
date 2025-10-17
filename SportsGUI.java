package jdbc;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SportsGUI extends JFrame {
    private JTextField idField, nameField, sportField, positionField;
    private JTextArea outputArea;
    static Connection conn;

    public SportsGUI() {
        setTitle("Sports Team Management");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Connect to DB
        connectDB();

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("Player ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Sport:"));
        sportField = new JTextField();
        inputPanel.add(sportField);

        inputPanel.add(new JLabel("Position:"));
        positionField = new JTextField();
        inputPanel.add(positionField);

        add(inputPanel, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add Player");
        JButton viewBtn = new JButton("View Players");
        JButton updateBtn = new JButton("Update Player");
        JButton deleteBtn = new JButton("Delete Player");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        add(buttonPanel, BorderLayout.CENTER);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        // Button actions
        addBtn.addActionListener(e -> addPlayer());
        viewBtn.addActionListener(e -> viewPlayers());
        updateBtn.addActionListener(e -> updatePlayer());
        deleteBtn.addActionListener(e -> deletePlayer());
    }

    private void connectDB() {
        try {
            String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String pass = "$Ziyan135790";
            conn = DriverManager.getConnection(url, user, pass);

            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Players (" +
                    "player_id INT PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "sport VARCHAR(30), " +
                    "position VARCHAR(30))");
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    private void addPlayer() {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Players VALUES (?, ?, ?, ?)");
            ps.setInt(1, Integer.parseInt(idField.getText()));
            ps.setString(2, nameField.getText());
            ps.setString(3, sportField.getText());
            ps.setString(4, positionField.getText());
            ps.executeUpdate();
            outputArea.setText("✅ Player added successfully!");
        } catch (Exception ex) {
            outputArea.setText("❌ Error: " + ex.getMessage());
        }
    }

    private void viewPlayers() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Players");
            StringBuilder sb = new StringBuilder("Player List:\n\n");
            while (rs.next()) {
                sb.append(rs.getInt("player_id")).append(" | ")
                        .append(rs.getString("name")).append(" | ")
                        .append(rs.getString("sport")).append(" | ")
                        .append(rs.getString("position")).append("\n");
            }
            outputArea.setText(sb.toString());
        } catch (Exception ex) {
            outputArea.setText("❌ Error: " + ex.getMessage());
        }
    }

    private void updatePlayer() {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Players SET position=? WHERE player_id=?");
            ps.setString(1, positionField.getText());
            ps.setInt(2, Integer.parseInt(idField.getText()));
            int rows = ps.executeUpdate();
            if (rows > 0) outputArea.setText("✅ Player updated successfully!");
            else outputArea.setText("⚠️ Player not found!");
        } catch (Exception ex) {
            outputArea.setText("❌ Error: " + ex.getMessage());
        }
    }

    private void deletePlayer() {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Players WHERE player_id=?");
            ps.setInt(1, Integer.parseInt(idField.getText()));
            int rows = ps.executeUpdate();
            if (rows > 0) outputArea.setText("✅ Player deleted successfully!");
            else outputArea.setText("⚠️ Player not found!");
        } catch (Exception ex) {
            outputArea.setText("❌ Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SportsGUI().setVisible(true));
    }
}

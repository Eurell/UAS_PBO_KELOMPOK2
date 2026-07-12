package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Farrel RM
 * NIM = 25104410017
 * Kelas = Teknik Informatika 2A
 */
public class DbConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Mengarah langsung ke database
                String url = "jdbc:mysql://localhost:3306/db_toko"; 
                String user = "root";
                String password = ""; 
                
                // Mendaftarkan driver dan membuka koneksi
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Koneksi Database Gagal! Pastikan XAMPP menyala.\nError: " + e.getMessage());
            }
        }
        return connection;
    }
}
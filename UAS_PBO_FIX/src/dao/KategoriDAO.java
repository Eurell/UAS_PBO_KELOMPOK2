package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Kategori;
import util.DbConnection;

/**
 *
 * @author Farrel RM
 * NIM = 25104410017
 * Kelas = Teknik Informatika 2A
 */
public class KategoriDAO {
    private Connection conn;

    public KategoriDAO() {
        // Memanggil koneksi database
        conn = DbConnection.getConnection();
    }

    // 1. READ: Mengambil semua data kategori untuk ditampilkan di Tabel & ComboBox
    public List<Kategori> getAllKategori() {
        List<Kategori> list = new ArrayList<>();
        String sql = "SELECT * FROM kategori";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Kategori k = new Kategori();
                k.setKode(rs.getInt("kode"));
                k.setKategori(rs.getString("kategori"));
                k.setBrand(rs.getString("brand"));
                list.add(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. CREATE: Menyimpan kategori baru (Lengkap dengan Cek Duplikat)
    public boolean insert(Kategori k) {
        // Cek apakah kombinasi Kategori + Brand sudah ada di database
        String checkSql = "SELECT * FROM kategori WHERE kategori = ? AND brand = ?";
        try {
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setString(1, k.getKategori());
            checkPs.setString(2, k.getBrand());
            ResultSet rs = checkPs.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "GAGAL: Kombinasi Kategori dan Brand ini sudah ada!", "Duplikat Data", JOptionPane.WARNING_MESSAGE);
                return false; 
            }

            // Jika aman, lanjutkan proses Insert
            String sql = "INSERT INTO kategori (kategori, brand) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, k.getKategori());
            ps.setString(2, k.getBrand());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Insert: " + e.getMessage());
            return false;
        }
    }

    // 3. UPDATE: Mengedit kategori
    public boolean update(Kategori k) {
        String sql = "UPDATE kategori SET kategori = ?, brand = ? WHERE kode = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, k.getKategori());
            ps.setString(2, k.getBrand());
            ps.setInt(3, k.getKode());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            // Error Code 1062 = Duplicate Entry (Jika user ngedit jadi nama yang sudah ada)
            if(e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "GAGAL: Kombinasi Kategori dan Brand sudah digunakan!", "Duplikat Data", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error Update: " + e.getMessage());
            }
            return false;
        }
    }

    // 4. DELETE: Menghapus kategori (Lengkap dengan Cegatan Foreign Key)
    public boolean delete(int kode) {
        String sql = "DELETE FROM kategori WHERE kode = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, kode);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            // Error code 1451 adalah aturan RESTRICT dari database jika data masih dipakai di tabel produk
            if (e.getErrorCode() == 1451) {
                JOptionPane.showMessageDialog(null, "TIDAK BISA DIHAPUS!\nKategori ini masih digunakan oleh data Produk.\nSilakan hapus produk terkait terlebih dahulu.", "Error Foreign Key", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error Delete: " + e.getMessage());
            }
            return false;
        }
    }
}
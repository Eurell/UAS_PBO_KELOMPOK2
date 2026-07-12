package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Produk;
import util.DbConnection;

/**
 *
 * @author Farrel RM
 * NIM = 25104410017
 * Kelas = Teknik Informatika 2A
 */
public class ProdukDAO {
    private Connection conn;

    public ProdukDAO() {
        conn = DbConnection.getConnection();
    }

    // 1. READ: Mengambil data produk dengan JOIN ke tabel kategori
    public List<Object[]> getAllProduk() {
        List<Object[]> list = new ArrayList<>();
        // Kueri JOIN untuk menarik nama kategori dan brand
        String sql = "SELECT p.kode, p.nama, k.kategori, k.brand, p.harga, p.stock, p.kode_produk " +
                     "FROM produk p JOIN kategori k ON p.kode_produk = k.kode " +
                     "ORDER BY p.kode DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getInt("kode");          // Kode Produk
                row[1] = rs.getString("nama");       // Nama Produk
                row[2] = rs.getString("kategori");   // Nama Kategori
                row[3] = rs.getString("brand");      // Brand
                row[4] = rs.getInt("harga");         // Harga
                row[5] = rs.getInt("stock");         // Stock
                row[6] = rs.getInt("kode_produk");   // Disembunyikan di tabel, tapi dipakai untuk fitur Edit
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. CREATE: Menyimpan produk baru
    public boolean insert(Produk p) {
        String sql = "INSERT INTO produk (kode_produk, nama, harga, stock) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getKode_produk());
            ps.setString(2, p.getNama());
            ps.setInt(3, p.getHarga());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            // Error Code 1062 = Duplicate Entry untuk nama produk
            if(e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "GAGAL: Nama Produk '" + p.getNama() + "' sudah ada di database!", "Duplikat Data", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error Insert: " + e.getMessage());
            }
            return false;
        }
    }

    // 3. UPDATE: Mengedit data produk
    public boolean update(Produk p) {
        String sql = "UPDATE produk SET kode_produk = ?, nama = ?, harga = ?, stock = ? WHERE kode = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getKode_produk());
            ps.setString(2, p.getNama());
            ps.setInt(3, p.getHarga());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getKode());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            if(e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "GAGAL: Nama Produk '" + p.getNama() + "' sudah digunakan produk lain!", "Duplikat Data", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error Update: " + e.getMessage());
            }
            return false;
        }
    }

    // 4. DELETE: Menghapus produk
    public boolean delete(int kode) {
        String sql = "DELETE FROM produk WHERE kode = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, kode);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Delete: " + e.getMessage());
            return false;
        }
    }
}
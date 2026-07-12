package model;
/**
 *
 * @author Farrel RM
 * NIM = 25104410017
 * Kelas = Teknik Informatika 2A
 */
public class Produk {
    private int kode;
    private int kode_produk;
    private String nama;
    private int harga;
    private int stock;

    public int getKode() { return kode; }
    public void setKode(int kode) { this.kode = kode; }

    public int getKode_produk() { return kode_produk; }
    public void setKode_produk(int kode_produk) { this.kode_produk = kode_produk; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public int getHarga() { return harga; }
    public void setHarga(int harga) { this.harga = harga; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
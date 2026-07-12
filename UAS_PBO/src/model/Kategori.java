package model;
/**
 *
 * @author Farrel RM
 * NIM = 25104410017
 * Kelas = Teknik Informatika 2A
 */
public class Kategori {
    private int kode;
    private String kategori;
    private String brand;

    public int getKode() { return kode; }
    public void setKode(int kode) { this.kode = kode; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    // Format khusus agar tampil rapi di dropdown (JComboBox)
    @Override
    public String toString() {
        return kode + " - " + kategori + " (" + brand + ")";
    }
}
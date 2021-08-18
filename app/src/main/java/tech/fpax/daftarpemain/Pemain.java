package tech.fpax.daftarpemain;

public class Pemain {

    private String nama;
    private String gambar;
    private String id;

    public Pemain(String nama, String gambar, String id) {
        this.nama = nama;
        this.gambar = gambar;
        this.id = id;

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

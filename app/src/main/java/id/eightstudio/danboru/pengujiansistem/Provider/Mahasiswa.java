package id.eightstudio.danboru.pengujiansistem.Provider;

public class Mahasiswa {

    private int id_mahasiswa;
    private String nama_mahasiswa;
    private int nilai_mahasiswa;
    private String alfabhet_mahasiswa;

    public Mahasiswa() {
    }

    public Mahasiswa(String nama_mahasiswa, int nilai_mahasiswa, String alfabhet_mahasiswa) {
        this.nama_mahasiswa = nama_mahasiswa;
        this.nilai_mahasiswa = nilai_mahasiswa;
        this.alfabhet_mahasiswa = alfabhet_mahasiswa;
    }

    public Mahasiswa(int id_mahasiswa, String nama_mahasiswa, int nilai_mahasiswa, String alfabhet_mahasiswa) {
        this.id_mahasiswa = id_mahasiswa;
        this.nama_mahasiswa = nama_mahasiswa;
        this.nilai_mahasiswa = nilai_mahasiswa;
        this.alfabhet_mahasiswa = alfabhet_mahasiswa;
    }

    public int getId_mahasiswa() {
        return id_mahasiswa;
    }

    public void setId_mahasiswa(int id_mahasiswa) {
        this.id_mahasiswa = id_mahasiswa;
    }

    public String getNama_mahasiswa() {
        return nama_mahasiswa;
    }

    public void setNama_mahasiswa(String nama_mahasiswa) {
        this.nama_mahasiswa = nama_mahasiswa;
    }

    public int getNilai_mahasiswa() {
        return nilai_mahasiswa;
    }

    public void setNilai_mahasiswa(int nilai_mahasiswa) {
        this.nilai_mahasiswa = nilai_mahasiswa;
    }

    public String getAlfabhet_mahasiswa() {
        return alfabhet_mahasiswa;
    }

    public void setAlfabhet_mahasiswa(String alfabhet_mahasiswa) {
        this.alfabhet_mahasiswa = alfabhet_mahasiswa;
    }
}

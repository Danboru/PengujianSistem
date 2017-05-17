package id.eightstudio.danboru.pengujiansistem.Activity;

/**
 * Created by danboru on 5/17/17.
 */
public class Perhitungan {

    private float nilaiSatu, nilaiDua, nilaiTiga , rataRataNilai;

    public void setNilaiSatu(float nilaiSatu) {
        this.nilaiSatu = nilaiSatu;
    }

    public void setNilaiDua(float nilaiDua) {
        this.nilaiDua = nilaiDua;
    }

    public void setNilaiTiga(float nilaiTiga) {
        this.nilaiTiga = nilaiTiga;
    }

    public void hitungRataRata(){
        rataRataNilai = (nilaiSatu + nilaiDua + nilaiTiga) / 3;
    }

    public  float getRataRataNilai(){
        return rataRataNilai;
    }


    //Fungsi ini untuk mengambil nilai akhir
    public float getNilaiRataRataAkhir(){
        float hasilAkhir;
        //Set nilai ke hasil akhir dari nilai
        hasilAkhir = rataRataNilai * 40 / 100;
        return hasilAkhir;
    }

    public void

}

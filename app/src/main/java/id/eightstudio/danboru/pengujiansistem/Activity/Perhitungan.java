package id.eightstudio.danboru.pengujiansistem.Activity;

/**
 * Created by danboru on 5/17/17.
 */
public class Perhitungan {

    //Variable yang di gunakan dalm perhitungan
    private float nilaiSatu, nilaiDua, nilaiTiga,
            rataRataNilai,
            nilaiTts, nilaiTas, hasilPersentaseTts, hasilPersentaseTas;

    public void setNilaiSatu(float nilaiSatu) {
        this.nilaiSatu = nilaiSatu;
    }

    public void setNilaiDua(float nilaiDua) {
        this.nilaiDua = nilaiDua;
    }

    public void setNilaiTiga(float nilaiTiga) {
        this.nilaiTiga = nilaiTiga;
    }

    public void setNilaiTts(float nilaiTts) {
        this.nilaiTts = nilaiTts;
    }

    public void setNilaiTas(float nilaiTas) {
        this.nilaiTas = nilaiTas;
    }

    public  float getRataRataNilai(){
        return rataRataNilai;
    }

    public float getNilaiTtsAkhir(){
        return hasilPersentaseTts;
    }

    public float getNilaiTasAkhir(){
        return hasilPersentaseTas;
    }

    //Menghitung rata- rata
    public void hitungRataRata(){
        rataRataNilai = (nilaiSatu + nilaiDua + nilaiTiga) / 3;
    }

    //Menghitung nilai akhir untuk TTS
    public void hitungNilaiTtsAkhir(){
        hasilPersentaseTts =  nilaiTts * 40 / 100;//40% dari nilai keseluruhan
    }

    //Menghitung nilai akhir untuk TASß
    public void hitungNilaiTasAkhir(){
        hasilPersentaseTas =  nilaiTas * 40 / 100;//40% dari nilai keseluruhan
    }

    //Fungsi ini untuk mengambil nilai akhir
    public float getNilaiRataRataAkhir(){
        float hasilAkhir;
        //Set nilai ke hasil akhir dari nilai
        hasilAkhir = rataRataNilai * 40 / 100;//40% dari nilai keseluruhan
        return hasilAkhir;
    }

}

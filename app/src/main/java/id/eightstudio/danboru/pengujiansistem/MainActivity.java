package id.eightstudio.danboru.pengujiansistem;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.eightstudio.danboru.pengujiansistem.Activity.Perhitungan;

public class MainActivity extends Activity {

    //View yang akan di gunakan
    EditText nilaiSatu, nilaiDua, nilaiTiga, nilaiTts, nilaiTas;
    TextView tampilkanHasil, tampilkanAlfabeth;
    Button kirimNilaiInputan;

    String nilaiAlfabhet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Mengisnisialisasi view yang akan di gunakan
        inisialView();
        
        kirimNilaiInputan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if( (nilaiSatu.getText().toString().isEmpty() == true) || (nilaiDua.getText().toString().isEmpty() == true)
                        || (nilaiTiga.getText().toString().isEmpty() == true) || (nilaiTts.getText().toString().isEmpty() == true)
                        || (nilaiTas.getText().toString().isEmpty() == true)
                        ){

                    Toast.makeText(MainActivity.this, "Isi Semua Inputan", Toast.LENGTH_SHORT).show();
                    
                }else {
                    //Toast.makeText(MainActivity.this, "Ada isinya", Toast.LENGTH_SHORT).show();

                    Perhitungan hitung = new Perhitungan();

                    //Set nilai dkedalam variable yang ada di dalam kelas perhitungan
                    hitung.setNilaiSatu(Float.parseFloat(nilaiSatu.getText().toString().trim()));
                    hitung.setNilaiDua(Float.parseFloat(nilaiDua.getText().toString().trim()));
                    hitung.setNilaiTiga(Float.parseFloat(nilaiTiga.getText().toString().trim()));
                    hitung.setNilaiTts(Float.parseFloat(nilaiTts.getText().toString().trim()));
                    hitung.setNilaiTas(Float.parseFloat(nilaiTas.getText().toString().trim()));

                    //Menjalan fungsi perhitungan
                    hitung.hitungRataRata();
                    hitung.hitungNilaiTtsAkhir();
                    hitung.hitungNilaiTasAkhir();

                    //Get nilai yang sebelumnya sudah di hitung
                    float hasilRataRataAkhir = hitung.getNilaiRataRataAkhir();
                    float hasilTtsAkhir = hitung.getNilaiTtsAkhir();
                    float hasilTasAkhir = hitung.getNilaiTtsAkhir();

                    //Set nilai akhir ke textView yang bersangkutan
                    tampilkanHasil.setText(String.valueOf(hasilRataRataAkhir + hasilTtsAkhir + hasilTasAkhir));

                    float alfabethic = hasilRataRataAkhir + hasilTtsAkhir + hasilTasAkhir;
                    cetakNilai(alfabethic);

                    tampilkanAlfabeth.setText(nilaiAlfabhet);

                }
            }
        });

    }


    /***
     * Fungsi ini di gunakan untuk menginisialisasi view yang akan di gunakan
     * */
    public void inisialView(){

        //Button
        kirimNilaiInputan = (Button) findViewById(R.id.btn_kirimDataNilai);

        //EditText
        nilaiSatu = (EditText) findViewById(R.id.edt_nilaiSatu);
        nilaiDua = (EditText) findViewById(R.id.edt_nilaiDua);
        nilaiTiga = (EditText) findViewById(R.id.edt_nilaiTiga);

        nilaiTts = (EditText) findViewById(R.id.edt_nilaiTts);
        nilaiTas = (EditText) findViewById(R.id.edt_nilaiTas);

        //TextView
        tampilkanHasil = (TextView) findViewById(R.id.tv_tampilkanHasil);
        tampilkanAlfabeth = (TextView) findViewById(R.id.tv_tampilkanAlfabhet);


    }

    public void cetakNilai(Float terimaNilai){

        int nilai =  Math.round(terimaNilai);

       if (nilai >=35 && nilai <45 && nilai !=0){
           nilaiAlfabhet = "D";
       }
       else if (nilai >=45 && nilai <55 && nilai !=0) {
           nilaiAlfabhet = "CD";
       }
       else if (nilai >=55 && nilai < 65 && nilai !=0) {
           nilaiAlfabhet = "C";
       }
       else if (nilai >=65 && nilai <= 75 && nilai !=0) {
           nilaiAlfabhet = "BC";
       }
       else if (nilai >75 && nilai <=85 && nilai !=0) {
           nilaiAlfabhet = "B";
       }
       else if (nilai >85 && nilai <90 && nilai !=0) {
           nilaiAlfabhet = "AB";
       }
       else if (nilai >=90 && nilai !=0) {
           nilaiAlfabhet = "A";
       }


    }

}

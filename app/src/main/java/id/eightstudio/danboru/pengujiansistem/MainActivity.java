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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.eightstudio.danboru.pengujiansistem.Activity.Perhitungan;
import id.eightstudio.danboru.pengujiansistem.Adapter.MahasiswaAdapter;
import id.eightstudio.danboru.pengujiansistem.Database.DatabaseHelper;
import id.eightstudio.danboru.pengujiansistem.Provider.MahasiswaProvider;

public class MainActivity extends Activity {

    //View yang akan di gunakan
    EditText nilaiSatu, nilaiDua, nilaiTiga, nilaiTts, nilaiTas;
    TextView tampilkanHasil, tampilkanAlfabeth;
    Button kirimNilaiInputan;
    ListView listMahasiswa;

    String nilaiAlfabhet;

    ArrayList<MahasiswaProvider> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Mengisnisialisasi view yang akan di gunakan
        inisialView();

        final DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        
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

                    db.addMahasiswa(new MahasiswaProvider("Nama", 672014113, 100, "AB"));

                }
            }
        });

        //Memasukkan data kedalam list
        list = db.getAllMahasiswa();
        ListAdapter adapter = new MahasiswaAdapter(MainActivity.this, list);

        //menggunakan findViewBYId di Fragment
        ListView listView = (ListView) findViewById(R.id.lv_listMahasiswa);
        listView.setAdapter(adapter);

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

        //listView
        listMahasiswa = (ListView) findViewById(R.id.lv_listMahasiswa);


    }

    public void cetakNilai(Float terimaNilai){

        int nilai =  Math.round(terimaNilai);

       if (nilai >=35 && nilai <45){
           nilaiAlfabhet = "D";
       }
       else if (nilai >=45 && nilai <55) {
           nilaiAlfabhet = "CD";
       }
       else if (nilai >=55 && nilai < 65) {
           nilaiAlfabhet = "C";
       }
       else if (nilai >=65 && nilai <= 75) {
           nilaiAlfabhet = "BC";
       }
       else if (nilai >75 && nilai <=85) {
           nilaiAlfabhet = "B";
       }
       else if (nilai >85 && nilai <90) {
           nilaiAlfabhet = "AB";
       }
       else if (nilai >=90) {
           nilaiAlfabhet = "A";
       }else {
           nilaiAlfabhet = "Belajar Nak";
       }


    }

}

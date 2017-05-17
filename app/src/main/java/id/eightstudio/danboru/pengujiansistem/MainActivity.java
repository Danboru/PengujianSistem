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

    EditText nilaiSatu, nilaiDua, nilaiTiga;
    TextView tampilkanHasil;
    Button kirimNilaiInputan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        inisialView();
        
        kirimNilaiInputan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if( (nilaiSatu.getText().toString().isEmpty() == true)
                        || (nilaiDua.getText().toString().isEmpty() == true)
                        || (nilaiTiga.getText().toString().isEmpty() == true)
                        ){

                    Toast.makeText(MainActivity.this, "Isi Semua Inputan", Toast.LENGTH_SHORT).show();
                    
                }else {
                    //Toast.makeText(MainActivity.this, "Ada isinya", Toast.LENGTH_SHORT).show();

                    Perhitungan hitung = new Perhitungan();

                    hitung.setNilaiSatu(Float.parseFloat(nilaiSatu.getText().toString().trim()));
                    hitung.setNilaiDua(Float.parseFloat(nilaiDua.getText().toString().trim()));
                    hitung.setNilaiTiga(Float.parseFloat(nilaiTiga.getText().toString().trim()));

                    hitung.hitungRataRata();

                    float hasilRataRataAkhir = hitung.getNilaiRataRataAkhir();

                    tampilkanHasil.setText(String.valueOf(hasilRataRataAkhir));

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

        //TextView
        tampilkanHasil = (TextView) findViewById(R.id.tv_tampilkanHasil);

    }

}

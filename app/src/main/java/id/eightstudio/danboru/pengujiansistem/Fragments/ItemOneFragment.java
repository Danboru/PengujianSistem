package id.eightstudio.danboru.pengujiansistem.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import id.eightstudio.danboru.pengujiansistem.R;

/**
 * Created by danboru on 5/17/17.
 */
public class ItemOneFragment extends Fragment {

    //View yang akan di gunakan
    EditText namaMahasiswa, nimMahasiswa, nilaiSatu, nilaiDua, nilaiTiga, nilaiTts, nilaiTas;
    Button kirimNilaiInputan;

    String nilaiAlfabhet;

    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_item_one, container, false);
        DatabaseHelper database = new DatabaseHelper(getContext());

        final ArrayList<MahasiswaProvider> nimMahasiswaList = database.getAllNim();

        //Button
        kirimNilaiInputan = (Button) view.findViewById(R.id.btn_kirimDataNilai);

        //EditText
        namaMahasiswa = (EditText) view.findViewById(R.id.edt_namaMahasiswa);
        nimMahasiswa = (EditText) view.findViewById(R.id.edt_nimMahasiswa);
        nilaiSatu = (EditText) view.findViewById(R.id.edt_nilaiSatu);
        nilaiDua = (EditText) view.findViewById(R.id.edt_nilaiDua);
        nilaiTiga = (EditText) view.findViewById(R.id.edt_nilaiTiga);

        //EditText yang di gunakan
        nilaiTts = (EditText) view.findViewById(R.id.edt_nilaiTts);
        nilaiTas = (EditText) view.findViewById(R.id.edt_nilaiTas);


        //Database object
        final DatabaseHelper db = new DatabaseHelper(getContext());

        kirimNilaiInputan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Cek input kosong
                if( (namaMahasiswa.getText().toString().isEmpty() == true) || (nimMahasiswa.getText().toString().isEmpty() == true)
                        || (nilaiSatu.getText().toString().isEmpty() == true) || (nilaiDua.getText().toString().isEmpty() == true)
                        || (nilaiTiga.getText().toString().isEmpty() == true) || (nilaiTts.getText().toString().isEmpty() == true)
                        || (nilaiTas.getText().toString().isEmpty() == true)
                        ) {

                    //Jika kosong
                    Toast.makeText(getContext(), "Isi Semua Inputan", Toast.LENGTH_SHORT).show();

                } else {
                    //Jika ada isinya semua

                            //Cek input nilai > 100
                            if ((Integer.parseInt(nilaiSatu.getText().toString()) > 100) || (Integer.parseInt(nilaiDua.getText().toString()) > 100)
                            || (Integer.parseInt(nilaiTiga.getText().toString()) > 100) || (Integer.parseInt(nilaiTts.getText().toString()) > 100)
                            || (Integer.parseInt(nilaiTas.getText().toString()) > 100)
                            ){

                                //Jika > 100
                                Toast.makeText(getContext(),"Nilai Tidak Boleh > 100", Toast.LENGTH_SHORT).show();

                    } else if ((Integer.parseInt(nilaiSatu.getText().toString()) < 1) || (Integer.parseInt(nilaiDua.getText().toString()) < 1)
                                    || (Integer.parseInt(nilaiTiga.getText().toString()) < 1) || (Integer.parseInt(nilaiTts.getText().toString()) < 1)
                                    || (Integer.parseInt(nilaiTas.getText().toString()) < 1)
                                    ) {

                                //Cek jika nilai = 0
                                Toast.makeText(getContext(),"Nilai Tidak Boleh 0", Toast.LENGTH_SHORT).show();
                    } else {

                                //Untuk memastikan nim belum ada di database
                                if(sudahAda(nimMahasiswaList, Integer.parseInt(nimMahasiswa.getText().toString()))){

                                    //Jika nim sudah tersimpan
                                    Toast.makeText(getContext(), "Nim sudah ada", Toast.LENGTH_SHORT).show();
                                    nimMahasiswa.setText("");

                                } else {

                                    //Jika nim belum tersimpan
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

                                    float hasil = hasilRataRataAkhir + hasilTtsAkhir + hasilTasAkhir;

                                    float alfabethic = hasilRataRataAkhir + hasilTtsAkhir + hasilTasAkhir;
                                    cetakNilai(alfabethic);

                                    db.addMahasiswa(new MahasiswaProvider(namaMahasiswa.getText().toString(),
                                            Integer.parseInt(nimMahasiswa.getText().toString()), Math.round(hasil), nilaiAlfabhet));

                                    Toast.makeText(getContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();

                                    //Bersihkan isi fields
                                    namaMahasiswa.setText("");
                                    nimMahasiswa.setText("");
                                    nilaiSatu.setText("");
                                    nilaiDua.setText("");
                                    nilaiTiga.setText("");
                                    nilaiTts.setText("");
                                    nilaiTas.setText("");

                                }
                            }
                }
            }
        });

        return view;
    }

    /**
     * Fungsi untuk mgecheck apakah nim yang di masukkan sudah terdaftar atau tersimpat di database
     * */
    public boolean sudahAda(ArrayList<MahasiswaProvider> nimList, int nim){

        boolean keberadaanMahasiswa = false;
        if (nimList != null){
            for (MahasiswaProvider mahasiswa : nimList){
                if (mahasiswa.getNim_mahasiswa() == nim){
                    keberadaanMahasiswa = true;
                }
            }
        } else {
            keberadaanMahasiswa = false;
        }

        return keberadaanMahasiswa;
    }


    /**
     * Fungsi untuk cetak nilai
     * */
    public void cetakNilai(Float terimaNilai){
        int nilai =  Math.round(terimaNilai);//Konversi float to int

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
            nilaiAlfabhet = "Keterlaluan";
        }

    }


}

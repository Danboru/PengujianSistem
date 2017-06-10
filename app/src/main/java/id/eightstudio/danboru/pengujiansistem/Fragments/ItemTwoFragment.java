package id.eightstudio.danboru.pengujiansistem.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.eightstudio.danboru.pengujiansistem.Adapter.MahasiswaAdapter;
import id.eightstudio.danboru.pengujiansistem.Database.DatabaseHelper;
import id.eightstudio.danboru.pengujiansistem.Provider.MahasiswaProvider;
import id.eightstudio.danboru.pengujiansistem.R;

/**
 * Created by danboru on 5/17/17.
 */
public class ItemTwoFragment extends Fragment {

    //List yang digunakan untuk menampilkan data
    ListView listMahasiswa;
    ArrayList<MahasiswaProvider> list = new ArrayList();//Data yang akan di iterasi

    public static ItemTwoFragment newInstance() {
        ItemTwoFragment fragment = new ItemTwoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_two, container, false);

        //listView
        listMahasiswa = (ListView) view.findViewById(R.id.lv_listMahasiswa);

        DatabaseHelper db = new DatabaseHelper(getContext());//Object Database

        //Memasukkan data kedalam list
        list = db.getAllMahasiswa();
        ListAdapter adapter = new MahasiswaAdapter((Activity) getContext(), list);

        //menggunakan findViewBYId di Fragment
        ListView listView = (ListView) view.findViewById(R.id.lv_listMahasiswa);
        listView.setAdapter(adapter);

        //Action untuk menerima setiap item yang di click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Memanggil fungsi showInfoDialog
                showInfoDialog(position);
            }
        });
        return view;
    }

    /**
     * Fungsi ini di gunakan untu memunculkan dialog yang berisikan informasi yang di ambil dari database
     * */
    private void showInfoDialog(int position) {

        //Pembuatan object database
        DatabaseHelper db = new DatabaseHelper(getContext());
        list = db.getAllMahasiswa();

        //Data row
        final MahasiswaProvider mahasiswa = (MahasiswaProvider) list.get(position);

        //View Dialog berdasarkan context
        final Dialog dialog = new Dialog(getContext());

        //Mengeset judul dialog
        dialog.setTitle("Info Barang");

        //Mengeset layout
        dialog.setContentView(R.layout.popup_layout);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(true);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

        //Inisialisasi View yang akan di gunakan
        final EditText infoNamaMahasiswa = (EditText) dialog.findViewById(R.id.infoNamaMahasiswa);
        final EditText infoNimMahasiswa = (EditText) dialog.findViewById(R.id.infoNimMahasiswa);
        final TextView infoNilaiMahasiswa = (TextView) dialog.findViewById(R.id.infoNilaiMahasiswa);
        Button updateMahasiswaInfo = (Button) dialog.findViewById(R.id.btn_updateMahasiswa);
        Button deleteMahasiswaInfo = (Button) dialog.findViewById(R.id.btn_deleteMahasiswa);

        //Mutator view
        infoNamaMahasiswa.setText(mahasiswa.getNama_mahasiswa().toString().trim());
        infoNimMahasiswa.setText(String.valueOf(mahasiswa.getNim_mahasiswa()));
        infoNilaiMahasiswa.setText(String.valueOf(mahasiswa.getNilai_mahasiswa()));

        //Listener Button
        updateMahasiswaInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = infoNamaMahasiswa.getText().toString();
                String nim = infoNimMahasiswa.getText().toString();
                Integer nilai = Integer.parseInt(infoNilaiMahasiswa.getText().toString());

                //Menangani ketika mahasiswa gagal di update, agar program tidak langsung keluar (Forceclose)
                try {
                    //Menjalankan fungsi update
                    DatabaseHelper db = new DatabaseHelper(getContext());
                    db.updateMahasiswa(new MahasiswaProvider(mahasiswa.getId_mahasiswa(), nama, Integer.parseInt(nim)) );
                    Toast.makeText(getContext(), "Berhasil di Update", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(getContext(), "Kesalahan saat Memanggil fungsi update" , Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();//Menyembunykan popup
            }
        });

        //Fungsi delete barang
        deleteMahasiswaInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Menampilkan alert dialog untuk verifikasi penghapusan barang
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setCanceledOnTouchOutside(false);//Supaya tidak hilang saat di click di luar
                alertDialog.setMessage("Apakah anda yakin ingin menghapus " + mahasiswa.getNama_mahasiswa() + " ?");
                alertDialog.setTitle("VERIFIKASI DELETE");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getContext(), mahasiswa.getNama_mahasiswa() + " Sudah di Hapus", Toast.LENGTH_SHORT).show();

                        //Menjalankan fungsi hapus barang
                        DatabaseHelper db = new DatabaseHelper(getContext());
                        db.deleteMahasiswa(new MahasiswaProvider(mahasiswa.getId_mahasiswa(), null, 0, 0, null));
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Penghapusan Di Batalkan", Toast.LENGTH_SHORT).show();
                    }
                });

                //Menampilkan popup
                alertDialog.show();
                //Menutup popup
                dialog.dismiss();
            }
        });

        //Menampilkan custom dialog
        dialog.show();
    }

}

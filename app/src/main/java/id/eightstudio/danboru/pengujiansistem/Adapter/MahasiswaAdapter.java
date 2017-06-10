package id.eightstudio.danboru.pengujiansistem.Adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.eightstudio.danboru.pengujiansistem.Provider.MahasiswaProvider;
import id.eightstudio.danboru.pengujiansistem.R;

/**
 * Created by danboru on 5/17/17.
 */
public class MahasiswaAdapter extends ArrayAdapter {

    //Variable yagn di gunakan untuk menampung dta dari database
    private ArrayList list;
    private Activity act;

    //Konstruktor dari program
    public MahasiswaAdapter(Activity context, ArrayList object) {
        super(context, R.layout.activity_mahasiswa_adapter,object);
        this.list = object;
        this.act = context;
    }

    /**
     * Ini adalah inner class yang di gunakan untuk penyesuaian view yang akan di gunakan
     * adapter membutuhkannya untuk mengetahui dimana data akan di leteakkan
     * */
    //Sesuaikan dengan data yang akan di tampilkan
    static class ViewHolder {

        protected TextView nama_mahasiswa;
        protected TextView nim_mahasiswa;
        protected TextView nilai_mahasiswa;
        protected TextView aksara_mahasiswa;

    }

    //Kelas yang di override dari parent
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {

            LayoutInflater inflater = act.getLayoutInflater();
            view = inflater.inflate(R.layout.activity_mahasiswa_adapter, null);

            MahasiswaAdapter.ViewHolder holder = new MahasiswaAdapter.ViewHolder();

            //Sesuaikan dengan view yang ada di dalam xml yang di gunakan
            holder.nama_mahasiswa = (TextView) view.findViewById(R.id.txt_rowNamaMahasiswa);
            holder.nim_mahasiswa = (TextView) view.findViewById(R.id.txt_rowNimMahasiswa);
            holder.nilai_mahasiswa = (TextView) view.findViewById(R.id.txt_rowNilaiAkhirMahasiswa);
            holder.aksara_mahasiswa = (TextView) view.findViewById(R.id.txt_rowNilaiAksaraMahasiswa);

            view.setTag(holder);
        }

        MahasiswaAdapter.ViewHolder holder = (MahasiswaAdapter.ViewHolder) view.getTag();
        MahasiswaProvider mahasiswa = (MahasiswaProvider) list.get(position);

        //Perhatikan bener-bener tipe data yang ada di dalam data provider
        holder.nama_mahasiswa.setText(String.valueOf(mahasiswa.getNama_mahasiswa()));
        holder.nim_mahasiswa.setText(String.valueOf(mahasiswa.getNim_mahasiswa()));
        holder.nilai_mahasiswa.setText(String.valueOf(mahasiswa.getNilai_mahasiswa()));
        holder.aksara_mahasiswa.setText(String.valueOf(mahasiswa.getAlfabhet_mahasiswa()));

        //Return view yang sudah di set dengan data holder
        return view;
    }

}

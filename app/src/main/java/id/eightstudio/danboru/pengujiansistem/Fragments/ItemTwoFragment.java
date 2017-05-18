package id.eightstudio.danboru.pengujiansistem.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import id.eightstudio.danboru.pengujiansistem.Adapter.MahasiswaAdapter;
import id.eightstudio.danboru.pengujiansistem.Database.DatabaseHelper;
import id.eightstudio.danboru.pengujiansistem.Provider.MahasiswaProvider;
import id.eightstudio.danboru.pengujiansistem.R;

public class ItemTwoFragment extends Fragment {

    ListView listMahasiswa;
    ArrayList<MahasiswaProvider> list = new ArrayList();

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

        DatabaseHelper db = new DatabaseHelper(getContext());

        //Memasukkan data kedalam list
        list = db.getAllMahasiswa();
        ListAdapter adapter = new MahasiswaAdapter((Activity) getContext(), list);

        //menggunakan findViewBYId di Fragment
        ListView listView = (ListView) view.findViewById(R.id.lv_listMahasiswa);
        listView.setAdapter(adapter);


        return view;
    }
}

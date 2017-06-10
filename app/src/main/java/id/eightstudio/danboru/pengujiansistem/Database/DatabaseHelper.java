package id.eightstudio.danboru.pengujiansistem.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import id.eightstudio.danboru.pengujiansistem.Provider.MahasiswaProvider;

/**
 * Created by danboru on 5/15/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //database attribute
    private static final String DATABASE_NAME = "universitas";
    private static final int DATABASE_VERSION = 1;

    //table list
    private static final String TABLE_MAHASISWA = "mahasiswa";

    //table mahasiswa attr
    private static final String KEY_ID_MAHASISWA = "id_mahasiswa";
    private static final String KEY_NAMA_MAHASISWA = "nama_mahasiswa";
    private static final String KEY_NIM_MAHASISWA = "nim_mahasiswa";
    private static final String KEY_NILAI_MAHASISWA = "nilai_mahasiswa";
    private static final String KEY_ALFABHET_NILAI_MAHASISWA = "alfabeth_nilai";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override //(FIX)
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_MAHASISWA = "CREATE TABLE " + TABLE_MAHASISWA + " ( "
                + KEY_ID_MAHASISWA + " INTEGER PRIMARY KEY NOT NULL, "
                + KEY_NAMA_MAHASISWA + " TEXT, "
                + KEY_NIM_MAHASISWA + " INTEGER, "
                + KEY_NILAI_MAHASISWA + " INTEGER, "
                + KEY_ALFABHET_NILAI_MAHASISWA + " TEXT "
                + ")";

        //menjalankan query yang di buat
        db.execSQL(CREATE_TABLE_MAHASISWA);

    }

    @Override //(FIX)
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_MAHASISWA);

    }

    //Adding New MahasiswaProvider (FIX)
    public void addMahasiswa(MahasiswaProvider mahasiswa) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAMA_MAHASISWA, mahasiswa.getNama_mahasiswa());
        values.put(KEY_NIM_MAHASISWA, mahasiswa.getNim_mahasiswa());
        values.put(KEY_NILAI_MAHASISWA, mahasiswa.getNilai_mahasiswa());
        values.put(KEY_ALFABHET_NILAI_MAHASISWA, mahasiswa.getAlfabhet_mahasiswa());

        // Inserting Row
        db.insert(TABLE_MAHASISWA, null, values);
        db.close(); // Closing database connection
    }

    // Getting single mahasiswa (FIX)
    MahasiswaProvider getMahasiswa(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        //ID - NAMA - NIM - NILAI - ALFABETH
        Cursor cursor = db.query(TABLE_MAHASISWA, new String[]{KEY_ID_MAHASISWA,
                        KEY_NAMA_MAHASISWA, KEY_NIM_MAHASISWA, KEY_NILAI_MAHASISWA,
                        KEY_ALFABHET_NILAI_MAHASISWA}, KEY_ID_MAHASISWA + " = ?",

                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        //Tipe data harus sama dengan yang ada di dalam provider
        MahasiswaProvider mahasiswa = new MahasiswaProvider(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)), cursor.getString(4));

        //Return user
        return mahasiswa;
    }


    // Getting All mahasiswa
    public ArrayList<MahasiswaProvider> getAllMahasiswa() {

        ArrayList<MahasiswaProvider> mahasiswaList = new ArrayList<MahasiswaProvider>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_MAHASISWA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        //ID - NAMA - NIM - NILAI - ALFABETH
        if (cursor.moveToFirst()) {
            do {
                MahasiswaProvider mahasiswa = new MahasiswaProvider();
                mahasiswa.setId_mahasiswa(Integer.parseInt(cursor.getString(0)));
                mahasiswa.setNama_mahasiswa(cursor.getString(1));
                mahasiswa.setNim_mahasiswa(Integer.parseInt(cursor.getString(2)));
                mahasiswa.setNilai_mahasiswa(Integer.parseInt(cursor.getString(3)));
                mahasiswa.setAlfabhet_mahasiswa(cursor.getString(4));

                // Adding mahasiswa to list
                mahasiswaList.add(mahasiswa);

            } while (cursor.moveToNext());
        }
        // return mahasiswa list
        return mahasiswaList;
    }

    // Getting All mahasiswa
    public ArrayList<MahasiswaProvider> getAllNim() {

        ArrayList<MahasiswaProvider> mahasiswaList = new ArrayList<MahasiswaProvider>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_MAHASISWA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        //ID - NAMA - NIM - NILAI - ALFABETH
        if (cursor.moveToFirst()) {
            do {
                MahasiswaProvider mahasiswa = new MahasiswaProvider();
                mahasiswa.setId_mahasiswa(Integer.parseInt(cursor.getString(0)));
                mahasiswa.setNama_mahasiswa(cursor.getString(1));
                mahasiswa.setNim_mahasiswa(Integer.parseInt(cursor.getString(2)));
                mahasiswa.setNilai_mahasiswa(Integer.parseInt(cursor.getString(3)));
                mahasiswa.setAlfabhet_mahasiswa(cursor.getString(4));

                // Adding mahasiswa to list
                mahasiswaList.add(mahasiswa);

            } while (cursor.moveToNext());
        }
        // return mahasiswa list
        return mahasiswaList;
    }


    // Updating Single MahasiswaProvider (FIX)
    public int updateMahasiswa(MahasiswaProvider mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA_MAHASISWA, mahasiswa.getNama_mahasiswa());
        values.put(KEY_NIM_MAHASISWA, mahasiswa.getNim_mahasiswa());
//        values.put(KEY_NILAI_MAHASISWA, mahasiswa.getNilai_mahasiswa());
//        values.put(KEY_ALFABHET_NILAI_MAHASISWA, mahasiswa.getAlfabhet_mahasiswa());

        // updating row
        return db.update(TABLE_MAHASISWA, values, KEY_ID_MAHASISWA + " = ?",
                new String[] { String.valueOf(mahasiswa.getId_mahasiswa()) });
    }

    // Deleting single mahasiswa (FIX)
    public void deleteMahasiswa(MahasiswaProvider mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MAHASISWA, KEY_ID_MAHASISWA + " = ?",
                new String[] { String.valueOf(mahasiswa.getId_mahasiswa()) });
        db.close();
    }

    // Getting mahasiswa Count (FIX)
    public int getUserCount() {
        String countQuery = "SELECT * FROM " + TABLE_MAHASISWA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }


    }

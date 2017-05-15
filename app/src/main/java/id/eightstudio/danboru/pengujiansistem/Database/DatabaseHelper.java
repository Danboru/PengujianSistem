package id.eightstudio.danboru.pengujiansistem.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by danboru on 5/15/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //database attribute
    private static final String DATABASE_NAME = "universitas";
    private static final int DATABASE_VERSION = 1;

    //table list
    private static final String TABLE_MAHASISWA = "mahasiswa";
    private static final String TABLE_ADMIN = "admin";

    //table mahasiswa attr
    private static final String KEY_ID_MAHASISWA = "id_mahasiswa";
    private static final String KEY_NAMA_MAHASISWA = "nama_mahasiswa";
    private static final String KEY_PROGDI_MAHASISWA = "progdi_mahasiswa";
    private static final String KEY_NILAI_MAHASISWA = "nilai_mahasiswa";
    private static final String KEY_ALFABHET_NILAI_MAHASISWA = "alfabeth_nilai";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null ,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_MAHASISWA = "CREATE TABLE " + TABLE_MAHASISWA + "{"
                + KEY_ID_MAHASISWA + " INTEGER PRIMARY KEY NOT NULL, "
                + KEY_NAMA_MAHASISWA + " TEXT, "
                + KEY_NILAI_MAHASISWA + " INTEGER, "
                + KEY_ALFABHET_NILAI_MAHASISWA + "TEXT "
                + "}";

        //menjalankan query yang di buat
        db.execSQL(CREATE_TABLE_MAHASISWA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_MAHASISWA);

    }



}

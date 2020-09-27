package abhishek.com.medreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;


public class CompleteDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Medicine.db";
    public static final String TABLE_NAME = "Report_table";
    public static final String COL_1 = "MED_ID";
    public static final String COL_2 = "MED_NAME";
    public static final String COL_3 = "MED_DES";
    public static final String COL_4 = "NEXT_DAT";
    public static final String COL_5 = "END_DAT";
    public static final String COL_6 = "OCCURRENCE";
    public static final String COL_7 = "MED_STATUS";
    public static final String COL_8 = "C_SNOOZE";

    public CompleteDatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(MED_ID INTEGER,MED_NAME TEXT,MED_DES TEXT,NEXT_DAT DATE,END_DAT DATE,OCCURRENCE TEXT,MED_STATUS TEXT,C_SNOOZE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String med_name, String med_des, Date next_dat, Date end_dat, String occurrence, String c_snooze, String med_status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,med_name);
        contentValues.put(COL_3,med_des);
        contentValues.put(COL_4,String.valueOf(next_dat));
        contentValues.put(COL_5,String.valueOf(end_dat));
        contentValues.put(COL_6,occurrence);
        contentValues.put(COL_7,med_status);
        contentValues.put(COL_8,c_snooze);

//        values.put("med_name", medicine.getMedicineName());
//        values.put("med_des", medicine.getMedicineDes());
//        values.put("next_dat", String.valueOf(medicine.getStartDate()));
//        values.put("end_dat", String.valueOf(medicine.getEndDate()));
//        values.put("occurrence", medicine.getRepeat());
//        values.put("med_status",medicine.getStatus());
//        values.put("c_snooze", medicine.getSnooze());

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}

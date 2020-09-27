package abhishek.com.medreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ExampleData.db";
    public static final String TABLE_NAME = "ExampleDetails_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "MED_NAME";
    public static final String COL_3 = "MED_DES";
    public static final String COL_4 = "C_DATE";
    public static final String COL_5 = "C_TIME";
    public static final String COL_6 = "OCCURRENCE";
    public static final String COL_7 = "E_DATE";
    public static final String COL_8 = "STATUS";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY,DIGIT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    //Insert Medicine details to MedicineDetails_table
    public boolean insertData(String med_name,String med_des,String c_date,String c_time,String e_date,String occurrence,String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,med_name);
        contentValues.put(COL_3,med_des);
        contentValues.put(COL_4,c_date);
        contentValues.put(COL_5,c_time);
        contentValues.put(COL_6,occurrence);
        contentValues.put(COL_7,e_date);
        contentValues.put(COL_8,status);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    // Display User Details in ListView
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT med_name,c_date,c_time FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("med_name",cursor.getString(cursor.getColumnIndex(COL_2)));
//            user.put("c_date",cursor.getString(cursor.getColumnIndex(COL_4)));
//            user.put("c_time",cursor.getString(cursor.getColumnIndex(COL_5)));
//            userList.add(user);
        }
//        Log.d(TAG, DatabaseUtils.dumpCursorToString(cursor));
        return  userList;
    }

    public ArrayList<Medicine> getAllMedicine(){
        ArrayList<Medicine> medicines = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        String selectAllQuery = "SELECT * FROM " + TABLE_NAME ;

        Cursor cursor = database.rawQuery(selectAllQuery, null);

        if(cursor.moveToFirst()){
            do{
                Medicine medicine = new Medicine();

                medicine.setMedicineName(cursor.getString(cursor.getColumnIndex("med_name")));
//                medicine.setStartDate(cursor.getString(Integer.parseInt("c_date")));
//                medicine.setTime(cursor.getString(cursor.getColumnIndex("c_time")));
                medicines.add(medicine);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return medicines;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
//        contentValues.put(COL_3,gender);
//        contentValues.put(COL_4,Stclass);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

//    public Cursor deleteData (String id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cur = db.rawQuery("SELECT ID FROM " + TABLE_NAME + "'WHERE MED_NAME= '",null);
//
//    }
}

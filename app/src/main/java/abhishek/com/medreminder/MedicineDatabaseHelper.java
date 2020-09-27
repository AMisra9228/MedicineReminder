package abhishek.com.medreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static android.os.Build.ID;
import java.sql.*;

public class MedicineDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Medicine.db";
    public static final String TABLE_NAME = "Medicine_table";
    public static final String TABLE_NAME1 = "Report_table";

    public static final String COL_1 = "MED_ID";
    public static final String COL_2 = "MED_NAME";
    public static final String COL_3 = "MED_DES";
    public static final String COL_4 = "NEXT_DAT";
    public static final String COL_5 = "END_DAT";
    public static final String COL_6 = "OCCURRENCE";
    public static final String COL_7 = "MED_STATUS";
    public static final String COL_8 = "C_SNOOZE";


    public MedicineDatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Create Table"," :T0");
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(MED_ID INTEGER PRIMARY KEY,MED_NAME TEXT,MED_DES TEXT,START_DATE TEXT,NEXT_DATE TEXT,END_DATE TEXT,REPETITION INTEGER,OCCURRENCE INTEGER,SLEEP_FROM TEXT,SLEEP_TO TEXT,MED_STATUS TEXT,C_SNOOZE INTEGER)");
        db.execSQL("CREATE TABLE " + TABLE_NAME1 +
                "(MED_ID INTEGER,MED_NAME TEXT,MED_DES TEXT,START_DATE TEXT,NEXT_DATE TEXT,END_DATE TEXT,REPETITION INTEGER,OCCURRENCE INTEGER,SLEEP_FROM TEXT,SLEEP_TO TEXT,MED_STATUS TEXT,C_SNOOZE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    //Insert Medicine details into MedicineDatabase
    public boolean insertMedicine(Medicine medicine){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("med_id",genId());
        Log.d("Success"," :Success");
        values.put("med_name", medicine.getMedicineName());
        values.put("med_des", medicine.getMedicineDes());
        values.put("start_date", medicine.getStartDate());
        values.put("next_date", medicine.getNextDate());
        Log.d("Next Time"," :T1");
        //values.put("next_time", medicine.getNextTime());
        values.put("end_date", medicine.getEndDate());
        Log.d("End Time"," :T2");
        //values.put("end_time", medicine.getEndTime());
        values.put("repetition", medicine.getReptition());
        values.put("occurrence", medicine.getRepeat());
        Log.d("Sleep From"," :T3");
        values.put("sleep_from", medicine.getSleepFrom());
        Log.d("Sleep TO"," :T4");
        values.put("sleep_to", medicine.getSleepTo());
        values.put("med_status",medicine.getStatus());
        values.put("c_snooze", medicine.getSnooze());

        long result = db.insert(TABLE_NAME,null ,values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertMedicine1(MedicineReport medicine1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("med_id",medicine1.getId());
        Log.d("Success"," :Success");
        values.put("med_name", medicine1.getMedicineName());
        values.put("med_des", medicine1.getMedicineDes());
        values.put("next_date", medicine1.getStartDate());
        Log.d("Next Time"," :T1");
        //values.put("next_time", medicine.getNextTime());
        values.put("end_date", medicine1.getEndDate());
        Log.d("End Time"," :T2");
        //values.put("end_time", medicine.getEndTime());
        values.put("occurrence", medicine1.getRepeat());
        Log.d("Sleep From"," :T3");
        values.put("sleep_from", medicine1.getSleepFrom());
        Log.d("Sleep TO"," :T4");
        values.put("sleep_to", medicine1.getSleepTo());
        values.put("med_status",medicine1.getStatus());
        values.put("c_snooze", medicine1.getSnooze());

        long result = db.insert(TABLE_NAME1,null ,values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public int genId(){
        int id1 = 0, id2 = 0, gen_id;
        // generate new id
        id1 = getMaxId("Medicine_table");
        id2 = getMaxId("Report_table");
        if(id2 > id1)
            gen_id = id2 + 1;
        else
            gen_id = id1 + 1;
        Log.d("GenId1 : ",""+id1);
        Log.d("GenId2 : ",""+id2);
        return gen_id;
    }

    public int getMaxId(String TableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableName,null);
        StringBuffer stringBuffer = new StringBuffer();
        if(res!=null && res.getCount()>0){
            String res_id;
            int max_id=0, temp_id;
            while (res.moveToNext()){
                res_id = res.getString(0);
                temp_id = Integer.parseInt(res_id);
                if(temp_id>max_id)
                    max_id=temp_id;
            }
            return max_id;
        }
        else {
            return 0;
        }
    }

    public int medDat(String TABLE_NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        if(res!=null && res.getCount()>0){
            String res_date,res_time;
            int max_id = 0, med_Dat;
            while (res.moveToNext()){
                res_date = res.getString(3);
                res_time = res.getString(4);
                med_Dat =Integer.valueOf(String.valueOf(res_date) + String.valueOf(res_time));
                if(med_Dat>max_id)
                    max_id=med_Dat;
            }
            return max_id;
        }
        else {
            return 0;
        }
    }


    public ArrayList<Medicine> getAllMedicine(){
        Log.d("M_Card 1 :","2");
        ArrayList<Medicine> medicines = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT med_id,med_name,med_des,next_date FROM "+ TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Medicine medicine = new Medicine();

                medicine.setId(cursor.getInt(0));
                medicine.setMedicineName(cursor.getString(1));
                medicine.setMedicineDes(cursor.getString(2));
                medicine.setNextDate(cursor.getString(4));

                medicines.add(medicine);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return medicines;
    }

    public ArrayList<Medicine> getQueryMedicines(String query){
        Log.d("M_Card 2 :","2");
        ArrayList<Medicine> medicines = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Medicine medicine = new Medicine();

                // take all details
                medicine.setId(cursor.getInt(0));
                medicine.setMedicineName(cursor.getString(1));
                medicine.setMedicineDes(cursor.getString(2));
                medicine.setStartDate(cursor.getString(3));

                medicines.add(medicine);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return medicines;
    }

    public ArrayList<Medicine> getQueryComMedicines(String query){
        Log.d("M_Card 2 :","2");
        ArrayList<Medicine> medicines = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Medicine medicine = new Medicine();

                // take all details
                medicine.setId(cursor.getInt(0));
                medicine.setMedicineName(cursor.getString(1));
                medicine.setMedicineDes(cursor.getString(2));
                medicine.setNextDate(cursor.getString(3));
                medicine.setStatus(cursor.getString(4));

                medicines.add(medicine);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return medicines;
    }

    //Edit Medicine details
    public boolean updateData(MedicineUp medicineUp) {

        Log.d("Edit Position 3.1 : ",""+"none");
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues values = new ContentValues();

//        int position = medicineUp.getId();
//        int getId=0;
//        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
//        if(res!=null && res.getCount()>0 && res.moveToFirst()){
//            int count = 0;
//            do{
//                if(count == position) {
//                    getId = Integer.valueOf(res.getString(0));
//                    //Toast.makeText(DummyDataActivity.this,"Delete Successful",Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                count++;
//            } while (res.moveToNext());
//        }
//        else {
//            //Toast.makeText(DummyDataActivity.this,"No data to retrieve",Toast.LENGTH_SHORT).show();
//        }
//


        //MedicineUp medicineUp = new MedicineUp();
        int med_id = medicineUp.getMedicineId();
        String med_name = medicineUp.getMedicineName();
        String med_des = medicineUp.getMedicineDes();
        String start_date = medicineUp.getStartDate();
        String next_date = medicineUp.getNextDate();
        String end_date = medicineUp.getEndDate();
        int repetition = medicineUp.getRepetition();
        int occurrence = medicineUp.getRepeat();
        String sleep_from = medicineUp.getSleepFrom();
        String sleep_to = medicineUp.getSleepTo();
        String med_status = medicineUp.getStatus();
        int c_snooze = medicineUp.getSnooze();
        db.execSQL("update "+TABLE_NAME+" set med_name = '"+med_name+"', med_des = '" +med_des +"',start_date = '" +start_date +"', next_date = '"
                +next_date+"', end_date = '"+end_date+"', repetition = "+repetition+",occurrence = "+occurrence+", sleep_from = '"+sleep_from+"', sleep_to = '"
                +sleep_to+"', med_status = '"+med_status+"', c_snooze = "+c_snooze+ " where med_id = "+med_id);
//        if(result == -1)
//            return false;
//        else
//            return true;
        return true;
    }

    public boolean updateMedicine(String tableName, int med_id, String med_name, String med_des, String start_date,String next_date, String end_date, int repetition,int occurrence, String sleep_from, String sleep_to, String med_status, int c_snooze) {

        Log.d("Edit Position 3.1 : ",""+"none");
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("update "+tableName+" set med_name = '"+med_name+"', med_des = '" +med_des +"', start_date ='" +start_date +"',next_date = '"
                +next_date+"', end_date = '"+end_date+"', repetition = "+repetition+",occurrence = "+occurrence+", sleep_from = '"+sleep_from+"', sleep_to = '"
                +sleep_to+"', med_status = '"+med_status+"', c_snooze = "+c_snooze+ " where med_id = "+med_id);

        return true;
    }

    public void execMyQuery(String myQuery) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(myQuery);
    }

    public Cursor getQueryData(String myQuery) {
        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor res = db1.rawQuery(myQuery,null);
        return res;
    }

    public Cursor DelAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }



    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getAllData1(String TableName) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor res = db1.rawQuery("select * from "+TableName,null);
        return res;
    }

    public Cursor getRowData(int m_id,String TableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableName +" where med_id = " + m_id,null);
        return res;
    }

    public Cursor getRowData0(int m_id,String TableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TableName +" where med_id = " + m_id,null);
        return res;
    }

    public int getRow(int position,String TableName) {

        SQLiteDatabase db = this.getWritableDatabase();
        //MedicineUp medicineUp = new MedicineUp();
        int getId=0;
        Cursor res = db.rawQuery("select * from "+TableName+" /*ORDER BY next_date AESC*/",null);
        if(res!=null && res.getCount()>0 && res.moveToFirst()){
            int count = 0;
            do{
                if(count == position) {
                    getId = res.getInt(0);
                    //Toast.makeText(DummyDataActivity.this,"Delete Successful",Toast.LENGTH_SHORT).show();
                    break;
                }
                count++;
            } while (res.moveToNext());
            //db.close();
        }
        else {
            //Toast.makeText(DummyDataActivity.this,"No data to retrieve",Toast.LENGTH_SHORT).show();
        }

//        Cursor res = db.rawQuery("select * from "+TableName +" where m_id = " + getId,null);
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
//        if(res!=null && res.getCount()>0 && res.moveToFirst()){
//            int count = 0;
//            do{
//                if(count == pos) {
//                    db.execSQL("select from " + TABLE_NAME +" where med_id =" + res.getString(0));
//                    //Toast.makeText(DummyDataActivity.this,"Delete Successful",Toast.LENGTH_SHORT).show();
//                    return res;
//                }
//                count++;
//            } while (res.moveToNext());
//        }
//       //db.rawQuery("select * from "+TABLE_NAME +" where med_id ="+res.getString(0));
        return getId;
    }

    public Cursor getMedData() {
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.rawQuery("select * from" + TABLE_NAME +"where med_name='"+ medicine +"'",null);

        String[] fields = new String[] { "med_name", "med_des", "c_date","e_date","c_time","occurrence","status" };
        Cursor res = db.query(TABLE_NAME, null, "med_name = ?", new String[]{"med_name"}, null, null, null);
        return res;
    }

    public boolean deleteListMedicine(int position) {
        Log.d("DELETE_ITEM: ","Step-1.1");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("DELETE_ITEM: ","Step-1.2");
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        Log.d("DELETE_ITEM: ","Step-1.3");
        if(res!=null && res.getCount()>0 && res.moveToFirst()){
            Log.d("DELETE_ITEM: ","Step-1.4");
            int count = 0;
            do{
                Log.d("DELETE_ITEM: ","Step-1.5");
                if(count == position) {
                    Log.d("DELETE_ITEM: ","Step-1.5.1");
                    db.execSQL("delete from "+TABLE_NAME+" where med_id ="+res.getString(0));
                    Log.d("DELETE_ITEM: ","Step-1.5.2");
                    //Toast.makeText(DummyDataActivity.this,"Delete Successful",Toast.LENGTH_SHORT).show();
                    return true;
                }
                count++;
            } while (res.moveToNext());
            Log.d("DELETE_ITEM: ","Step-1.6");
        }
        else {
            Log.d("DELETE_ITEM: ","Step1.7-");
            //Toast.makeText(DummyDataActivity.this,"No data to retrieve",Toast.LENGTH_SHORT).show();
        }
        Log.d("DELETE_ITEM: ","Step-1.8");
        return false;
    }

    public boolean deleteMedicine(String tableName, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+tableName+" where med_id = "+id);
        return true;
    }

    public boolean tableCreate() {
        return true;
    }

}

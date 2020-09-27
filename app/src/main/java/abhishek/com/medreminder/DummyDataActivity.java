//package abhishek.com.medreminder;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class DummyDataActivity extends AppCompatActivity {
//    private EditText tv_med_name, tv_med_des, tv_start_date, tv_end_date, tv_current_time, tv_occurrence, tv_status, ed_search;
//    private Button btn_show_M,btn_show_R, btn_submit,btn_insert;
//    private TextView result;
//    private String med_name, med_des, start_date, end_date, current_time, occurrence, med_status;
//
//    MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(this);                      //Medicine_database
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dummy_data);
//
//        tv_med_name = (EditText) findViewById(R.id.tv_med_name);
////        tv_med_des = (EditText) findViewById(R.id.tv_med_des);
////        tv_start_date = (EditText) findViewById(R.id.tv_start_date);
////        tv_end_date = (EditText) findViewById(R.id.tv_end_date);
////        tv_current_time = (EditText) findViewById(R.id.tv_current_time);
////        tv_occurrence = (EditText) findViewById(R.id.tv_occurrence);
////        tv_status = (EditText) findViewById(R.id.tv_status);
//////        ed_search = (EditText) findViewById(R.id.ed_search);
//        result = (TextView) findViewById(R.id.result);
//        btn_show_M = (Button) findViewById(R.id.btn_search);
//        btn_show_R = (Button) findViewById(R.id.btn_search1);
////        btn_submit = (Button) findViewById(R.id.btn_submit);
//        btn_insert = (Button) findViewById(R.id.btn_insert);
//
//
////        btn_submit.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                med_name = tv_med_name.getText().toString();
////                med_des = tv_med_des.getText().toString();
////                start_date = tv_start_date.getText().toString();
////                end_date = tv_end_date.getText().toString();
////                current_time = tv_current_time.getText().toString();
////                occurrence = tv_occurrence.getText().toString();
////                med_status = tv_status.getText().toString();
////
////                AddData();                                                 //Add data to Medicine DB
////            }
////        });
//
//        btn_show_M.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ViewAll();
//
//                Log.d("Parse String_1 : ","S1");
//                ParseString db = new ParseString();
//                db.check();
//            }
//        });
//        btn_show_R.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ViewAll1("Report_table");
//            }
//        });
//
//        btn_insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // Medicine_table
//                myDb.insertMedicine(new Medicine("Medicine1", "Abc...", "2018-11-20 05:00","2018-11-20 13:00", 60,"22:00", "06:00","ACTIVE",0));
//                myDb.insertMedicine(new Medicine("Medicine8", "Abc...", "2018-11-20 06:00","2018-11-20 06:05", 60,"22:00", "06:00","ACTIVE",0));
//                myDb.insertMedicine(new Medicine("Medicine3", "Abc...", "2018-11-20 20:00","2018-11-20 16:05", 60,"22:00", "06:00","ACTIVE",0));
//                myDb.insertMedicine(new Medicine("Medicine5", "Abc...", "2018-11-20 11:30","2018-11-20 06:05", 60*24*7,"22:00", "06:00","ACTIVE",0));
//                myDb.insertMedicine(new Medicine("Medicine2", "Abc...", "2018-11-26 17:15","2018-11-22 15:05", 60*24,"22:00", "06:00","ACTIVE",0));
//                myDb.insertMedicine(new Medicine("Medicine7", "Abc...", "2018-11-22 15:00","2018-11-23 06:05", 60,"22:00", "06:00","",0));
//                myDb.insertMedicine(new Medicine("Medicine6", "Abc...", "2018-11-20 15:40","2018-11-22 06:00", 60,"22:00", "06:00","",0));
//                myDb.insertMedicine(new Medicine("Medicine4", "Abc...", "2018-12-06 22:00","2018-12-06 22:00", 0,"22:00", "6:00","ACTIVE",0));
//                // Report_table
//                myDb.insertMedicine1(new MedicineReport(1, "Medicine11", "Abc...", "2018-11-20 05:00","2018-11-22 13:00", 60,"22:00", "06:00","COMPLETED",0));
//                myDb.insertMedicine1(new MedicineReport(2, "Medicine18", "Abc...", "2018-11-20 06:00","2018-11-20 06:05", 60,"22:00", "06:00","COMPLETED",0));
//                myDb.insertMedicine1(new MedicineReport(4,"Medicine13", "Abc...", "2018-11-20 20:00","2018-11-22 16:05", 60,"22:00", "06:00","COMPLETED",0));
//                myDb.insertMedicine1(new MedicineReport(5,"Medicine15", "Abc...", "2018-11-20 11:30","2018-11-22 06:05", 60*24*7,"22:00", "06:00","COMPLETED",0));
//                myDb.insertMedicine1(new MedicineReport(6,"Medicine12", "Abc...", "2018-11-26 17:15","2018-11-22 15:05", 60*24,"22:00", "06:00","COMPLETED",0));
//                myDb.insertMedicine1(new MedicineReport(7,"Medicine17", "Abc...", "2018-11-22 15:00","2018-11-23 06:05", 60,"22:00", "06:00","COMPLETED",0));
//                myDb.insertMedicine1(new MedicineReport(8,"Medicine16", "Abc...", "2018-11-20 15:40","2018-11-22 06:00", 60,"22:00", "06:00","TERMINATED",0));
//                myDb.insertMedicine1(new MedicineReport(9,"Medicine14", "Abc...", "2018-12-06 22:00","2018-12-06 22:00", 0,"22:00", "6:00","COMPLETED",0));
//                myDb.insertMedicine1(new MedicineReport(6,"Medicine12", "Abc...", "2018-11-24 17:00","2018-11-22 15:05", 60*24,"22:00", "06:00","COMPLETED",0));
//
//                Toast.makeText(DummyDataActivity.this, "Dummy Data Inserted ", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
//
//
//    public void ViewAll(){
//        Cursor res = myDb.getAllData();
//        StringBuffer stringBuffer = new StringBuffer();
//        //int c = myDb.medDat();
//        if(res!=null && res.getCount()>0){
//            while (res.moveToNext()){
//                stringBuffer.append("Id: "+res.getString(0)+"\n");
//                stringBuffer.append("Med. Name: "+res.getString(1)+"\n");
//                stringBuffer.append("Med. Des: "+res.getString(2)+"\n");
//                stringBuffer.append("Start Date: "+res.getString(3)+"\n");
//                stringBuffer.append("End Date: "+res.getString(4)+"\n");
//                stringBuffer.append("Occurrence: "+res.getString(5)+"\n");
//                stringBuffer.append("Sleep From: "+res.getString(6)+"\n");
//                stringBuffer.append("Sleep To: "+res.getString(7)+"\n");
//                stringBuffer.append("Med_status: "+res.getString(8)+"\n");
//                stringBuffer.append("C_snooze: "+res.getString(9)+"\n");
//                stringBuffer.append("\n");
//            }
//            result.setText(stringBuffer.toString());
//        }
//        else {
//            Toast.makeText(DummyDataActivity.this,"No data to retrieve",Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void ViewAll1(String TableName){
//        Cursor res = myDb.getAllData1(TableName);
//        StringBuffer stringBuffer = new StringBuffer();
//        //int c = myDb.medDat();
//        if(res!=null && res.getCount()>0){
//            while (res.moveToNext()){
//                stringBuffer.append("Id: "+res.getString(0)+"\n");
//                stringBuffer.append("Med. Name: "+res.getString(1)+"\n");
//                stringBuffer.append("Med. Des: "+res.getString(2)+"\n");
//                stringBuffer.append("Start Date: "+res.getString(3)+"\n");
//                stringBuffer.append("End Date: "+res.getString(4)+"\n");
//                stringBuffer.append("Occurrence: "+res.getString(5)+"\n");
//                stringBuffer.append("Sleep From: "+res.getString(6)+"\n");
//                stringBuffer.append("Sleep To: "+res.getString(7)+"\n");
//                stringBuffer.append("Med_status: "+res.getString(8)+"\n");
//                stringBuffer.append("C_snooze: "+res.getString(9)+"\n");
//                stringBuffer.append("\n");
//            }
//            result.setText(stringBuffer.toString());
//        }
//        else {
//            Toast.makeText(DummyDataActivity.this,"No data to retrieve",Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//
//        super.onBackPressed();
//
//        Intent intent =new Intent(DummyDataActivity.this,MainActivity.class);
//        startActivity(intent);
//        finish();
//        return;
//    }
//
//}

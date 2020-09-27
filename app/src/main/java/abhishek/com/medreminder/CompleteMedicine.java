package abhishek.com.medreminder;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class CompleteMedicine extends AppCompatActivity {

    MedicineDatabaseHelper medicineDatabaseHelper = new MedicineDatabaseHelper(this);
//    private RecyclerView recyclerViewComplete;
//    private RecyclerView.LayoutManager layoutManager;
//    private RecyclerView.Adapter adapter;
//    private ArrayList<Medicine> medicines;

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private RecyclerView.LayoutManager layoutManager1;
    private RecyclerView.LayoutManager layoutManager2;
    private RecyclerView.LayoutManager layoutManager3;
    private ArrayList<Medicine> medicines;
    private RecyclerView.Adapter adapter1;
    private ArrayList<Medicine> medicines1 = new ArrayList<>();
    private RecyclerView.Adapter adapter2;
    private ArrayList<Medicine> medicines2 = new ArrayList<>();
    private RecyclerView.Adapter adapter3;
    private ArrayList<Medicine> medicines3 = new ArrayList<>();

    Toolbar toolbar;

    ParseString ps = new ParseString();
    String myPath, path = "";

    Intent shareIntent;

    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_medicine);

        //customized actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Report");

        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        recyclerViewComplete = findViewById(R.id.recyclerView_complete);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerViewComplete.setLayoutManager(layoutManager);

        recyclerView1 = findViewById(R.id.recyclerViewCom1);
        recyclerView2 = findViewById(R.id.recyclerViewCom2);
        recyclerView3 = findViewById(R.id.recyclerViewCom3);

        layoutManager1 = new LinearLayoutManager(this);
        layoutManager2 = new LinearLayoutManager(this);
        layoutManager3 = new LinearLayoutManager(this);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);

//        report = sharedpreferences.getString("med_report", " ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.download:
                String myQuery = "SELECT MED_NAME, MED_DES, NEXT_DATE, MED_STATUS FROM Report_table ORDER BY MED_ID ASC, NEXT_DATE DESC";
                myExcel(myQuery);
                break;
            case R.id.del_all:
                MedicineDatabaseHelper myDb1 = new MedicineDatabaseHelper(this);
                myDb1.execMyQuery("delete from Report_table where med_status = 'COMPLETED'");
                myDb1.execMyQuery("delete from Report_table where med_status = 'TERMINATED'");


                String query = "SELECT med_id,med_name,med_des,next_date FROM Report_table ORDER BY next_date DESC";
//                medicines = medicineDatabaseHelper.getQueryMedicines(query);
//                adapter = new RecyclerAdapterComplete(medicines,this);
//                recyclerViewComplete.setAdapter(adapter);
                showCards();

                Toast.makeText(this, "Deleted Completed Reports Successfully", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this,CompleteMedicine.class);
//                startActivity(intent);
                break;
            case R.id.share:
                SharedPreferences sharedpreferences2;
                sharedpreferences2 = getSharedPreferences("login_pin", Context.MODE_PRIVATE);
                String filepath = sharedpreferences2.getString("m_report", "My_Report.xls");

                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/csv");
                Log.d("Med_report_Path :", "" + filepath);
                //Uri uri = Uri.fromFile(new File(filepath));
                Uri.parse(filepath);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(filepath));
                Log.d("Med_report_Path :", "" + filepath);
                Toast.makeText(this, "Med_path :" + filepath
                        , Toast.LENGTH_LONG).show();
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Medicine Report File");
                startActivity(Intent.createChooser(shareIntent, "share via"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        Log.d("M_Card 2 :", "1");
        super.onResume();

        Log.d("Medicine Status:", "1");
        showCards();
        Log.d("Medicine Status:", "3");
    }

    public void showCards() {
        Log.d("Medicine Status:", "2");


//        ParseString parseString = new ParseString();
//        String initialDate = parseString.stringBeginCurrentDate();
//        String today = parseString.addDate(initialDate, 1*24*60);
//        String tomorrow = parseString.addDate(initialDate, 2*24*60);

        String query = "SELECT med_id,med_name,med_des,next_date,med_status FROM Report_table ORDER BY next_date DESC";

        medicines = medicineDatabaseHelper.getQueryComMedicines(query);

        medicines1.clear();
        medicines2.clear();
        medicines3.clear();

        int c1 = 0, c2 = 0, c3 = 0;
        Log.d("Medicine Status:", "2.1");
        for (int i = 0; i < medicines.size(); i++) {
            String tempStatus = medicines.get(i).getStatus();
            Log.d("Medicine Status: ", tempStatus);

            Medicine medicine0 = new Medicine();

            medicine0.setId(medicines.get(i).getId());
            medicine0.setMedicineName(medicines.get(i).getMedicineName());
            medicine0.setMedicineDes(medicines.get(i).getMedicineDes());
            medicine0.setStartDate(medicines.get(i).getStartDate());
//            medicine0.setStatus(medicines.get(i).getStatus());

            if (tempStatus.equals("COMPLETED")) {
                medicines1.add(medicine0);
                c1++;
            } else if (tempStatus.equals("TERMINATED")) {
                medicines2.add(medicine0);
                c2++;
            } else {
                medicines3.add(medicine0);
                c3++;
            }
        }

        adapter1 = new RecyclerAdapterComplete(medicines1, this);
        recyclerView1.setAdapter(adapter1);
        adapter2 = new RecyclerAdapterComplete(medicines2, this);
        recyclerView2.setAdapter(adapter2);
        adapter3 = new RecyclerAdapterComplete(medicines3, this);
        recyclerView3.setAdapter(adapter3);

//        adapter = new RecyclerAdapterComplete(medicines,this);
//        recyclerViewComplete.setAdapter(adapter);
    }

    //handle onBack pressed(go previous activity)
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    //device onBack pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(CompleteMedicine.this, MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
        return;
    }

//    private void exportDB() {
//
//        MedicineDatabaseHelper dbhelper = new MedicineDatabaseHelper(getApplicationContext());
//        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
//        if (!exportDir.exists())
//        {
//            exportDir.mkdirs();
//        }
//
//        File file = new File(exportDir, "csvname.csv");
//        try
//        {
//            file.createNewFile();
//            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
//            SQLiteDatabase db = dbhelper.getReadableDatabase();
//            Cursor curCSV = db.rawQuery("select * from Report_table" ,null);
//            csvWrite.writeNext(curCSV.getColumnNames());
////            while(curCSV.moveToNext())
////            {
////                //Which column you want to exprort
////                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
////                csvWrite.writeNext(arrStr);
////            }
//            while(curCSV.moveToNext()) {
//                String arrStr[]=null;
//                String[] mySecondStringArray = new String[curCSV.getColumnNames().length];
//                for(int i=0;i<curCSV.getColumnNames().length;i++)
//                {
//                    mySecondStringArray[i] =curCSV.getString(i);
//                }
//                csvWrite.writeNext(mySecondStringArray);
//            }
//            csvWrite.close();
//            curCSV.close();
//        }
//        catch(Exception sqlEx)
//        {
//            Log.e("DownloadReport", sqlEx.getMessage(), sqlEx);
//        }
//    }

    //Download report
    public void myExcel(String query) {
        SharedPreferences sharedpreferences1;

        if (checkPermission()) {

            //MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(this);
            final Cursor cursor = medicineDatabaseHelper.getQueryData(query);

            File sd = Environment.getExternalStorageDirectory();
            String csvFile = "Med_Report_" + ps.stringCurrentDateWS() + ".xls";

            myPath = sd.getAbsolutePath();
            File directory = new File(myPath);

//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putString("Med_Report_", ".xls"+directory);
//        editor.commit();

            //create directory if not exist
            if (!directory.isDirectory()) {
                directory.mkdirs();
            }
            try {

                //file path
                File file = new File(directory, csvFile);

                // get absolute path
                path = file.getAbsolutePath();

                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setLocale(new Locale("en", "EN"));
                WritableWorkbook workbook;
                workbook = Workbook.createWorkbook(file, wbSettings);
                String filename = "Med_Report_" + ps.stringCurrentDateWS() + ".xls";
                //Excel sheet name. 0 represents first sheet
                WritableSheet sheet = workbook.createSheet(filename, 0);

                sheet.addCell(new Label(0, 0, "SERIAL_NO"));
                sheet.addCell(new Label(1, 0, "MED_NAME")); // column and row
                sheet.addCell(new Label(2, 0, "MED_DESCRIPTION"));
                sheet.addCell(new Label(3, 0, "DATE_TIME"));
                sheet.addCell(new Label(4, 0, "STATUS"));
                int count = 0;

                if (cursor.moveToFirst()) {
                    do {
                        count++;

                        String med_name = cursor.getString(cursor.getColumnIndex("MED_NAME"));
                        String med_purpose = cursor.getString(cursor.getColumnIndex("MED_DES"));
                        String med_date = cursor.getString(cursor.getColumnIndex("NEXT_DATE"));
                        String med_status = cursor.getString(cursor.getColumnIndex("MED_STATUS"));

                        int i = cursor.getPosition() + 1;

                        sheet.addCell(new Label(0, i, "" + count));
                        sheet.addCell(new Label(1, i, med_name));
                        sheet.addCell(new Label(2, i, med_purpose));
                        sheet.addCell(new Label(3, i, med_date));
                        sheet.addCell(new Label(4, i, med_status));
                    } while (cursor.moveToNext());
                }
                //closing cursor
                cursor.close();
                workbook.write();
                workbook.close();

                sharedpreferences1 = getSharedPreferences("login_pin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences1.edit();
                editor.putString("m_report", myPath + "/" + filename);
                editor.commit();
                Toast.makeText(getApplication(), "Data Exported to " + myPath + "/" + filename, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
//            Toast.makeText(CompleteMedicine.this,"Kindly allow storage permission",Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);


        }

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean readStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (readStorage && writeStorage) {
                        Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_LONG).show();
                        String myQuery = "SELECT MED_NAME, MED_DES, NEXT_DATE, MED_STATUS FROM Report_table ORDER BY MED_ID ASC, NEXT_DATE DESC";
                        myExcel(myQuery);
                    } else {

                        Toast.makeText(this, "Storage Permission Denied, Allow storage permission to download report.", Toast.LENGTH_LONG).show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
                                showMessageOKCancel("You need to allow permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}
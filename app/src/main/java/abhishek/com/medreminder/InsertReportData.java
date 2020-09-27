package abhishek.com.medreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class InsertReportData extends AppCompatActivity {

    MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(InsertReportData.this);

    public boolean AddData(int med_id,String med_name, String med_des, String next_date, String end_date, int occurrence, String from, String to, String med_status, int c_snooze) {

//            boolean id = myDb.insertMedicine1(new MedicineReport(med_id,med_name, med_des, next_date, end_date, occurrence, from, to, med_status, c_snooze));
//            myDb.close();
            Toast.makeText(this,"Write",Toast.LENGTH_SHORT).show();
            return true;
    }
}

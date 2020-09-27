package abhishek.com.medreminder;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class SaveData extends AppCompatActivity {


    String myPath;

    ParseString ps = new ParseString();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String myQuery = "SELECT MED_NAME, MED_DES, NEXT_DATE, MED_STATUS FROM Report_table";
        myExcel(myQuery);

    }

    public boolean myExcel(String query) {

        MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(this);
        final Cursor cursor = myDb.getQueryData(query);

        File sd = Environment.getExternalStorageDirectory();
        String csvFile = "Med_Report_" + ps.stringCurrentDate() + ".xls";

        myPath = sd.getAbsolutePath();
        File directory = new File(myPath);
        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {

            //file path
            File file = new File(directory, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet("Report_" + ps.stringCurrentDate(), 0);

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
            Toast.makeText(getApplication(), "Data Exported to " + myPath, Toast.LENGTH_SHORT).show();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

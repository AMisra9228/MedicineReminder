package abhishek.com.medreminder;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;

public class MedicineUp {
    public static final String TABLE_NAME = "Medicine_table";
    public static final String TABLE_NAME1 = "Report_table";
    private String med_name, med_des, start_date,next_date, end_date, next_time,end_time,sleep_from,sleep_to,med_status;
    private Date next_dat, end_dat;
    private int med_id,c_snooze,repetition,occurrence;

    public MedicineUp(int med_id,String med_name, String med_des, String start_date,String next_date,String end_date,int repetition,int occurrence,String sleep_from,String sleep_to,String med_status,int c_snooze) {
        this.med_id = med_id;
        this.med_name = med_name;
        this.med_des = med_des;
        this.start_date = start_date;
        this.next_date = next_date;
        //this.next_time = next_time;
        this.end_date = end_date;
        //this.end_time = end_time;
        this.repetition = repetition;
        this.occurrence = occurrence;
        this.sleep_from = sleep_from;
        this.sleep_to = sleep_to;
        this.med_status = med_status;
        this.c_snooze = c_snooze;
    }

    public MedicineUp() {
    }

    public int getMedicineId() {
        return med_id;
    }

    public void setMedicineId(int med_id) {
        this.med_id = med_id;
    }

    public String getMedicineName() {
        return med_name;
    }

    public void setMedicineName(String med_name) {
        this.med_name = med_name;
    }

    public String getMedicineDes() {
        return med_des;
    }

    public void setMedicineDes(String med_des) {
        this.med_des = med_des;
    }

    public String getNextDate() {
        return next_date;
    }

    public void setNextDate(String next_date) {
        this.next_date = next_date;
    }

    public String getNextTime() {
        return next_time;
    }

    public void setNextTime(String next_time) {
        this.next_time = next_time;
    }

    public String getStartDate() {
        return start_date;
    }

    public void setStartDate(String start_date) {
        this.start_date = start_date;
    }

    public String getEndDate() {
        return end_date;
    }

    public void setEndDate(String end_date) {
        this.end_date = end_date;
    }

    public String getEndTime() {
        return end_time;
    }

    public void setEndTime(String end_time) {
        this.end_time = end_time;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public int getRepeat() {
        return occurrence;
    }

    public void setRepeat(int occurrence) {
        this.occurrence = occurrence;
    }

    public String getSleepFrom() {
        return sleep_from;
    }

    public void setSleepFrom(String sleep_from) {
        this.sleep_from = sleep_from;
    }

    public String getSleepTo() {
        return sleep_to;
    }

    public void setSleepTo(String sleep_to) {
        this.sleep_to = sleep_to;
    }

    public int getSnooze() {
        return c_snooze;
    }

    public void setSnooze(int c_snooze) {
        this.c_snooze = c_snooze;
    }

    public void setStatus(String med_status) {
        this.med_status = med_status;
    }

    public String getStatus() {
        Log.d("Edit Position 3.3.1 : ",""+med_status);
        return med_status;
    }

}

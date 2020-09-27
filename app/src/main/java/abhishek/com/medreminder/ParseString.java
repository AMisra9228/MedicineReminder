package abhishek.com.medreminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class ParseString extends AppCompatActivity {

    String date1 ="15-12-2018 12:00",date2="15-12-2018 11:00";
    int dd,mmm,yyyy,hh,mm;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //parse(date1);
//    }

    public void parse(String date_1){
        Log.d("Parse String_1.2","S3");
        yyyy = Integer.valueOf(date_1.substring(0,4));
        Log.d("Parse String_1.2","S3.1"+yyyy);
        mmm = Integer.valueOf(date_1.substring(5,7));
        Log.d("Parse String_1.2","S3.2"+mmm);
        dd = Integer.valueOf(date_1.substring(8,10));
        Log.d("Parse String_1.2","S3.3"+dd);
        hh = Integer.valueOf(date_1.substring(11,13));
        Log.d("Parse String_1.2","S3.4"+hh);
        mm = Integer.valueOf(date_1.substring(14,16));
        Log.d("Parse String_1.2","S3.5"+mm);

//        Toast.makeText(this,"DD"+dd+"MMM"+mmm+"YYYY"+yyyy,Toast.LENGTH_SHORT).show();
    }

    public int parseYear(String date_1){
        yyyy = Integer.valueOf(date_1.substring(0,4));
        return yyyy;
    }

    public int parseMonth(String date_1){
        mmm = Integer.valueOf(date_1.substring(5,7));
        return mmm;
    }

    public int parseDate(String date_1){
        dd = Integer.valueOf(date_1.substring(8,10));
        return dd;
    }

    public int parseHour(String date_1){
        hh = Integer.valueOf(date_1.substring(11,13));
        return hh;
    }

    public int parseMinute(String date_1){
        mm = Integer.valueOf(date_1.substring(14,16));
        return mm;
    }

    public int compareDate(String date_1,String date_2){

        int dd1,mmm1,yyyy1,hh1,mm1,dd2,mmm2,yyyy2,hh2,mm2,result=-2;

        dd1 = parseDate(date_1);
        mmm1 = parseMonth(date_1);
        yyyy1 = parseYear(date_1);
        hh1 = parseHour(date_1);
        mm1 = parseMinute(date_1);

        dd2 = parseDate(date_2);
        mmm2 = parseMonth(date_2);
        yyyy2 = parseYear(date_2);
        hh2 = parseHour(date_2);
        mm2 = parseMinute(date_2);

        Log.d("Parse String_1.01","X");
        Log.d("Parse String_1.D1","X"+dd1);
        Log.d("Parse String_1.M1","X"+mmm1);
        Log.d("Parse String_1.Y1","X"+yyyy1);
        Log.d("Parse String_1.h1","X"+hh1);
        Log.d("Parse String_1.m1","X"+mm1);
        Log.d("Parse String_1.01","X");
        Log.d("Parse String_1.D1","X"+dd2);
        Log.d("Parse String_1.M1","X"+mmm2);
        Log.d("Parse String_1.Y1","X"+yyyy2);
        Log.d("Parse String_1.h1","X"+hh2);
        Log.d("Parse String_1.m1","X"+mm2);

        // if  date1 < date2 then result -1
        // if date1 = date2 then result 0
        // if date1 > date2 then result +1
        if(yyyy1 < yyyy2) {
            result = -1;
        }
        else if (yyyy1 > yyyy2) {
            result = 1;
        }
        else if (yyyy1 == yyyy2) {
            if(mmm1 < mmm2) {
                result = -1;
            }
            else if (mmm1 > mmm2) {
                result = 1;
            }
            else if (mmm1 == mmm2) {
                if(dd1 < dd2) {
                    result = -1;
                }
                else if (dd1 > dd2) {
                    result = 1;
                }
                else if (dd1 == dd2) {
                    if(hh1 < hh2) {
                        result = -1;
                    }
                    else if (hh1 > hh2) {
                        result = 1;
                    }
                    else if (hh1 == hh2){
                        if(mm1 < mm2) {
                            result = -1;
                        }
                        else if (mm1 > mm2) {
                            result = 1;
                        }
                        else if (mm1 == mm2) {
                            result = 0;
                        }
                        else
                            result = 2;
                    }
                }
            }
        }

        return result;
    }

    public String addDate(String date_1, long minute) {
        String addedDate = "";
        int day;
        int dd1,mmm1, yyyy1, hh1, mm1, dd2,mmm2, yyyy2, hh2, mm2;

        dd1 = parseDate(date_1);
        mmm1 = parseMonth(date_1);
        yyyy1 = parseYear(date_1);
        hh1 = parseHour(date_1);
        mm1 = parseMinute(date_1);

        long reminder = minute;
        day = (int)reminder/(60*24);
        reminder = reminder - (day * 60 *24);
        hh2 = (int)reminder/60;
        reminder = reminder - (hh2 * 60);
        mm2 = (int)reminder;
        reminder = reminder - mm2;
        Log.d("Date_Addition : hr1 - ",""+hh1);
        Log.d("Date_Addition : hr2 - ",""+hh2);

        mm1 = mm1 + mm2;
        Log.d("Date_Addition : min - ",""+mm1);
        if(mm1 >= 60) {
            hh2 = hh2 + 1;
            mm1 = mm1 - 60;
            Log.d("Minute_01 :",""+mm1);
        }
        Log.d("Date_Addition : min - ",""+mm1);

        hh1 = hh1 + hh2;
        Log.d("Date_Addition : hr - ",""+hh1);
        if(hh1 >= 24) {
            day = day + 1;
            hh1 = hh1 - 24;
        }
        Log.d("Date_Addition : hr - ",""+hh1);

        addedDate = addDays(dd1, mmm1, yyyy1, day) +" "+ stringShortTime(hh1, mm1);

        Log.d("Date_Addition : ",addedDate);

        return addedDate;
    }

    public String stringDate(int dd1, int mmm1, int yyyy1, int hh1, int mm1) {
        String sDate = "";

        int counter = 0;
        Log.d("1. date1: ", sDate);

        if(counter == 0) {
            if(yyyy1<10)
                sDate = sDate + ("000"+yyyy1+"-");
            else if(yyyy1<100)
                sDate = sDate + ("00"+yyyy1+"-");
            else if(yyyy1<1000)
                sDate = sDate + ("0"+yyyy1+"-");
            else
                sDate = sDate + (""+yyyy1+"-");
            counter = 1;
        }
        Log.d("2. date1: ", sDate);

        if(counter == 1) {
            if(mmm1<10)
                sDate = sDate + ("0"+(mmm1)+"-");
            else
                sDate = sDate + (""+(mmm1)+"-");
            counter = 2;
        }
        Log.d("3. date1: ", sDate);

        if(counter == 2) {
            if (dd1 < 10)
                sDate = sDate + ("0" + dd1 + " ");
            else
                sDate = sDate + ("" + dd1 + " ");
            counter = 3;
        }
        Log.d("4. date1: ",sDate);

        if(counter == 3) {
            if (hh1<10)
                sDate = sDate + ("0"+hh1+":");
            else
                sDate = sDate + (""+hh1+":");
            counter = 4;
        }
        Log.d("5. date1: ", sDate);

        if(counter == 4) {
            if (mm1<10)
                sDate = sDate + ("0"+mm1+"");
            else
                sDate = sDate + (""+mm1+"");
            counter = 5;
        }
        Log.d("6. date1: ", sDate);

        return sDate;
    }

    public String stringShortDate(int dd1, int mmm1, int yyyy1) {
        String sSDate = "";

        int counter = 0;
        Log.d("1. date1: ", sSDate);

        if(counter == 0) {
            if(yyyy1<10)
                sSDate = sSDate + ("000"+yyyy1+"-");
            else if(yyyy1<100)
                sSDate = sSDate + ("00"+yyyy1+"-");
            else if(yyyy1<1000)
                sSDate = sSDate + ("0"+yyyy1+"-");
            else
                sSDate = sSDate + (""+yyyy1+"-");
            counter = 1;
        }
        Log.d("2. date1: ", sSDate);

        if(counter == 1) {
            if(mmm1<10)
                sSDate = sSDate + ("0"+(mmm1)+"-");
            else
                sSDate = sSDate + (""+(mmm1)+"-");
            counter = 2;
        }
        Log.d("3. date1: ", sSDate);

        if(counter == 2) {
            if (dd1 < 10)
                sSDate = sSDate + ("0" + dd1 + "");
            else
                sSDate = sSDate + ("" + dd1 + "");
            counter = 3;
        }
        Log.d("4. date1: ",sSDate);

        return sSDate;
    }

    public String stringShortTime(int hh1, int mm1) {
        String sSTime = "";

        int counter = 0;
        Log.d("1. date1: ", sSTime);

        if(counter == 0) {
            if (hh1<10)
                sSTime = sSTime + ("0"+hh1+":");
            else
                sSTime = sSTime + (""+hh1+":");
            counter = 1;
        }
        Log.d("5. date1: ", sSTime);

        if(counter == 1) {
            if (mm1<10)
                sSTime = sSTime + ("0"+mm1+"");
            else
                sSTime = sSTime + (""+mm1+"");
            counter = 2;
        }
        Log.d("6. date1: ", sSTime);

        return sSTime;
    }

    // Return if year is leap year or not.
    boolean isLeap(int y)
    {
        if (y%100 != 0 && y%4 == 0 || y %400 == 0)
            return true;

        return false;
    }

    // Given a date, returns number of days elapsed
    // from the  beginning of the current year (1st
    // jan).
    int offsetDays(int d, int m, int y)
    {
        int offset = d;

        switch (m - 1)
        {
            case 11:
                offset += 30;
            case 10:
                offset += 31;
            case 9:
                offset += 30;
            case 8:
                offset += 31;
            case 7:
                offset += 31;
            case 6:
                offset += 30;
            case 5:
                offset += 31;
            case 4:
                offset += 30;
            case 3:
                offset += 31;
            case 2:
                offset += 28;
            case 1:
                offset += 31;
        }

        if (isLeap(y) && m > 2)
            offset += 1;

        return offset;
    }

    // Given a year and days elapsed in it, finds
    // date by storing results in d and m.
    int revoffsetDays(int offset, int y)
    {
        int month[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeap(y))
            month[2] = 29;

        int i;
        for (i = 1; i <= 12; i++)
        {
            if (offset <= month[i])
                break;
            offset = offset - month[i];
        }

        return ((offset*100)+i);

    }

    // Add x days to the given date.
    String addDays(int d1, int m1, int y1, int x)
    {
        int offset1 = offsetDays(d1, m1, y1);
        int remDays = isLeap(y1)?(366-offset1):(365-offset1);

        // y2 is going to store result year and
        // offset2 is going to store offset days
        // in result year.
        int y2, offset2=offset1;
        if (x <= remDays)
        {
            y2 = y1;
            offset2 += x;
        }

        else
        {
            // x may store thousands of days.
            // We find correct year and offset
            // in the year.
            x -= remDays;
            y2 = y1 + 1;
            int y2days = isLeap(y2)?366:365;
            while (x >= y2days)
            {
                x -= y2days;
                y2++;
                y2days = isLeap(y2)?366:365;
            }
            offset2 = x;
        }

        // Find values of day and month from
        // offset of result year.
        int m = 0, d = 0;
        int dm = revoffsetDays(offset2, y2);
        d = dm/100;
        m = dm - (d*100);

        String result_date = stringShortDate(d, m, y2);

        return result_date;
    }

    public String stringCurrentDate() {
        String currentDate ="";
        int mYear, mMonth, mDay, mHour, mMinute;

        // Get next Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        currentDate = stringDate(mDay, mMonth, mYear, mHour, mMinute);

        return currentDate;
    }

    // Get current date without space yyyymmddhhmmss
    public String stringCurrentDateWS() {
        String currentDateWS ="";
        int mYear, mMonth, mDay, mHour, mMinute, mSecond;

        // Get next Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mSecond = c.get(Calendar.SECOND);

        currentDateWS = ""+mYear+mMonth+mDay+mHour+mMinute+mSecond;

        return currentDateWS;
    }

    public String stringBeginCurrentDate() {
        String beginCDate ="";
        int mYear, mMonth, mDay, mHour, mMinute;

        // Get next Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = 0;
        mMinute = 0;

        beginCDate = stringDate(mDay, mMonth, mYear, mHour, mMinute);

        return beginCDate;
    }

    // Main function which finds difference
    public int diffenceTime(int hh1, int mm1, int hh2, int mm2)
    {

        // change to (eg. 2:21 --> 221, 00:23 --> 23)
        int time1 = (hh1*100)+mm1;
        int time2 = (hh2*100)+mm2;

        // difference between hours
        int hourDiff = time2 / 100 - time1 / 100 - 1;

        // difference between minutes
        int minDiff = time2 % 100 + (60 - time1 % 100);

        if (minDiff >= 60) {
            hourDiff++;
            minDiff = minDiff - 60;
        }

        // convert answer again in string with ':'
        int result = (hourDiff*60) + minDiff;

        if(result < 0)
            result = (24*60) + result;

        return result;
    }

    public int parseSHour(String date_1){
        hh = Integer.valueOf(date_1.substring(0,2));
        return hh;
    }

    public int parseSMinute(String date_1){
        mm = Integer.valueOf(date_1.substring(3,5));
        return mm;
    }

    public boolean checkSleep(String next_date, String sleep_from, String sleep_to) {
        if(sleep_from.equals(sleep_to))
            return false;

        int n_hh, n_mm, f_hh, f_mm, t_hh, t_mm;
        n_hh = parseHour(next_date);
        n_mm = parseHour(next_date);
        f_hh = parseSHour(sleep_from);
        f_mm = parseSMinute(sleep_from);
        t_hh = parseSHour(sleep_to);
        t_mm = parseSMinute(sleep_to);

        int s = diffenceTime(f_hh, f_mm, t_hh, t_mm);
        int a = diffenceTime(f_hh, f_mm, n_hh, n_mm);
        Log.d("Sleep_s",""+s);
        Log.d("Sleep_a",""+a);

        if(a > s)
            return false;
        else
            return true;
    }

    public void check(){
//        String addedDate = addDate("07-12-2018 23:05", (10555*24*60)+124);
//        Log.d("Date_Addition : ",addedDate);
    }
}

package abhishek.com.medreminder;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Boolean.TRUE;


public class AddReminderActivity extends AppCompatActivity {

    private RelativeLayout rl_add;
    private EditText med_name1,med_des1;
    private TextView tv_next_date,tv_next_time,tv_end_time,tv_end_date,tv_ed_title,tv_ed_title1,tv_ed_view,tv_ed_view1,tv_from,tv_to;
    private ImageButton next_dat1,s_time,btn_end_date,next_time,end_time;
    private Button btn_submit,btn_cancel;
    private Spinner repeat,repeat2;
    private int mYear, mMonth, mDay, mHour, mMinute, nYear, nMonth, nDay, nHour, nMinute, eYear, eMonth, eDay, eHour, eMinute;
    public int interval,repOp = 1;
    String med_name,med_des,start_date,next_date,end_date,next_time1,end_time1,from,to,med_status,occurance1,fr;
    Date next_dat,end_dat;
    public int repetition,occurrence,occur_mins,c_snooze;
    public int n_yyyy,n_mmm,n_dd,n_hh,n_mm,e_yyyy,e_mmm,e_dd,e_hh,e_mm;
    private RadioButton r1,r2;
    private RadioGroup r;
    private RelativeLayout rep1,rep2;

    //Medicine database
    MedicineDatabaseHelper medicineDatabaseHelper = new MedicineDatabaseHelper(this);

    //ParseString
    ParseString parseString = new ParseString();

    SharedPreferences sharedpreferences;
//    String mypreference = "login_pin";

    Speech sp = new Speech();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminder);

        sp.Speech(AddReminderActivity.this,"");

        rl_add = (RelativeLayout) findViewById(R.id.rl_add);
        med_name1 = (EditText) findViewById(R.id.med_name);
        med_des1 = (EditText) findViewById(R.id.med_des);
        next_dat1 = (ImageButton) findViewById(R.id.s_date);
        btn_end_date = (ImageButton) findViewById(R.id.btn_end_date);
        tv_next_date =(TextView) findViewById(R.id.tv_next_date);
        tv_end_date =(TextView) findViewById(R.id.tv_end_date);
        tv_next_time =(TextView) findViewById(R.id.tv_next_time);
        tv_end_time =(TextView) findViewById(R.id.tv_end_time);
        tv_ed_title =(TextView) findViewById(R.id.tv_ed_title);
        tv_ed_title1 =(TextView) findViewById(R.id.tv_ed_title1);
        tv_ed_view =(TextView) findViewById(R.id.tv_ed_view);
        tv_ed_view1 =(TextView) findViewById(R.id.tv_ed_view1);
        next_time =(ImageButton) findViewById(R.id.next_time);
        end_time =(ImageButton) findViewById(R.id.end_time);

        repeat = (Spinner) findViewById(R.id.repeat);
        repeat2 = (Spinner) findViewById(R.id.repeat2);

        tv_from =(TextView) findViewById(R.id.tv_from);
        tv_to =(TextView) findViewById(R.id.tv_to);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        r = (RadioGroup) findViewById(R.id.btn_r);
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);

        rep1 = (RelativeLayout) findViewById(R.id.rep1);
        rep2 = (RelativeLayout) findViewById(R.id.rep2);


        String currentDate, defaultDate, showDate, showTime;

//        sharedpreferences = getSharedPreferences("login_pin", Context.MODE_PRIVATE);
//        String m_pin = sharedpreferences.getString("login_pin", "1991");
//        sharedpreferences = getSharedPreferences("user_name", Context.MODE_PRIVATE);
//        String Name = sharedpreferences.getString("user_name", "Your Name");

        sharedpreferences = getSharedPreferences("sleep_from", Context.MODE_PRIVATE);
        from = sharedpreferences.getString("sleep_from", "00:00");
        sharedpreferences = getSharedPreferences("sleep_to", Context.MODE_PRIVATE);
        to = sharedpreferences.getString("sleep_to", "00:00");
        sharedpreferences = getSharedPreferences("sleep_to", Context.MODE_PRIVATE);
        fr = sharedpreferences.getString("sleep_fto", "00:00");

        showTime = fr;
        tv_from.setText(showTime);

        showTime = to;
        tv_to.setText(showTime);

        currentDate = parseString.stringCurrentDate();

        defaultDate = parseString.addDate(currentDate, (long) 10);
        showDate = defaultDate.substring(0,10);
        showTime = defaultDate.substring(11,16);
        tv_next_date.setText(showDate);
        tv_next_time.setText(showTime);

        defaultDate = parseString.addDate(currentDate, (long) 20);
        showDate = defaultDate.substring(0,10);
        showTime = defaultDate.substring(11,16);
        tv_end_date.setText(showDate);
        tv_end_time.setText(showTime);
//        Toast.makeText(this,"Default Date"+defaultDate,Toast.LENGTH_SHORT).show();

        //next Date using ImageButton
        next_dat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == next_dat1) {

                    // Get next Date
                    final Calendar c = Calendar.getInstance();
                    nYear = c.get(Calendar.YEAR);
                    nMonth = c.get(Calendar.MONTH);
                    nDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(AddReminderActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    tv_next_date.setText(parseString.stringShortDate(dayOfMonth,monthOfYear+1,year));
                                    n_yyyy = year; n_mmm = monthOfYear; n_dd = dayOfMonth;
                                }
                            }, nYear, nMonth, nDay);
                    datePickerDialog.show();
                    //Toast.makeText(getBaseContext(),nDay + "-" + nMonth + "-" + nYear, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //next Date using TextView
        tv_next_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == tv_next_date) {

                    // Get next Date
                    final Calendar c = Calendar.getInstance();
                    nYear = c.get(Calendar.YEAR);
                    nMonth = c.get(Calendar.MONTH);
                    nDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(AddReminderActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    tv_next_date.setText(parseString.stringShortDate(dayOfMonth,monthOfYear+1,year));
                                    n_yyyy = year; n_mmm = monthOfYear; n_dd = dayOfMonth;
                                }
                            }, nYear, nMonth, nDay);
                    datePickerDialog.show();
                    //Toast.makeText(getBaseContext(),nDay + "-" + nMonth + "-" + nYear, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Next Time using ImageButton
        next_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == next_time) {
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    nHour = c.get(Calendar.HOUR_OF_DAY);
                    nMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(AddReminderActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    n_hh = hourOfDay; n_mm = minute;
                                    Calendar c = Calendar.getInstance();
                                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                    c.set(Calendar.MINUTE, minute);
                                    if(hourOfDay < 10 && minute < 10)
                                        tv_next_time.setText("0" + hourOfDay + ":0" + minute);
                                    else if(hourOfDay < 10 && minute >= 10)
                                        tv_next_time.setText("0" + hourOfDay + ":" + minute);
                                    else if(hourOfDay >= 10 && minute < 10)
                                        tv_next_time.setText("" + hourOfDay + ":0" + minute);
                                    else
                                        tv_next_time.setText("" + hourOfDay + ":" + minute);
                                }
                            }, nHour, nMinute, false);
                    timePickerDialog.show();
                    //Toast.makeText(getBaseContext(),nHour + ":" + nMinute, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Next Time using Text View
        tv_next_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == tv_next_time) {
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    nHour = c.get(Calendar.HOUR_OF_DAY);
                    nMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(AddReminderActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    n_hh = hourOfDay; n_mm = minute;
                                    Calendar c = Calendar.getInstance();
                                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                    c.set(Calendar.MINUTE, minute);
                                    if(hourOfDay < 10 && minute < 10)
                                        tv_next_time.setText("0" + hourOfDay + ":0" + minute);
                                    else if(hourOfDay < 10 && minute >= 10)
                                        tv_next_time.setText("0" + hourOfDay + ":" + minute);
                                    else if(hourOfDay >= 10 && minute < 10)
                                        tv_next_time.setText("" + hourOfDay + ":0" + minute);
                                    else
                                        tv_next_time.setText("" + hourOfDay + ":" + minute);
                                }
                            }, nHour, nMinute, false);
                    timePickerDialog.show();
                    //Toast.makeText(getBaseContext(),nHour + ":" + nMinute, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Interval
        repeat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                occurance1 = repeat.getSelectedItem().toString();
//                if(repOp == 1){
//                    occurance1 = repeat.getSelectedItem().toString();
//                }
//                else if(repOp == 2){
//                    occurance1 = repeat2.getSelectedItem().toString();
//                }

//                if(occurance1.equals("No Repeat")){
//                    btn_end_date.setVisibility(View.INVISIBLE);
//                    tv_end_date.setVisibility(View.INVISIBLE);
//                    end_time.setVisibility(View.INVISIBLE);
//                    tv_end_time.setVisibility(View.INVISIBLE);
//                    tv_ed_title.setVisibility(View.INVISIBLE);
//                    tv_ed_title1.setVisibility(View.INVISIBLE);
//                    tv_ed_view.setVisibility(View.INVISIBLE);
//                    tv_ed_view1.setVisibility(View.INVISIBLE);
//                    e_yyyy = 0; e_mmm = 0; e_dd = 0; e_hh = 0; e_mm = 0;
//                } else {
//                    btn_end_date.setVisibility(View.VISIBLE);
//                    tv_end_date.setVisibility(View.VISIBLE);
//                    end_time.setVisibility(View.VISIBLE);
//                    tv_end_time.setVisibility(View.VISIBLE);
//                    tv_ed_title.setVisibility(View.VISIBLE);
//                    tv_ed_title1.setVisibility(View.VISIBLE);
//                    tv_ed_view.setVisibility(View.VISIBLE);
//                    tv_ed_view1.setVisibility(View.VISIBLE);
//                    e_yyyy = 0; e_mmm = 0; e_dd = 0; e_hh = 0; e_mm = 0;
//                }
                //Toast.makeText(getBaseContext(),"Selected: " + occurance1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        No. Of Times
        repeat2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                occurance1 = repeat2.getSelectedItem().toString();

//                if(occurance1.equals("No Repeat")){
//                    btn_end_date.setVisibility(View.INVISIBLE);
//                    tv_end_date.setVisibility(View.INVISIBLE);
//                    end_time.setVisibility(View.INVISIBLE);
//                    tv_end_time.setVisibility(View.INVISIBLE);
//                    tv_ed_title.setVisibility(View.INVISIBLE);
//                    tv_ed_title1.setVisibility(View.INVISIBLE);
//                    tv_ed_view.setVisibility(View.INVISIBLE);
//                    tv_ed_view1.setVisibility(View.INVISIBLE);
//                    e_yyyy = 0; e_mmm = 0; e_dd = 0; e_hh = 0; e_mm = 0;
//                } else {
//                    btn_end_date.setVisibility(View.VISIBLE);
//                    tv_end_date.setVisibility(View.VISIBLE);
//                    end_time.setVisibility(View.VISIBLE);
//                    tv_end_time.setVisibility(View.VISIBLE);
//                    tv_ed_title.setVisibility(View.VISIBLE);
//                    tv_ed_title1.setVisibility(View.VISIBLE);
//                    tv_ed_view.setVisibility(View.VISIBLE);
//                    tv_ed_view1.setVisibility(View.VISIBLE);
//                    e_yyyy = 0; e_mmm = 0; e_dd = 0; e_hh = 0; e_mm = 0;
//                }
                //Toast.makeText(getBaseContext(),"Selected: " + occurance1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //End Date
        btn_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btn_end_date) {

                    // Get next Date
                    final Calendar c = Calendar.getInstance();
                    eYear = c.get(Calendar.YEAR);
                    eMonth = c.get(Calendar.MONTH);
                    eDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(AddReminderActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    e_yyyy = year; e_mmm = monthOfYear; e_dd = dayOfMonth;
                                    tv_end_date.setText(parseString.stringShortDate(dayOfMonth,monthOfYear+1,year));

                                }
                            }, eYear, eMonth, eDay);
                    datePickerDialog.show();
                    //Toast.makeText(getBaseContext(),eDay + "-" + eMonth + "-" + eYear, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //End Date using TextView
        tv_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == tv_end_date) {

                    // Get next Date
                    final Calendar c = Calendar.getInstance();
                    eYear = c.get(Calendar.YEAR);
                    eMonth = c.get(Calendar.MONTH);
                    eDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(AddReminderActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    e_yyyy = year; e_mmm = monthOfYear; e_dd = dayOfMonth;
                                    tv_end_date.setText(parseString.stringShortDate(dayOfMonth,monthOfYear+1,year));

                                }
                            }, eYear, eMonth, eDay);
                    datePickerDialog.show();
                    //Toast.makeText(getBaseContext(),eDay + "-" + eMonth + "-" + eYear, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //End Time using ImageButton
        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == end_time) {
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    eHour = c.get(Calendar.HOUR_OF_DAY);
                    eMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(AddReminderActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    e_hh = hourOfDay; e_mm = minute;
                                    Calendar c = Calendar.getInstance();
                                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                    c.set(Calendar.MINUTE, minute);
                                    if(hourOfDay < 10 && minute < 10)
                                        tv_end_time.setText("0" + hourOfDay + ":0" + minute);
                                    else if(hourOfDay < 10 && minute >= 10)
                                        tv_end_time.setText("0" + hourOfDay + ":" + minute);
                                    else if(hourOfDay >= 10 && minute < 10)
                                        tv_end_time.setText("" + hourOfDay + ":0" + minute);
                                    else
                                        tv_end_time.setText("" + hourOfDay + ":" + minute);
                                }
                            }, eHour, eMinute, false);
                    timePickerDialog.show();
                    //Toast.makeText(getBaseContext(),eHour + ":" + eMinute, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //End Time using Text View
        tv_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == tv_end_time) {
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    eHour = c.get(Calendar.HOUR_OF_DAY);
                    eMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(AddReminderActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    e_hh = hourOfDay; e_mm = minute;
                                    Calendar c = Calendar.getInstance();
                                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                    c.set(Calendar.MINUTE, minute);
                                    if(hourOfDay < 10 && minute < 10)
                                        tv_end_time.setText("0" + hourOfDay + ":0" + minute);
                                    else if(hourOfDay < 10 && minute >= 10)
                                        tv_end_time.setText("0" + hourOfDay + ":" + minute);
                                    else if(hourOfDay >= 10 && minute < 10)
                                        tv_end_time.setText("" + hourOfDay + ":0" + minute);
                                    else
                                        tv_end_time.setText("" + hourOfDay + ":" + minute);
                                }
                            }, eHour, eMinute, false);
                    timePickerDialog.show();
                    //Toast.makeText(getBaseContext(),eHour + ":" + eMinute, Toast.LENGTH_SHORT).show();
                }
            }
        });

//        int selectedId = r.getCheckedRadioButtonId();
//
//        r1 = (RadioButton) findViewById(selectedId);
//        if(r.getCheckedRadioButtonId() == R.id.r1) {
//            r1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    rep1.setVisibility(View.VISIBLE);
////                    rep2.setVisibility(View.GONE);
//                }
//            });
//        }
//        else if(r.getCheckedRadioButtonId() == R.id.r2){
////        r2 = (RadioButton) findViewById(selectedId);
//            r2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    rep2.setVisibility(View.VISIBLE);
////                    rep1.setVisibility(View.GONE);
//                }
//            });
//        }


//        //Sleep Time from using Text View
        tv_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == tv_from) {
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(AddReminderActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    Calendar c = Calendar.getInstance();
                                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                    c.set(Calendar.MINUTE, minute);
                                    if(hourOfDay < 10 && minute < 10)
                                        tv_from.setText("0" + hourOfDay + ":0" + minute);
                                    else if(hourOfDay < 10 && minute >= 10)
                                        tv_from.setText("0" + hourOfDay + ":" + minute);
                                    else if(hourOfDay >= 10 && minute < 10)
                                        tv_from.setText("" + hourOfDay + ":0" + minute);
                                    else
                                        tv_from.setText("" + hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, false);
                    timePickerDialog.show();
                }
            }
        });
        //Sleep Time from using Text View
        tv_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == tv_to) {
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(AddReminderActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    Calendar c = Calendar.getInstance();
                                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                    c.set(Calendar.MINUTE, minute);
                                    if(hourOfDay < 10 && minute < 10)
                                        tv_to.setText("0" + hourOfDay + ":0" + minute);
                                    else if(hourOfDay < 10 && minute >= 10)
                                        tv_to.setText("0" + hourOfDay + ":" + minute);
                                    else if(hourOfDay >= 10 && minute < 10)
                                        tv_to.setText("" + hourOfDay + ":0" + minute);
                                    else
                                        tv_to.setText("" + hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, false);
                    timePickerDialog.show();
                }
            }
        });



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                med_name = med_name1.getText().toString();

                Log.d("Speech Announcement 1.1","Speech");
                String text = med_name + "has been added to your reminder list";
                sp.Speech(AddReminderActivity.this,text);
                Log.d("Speech Announcement 1.2","Speech");

                med_des = med_des1.getText().toString();
//                //DateFormat format = new DateFormat("yyyy/MM/dd").parse(sDate1);
//                try {
//                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(tv_next_date.getText().toString());
//                    next_dat = date1;
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
////                next_dat = tv_next_date.getText().toString();
//
//                //DateFormat format = new DateFormat("yyyy/MM/dd").parse(sDate1);
//                try {
//                    Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(tv_end_date.getText().toString());
//                    end_dat = date2;
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
////                end_dat = tv_next_time.getText().toString();

                if(occurance1.equals("Hourly")){
                    occurrence = 1; //60 * 1;
                }
                else if(occurance1.equals("Every 2 Hours")) {
                    occurrence = 2; //60 * 2;
                }
                else if(occurance1.equals("Every 3 Hours")) {
                    occurrence = 3; //60 * 3;
                }
                else if(occurance1.equals("Every 4 Hours")) {
                    occurrence = 4; //60 * 4;
                }
                else if(occurance1.equals("Every 5 Hours")) {
                    occurrence = 5; //60 * 5;
                }
                else if(occurance1.equals("Every 6 Hours")) {
                    occurrence = 6; //60 * 6;
                }
                else if(occurance1.equals("Every 7 Hours")) {
                    occurrence = 7; //60 * 7;
                }
                else if(occurance1.equals("Every 8 Hours")) {
                    occurrence = 8; //60 * 8;
                }
                else if(occurance1.equals("Every 9 Hours")) {
                    occurrence = 9; //60 * 9;
                }
                else if(occurance1.equals("Every 10 Hours")) {
                    occurrence = 10; //60 * 10;
                }
                else if(occurance1.equals("Every 11 Hours")) {
                    occurrence = 11; //60 * 11;
                }
                else if(occurance1.equals("Every 12 Hours")) {
                    occurrence = 12; //60 * 12;
                }
                else if(occurance1.equals("Everyday")) {
                    occurrence = 24; //60 * 24;
                }
                else if(occurance1.equals("Once")) {
                    occurrence = 1; //60 * (1 - 1);
                }
                else if(occurance1.equals("Twice")) {
                    occurrence = 2; //60 * (15 / (2 - 1));
                }
                else if(occurance1.equals("Thrice")) {
                    occurrence = 3; //60 * (15 / (3 - 1));
                }
                else if(occurance1.equals("4 Times")) {
                    occurrence = 4; //60 * (15 / (4 - 1));
                }
                else if(occurance1.equals("5 Times")) {
                    occurrence = 5; //60 * (15 / (5 - 1));
                }
                else if(occurance1.equals("6 Times")) {
                    occurrence = 6; //60 * (15 / (6 - 1));
                }
                else if(occurance1.equals("No Repeat")) {
                    occurrence = 0;
                }

                //occurrence = 0;

                // date+time to string
                start_date = tv_next_date.getText().toString()+" "+tv_next_time.getText().toString();
                // date+time to string
                next_date = tv_next_date.getText().toString()+" "+tv_next_time.getText().toString();
                //next_date = parseString.stringDate(n_dd,n_mmm,n_yyyy,n_hh,n_mm);
                //next_date = dateString(nDay, nMonth, nYear, nHour, nMinute);
                Log.d("Next_Date:",String.valueOf(nDay));
                //next_date = (mDay+"-"+mMonth+"-"+mYear+" "+mHour+":"+mMinute);
                //next_date = tv_next_date.getText().toString();
                //next_time1 = tv_next_time.getText().toString();
                // date+time to string
                end_date = tv_end_date.getText().toString()+" "+tv_end_time.getText().toString();
                //end_date = parseString.stringDate(e_dd,e_mmm,e_yyyy,e_hh,e_mm);
                Log.d("End_Date:",String.valueOf(eDay));
                //end_date = tv_end_date.getText().toString();
                //end_date = (mDay+"-"+mMonth+"-"+mYear+" "+mHour+":"+mMinute);
                //end_time1 = tv_end_time.getText().toString();
                from = tv_from.getText().toString();
                to = tv_to.getText().toString();
                med_status = "";
                c_snooze = 0;

//                Toast.makeText(AddReminderActivity.this,"Data Added Successfully",Toast.LENGTH_SHORT).show();

                AddData();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddReminderActivity.this, "'Add Reminder' action cancelled by user", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AddReminderActivity.this,MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    public  void AddData() {

        ParseString parseString = new ParseString();
        String currentDate = parseString.stringCurrentDate();
        if(med_name.isEmpty()) {
            med_name1.setError("Field can't be empty");
        }
        else if(tv_next_date.getText().toString().isEmpty()) {
            tv_next_date.setError("Field can't be empty");
        }
        else if(tv_next_time.getText().toString().isEmpty()) {
            tv_next_time.setError("Field can't be empty");
        }
        else if(tv_end_date.getText().toString().isEmpty()) {
            tv_end_date.setError("Field can't be empty");
        }
        else if(tv_end_time.getText().toString().isEmpty()) {
            tv_end_time.setError("Field can't be empty");
        }
        else if(parseString.compareDate(next_date,currentDate) < 1) {
            Toast.makeText(this,"Start time must be greater than Current time",Toast.LENGTH_SHORT).show();
        }
        else if(parseString.compareDate(next_date,end_date) > -1) {
            Toast.makeText(this,"End time must be greater than Start time",Toast.LENGTH_SHORT).show();
        }
//        else if(parseString.compareDate((tv_next_date.getText().toString()+tv_next_time.getText().toString()), parseString.stringCurrentDate()) <= 1) {
//            tv_next_date.setError("Error");
//            tv_next_time.setError("Error");
//            //Toast.makeText(this,"Start Date & Time must be greater than current Date & Time",Toast.LENGTH_SHORT).show();
//        }
//        else if(parseString.compareDate((tv_end_date.getText().toString()+tv_end_time.getText().toString()), parseString.stringCurrentDate()) <= 1) {
//            tv_end_date.setError("Error");
//            tv_end_time.setError("Error");
//            //Toast.makeText(this,"End Date & Time must be greater than current Date & Time",Toast.LENGTH_SHORT).show();
//        }
//        else if(parseString.compareDate((tv_next_date.getText().toString()+tv_next_time.getText().toString()), (tv_end_date.getText().toString()+tv_end_time.getText().toString())) <= 1) {
//            tv_next_date.setError("Error");
//            tv_next_time.setError("Error");
//            tv_end_date.setError("Error");
//            tv_end_time.setError("Error");
//            //Toast.makeText(this,"End Date & Time must be greater than Start Date & Time",Toast.LENGTH_SHORT).show();
//        }
        else {
            boolean id = medicineDatabaseHelper.insertMedicine(new Medicine(med_name, med_des, start_date, next_date, end_date, repetition,occurrence, from, to, med_status, c_snooze));
            if (id == true)
                Toast.makeText(AddReminderActivity.this, "Medicine: " + med_name + " added to list ", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(AddReminderActivity.this, "Error adding medicine '" + med_name + "' to list ", Toast.LENGTH_LONG).show();

            medicineDatabaseHelper.close();

            Intent intent = new Intent(AddReminderActivity.this, MainActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

//    public String dateString(int mDay1, int mMonth1, int mYear1, int mHour1, int mMinute1) {
//
//        String date1 = "";
//        int counter = 0;
//        Log.d("1. date1: ", date1);
//
//        if(counter == 0) {
//            if (mDay1 < 10)
//                date1 = date1 + ("0" + mDay1 + "-");
//            else
//                date1 = date1 + ("" + mDay1 + "-");
//            counter = 1;
//        }
//        Log.d("2. date1: ", date1);
//
//        if(counter == 1) {
//            if(mMonth1<10)
//                date1 = date1 + ("0"+(mMonth1+1)+"-");
//            else
//                date1 = date1 + (""+(mMonth1+1)+"-");
//            counter = 2;
//        }
//        Log.d("3. date1: ", date1);
//
//        if(counter == 2) {
//            if(mYear1<10)
//                date1 = date1 + ("000"+mYear1+"-");
//            else if(mYear1<100)
//                date1 = date1 + ("00"+mYear1+"-");
//            else if(mYear1<1000)
//                date1 = date1 + ("0"+mYear1+"-");
//            else
//                date1 = date1 + (""+mYear1+" ");
//            counter = 3;
//        }
//        Log.d("4. date1: ", date1);
//
//        if(counter == 3) {
//            if (mHour1<10)
//                date1 = date1 + ("0"+mHour1+":");
//            else
//                date1 = date1 + (""+mHour1+":");
//            counter = 4;
//        }
//        Log.d("5. date1: ", date1);
//
//        if(counter == 4) {
//            if (mMinute1<10)
//                date1 = date1 + ("0"+mMinute1+"");
//            else
//                date1 = date1 + (""+mMinute1+"");
//            counter = 5;
//        }
//        Log.d("6. date1: ", date1);
//
//        return date1;
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(AddReminderActivity.this,MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
        return;
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button has been clicked
        switch(view.getId()) {
            // 1.Interval
            case R.id.r1:
                if(checked)
                    rep1.setVisibility(View.VISIBLE);
                    rep2.setVisibility(View.GONE);
                    repOp = 1;
                    repetition = repOp;
                break;
            // 2.No. Of Times
            case R.id.r2:
                if(checked)
                    rep2.setVisibility(View.VISIBLE);
                    rep1.setVisibility(View.GONE);
                repOp = 2;
                repetition = repOp;
                break;
        }
    }
}

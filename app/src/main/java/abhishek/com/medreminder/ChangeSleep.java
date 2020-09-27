package abhishek.com.medreminder;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ChangeSleep extends AppCompatActivity {

    private TextView tv_next_time,tv_end_time,tv_from,tv_to;
    private ImageButton next_time,end_time;
    private Button btn_submit,btn_cancel;
    private int nHour, nMinute, eHour, eMinute;
    String next_time1,end_time1,from,to, Name, m_pin, fr;
    public int n_hh,n_mm,e_hh,e_mm;

    SharedPreferences sharedpreferences;
    String mypreference = "login_pin";

    //Medicine database
    MedicineDatabaseHelper medicineDatabaseHelper = new MedicineDatabaseHelper(this);

    //ParseString
    ParseString parseString = new ParseString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_sleep);


        tv_next_time = (TextView) findViewById(R.id.tv_next_time);
        tv_end_time = (TextView) findViewById(R.id.tv_end_time);
        next_time = (ImageButton) findViewById(R.id.next_time);
        end_time = (ImageButton) findViewById(R.id.end_time);
        tv_from = (TextView) findViewById(R.id.tv_from);
        tv_to = (TextView) findViewById(R.id.tv_to);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        String showTime;

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        m_pin = sharedpreferences.getString(mypreference, "1991");
        sharedpreferences = getSharedPreferences("user_name", Context.MODE_PRIVATE);
        Name = sharedpreferences.getString("user_name", "Your Name");
        sharedpreferences = getSharedPreferences("sleep_from", Context.MODE_PRIVATE);
        from = sharedpreferences.getString("sleep_from", "00:00");
        sharedpreferences = getSharedPreferences("sleep_to", Context.MODE_PRIVATE);
        to = sharedpreferences.getString("sleep_to", "00:00");
        sharedpreferences = getSharedPreferences("sleep_to", Context.MODE_PRIVATE);
        fr = sharedpreferences.getString("sleep_fto", "00:00");

        showTime = fr;
        tv_next_time.setText(showTime);
        tv_from.setText(showTime);

        showTime = to;
        tv_end_time.setText(showTime);
        tv_to.setText(showTime);

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
                    TimePickerDialog timePickerDialog = new TimePickerDialog(ChangeSleep.this,
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
                    TimePickerDialog timePickerDialog = new TimePickerDialog(ChangeSleep.this,
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
                    TimePickerDialog timePickerDialog = new TimePickerDialog(ChangeSleep.this,
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
                    TimePickerDialog timePickerDialog = new TimePickerDialog(ChangeSleep.this,
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

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // time to string
                next_time1 = tv_next_time.getText().toString();
                Log.d("Next time (var):",next_time1);
                Log.d("Next time (text_view):",tv_next_time.getText().toString());
                end_time1 = tv_end_time.getText().toString();
                Log.d("Next time (var):",end_time1);
                from = tv_from.getText().toString();
                to = tv_to.getText().toString();
                fr = tv_from.getText().toString();

                if(next_time1.equals(fr) && end_time1.equals(to))
                    Toast.makeText(ChangeSleep.this,"Sleep Hours Same as Current Sleep Hours", Toast.LENGTH_SHORT).show();
                else {
                    UpdateInTable();
                    tv_from.setText(tv_next_time.getText().toString());
                    tv_to.setText(tv_end_time.getText().toString());
                    Toast.makeText(ChangeSleep.this,"New Sleep Hours has been Set", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ChangeSleep.this, "'Add Reminder' action cancelled by user", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ChangeSleep.this,PasswordSettingsActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    public  void UpdateInTable() {

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("user_name", Name);
        editor.putString("login_pin", m_pin);
        editor.putString("sleep_from", next_time1);
        Log.d("New Sleep from :",next_time1);
        editor.putString("sleep_to", end_time1);
        editor.putString("sleep_fto", next_time1);
        editor.commit();

        medicineDatabaseHelper.execMyQuery("update Medicine_table set sleep_from = '"+next_time1+"'");
        medicineDatabaseHelper.execMyQuery("update Medicine_table set sleep_to = '"+end_time1+"'");
//        if (id == true)
            Toast.makeText(ChangeSleep.this, "New Sleep Hours Set", Toast.LENGTH_LONG).show();
//        else
//            Toast.makeText(ChangeSleep.this, "Error: Please Try Again", Toast.LENGTH_LONG).show();

        medicineDatabaseHelper.close();

//        Intent intent = new Intent(ChangeSleep.this, MainActivity.class);
//        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        finish();

    }



    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Intent intent = new Intent(ChangeSleep.this,MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
        return;
    }
}

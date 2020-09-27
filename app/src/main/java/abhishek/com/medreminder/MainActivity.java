package abhishek.com.medreminder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    MedicineDatabaseHelper medicineDatabaseHelper = new MedicineDatabaseHelper(this);
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

    private TextView tvToday,tvTom,tvUp,tvNo;

    Toolbar toolbar;
    private boolean doubleBackToExitPressedOnce = false;

    Speech sp = new Speech();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial speech
        sp.Speech(this,"");


        // If possible change Notification icon when there is at least one c_snooze > 0, else default icon

        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView3 = findViewById(R.id.recyclerView3);

        layoutManager1 = new LinearLayoutManager(this);
        layoutManager2 = new LinearLayoutManager(this);
        layoutManager3 = new LinearLayoutManager(this);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);

//        sp.Speech(MainActivity.this,"Welcome To MedReminder");

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            // thik kora hoyeche finish() diye ... clear all from stack/flag
            super.onBackPressed();
//            System.exit(0);
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press once again to exit", Toast.LENGTH_SHORT).show();
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.add:
////                        Delay
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        sp.Speech(MainActivity.this,"Add");
//                    }
//                }, 900);
                intent = new Intent(MainActivity.this, AddReminderActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                break;
            case R.id.complete:
                intent = new Intent(MainActivity.this, CompleteMedicine.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                break;
            case R.id.info:
                intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                break;
            case R.id.notification:
                intent = new Intent(MainActivity.this, NotificationInbox.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                break;
            case R.id.password_settings:
                intent = new Intent(MainActivity.this, PasswordSettingsActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                break;
//            case R.id.dummy_check:
//                intent = new Intent(MainActivity.this, DummyDataActivity.class);
//                startActivity(intent);
//                finish();
//                break;
            case R.id.about:
                intent = new Intent(MainActivity.this,AboutApp.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                break;
//            case R.id.demo:
//                intent = new Intent(MainActivity.this,ExpandCard.class);
//                startActivity(intent);
//                finish();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        Log.d("M_Card 1 :","1");
        super.onResume();

        ParseString parseString = new ParseString();
        String initialDate = parseString.stringBeginCurrentDate();
        String today = parseString.addDate(initialDate, 1*24*60);
        String tomorrow = parseString.addDate(initialDate, 2*24*60);

        String query = "SELECT med_id,med_name,med_des,next_date FROM Medicine_table ORDER BY next_date ASC";
        medicines = medicineDatabaseHelper.getQueryMedicines(query);

        medicines1.clear();
        medicines2.clear();
        medicines3.clear();

        int c1 = 0, c2 = 0, c3 = 0;
        for(int i = 0 ; i < medicines.size(); i++){
            String tempDate = medicines.get(i).getStartDate();
            Log.d("Temp Date",tempDate);
            Log.d("Today Date",today);

            Medicine medicine0 = new Medicine();

            medicine0.setId(medicines.get(i).getId());
            medicine0.setMedicineName(medicines.get(i).getMedicineName());
            medicine0.setMedicineDes(medicines.get(i).getMedicineDes());
            medicine0.setStartDate(medicines.get(i).getStartDate());


            if(parseString.compareDate(tempDate,today) == -1){
                medicines1.add(medicine0);
                c1++;
            }
            else if(parseString.compareDate(tempDate,tomorrow) == -1){
                medicines2.add(medicine0);
                c2++;
            }
            else {
                medicines3.add(medicine0);
                c3++;
            }
        }

        adapter1 = new RecyclerAdapter(medicines1,this);
        recyclerView1.setAdapter(adapter1);
        adapter2 = new RecyclerAdapter(medicines2,this);
        recyclerView2.setAdapter(adapter2);
        adapter3 = new RecyclerAdapter(medicines3,this);
        recyclerView3.setAdapter(adapter3);

        tvToday = (TextView)findViewById(R.id.tv_today);
        tvTom = (TextView)findViewById(R.id.tv_tomorrow);
        tvUp = (TextView)findViewById(R.id.tv_upcoming);
        tvNo = (TextView)findViewById(R.id.tv_no);

        if (c1 == 0){
            tvToday.setVisibility(View.GONE);
        }
        else{
            tvToday.setVisibility(View.VISIBLE);
        }

        if (c2 == 0){
            tvTom.setVisibility(View.GONE);
        }
        else{
            tvTom.setVisibility(View.VISIBLE);
        }

        if (c3 == 0){
            tvUp.setVisibility(View.GONE);
        }
        else{
            tvUp.setVisibility(View.VISIBLE);
        }

        if (c1 == 0 && c2 == 0 && c3 == 0){
            tvNo.setVisibility(View.VISIBLE);
        }
        else{
            tvNo.setVisibility(View.GONE);
        }
    }
}

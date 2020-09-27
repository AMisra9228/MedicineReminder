package abhishek.com.medreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationInbox extends AppCompatActivity {

    MedicineDatabaseHelper medicineDatabaseHelper = new MedicineDatabaseHelper(this);
    private RecyclerView recyclerViewNotification;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Medicine> medicines;
    public TextView tv_msg;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inbox_notification);

        recyclerViewNotification = findViewById(R.id.recyclerView_notification);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewNotification.setLayoutManager(layoutManager);
        tv_msg = (TextView) findViewById(R.id.tv_msg);

        //customized actionbar
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notification Inbox");

        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onResume() {
        Log.d("Notification Inbox ::","1");
        super.onResume();
        String query = "SELECT med_id,med_name,med_des,next_date FROM Medicine_table WHERE c_snooze != 0 ORDER BY next_date DESC";

        medicines = medicineDatabaseHelper.getQueryMedicines(query);

        if(medicines.size()!=0) {
            tv_msg.setVisibility(View.INVISIBLE);
            adapter = new RecyclerAdapterNotification(medicines, this); //change to RecyclerAdpaterNotification
            recyclerViewNotification.setAdapter(adapter);
        } else {
            tv_msg.setVisibility(View.VISIBLE);
        }
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

        Intent intent = new Intent(NotificationInbox.this,MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
        return;
    }
}

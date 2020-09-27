package abhishek.com.medreminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EnablePin extends AppCompatActivity {
    private Button btn_enable,btn_disable;
    SharedPreferences sharedpreferences;
    private String mypreference = "login_pin";
    private String p_enabled = "";
    private boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_enable);

        btn_enable =(Button) findViewById(R.id.btn_enable);
        btn_disable =(Button) findViewById(R.id.btn_disable);

        btn_enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // button enable
                Intent intent = new Intent(EnablePin.this, userSetPIN.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        });

        btn_disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // button disable
                sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("user_name", "");
                editor.putString(mypreference, "");
                editor.putString("sleep_from", "00:00");
                editor.putString("sleep_to", "06:00");
                editor.putString("sleep_fto", "22:00");
                editor.putString("m_report", "Med_Report.xls");
                editor.putString("p_enabled", "0");  // pin security disabled
                editor.putString("p_enable", "0");
                editor.commit();
                Toast.makeText(EnablePin.this, "MedReminder Login Security Check Disabled", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EnablePin.this, MainActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        });
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
}

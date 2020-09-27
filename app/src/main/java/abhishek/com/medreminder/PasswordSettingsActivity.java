package abhishek.com.medreminder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class PasswordSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PasswordSettingActivity"; //logging character < 24
    private static final int REQUEST_CODE_ENABLE = 11;
    TextView tv1;
    String flg = "true";
    SharedPreferences sharedpreferences;
    private String mypreference = "login_pin";
    private String p_enabled = "";

    private String login_pin1, user_name1, sleep_from1, sleep_to1, sleep_fto1, m_report1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_password);

//        this.findViewById(R.id.btn_enable).setOnClickListener(this);
//        this.findViewById(R.id.btn_disable).setOnClickListener(this);
//        this.findViewById(R.id.button_change_sleep).setOnClickListener(this);
        this.findViewById(R.id.button_user_profile).setOnClickListener(this);
        this.findViewById(R.id.button_change_pin).setOnClickListener(this);

        this.findViewById(R.id.btn_enable).setOnClickListener(this);
        this.findViewById(R.id.btn_disable).setOnClickListener(this);

        this.findViewById(R.id.button_home).setOnClickListener(this);

//        this.findViewById(R.id.btn_enable);
//        this.findViewById(R.id.btn_disable);
//        this.findViewById(R.id.btn_enable);

//        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
//        p_enabled = sharedpreferences.getString("p_enabled", null);
//
//        if(p_enabled.equals("1")){
//            this.findViewById(R.id.btn_enable).setVisibility(View.GONE);
//            this.findViewById(R.id.btn_disable).setVisibility(View.VISIBLE);
//            this.findViewById(R.id.button_change_pin).setVisibility(View.VISIBLE);
//        }
//        else {
//            this.findViewById(R.id.btn_enable).setVisibility(View.VISIBLE);
//            this.findViewById(R.id.btn_disable).setVisibility(View.GONE);
//            this.findViewById(R.id.button_change_pin).setVisibility(View.GONE);
//        }

        Log.d("KeyVisibilityTest","KeyVisible1.0");
        keyVisibility();
        Log.d("KeyVisibilityTest","KeyVisible1.1");
    }


    public void keyVisibility(){
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        p_enabled = sharedpreferences.getString("p_enabled", null);
        Log.d("Pin_enable_check","p_enabled = "+p_enabled);

        Button btn_e = findViewById(R.id.btn_enable);
        Button btn_d = findViewById(R.id.btn_disable);
        Button btn_cp = findViewById(R.id.button_change_pin);
        if(p_enabled.equals("1")) {
//            Button btn_e = findViewById(R.id.btn_enable);
            btn_e.setVisibility(View.GONE);

//            Button btn_d = findViewById(R.id.btn_disable);
            btn_d.setVisibility(View.VISIBLE);

//            Button btn_cp = findViewById(R.id.button_change_pin);
            btn_cp.setVisibility(View.VISIBLE);
        }
        else {
//            Button btn_e = findViewById(R.id.btn_enable);
            btn_e.setVisibility(View.VISIBLE);

//            Button btn_d = findViewById(R.id.btn_disable);
            btn_d.setVisibility(View.GONE);

//            Button btn_cp = findViewById(R.id.button_change_pin);
            btn_cp.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent1;
        switch (v.getId()) {
//            case R.id.btn_enable:
//                flg = "true";
//                Intent intent = new Intent(PasswordSettingsActivity.this,userLogin.class);
//                intent.putExtra("enbl",flg);
//                startActivity(intent);
//                break;
//            case R.id.btn_disable:
//                flg = "false";
//                break;

//            **Change as per requirement**
//            case R.id.button_change_sleep:
//                tv1 = this.findViewById(R.id.button_change_sleep);
//                tv1.setText("Change Sleep Hours");
//                Toast.makeText(this, "* Change Sleep Timings *", Toast.LENGTH_SHORT).show();
//                intent1 = new Intent(PasswordSettingsActivity.this, ChangeSleep.class);
//                //intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent1);
//                //setContentView(R.layout.activity_settings_password);
//                finish();
//                break;
            case R.id.button_user_profile:
//                Button btn_e = findViewById(R.id.btn_enable);
//                Button btn_d = findViewById(R.id.btn_disable);
//                Button btn_cp = findViewById(R.id.button_change_pin);
//
//                if(btn_e.getVisibility() == View.VISIBLE){
//                    btn_d.setVisibility(View.GONE);
//                    btn_cp.setVisibility(View.GONE);
//                }else if(btn_d.getVisibility() == View.VISIBLE & btn_cp.getVisibility() == View.VISIBLE){
//                    btn_e.setVisibility(View.GONE);
//                }
                tv1 = this.findViewById(R.id.button_user_profile);
                tv1.setText("User Profile");
                Toast.makeText(this, "* User Profile *", Toast.LENGTH_SHORT).show();
                intent1 = new Intent(PasswordSettingsActivity.this, UserProfileActivity.class);
                //intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                //setContentView(R.layout.activity_settings_password);
                finish();
                return;
                //break;
            case R.id.btn_enable:
//                tv1 = this.findViewById(R.id.button_change_pin);
//                tv1.setText("Enable");
                Button btn_e1 = findViewById(R.id.btn_enable);
                Button btn_d1 = findViewById(R.id.btn_disable);
                Button btn_cp1 = findViewById(R.id.button_change_pin);
                btn_e1.setVisibility(View.VISIBLE);
                btn_d1.setVisibility(View.GONE);
                btn_cp1.setVisibility(View.GONE);
                Intent intentE = new Intent(PasswordSettingsActivity.this, userSetPIN0.class);
                startActivity(intentE.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                return;
                //break;
            case R.id.btn_disable:
//                tv1 = this.findViewById(R.id.button_change_pin);
//                tv1.setText("Disable");
                Button btn_e2 = findViewById(R.id.btn_enable);
                Button btn_d2 = findViewById(R.id.btn_disable);
                Button btn_cp2 = findViewById(R.id.button_change_pin);
                btn_e2.setVisibility(View.VISIBLE);
                btn_d2.setVisibility(View.GONE);
                btn_cp2.setVisibility(View.GONE);
                sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                user_name1 = sharedpreferences.getString("user_name", null);
                login_pin1 = sharedpreferences.getString("login_pin", null);
                sleep_from1 = sharedpreferences.getString("sleep_from", null);
                sleep_to1 = sharedpreferences.getString("sleep_to", null);
                sleep_fto1 = sharedpreferences.getString("sleep_fto", null);
                m_report1 = sharedpreferences.getString("m_report", null);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("user_name", user_name1);
                editor.putString(mypreference, "");
                editor.putString("sleep_from", sleep_from1);
                editor.putString("sleep_to", sleep_to1);
                editor.putString("sleep_fto", sleep_fto1);
                editor.putString("m_report", m_report1);
                editor.putString("p_enabled", "0");  // pin security disabled
                editor.putString("p_enable", "0");
                editor.commit();
                Toast.makeText(PasswordSettingsActivity.this, "MedReminder Login Security Check Disabled", Toast.LENGTH_SHORT).show();
                Intent intentD = new Intent(PasswordSettingsActivity.this, MainActivity.class);
                startActivity(intentD.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                return;
                //break;
            case R.id.button_change_pin:
                tv1 = this.findViewById(R.id.button_change_pin);
                tv1.setText("Change PIN");
                Toast.makeText(this, "* Change PIN *", Toast.LENGTH_SHORT).show();
                intent1 = new Intent(PasswordSettingsActivity.this, userOldPIN.class);
                //intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                //setContentView(R.layout.activity_settings_password);
                finish();
                return;
                //break;
            case R.id.button_home:
                tv1 = this.findViewById(R.id.button_home);
                tv1.setText("Back to Home Screen");
                Toast.makeText(this, "* Home *", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(PasswordSettingsActivity.this, MainActivity.class);
                // flag_clear_top
                //startActivity(intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                //setContentView(R.layout.activity_settings_password);  // make it comment
                startActivity(intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                return;
                //break;
        }
        setContentView(R.layout.activity_settings_password);
        tv1.setText("☺️ Have A Nice Day ☺");
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Intent intent =new Intent(PasswordSettingsActivity.this,MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
        return;
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        mt("Resume Activity1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mt("Pause Activity1");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mt("Restart Activity1");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mt("Start Activity1");
    }

    @Override
    protected void onStop() {
        mt("Stop Activity1 ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mt("Destroy Activity1");
        super.onDestroy();
    }

    public void mt(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }*/
}

package abhishek.com.medreminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.pm.PackageManager;

import static android.speech.tts.TextToSpeech.QUEUE_ADD;


public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashLoadingActivity";
    SharedPreferences sharedpreferences;
    SharedPreferences sharedprefer;
    private String mypreference = "login_pin";
//    private String mypin = "no_pin";
    private String p_enabled = "0";
    boolean flag = false;

    //Alarm declarations
    public static final String ALARM_TYPE = "ALARM_TYPE";
    //public static final String ALARM_TYPE_ONE_TIME = "ALARM_TYPE_ONE_TIME";
    public static final String ALARM_TYPE_REPEAT = "ALARM_TYPE_REPEAT";
    public static final String ALARM_DESCRIPTION = "ALARM_DESCRIPTION";
    private AlarmManager alarmManager = null;
    private PendingIntent pendingIntent = null;

//    Speech sp = new Speech();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initial speech
//        sp.playSilentUtterance(2000, QUEUE_ADD, null);
//        sp.Speech(SplashActivity.this,"Hello");

        // Create database tables
        MedicineDatabaseHelper medicineDatabaseHelper = new MedicineDatabaseHelper(this);
        medicineDatabaseHelper.tableCreate();

        // Enable BootReceiver Component
        setBootReceiverEnabled(PackageManager.COMPONENT_ENABLED_STATE_ENABLED);

        // Disable BootReceiver Component
        // setBootReceiverEnabled(PackageManager.COMPONENT_ENABLED_STATE_DISABLED);

        // Get system built-in alarm manager object.
        alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

        // Set Alarm Broadcast
        Intent intent0 = new Intent(getApplicationContext(), AlarmTriggerBroadcastReceiver.class);
        intent0.putExtra(ALARM_TYPE, ALARM_TYPE_REPEAT);
        intent0.putExtra(ALARM_DESCRIPTION, "Repeat alarm start this broadcast.");
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent0, PendingIntent.FLAG_UPDATE_CURRENT);


        long alarmStartTime = System.currentTimeMillis();
        // This is too short, it will be expanded by android os to 60 seconds by default.
        long alarmExecuteInterval = 10 * 1000;
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmExecuteInterval, pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmExecuteInterval, pendingIntent);

        Toast.makeText(getApplicationContext(), "A repeat alarm has been created. This alarm will send to a broadcast receiver.", Toast.LENGTH_LONG).show();

        // Cancel Alarm Broadcast
//        Intent intent3 = new Intent(getApplicationContext(), AlarmTriggerBroadcastReceiver.class);
//        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 10*60*1000, pendingIntent);
//        alarmManager.cancel(pendingIntent);

//        alarmManager.cancel(pendingIntent);
//        Toast.makeText(this, "Cancel current alarm.", Toast.LENGTH_LONG).show();

        // Pin Checking
//        sharedprefer = getSharedPreferences(mypin, Context.MODE_PRIVATE);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        // Pin Checking
//        sharedprefer = getSharedPreferences(mypin, Context.MODE_PRIVATE);
//        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        Log.d("Check Sharedpreference","Test_spf: 1_ "+sharedpreferences);
        Log.d("Check Sharedpreference","Test_spf: 1_0" + sharedpreferences.contains(mypreference));

        //if no pin
//        if (sharedprefer.contains(mypin)) {
//            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
//            startActivity(intent);
//         }
//         if (!sharedpreferences.contains(mypreference)) {
//            Toast.makeText(this, "* WELCOME NEW USER *", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(SplashActivity.this, userSetPIN.class);
//            startActivity(intent);
//            flag = true;
//        } else if (sharedpreferences.contains(mypreference)) {
//            Intent intent = new Intent(SplashActivity.this, userLogin.class);
//            startActivity(intent);
//            flag = true;
//        } else {
//            System.exit(1);
//            finish();
//        }

        Log.d("Speech Announcement 1.0","Speech");
//        sp.Speech(SplashActivity.this,"Welcome To MedReminder");
        Log.d("Speech Announcement 2.0","Speech");

        if (!sharedpreferences.contains(mypreference)) {
            //Redirect to Pin Enable or Disable page
            //Comment next four lines

            Toast.makeText(this, "* WELCOME NEW USER *", Toast.LENGTH_SHORT).show();
            Log.d("PIN_enable_status","Pin_enable_0.1");
            Intent intent = new Intent(SplashActivity.this, EnablePin.class);
            startActivity(intent);
            //Speech Announcement
            //sp.Speech(SplashActivity.this,"Welcome To MedReminder");

            flag = true;
        } else if (sharedpreferences.contains(mypreference)) {
            Intent intent;
            sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            p_enabled = sharedpreferences.getString("p_enabled", null);
            Log.d("Pin_enable_check","p_enabled = "+p_enabled);
            if(p_enabled.equals("1")) {
                Log.d("PIN_enable_status","Pin_enable_1.0");
                intent = new Intent(SplashActivity.this, userLogin.class);
            }
            else {
                Log.d("PIN_enable_status","Pin_disable_1.1");
                intent = new Intent(SplashActivity.this, MainActivity.class);
            }
//            startActivity(intent));
//            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
            finish();
            flag = true;
        } else {
            System.exit(1);
            finish();
        }



        //Intent intent = new Intent(this, LoadingActivity.class);
        //startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        System.exit(0);
        finish();
    }

    private void setBootReceiverEnabled(int componentEnabledState) {
        ComponentName componentName = new ComponentName(this, BootReceiver.class);
        PackageManager packageManager = getPackageManager();
        packageManager.setComponentEnabledSetting(componentName,
                componentEnabledState,
                PackageManager.DONT_KILL_APP);
    }
}
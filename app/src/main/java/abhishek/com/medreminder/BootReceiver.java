package abhishek.com.medreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = BootReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive - Intent Action: " + intent.getAction());

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")
                || intent.getAction().equals("android.intent.action.QUICKBOOT_POWERON")
                || intent.getAction().equals("com.htc.intent.action.QUICKBOOT_POWERON")
                || intent.getAction().equals("android.intent.action.REBOOT")) {
//            Toast.makeText(context, "A repeat alarm has been created by Boot Receiver. This alarm will send to a service/broadcast receiver.", Toast.LENGTH_LONG).show();
            setAlarm(context);
        }
    }

//    // Using Service
//    public void setAlarm(Context context) {
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        intent.putExtra("ALARM_TYPE", "ALARM_TYPE_REPEAT");
//        intent.putExtra("ALARM_DESCRIPTION", "Repeat alarm start this service.");
//        Intent intent = new Intent(context, AlarmTriggerService.class);
//        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60, pendingIntent);
//    }

    //    // Using Broadcast
    public void setAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmTriggerBroadcastReceiver.class);
        intent.putExtra("ALARM_TYPE", "ALARM_TYPE_REPEAT");
        intent.putExtra("ALARM_DESCRIPTION", "Repeat alarm start this Broadcast.");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60, pendingIntent);
    }
}

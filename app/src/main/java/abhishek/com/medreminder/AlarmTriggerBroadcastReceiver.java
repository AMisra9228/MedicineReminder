package abhishek.com.medreminder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


public class AlarmTriggerBroadcastReceiver extends BroadcastReceiver  {

    private final static String TAG_ALARM_TRIGGER_BROADCAST = "ALARM_TRIGGER_BROADCAST";
    private MediaPlayer mediaPlayer;
    private AlarmTriggerBroadcastReceiver context;

    private static final String TAG = "SplashLoadingActivity";
    SharedPreferences sharedpreferences;
    private String mypreference = "login_pin";

    Speech sp = new Speech();


    @Override
    public void onReceive(Context context, Intent intent) {
        String alarmType = intent.getStringExtra(SplashActivity.ALARM_TYPE);

//        Toast.makeText(context,"Broadcast is running...",Toast.LENGTH_SHORT).show();

        boolean ALARM_FLAG = false;
        String med_note = "", count_msg = "Medicine";
        int med_count = 0;

        String alarmDescription = intent.getStringExtra(SplashActivity.ALARM_DESCRIPTION);

        this.context = this;
        MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(context);
//        ReportDataBaseHelper myDb1 = new ReportDatabaseHelper(context);
        InsertReportData myReport = new InsertReportData();
        ParseString parseString = new ParseString();

//        @Override
//        public void run() {
//            DataBaseHelper myDb1 = new DataBaseHelper(context);
//            myDb.eisagoghfititi(temaxismeno_sms[1],temaxismeno_sms[2],temaxismeno_sms[3],temaxismeno_sms[4]);
//            // have more database operation here
//        }

        String currentDate = parseString.stringCurrentDate();
        int snooze_interval = 5, auto_snooze = 3;

        Log.d("Alarm_a_1:","1");
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        Log.d("Alarm_a_1 :","2");
        if(res!=null && res.getCount()>0){
            Log.d("Alarm_a_1:","3");
            while (res.moveToNext()){
                Log.d("Alarm_a_1:","4");
                int med_id = res.getInt(0);
                String med_name = res.getString(1);
                String med_des = res.getString(2);
                String start_date = res.getString(3);
                String next_date = res.getString(4);
                String end_date = res.getString(5);
                int repetition = res.getInt(6);
                int occurrence = res.getInt(7);
                String sleep_from = res.getString(8);
                String sleep_to = res.getString(9);
                String med_status = res.getString(10);
                int c_snooze = res.getInt(11);
                long handle_occurance = 0;
                Log.d("Alarm_a_1:","4.5");

                // Auto update date on booting after powered down for a while
//                String temporary_next = parseString.addDate(next_date, (long) occurrence);
//                boolean temporary_flag = false;
//                while(parseString.compareDate(temporary_next,currentDate) <= 1) {
//                    next_date = temporary_next;
//                    temporary_next = parseString.addDate(next_date, (long) occurrence);
//                    temporary_flag = true;
//                }
//                if(temporary_flag) {
//                    myDb.execMyQuery("update Report_table set next_date = '"+next_date+"' where med_id = " + med_id);
//                }
                // End of auto update

                if(parseString.compareDate(end_date,currentDate) > -1) {
                    if (parseString.compareDate(next_date, currentDate) <= 1) { // changed to 1 from -1
                        Log.d("Alarm_a_1:", "5");
                        if (parseString.compareDate(next_date, end_date) == -1) {
                            Log.d("Alarm_a_1:", "6");
                            boolean check_sleep = parseString.checkSleep(currentDate, sleep_from, sleep_to);

                            Log.d("Alarm_a_1:", "7");
                            if (check_sleep == false) {
                                Log.d("Alarm_a_1:", "8");
                                if ((c_snooze < auto_snooze) && (c_snooze != -1)) {
                                    String snooze_next_date = parseString.addDate(next_date, (long) (c_snooze * snooze_interval));
                                    Log.d("Next Date :", next_date);
                                    Log.d("Snooze Next :", snooze_next_date);
                                    Log.d("Current Date :", currentDate);
                                    if (parseString.compareDate(snooze_next_date, currentDate) < 1) {
                                        // update notification for each unique med_id
                                        // Start media player
                                        Log.d("Alarm_a_1:", "9");
                                        ALARM_FLAG = true;
//                                        //to test
                                        mediaPlayer = MediaPlayer.create(context, R.raw.alarm_sound);
//                                mediaPlayer.start();
//                                mediaPlayer.stop();
                                        //to test
                                        mediaPlayer.start();

//                                        String readSpeech = "" + med_count +" "+ count_msg+ " Pending" + ". " + "It's medicine time: Please take " + count_msg + med_note + ".";
//                                        sp.Speech(context,readSpeech);

                                        med_status = "ACTIVE";

                                        Toast.makeText(context,"AlarmCasting="+med_id+":"+c_snooze,Toast.LENGTH_SHORT).show();
                                        // replace the below query with update query for medicine_table
                                        c_snooze = c_snooze + 1;
                                        myDb.updateMedicine("Medicine_table", med_id, med_name,med_des,start_date, next_date, end_date, repetition,occurrence, sleep_from, sleep_to, med_status, c_snooze);
                                    }
                                    Log.d("Alarm_a_1:", "10");
                                } else if (c_snooze == -1) {
                                    Log.d("Alarm_a_1 :", "10.5");
                                    if(repetition == 1){
                                        handle_occurance = 60 * occurrence;
                                    }
                                    else if(repetition == 2) {
                                        if (occurrence == 1)
                                            handle_occurance = 60 * 24;
                                        else
                                            handle_occurance = 60 * (15 / (occurrence - 1));
                                    }
                                    String temp_next_date = parseString.addDate(next_date, handle_occurance);
                                    if (parseString.compareDate(temp_next_date, currentDate) <= 0) {
                                        Log.d("Alarm_a_1 :", "10.5.1");
                                        next_date = temp_next_date;
                                        ALARM_FLAG = true;
                                        //to test
                                        mediaPlayer = MediaPlayer.create(context, R.raw.alarm_sound);
                                        mediaPlayer.start();

//                                        String readSpeech = "" + med_count +" "+ count_msg+ " Pending" + ". " + "It's medicine time: Please take " + count_msg + med_note + ".";
////                                        sp.Speech(context,readSpeech);

                                        // replace the below query with update query for medicine_table
                                        c_snooze = 1;
                                        myDb.updateMedicine("Medicine_table", med_id, med_name, med_des, start_date,next_date, end_date, repetition,occurrence, sleep_from, sleep_to, med_status, c_snooze);
                                    }
                                    if (parseString.compareDate(end_date, currentDate) == -1) {
                                        Log.d("Alarm_a_1 :", "10.5.2");
                                        Toast.makeText(context,"Medicine "+med_name+" course is complete",Toast.LENGTH_SHORT).show();
                                        myDb.execMyQuery("update Report_table set med_status = 'COMPLETED' where med_id = " + med_id);
                                        myDb.deleteMedicine("Medicine_table", med_id);
                                    }
                                } else {
//                                AlarmNotify alarmNotify = new AlarmNotify();        //Start Notify service
//                                alarmNotify.Start(med_id, med_name, med_des, next_date, end_date, occurrence, sleep_from, sleep_to, med_status, c_snooze);
                                    Log.d("Alarm_a_1:", "11");
//                                next_date = parseString.addDate(next_date, (long) occurrence);
//                                    mediaPlayer = MediaPlayer.create(context, R.raw.alarm_sound);
//                                    mediaPlayer.start();
                                    // replace the below query with update query for medicine_table
                                    c_snooze = -1;
                                    myDb.updateMedicine("Medicine_table", med_id, med_name, med_des,start_date, next_date, end_date, repetition,occurrence, sleep_from, sleep_to, med_status, c_snooze);

                                    if (parseString.compareDate(end_date, currentDate) == -1) {
                                        Log.d("Alarm_a_1 :", "11.1");
                                        Toast.makeText(context,"Medicine "+ med_name +" course is complete",Toast.LENGTH_SHORT).show();
                                        myDb.execMyQuery("update Report_table set med_status = 'COMPLETED' where med_id = " + med_id);
                                        myDb.deleteMedicine("Medicine_table", med_id);
                                    }
                                }
//                                ALARM_FLAG = true;
                                Log.d("Notification_1:", "1");
//                            AlarmNotify alarmNotify = new AlarmNotify();
//                            alarmNotify.generateNotification("Bitcoin","Message123","First Type");
                                Log.d("Notification_1:", "3");
                            }
                            if(c_snooze != 0) {
                                med_count++;
                                if (med_count == 1) {
                                    med_note = med_note + med_name;
                                } else if (med_count > 1) {
                                    med_note = med_note + ", " + med_name;
                                    count_msg = "Medicines ";
                                }
                            }
                        } else {
                            med_status = "COMPLETE";
                            Toast.makeText(context,"Medicine "+med_name+" course is complete",Toast.LENGTH_SHORT).show();
                            myDb.execMyQuery("update Report_table set med_status = 'COMPLETED' where med_id = " + med_id);
                            myDb.deleteMedicine("Medicine_table", med_id);
                            Log.d("Alarm_a_1:", "12");
                            // Update Status in report table for all having this med_id
                            // Delete from medicine table for this med_id
                            // notify or may be not
                        }
                    }
                } else {
                    med_status = "COMPLETE";
                    Toast.makeText(context,"Medicine "+med_name+" course is complete",Toast.LENGTH_SHORT).show();
                    myDb.execMyQuery("update Report_table set med_status = 'COMPLETED' where med_id = " + med_id);
                    myDb.deleteMedicine("Medicine_table", med_id);
                    Log.d("Alarm_a_1:", "12");
                    // Update Status in report table for all having this med_id
                    // Delete from medicine table for this med_id
                    // notify or may be not
                }
            }
        }
        Log.d(TAG_ALARM_TRIGGER_BROADCAST, alarmDescription);
//        Toast.makeText(context, alarmDescription, Toast.LENGTH_LONG).show();
        Log.d("Alarm_a_1:","13");

        if(ALARM_FLAG == true){

            // Pin Checking
            sharedpreferences = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            Intent intent2 = new Intent(context, NotificationInbox.class);
            if (!sharedpreferences.contains(mypreference)) {
                intent2 = new Intent(context, NotificationInbox.class);
            } else if (sharedpreferences.contains(mypreference)) {
                sharedpreferences = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                String p_enabled = sharedpreferences.getString("p_enabled", null);
                if (p_enabled.equals("1")) {
                    intent2 = new Intent(context, NotificationInboxOnce.class);
                } else {
                    intent2 = new Intent(context, NotificationInbox.class);
                }
            }

//            Intent intent2 = new Intent(context,NotificationInboxOnce.class);
            showNotification(context,"" + med_count +" "+ count_msg+ " Pending","It's medicine time: Please take " + count_msg + med_note + ".",intent2);

//            Speech sp = new Speech();

        }
    }


    //Broadcast Notification
    public void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Medicine Reminder";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.alarm_icon)
                .setContentTitle(title)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        mBuilder.setAutoCancel(true);

        notificationManager.notify(notificationId, mBuilder.build());
    }
}


//package abhishek.com.medreminder;
//
//import android.app.AlertDialog;
//import android.app.NotificationChannel;
//import android.app.NotificationChannelGroup;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.support.v4.app.NotificationCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AlarmNotify extends AppCompatActivity{
//
////    public void addNotification() {
////        Log.d("Notification_1:", "2");
////        NotificationCompat.Builder builder =
////                new NotificationCompat.Builder(this)
////                        .setSmallIcon(R.drawable.ic_medine_reminder)
////                        .setContentTitle("Notifications Example")
////                        .setContentText("This is a test notification");
//
////        Intent notificationIntent = new Intent(this, AlarmTriggerBroadcastReceiver.class);
////        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
////                PendingIntent.FLAG_UPDATE_CURRENT);
////        builder.setContentIntent(contentIntent);
//
//        // Add as notification
////        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
////        manager.notify(0, builder.build());
////    }
////        public void addNotification() {
////            NotificationCompat.Builder builder =
////                    new NotificationCompat.Builder(this)
////                            .setSmallIcon(R.drawable.ic_medine_reminder)
////                            .setContentTitle("Notifications Example")
////                            .setContentText("This is a test notification");
////
////            Intent notificationIntent = new Intent(this, MainActivity.class);
////            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
////                    PendingIntent.FLAG_UPDATE_CURRENT);
////            builder.setContentIntent(contentIntent);
////
////            // Add as notification
////            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
////            manager.notify(0, builder.build());
////        }
//
//    List<String> data = new ArrayList<>();
//
//    int notifier_counter = 0;
//    NotificationManager notificationManager;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); // copied
////        generateNotification("Bitcoin","Message123","First Type");
//    }
//
//    public boolean generateNotification(String Header, String Message, String Type) {
//
//        Log.d("Notification_Status:", "Started");
//        boolean notificationFlag = sendNotification(Header, Message, Type);
//
//        Log.d("Notification_Status:", "Finished");
//        if(notificationFlag == true)
//            return true;
//        else
//            return false;
//    }
//
//    private boolean sendNotification(String notificationHeader, String notificationMessage, String notificationType) {
//        Log.d("Notification_Status:", "Generated");
////        NotificationManager notificationManager;    // copied
////        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        Log.d("Notification_Status:", "ManagerActived");
//        data.add(notificationHeader);
//
//        Log.d("Notification_Status:", "Sending 1.0");
//        createNotificationGroups(notificationType);
//        Log.d("Notification_Status:", "Sending 2.0");
//        createNotificationChannels(notificationType);
//
//        Log.d("Notification_Status:", "Sending 3.0");
//        if (notificationMessage.length() > 0) {
//            String channel_id = "";
//            String group_id = "";
//            PendingIntent contentIntent = PendingIntent.getActivity(AlarmNotify.this, 0, new Intent(AlarmNotify.this, NotificationInboxOnce.class), 0);
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                group_id = notificationType;
//                channel_id = notificationManager.getNotificationChannel(notificationHeader + "_" + group_id).getId();
//                contentIntent = PendingIntent.getActivity(AlarmNotify.this, 0, new Intent(AlarmNotify.this, NotificationInboxOnce.class).putExtra("importance", notificationManager.getNotificationChannel(channel_id).getImportance()).putExtra("channel_id", channel_id), PendingIntent.FLAG_UPDATE_CURRENT);
//            }
//
//            NotificationCompat.Builder notification = new NotificationCompat.Builder(AlarmNotify.this, channel_id)
//                    .setContentTitle(notificationHeader)
//                    .setContentText(notificationMessage)
//                    .setGroup(group_id)
//                    .setContentIntent(contentIntent)
//                    .setSmallIcon(R.mipmap.ic_launcher);
//
//            notifier_counter++;
//            notificationManager.notify(notifier_counter, notification.build());
//
//            return true;
//        } else {
//            Toast.makeText(getApplicationContext(), "Empty Notification Message", Toast.LENGTH_LONG).show();
//            return false;
//        }
//    }
//
//    private void createNotificationGroups(String notificationType) {
//        Log.d("Notification_Status:", "Sending 1.1");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Log.d("Notification_Status:", "Sending 1.1.1");
//            List<NotificationChannelGroup> list = new ArrayList<>();
//            Log.d("Notification_Status:", "Sending 1.1.2");
//            list.add(new NotificationChannelGroup(notificationType, notificationType));
//
//            Log.d("Notification_Status:", "Sending 1.1.3");
//            notificationManager.createNotificationChannelGroups(list);
//        }
//    }
//
//
//    private void createNotificationChannels(String notificationType) {
//        Log.d("Notification_Status:", "Sending 2.1");
//        for (String s : data) {
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                NotificationChannel notificationChannel = new NotificationChannel(s + "_" + notificationType, s, NotificationManager.IMPORTANCE_HIGH);
//                notificationChannel.enableLights(true);
//                notificationChannel.enableVibration(true);
//                notificationChannel.setGroup(notificationType);
//                notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//
////                NotificationManager notificationManager;    // copied
////                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);    // copied
//                if (notificationManager != null) {
//                    notificationManager.createNotificationChannel(notificationChannel);
//                }
//            }
//        }
//    }
//
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        Log.d("Notification_Status:", "NewIntent");
//        super.onNewIntent(intent);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            final Bundle bundle = intent.getExtras();
//            int importance = -1;
//            if (bundle != null) {
//                importance = bundle.getInt("importance");
//            }
//            if (importance != -1) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(this);
//                alert.setMessage("Goto settings to change the Notification channel")
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                updateNotificationSettings(bundle.getString("channel_id"));
//                            }
//                        }).setNegativeButton("CANCEL", null)
//                        .show();
//            }
//        }
//    }
//
//    private void updateNotificationSettings(String channel_id) {
//        Log.d("Notification_Status:", "updatingSettings");
//        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
//        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel_id);
//        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//        startActivity(intent);
//    }
//}

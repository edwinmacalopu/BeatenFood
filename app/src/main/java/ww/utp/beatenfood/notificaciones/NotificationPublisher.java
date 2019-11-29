package ww.utp.beatenfood.notificaciones;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

import ww.utp.beatenfood.MainActivity;
import ww.utp.beatenfood.R;


public class NotificationPublisher extends BroadcastReceiver {
    SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
    String hora = sdf.format(new Date());
    int idnotif=Integer.parseInt(hora);



    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        int NOTIFICATION_ID = idnotif;
        String CHANNEL_ID = hora;

        String titulo=intent.getStringExtra("TITULO");
        String tit=titulo+" : ESTA POR VENCER !!!";

        String detalle=intent.getStringExtra("DETALLE");
        //String tit=titulo+" : ESTA POR VENCER !!!";


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        //Create the content intent for the notification, which launches this activity
        Intent contentIntent = new Intent(context, MainActivity.class);

        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(),CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_food)
                .setChannelId(hora)
                .setContentTitle(tit)
                .setContentText(detalle)
                .setColor(Color.RED)
                .setColorized(true)
                .setContentIntent(contentPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        //Deliver the notification
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }
}

package com.jh.safereturn;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;

/**
 * Created by Jiyoung on 2015-12-07.
 */
public class AlarmReceiver extends Service {

    NotificationManager nManager;

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();

    }

    @SuppressWarnings("staic-access")
    @Override
    public void onStart(Intent intent, int startID) {

        super.onStart(intent, startID);

        String setMessage = NotificationActivity.setMsg;

        nManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(),MainActivity.class);

        Notification notification = new Notification(R.drawable.gohome,setMessage, System.currentTimeMillis());
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.setLatestEventInfo(this.getApplicationContext(), "SafeReturn", setMessage, pendingNotificationIntent);

        notification.sound = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6");
        nManager.notify(0, notification);
    }
}

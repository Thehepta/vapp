package com.example.lib.client.stub;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class DaemonService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private static final int NOTIFY_ID = 1001;

    public static void startup(Context context) {
        context.startService(new Intent(context, DaemonService.class));
    }

    public void onDestroy() {
        super.onDestroy();
        startup(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, InnerService.class));
        startForeground(NOTIFY_ID, new Notification());

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public static final class InnerService extends Service {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(NOTIFY_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}

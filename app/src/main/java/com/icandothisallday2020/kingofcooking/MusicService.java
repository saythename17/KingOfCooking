package com.icandothisallday2020.kingofcooking;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;


public class MusicService extends Service {
    MediaPlayer mp;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel=new NotificationChannel("music","Music",NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"music");
            builder.setSmallIcon(R.drawable.ic_music_note_skyblue);
            builder.setContentTitle("요리왕 BGM");
            builder.setContentText("Playing BGM...");
            Intent i=new Intent(this,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,17,i,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);
            Notification notification=builder.build();
            startForeground(18,notification);
        }
        if(mp==null){
            mp=MediaPlayer.create(this,R.raw.mimi);
            mp.setLooping(true);
            mp.setVolume(0.8f,0.8f);
        }
        mp.start();
        return START_STICKY;
    }

    void pause(){
        if(mp==null&&mp.isPlaying())
        mp.pause();
    }

    @Override
    public void onDestroy() {
        if(mp!=null && mp.isPlaying()){
            mp.stop();
            mp.release();
            mp=null;
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    class MyBinder extends Binder{
        public MusicService getService(){return  MusicService.this;}

    }

}


package com.example.tarde.notificaciones;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private Notification.Builder builderBigPicture;
    private Notification.Builder builderProgress;
    private int incr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,new Intent(this,MainActivity.class),0);
        builderBigPicture = new Notification.Builder(this);
        builderBigPicture.setTicker("Aviso")
                .setSmallIcon(android.R.drawable.ic_menu_camera)
                .setContentTitle("Notificacion de aviso")
                .setContentText("Descripcion de la notificacion")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_dialog_alert))
                .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_dialog_alert)))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        builderProgress = new Notification.Builder(this);
        builderProgress.setProgress(100,10,false)
                .setTicker("Aviso 2")
                .setSmallIcon(android.R.drawable.ic_menu_agenda)
                .setContentTitle("Cargando");
        incr = 0;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_bigPicture) {
            Notification notification2 = builderBigPicture.build();
            notificationManager.notify(1,notification2);
            return true;
        }else if(id == R.id.action_progress){
            Notification notification = builderProgress.build();
            notificationManager.notify(2,notification);
        }else if(id == R.id.action_cancel_notificacion_progress){
            notificationManager.cancel(2);
        }else if(id == R.id.action_add_progress){
            if(incr == 100){
                incr = 10;
            }
            incr = incr + 10;
            builderProgress.setProgress(100,incr,false);
        }
        return super.onOptionsItemSelected(item);
    }
}

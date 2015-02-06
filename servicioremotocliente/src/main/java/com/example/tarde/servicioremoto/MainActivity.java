package com.example.tarde.servicioremoto;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private Messenger messenger;
    private IServicioSaludador servicioSaludador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_bind_service:
                Intent intent = new Intent("com.examle.tarde.servicioremoto.saludo");
                bindService(intent,new RemoteServiceConnecction(), Context.BIND_AUTO_CREATE);
                return true;
            case R.id.action_saludo_service:
                try {
                    Message message = Message.obtain(null,0);
                    Bundle bundle = new Bundle();
                    bundle.putString("mensaje","Hola mundo!");
                    message.setData(bundle);
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.action_bind_servicio_saludador:
                Intent intent1 = new Intent("com.examle.tarde.serviciosaludador.SALUDO");
                bindService(intent1,new SaludadorServiceConnection(), Context.BIND_AUTO_CREATE);
                return true;
            case R.id.action_saludar_servicio_saludador:
                try {
                    if(servicioSaludador != null) {
                        servicioSaludador.basicTypes(12, 12, true, 12f, 12.5, "Texto");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class RemoteServiceConnecction implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
    
    class SaludadorServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            servicioSaludador = IServicioSaludador.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}

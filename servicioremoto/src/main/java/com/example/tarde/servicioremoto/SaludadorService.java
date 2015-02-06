package com.example.tarde.servicioremoto;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class SaludadorService extends Service {
    public SaludadorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        IServicioSaludador.Stub stub = new IServicioSaludador.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
                //Desde aqui no se puede hacer un Toast
                Log.i("SERVICIO_SALUDADOR","El enterno es "+anInt);
            }
        };
        return null;
    }
}

package com.example.tarde.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FuncionalidadRepetidaBroadcastReceiver extends BroadcastReceiver {

    public static final String EXTRA_DATO = "dato";

    public FuncionalidadRepetidaBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Intent intent2 = new Intent(MainActivity.ACTION);

        intent2.putExtra(EXTRA_DATO,"Realizando el saludo...");

        context.sendBroadcast(intent2);
    }
}

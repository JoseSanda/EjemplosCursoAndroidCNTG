package com.example.tarde.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Advertencia")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Se acepto", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

        View btActividad = findViewById(R.id.btActividad);
        btActividad.setOnClickListener(this);

        View btFragmento = findViewById(R.id.btFragmento);
        btFragmento.setOnClickListener(this);

        View btPersonalizado = findViewById(R.id.btPersonalizado);
        btPersonalizado.setOnClickListener(this);

        View btSeleccionMultiple = findViewById(R.id.btSeleccionMultiple);
        btSeleccionMultiple.setOnClickListener(this);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return builder.create();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case(R.id.btActividad):
                showDialog(1);
                break;
            case(R.id.btFragmento):
                new AdvertenciaDialogFragment().show(getFragmentManager(),"Dialogo");
                break;
            case(R.id.btPersonalizado):
                new PersonalizadoDialogFragment().show(getFragmentManager(),"Personalizado");
                break;
            case(R.id.btSeleccionMultiple):
                new SeleccionMultipleDialogFragment().show(getFragmentManager(),"SeleccionMultiple");
                break;
        }
    }

}

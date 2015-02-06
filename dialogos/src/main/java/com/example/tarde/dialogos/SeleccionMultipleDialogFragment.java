package com.example.tarde.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by tarde on 04/02/2015.
 */
public class SeleccionMultipleDialogFragment extends DialogFragment {

    private AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String[] coloresText = {"rojo","azul","verde"};
        final boolean[] coloresSeleccionados = {true,false,true};

        builder = new AlertDialog.Builder(getActivity());
        builder.setMultiChoiceItems(coloresText,coloresSeleccionados, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                coloresSeleccionados[which] = isChecked;
                StringBuilder sBuilder = new StringBuilder("Se han seleccionado los colores: ");
                boolean algunColorSeleccionado = false;
                for (int i =0;i<coloresSeleccionados.length;i++) {
                    if(coloresSeleccionados[i]){
                        algunColorSeleccionado = true;
                        sBuilder.append(coloresText[i]);
                        sBuilder.append(",");
                    }
                }
                if(!algunColorSeleccionado) {
                    sBuilder = new StringBuilder("No se ha seleccionado ningun color");
                }
                Toast.makeText(getActivity(),sBuilder.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return builder.create();
    }
}


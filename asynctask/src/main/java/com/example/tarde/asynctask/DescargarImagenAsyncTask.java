package com.example.tarde.asynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by tarde on 04/02/2015.
 */
public class DescargarImagenAsyncTask extends AsyncTask<String,Integer,Bitmap> {

    private ProgressDialog progressDialog;
    private ImageView imageView;

    public DescargarImagenAsyncTask(ProgressDialog progressDialog, ImageView imageView) {
        this.progressDialog = progressDialog;
        this.imageView = imageView;
        this.progressDialog.setMax(100);
    }

    /**
     * Unico metodo que se ejecuta en el hilo secuntario
     *
     * @param params
     * @return
     */
    @Override
    protected Bitmap doInBackground(String... params) {
        //Realizar tarea de larga duracion
        String paramURL = null;
        if(params != null && params.length>0){
            paramURL = params[0];
        }
        if(paramURL != null && !paramURL.equals("")){
            try {
                URL url = new URL(paramURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();

                int tamanhoImagen = con.getContentLength();
                byte[] imagen = new byte[tamanhoImagen];
                byte[] buffer = new byte[1024];

                InputStream inputStream = con.getInputStream();
                for(int bytesTotalesLeidos =0;bytesTotalesLeidos< tamanhoImagen;){
                    int bytesLeidos = inputStream.read(buffer);
                    System.arraycopy(buffer,0,imagen,bytesTotalesLeidos,bytesLeidos);
                    bytesTotalesLeidos+=bytesLeidos;

                    publishProgress((bytesTotalesLeidos*100)/tamanhoImagen);
                }
                return BitmapFactory.decodeByteArray(imagen, 0, tamanhoImagen);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //Metodos que se ejecutan en el hilo principal

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }
}


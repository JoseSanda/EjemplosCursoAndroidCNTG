package com.example.tarde.asynctask;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            DescargarImagenAsyncTask asyncTask = new DescargarImagenAsyncTask(progressDialog, imageView);
            //String imagen = "http://en.wikipedia.org/wiki/McLaren_12C#mediaviewer/File:McLaren_MP4-12C_%E2%80%93_Frontansicht_(3),_30._August_2012,_D%C3%BCsseldorf.jpg";
            String imagen = "http://pictures.topspeed.com/IMG/crop/201307/mclaren-mp4-12c-2_1600x0w.jpg";
            asyncTask.execute(imagen);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

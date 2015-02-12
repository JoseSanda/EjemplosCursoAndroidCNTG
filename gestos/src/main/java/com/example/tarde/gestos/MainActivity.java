package com.example.tarde.gestos;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements GestureOverlayView.OnGesturePerformedListener{

    private GestureLibrary gestureLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureLibrary = GestureLibraries.fromRawResource(this,R.raw.gestures);
        GestureOverlayView gestureOverlay = (GestureOverlayView) findViewById(R.id.gestureOverlayView);
        gestureOverlay.addOnGesturePerformedListener(this);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> recognize = gestureLibrary.recognize(gesture);
        //Toast.makeText(this,"Gesto con trazas: "+gesture.getStrokesCount(),Toast.LENGTH_LONG).show();
        for(Prediction p : recognize){
            Toast.makeText(this,p.name+" score: "+p.score,Toast.LENGTH_LONG).show();
        }

    }
}

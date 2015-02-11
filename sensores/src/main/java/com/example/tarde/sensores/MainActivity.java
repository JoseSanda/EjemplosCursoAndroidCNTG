package com.example.tarde.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor acelerometro;
    private Sensor campoMagnetico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        campoMagnetico = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,campoMagnetico,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
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
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //event.accuracy;
        float[] values = event.values;
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Toast.makeText(this,"Acelerometro: "+values[0]+" "+values[1]+" "+values[2],Toast.LENGTH_LONG).show();
        }else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            Toast.makeText(this,"Campo magnetico: "+values[0]+" "+values[1]+" "+values[2],Toast.LENGTH_LONG).show();
        }
    }
}

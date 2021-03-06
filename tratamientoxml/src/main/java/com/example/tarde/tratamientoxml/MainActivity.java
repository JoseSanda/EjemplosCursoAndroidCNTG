package com.example.tarde.tratamientoxml;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_parse) {
            InputStream is = getResources().openRawResource(R.raw.terremotos);
            try {
                List<Terremoto> terremotos = TerremotosPullParser.parse(is);
                ListView terremotosView = (ListView) findViewById(R.id.listView);
                ArrayAdapter<Terremoto> terremotoArrayAdapter = new ArrayAdapter<Terremoto>(this, android.R.layout.simple_expandable_list_item_1, terremotos);
                terremotosView.setAdapter(terremotoArrayAdapter);
            } catch (Exception e) {
                Log.e("ERROR",e.getLocalizedMessage());
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.tarde.basededatos;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tarde.basededatos.com.example.tarde.basededatos.dao.NoticiaDAO;

import java.util.Date;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoticiasSQLiteOpenHelper noticiasSQL = new NoticiasSQLiteOpenHelper(this, "NoticiasBD.s3db", null, R.integer.database_version);
        SQLiteDatabase db = noticiasSQL.getWritableDatabase();
        NoticiaDAO dao = new NoticiaDAO(db);
        db.beginTransaction();
        try{
            dao.insert(new Noticia("123456","Titulo de la noticia","http://www.elpais.es","YO","Descripción de la noticia",new Date()));
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
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
}

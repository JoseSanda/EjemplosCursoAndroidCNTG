package com.example.tarde.clientecontentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tarde.noticiasutils.NoticiasProviderUtil;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentResolver contentResolver = getContentResolver();
        Uri uri = NoticiasProviderUtil.NOTICIAS_URI;
        ContentValues values = new ContentValues();
        values.put(NoticiasProviderUtil.ID_COLUMN,"ABC");
        values.put(NoticiasProviderUtil.TITULO_COLUMN,"Nueva noticia");
        values.put(NoticiasProviderUtil.LINK_COLUMN,"http://www.elpais.es");
        values.put(NoticiasProviderUtil.DESC_COLUMN,"Descripcion de la nueva noticia");
        values.put(NoticiasProviderUtil.CREADOR_COLUMN,"yo");
        values.put(NoticiasProviderUtil.FECHA_COLUMN,new Date().getTime());
        Uri newURI = contentResolver.insert(uri, values);

        String[] projection = {NoticiasProviderUtil.ID_COLUMN,NoticiasProviderUtil.TITULO_COLUMN,NoticiasProviderUtil.LINK_COLUMN,NoticiasProviderUtil.DESC_COLUMN};
        Cursor cursor = contentResolver.query(newURI, projection, null, null, null);
        List<Noticia> noticias = toNoticia(cursor);
        Toast.makeText(this,"Tamaño "+noticias.size()+ "| "+noticias.toString(),Toast.LENGTH_LONG).show();

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

    private List<Noticia> toNoticia(Cursor cursor){
        List<Noticia> resultado = new LinkedList<>();
        if(cursor.moveToFirst()) {
            do{
                Noticia noticia = new Noticia();
                if(cursor.getColumnIndex(Noticia.ID_COLUMN)!=-1){
                    noticia.setId(cursor.getString(cursor.getColumnIndex(Noticia.ID_COLUMN)));
                }
                if(cursor.getColumnIndex(Noticia.TITULO_COLUMN)!=-1){
                    noticia.setTitulo(cursor.getString(cursor.getColumnIndex(Noticia.TITULO_COLUMN)));
                }
                if(cursor.getColumnIndex(Noticia.CREADOR_COLUMN)!=-1){
                    noticia.setCreador(cursor.getString(cursor.getColumnIndex(Noticia.CREADOR_COLUMN)));
                }
                if(cursor.getColumnIndex(Noticia.DESC_COLUMN)!=-1){
                    noticia.setDescripcion(cursor.getString(cursor.getColumnIndex(Noticia.DESC_COLUMN)));
                }
                if(cursor.getColumnIndex(Noticia.LINK_COLUMN)!=-1){
                    noticia.setLink(cursor.getString(cursor.getColumnIndex(Noticia.LINK_COLUMN)));
                }
                if(cursor.getColumnIndex(Noticia.FECHA_COLUMN)!=-1){
                    noticia.setFechaPublicacion(new Date(cursor.getLong(cursor.getColumnIndex(Noticia.ID_COLUMN))));
                }
                resultado.add(noticia);
            }while(cursor.moveToNext());
        }
        return resultado;
    }
}

package com.example.tarde.basededatos.com.example.tarde.basededatos.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tarde.basededatos.Noticia;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tarde on 09/02/2015.
 */
public class NoticiaDAO {

    private SQLiteDatabase db;

    public NoticiaDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void insert(Noticia noticia){
        db.insert(Noticia.TABLE,Noticia.TITULO_COLUMN,getContentValues(noticia));
    }

    public void update(Noticia noticia){
        String[] whereClause = {noticia.getId()};
        db.update(Noticia.TABLE,getContentValues(noticia),"ID = '?' ",whereClause);
    }

    public void borrar(Noticia noticia){
        String whereClause = "ID = '?' ";
        String[] whereArgs = {noticia.getId()};
        db.delete(Noticia.TABLE,whereClause,whereArgs);
    }

    public void borrar(String id){
        String whereClause = "ID = '?' ";
        String[] whereArgs = {id};
        db.delete(Noticia.TABLE,whereClause,whereArgs);
    }

    public List<Noticia> consultar(String id){
        String[] proyeccion = {Noticia.ID_COLUMN,Noticia.TITULO_COLUMN,Noticia.CREADOR_COLUMN};
        String whereClause = Noticia.ID_COLUMN + " = '?'";
        String[] whereArgs = {id};
        Cursor cursor = db.query(false, Noticia.TABLE, proyeccion, whereClause, whereArgs, null, null, null, null);
        return toNoticia(cursor);
    }

    public List<Noticia> consultar(){
        String[] proyeccion = {Noticia.ID_COLUMN,Noticia.TITULO_COLUMN,Noticia.CREADOR_COLUMN};
        Cursor cursor = db.query(false, Noticia.TABLE, proyeccion, null, null, null, null, null, null);
        return toNoticia(cursor);
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

    private ContentValues getContentValues(Noticia noticia){
        ContentValues contentValues = new ContentValues();

        contentValues.put(Noticia.ID_COLUMN, noticia.getId());
        contentValues.put(Noticia.TITULO_COLUMN,noticia.getTitulo());
        contentValues.put(Noticia.CREADOR_COLUMN,noticia.getCreador());
        contentValues.put(Noticia.LINK_COLUMN,noticia.getLink());
        contentValues.put(Noticia.DESC_COLUMN,noticia.getDescripcion());
        contentValues.put(Noticia.FECHA_COLUMN,noticia.getFechaPublicacion().getTime());

        return contentValues;
    }
}

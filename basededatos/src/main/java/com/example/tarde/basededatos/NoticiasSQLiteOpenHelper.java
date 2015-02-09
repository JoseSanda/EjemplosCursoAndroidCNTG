package com.example.tarde.basededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tarde on 09/02/2015.
 */
public class NoticiasSQLiteOpenHelper extends SQLiteOpenHelper{

    private Context context;
    private String TAG_LOG = NoticiasSQLiteOpenHelper.class.getCanonicalName();

    public NoticiasSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    //Gestion de la BD
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQLiteDatabase es semejante al session de hibernate
        ejecutarScript(db,R.array.scriptCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                ejecutarScript(db,R.array.scriptUpgrade_1_3);
                break;
            case 2:
                ejecutarScript(db,R.array.scriptUpgrade_2_3);
                break;
        }
    }

    private void ejecutarScript(SQLiteDatabase db,int idResScript) {
        db.beginTransaction();
        String[] scripts = context.getResources().getStringArray(idResScript);
        try{
            for(String s: scripts){
                db.execSQL(s);
            }
            db.setTransactionSuccessful();
        }catch(Exception e){
            Log.e(TAG_LOG, "Error al crear la base de datos", e);
        }finally {
            db.endTransaction();
        }
    }
}

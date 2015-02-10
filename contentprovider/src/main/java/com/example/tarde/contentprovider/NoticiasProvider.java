package com.example.tarde.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.example.tarde.noticiasutils.NoticiasProviderUtil;

public class NoticiasProvider extends ContentProvider {

    private Context context;
    private SQLiteDatabase db;
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int NOTICIAS_CODE = 1;
    private static final int NOTICIA_CODE = 2;
    private static final int AUTORES_CODE = 3;
    private static final int AUTOR_CODE = 4;
    private static final String TABLE = "Noticia";

    static{
        //content://com.example.tarder.contentprovider/Noticia -> Affects all content
        matcher.addURI(NoticiasProviderUtil.NOTICIAS_AUTHORITY, NoticiasProviderUtil.NOTICIA_ENTIDAD, NOTICIAS_CODE);
        //content://com.example.tarder.contentprovider/Noticia -> Affects item with ID 1
        //* -> any character, # -> number
        matcher.addURI(NoticiasProviderUtil.NOTICIAS_AUTHORITY,NoticiasProviderUtil.NOTICIA_ENTIDAD+"/*", NOTICIA_CODE);
        matcher.addURI(NoticiasProviderUtil.NOTICIAS_AUTHORITY,NoticiasProviderUtil.AUTOR_ENTIDAD, AUTORES_CODE);
        matcher.addURI(NoticiasProviderUtil.NOTICIAS_AUTHORITY,NoticiasProviderUtil.AUTOR_ENTIDAD+"/#", AUTOR_CODE);
    }

    public NoticiasProvider() {
    }

    @Override
    public boolean onCreate() {
        NoticiasSQLiteOpenHelper helper = new NoticiasSQLiteOpenHelper(getContext(),"NoticiasDB.s3db",null,getContext().getResources().getInteger(R.integer.DataBaseVersion));
        db = helper.getWritableDatabase();
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tabla = "";
        switch (matcher.match(uri)){
            case NOTICIA_CODE:
                String whereClause = NoticiasProviderUtil.ID_COLUMN+"= ?";
                String[] whereArgs = {uri.getLastPathSegment()};
                return db.delete(TABLE,whereClause,whereArgs);
            case NOTICIAS_CODE:
                return db.delete(TABLE,null,null);
            default:
                throw new UnsupportedOperationException("No se soporta la operacion");
        }
    }

    @Override
    //Must return a uri to new notice
    public Uri insert(Uri uri, ContentValues values) {
        switch (matcher.match(uri)) {
            case NOTICIAS_CODE:
                db.insert(TABLE, NoticiasProviderUtil.TITULO_COLUMN, values);
                return uri.withAppendedPath(uri,values.getAsString(NoticiasProviderUtil.ID_COLUMN));
            default:
                throw new UnsupportedOperationException("No se soporta la operacion");
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (matcher.match(uri)){
            case NOTICIA_CODE:
                String whereClause = NoticiasProviderUtil.ID_COLUMN+"= ?";
                String[] whereArgs = {uri.getLastPathSegment()};
                return db.query(false,TABLE,projection,whereClause,whereArgs,null,null,sortOrder,null);
            case NOTICIAS_CODE:
                return db.query(false, TABLE, projection, selection, selectionArgs, null, null, sortOrder, null);
            default:
                throw new UnsupportedOperationException("No se soporta la operacion");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (matcher.match(uri)){
            case NOTICIA_CODE:
                String whereClause = NoticiasProviderUtil.ID_COLUMN+"= '?'";
                String[] whereArgs = {uri.getLastPathSegment()};
                return db.update(TABLE, values, whereClause, whereArgs);
            case NOTICIAS_CODE:
                return db.update(TABLE, values, selection, selectionArgs);
            default:
                throw new UnsupportedOperationException("No se soporta la operacion");
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (matcher.match(uri)){
            case NOTICIA_CODE:
                return "vnd.android.cursor.item/vnd."+NoticiasProviderUtil.NOTICIAS_AUTHORITY+"."+NoticiasProviderUtil.NOTICIA_ENTIDAD;
            case NOTICIAS_CODE:
                return "vnd.android.cursor.dir/vnd."+NoticiasProviderUtil.NOTICIAS_AUTHORITY+"."+NoticiasProviderUtil.NOTICIA_ENTIDAD;
            default:
                throw new UnsupportedOperationException("Tipo MIME no soportado");
        }
    }
}

package com.example.tarde.noticiasutils;

import android.net.Uri;

/**
 * Created by tarde on 10/02/2015.
 */
public interface NoticiasProviderUtil {

    public static final Uri NOTICIAS_URI = Uri.parse("content://com.example.tarder.contentprovider/Noticia");

    public static final String NOTICIAS_AUTHORITY = "com.example.tarder.contentprovider";
    public static final String NOTICIA_ENTIDAD = "Noticia";
    public static final String AUTOR_ENTIDAD = "Autor";

    public static final String TITULO_COLUMN = "TITULO";
    public static final String LINK_COLUMN = "LINK";
    public static final String DESC_COLUMN = "DESCRIPCION";
    public static final String FECHA_COLUMN = "FECHA";
    public static final String ID_COLUMN = "ID";
    public static final String CREADOR_COLUMN = "CREADOR";
}

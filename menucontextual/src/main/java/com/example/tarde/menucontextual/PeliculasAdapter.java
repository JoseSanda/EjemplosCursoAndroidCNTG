package com.example.tarde.menucontextual;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tarde on 03/02/2015.
 */
public class PeliculasAdapter extends BaseAdapter {

    private final Activity contexto;
    private final int layout;
    private final List<Pelicula> listado;

    public PeliculasAdapter(Activity contexto, int resLayout, List<Pelicula> listado) {
        this.contexto  = contexto;
        this.layout = resLayout;
        this.listado = listado;
    }

    @Override
    public int getCount() {
        return listado.size();
    }

    @Override
    public Object getItem(int position) {
        return listado.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Pelicula pelicula = listado.get(position);

        PeliculaViewHelper peliculaViewHelper;

        //ahorra la creacion de objetos de tipo View, para repitandos del AdapterView
        if (convertView == null){
            convertView = contexto.getLayoutInflater().inflate(layout, null);

            peliculaViewHelper = new PeliculaViewHelper();

            peliculaViewHelper.actores = (TextView) convertView.findViewById(R.id.actores);
            //FIXME no encuentra R.id.anno
            peliculaViewHelper.año = (TextView) convertView.findViewById(R.id.anno);
            peliculaViewHelper.titulo = (TextView) convertView.findViewById(R.id.titulo);

            convertView.setTag(peliculaViewHelper);
        } else {
            peliculaViewHelper = (PeliculaViewHelper) convertView.getTag();
        }

        peliculaViewHelper.titulo.setText(pelicula.getTitulo());
        peliculaViewHelper.año.setText(String.valueOf(pelicula.getAño()));
        peliculaViewHelper.actores.setText(pelicula.getActores().toString());

        return convertView;
    }

    public void addPelicula(Pelicula pelicula){
        this.listado.add(pelicula);
        notifyDataSetChanged();
    }

    public void deletePelicula(int posicion){
        this.listado.remove(posicion);
        notifyDataSetChanged();
    }

    public void editPelicula(int posicion, Pelicula newPelicula){
        Pelicula oldPelicula = listado.get(posicion);
        oldPelicula.setTitulo(newPelicula.getTitulo());
        oldPelicula.setAño(newPelicula.getAño());
        oldPelicula.setActores(newPelicula.getActores());
        notifyDataSetChanged();
    }

    //Clase de apoyo, para mantener las referencias a los elementos View del layout,
    //para no tener que realizar la busqueda de los elementos en el arbol, cada vez
    //que se quiere cambiar sus datos.
    class PeliculaViewHelper{
        TextView titulo;
        TextView año;
        TextView actores;
    }



}

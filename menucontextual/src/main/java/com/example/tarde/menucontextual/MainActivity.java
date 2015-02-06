package com.example.tarde.menucontextual;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private PeliculasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Pelicula> lista = new LinkedList<>();
        lista.add(new Pelicula("Lo que el viento se llevo", 1939, new String[]{"Viviang Leigth", "Clark Gable"}));
        lista.add(new Pelicula("Titanic", 1997, new String[]{"Leonardo DiCaprio", "Kate Winslet"}));

        adapter = new PeliculasAdapter(this,R.layout.list_item_pelicula,lista);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.listView:
                int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
                menu.setHeaderTitle(((AdapterView)v).getAdapter().getItem(position).toString());
                getMenuInflater().inflate(R.menu.menu_listview, menu);
            break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
        Pelicula pelicula = (Pelicula) adapter.getItem(position);
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(this,NuevaPeliculaActivity.class);
                startActivityForResult(intent,1);
            break;
            case R.id.action_edit:
                Intent intent2 = new Intent(this,NuevaPeliculaActivity.class);
                intent2.putExtra("posicion",position);
                startActivityForResult(intent2, 2);
            break;
            case R.id.action_delete:
                adapter.deletePelicula(position);
                Toast.makeText(this,"Se a borrado el objeto "+pelicula.toString(),Toast.LENGTH_LONG).show();
            break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            Pelicula pelicula = (Pelicula) data.getSerializableExtra("pelicula");
            int position = data.getIntExtra("posicion", -1);

            switch (requestCode) {
                case 1:
                    adapter.addPelicula(pelicula);
                    break;
                case 2:
                    adapter.editPelicula(position, pelicula);
                    break;
            }
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

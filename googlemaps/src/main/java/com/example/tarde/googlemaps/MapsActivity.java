package com.example.tarde.googlemaps;

import android.app.ActionBar;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

public class MapsActivity extends ActionBarActivity {

    public static final String EXTRA_TERREMOTO = "terremoto";
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


////      Funcionalidad para recoger con una intencion el terremoto
//        Terremoto terremoto = (Terremoto) getIntent().getSerializableExtra(EXTRA_TERREMOTO);
//        if(terremoto == null){
//            finish();
//        }
        Terremoto terremoto = new Terremoto("Terremoto CNTG",1.3f,42.862130f,-8.554f);
        TerremotoMapFragment mapFragment = (TerremotoMapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        if(mapFragment != null){
            mapFragment.dibujarTerremoto(terremoto);
        }
//        Fragment detalleFragment = getFragmentManager().findFragmentById(R.id.detalle);
//        if(detalleFragment != null){
//            detalleFragment.dibujarTerremoto(terremoto);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}

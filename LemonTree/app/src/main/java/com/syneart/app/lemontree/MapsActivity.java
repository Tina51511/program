package com.syneart.app.lemontree;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Spinner mSpnLocation;
    private static String[][] mLocations = {
            //{"location name" ," 25.0336110,1221.56500000"},
            {"多肉植物區","25.032853, 121.508707"},
            {"台北植物園腊葉館","25.031910, 121.508889"},
            {"薑科園區","25.031565, 121.509109"},
            {"欽差行臺","25.032211, 121.508750"},
            {"荷花池","25.031993, 121.510982"}

    };
    private SupportMapFragment supportMapFragment;
    private boolean mbIsZoomFirst= true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mSpnLocation = (Spinner) this.findViewById(R.id.spnLocation);
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item);

        for (int i = 0; i < mLocations.length; i++)
            arrAdapter.add(mLocations[i][0]);

        arrAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        mSpnLocation.setAdapter(arrAdapter);
        mSpnLocation.setOnItemSelectedListener(spnLocationOnItemSelected);
    }
    


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //25.031014
       LatLng sydney = new LatLng(25.032108, 121.509474);
       mMap.addMarker(new MarkerOptions().visible(true).position(new LatLng(25.032853, 121.508707)).title("多肉植物區").snippet("又被称作肉质植物，是指植物能在土壤干旱的条件下拥有肥大的叶或茎"));
       mMap.addMarker(new MarkerOptions().visible(true).position(new LatLng(25.031910, 121.508889)).title("台北植物園腊葉館").snippet("收集保存植物标本的地方，这些标本通常是干制、压制的腊叶标本"));
       mMap.addMarker(new MarkerOptions().visible(true).position(new LatLng(25.031565, 121.509109)).title("薑科園區").snippet("是单子叶植物的一目，本目也叫做美人蕉目。本目常见的植物有香蕉、姜、美人蕉等"));
       mMap.addMarker(new MarkerOptions().visible(true).position(new LatLng(25.032211, 121.508750)).title("欽差行臺").snippet("文明古蹟"));
       mMap.addMarker(new MarkerOptions().visible(true).position(new LatLng(25.031993, 121.510982)).title("荷花池").snippet("莲科莲属多年生草本出水植物，又称荷花、莲花、荷，古称芙蓉、菡萏、芙蕖，是多年生草本花卉"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
    private AdapterView.OnItemSelectedListener spnLocationOnItemSelected =
            new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView parent, View v,
                                           int position, long id) {

                    updateMapLocation();


                }

                @Override
                public void onNothingSelected(AdapterView parent) {
                }

            };

    private void updateMapLocation() {
        int iSelected = mSpnLocation.getSelectedItemPosition();
        String[] sLocation = mLocations[iSelected][1].split(",");
        double dLat = Double.parseDouble(sLocation[0]);	// 南北緯
        double dLon = Double.parseDouble(sLocation[1]);	// 東西經

        if (mbIsZoomFirst) {
            mbIsZoomFirst = false;
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(dLat, dLon), 18));
        } else
            mMap.animateCamera(CameraUpdateFactory.newLatLng(
                    new LatLng(dLat, dLon)));

    }
}

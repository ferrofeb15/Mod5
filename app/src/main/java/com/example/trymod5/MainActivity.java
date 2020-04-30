package com.example.trymod5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mapAPI;
    SupportMapFragment mapFragment;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.sv);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapAPI);
        mapFragment.getMapAsync(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if(location != null || !location.equals("")){
                    Geocoder geocoder = new Geocoder( MainActivity.this);
                    try{
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                    mapAPI.addMarker(new MarkerOptions().position(latLng).title(location));
                    mapAPI.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapAPI = googleMap;

        LatLng umm = new LatLng(-7.920789, 112.597040);
        mapAPI.addMarker(new MarkerOptions().position(umm).title("UMM Kampus 3").snippet("Babatan, Tegalgondo, Kec. Karang Ploso, Malang"));

        LatLng wisata1 = new LatLng(-8.165326, 112.594920);
        mapAPI.addMarker(new MarkerOptions().position(wisata1).title("Sumber Maron").snippet("Karangsuko, Pagelaran, Dusun Adi Luwih, Karangsuko, Kec. Pagelaran"));

        LatLng wisata2 = new LatLng(-8.122948, 112.620754);
        mapAPI.addMarker(new MarkerOptions().position(wisata2).title("Sumber Sira").snippet("Jalan Sunan Kalijaga I RT.05/RW.02, Putuk Utara, Putukrejo, Kec. Gondanglegi"));

        LatLng wisata3 = new LatLng(-8.155845, 112.762079);
        mapAPI.addMarker(new MarkerOptions().position(wisata3).title("Bon Pring").snippet("Sanankerto, Turen, Dusun Kp. Anyar, Sanankerto, Turen"));

        LatLng wisata4 = new LatLng(-8.151407, 112.712076);
        mapAPI.addMarker(new MarkerOptions().position(wisata4).title("Masjid Tiban").snippet("Sananrejo, Kec. Turen"));

        LatLng wisata5 = new LatLng(-7.916041, 112.588815);
        mapAPI.addMarker(new MarkerOptions().position(wisata5).title("Sengkaling Waterpark").snippet("Jl. Raya Sengkaling No.162, Jetis, Mulyoagung, Kec. Dau"));

        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(umm));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(wisata1));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(wisata2));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(wisata3));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(wisata4));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(wisata5));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLngZoom(umm, 15));
    }
}

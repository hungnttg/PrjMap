package com.duanmau.prjmap;

import com.duanmau.prjmap.retrofit.InterfaceInsert;
import com.duanmau.prjmap.retrofit.Product;
import com.duanmau.prjmap.retrofit.ServerResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsDemoActivity extends AppCompatActivity
        implements OnMapClickListener, OnMapLongClickListener, OnCameraIdleListener,
        OnMapReadyCallback {

    private TextView tapTextView;
    private TextView cameraTextView;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_demo);

        tapTextView = findViewById(R.id.tap_text);
        cameraTextView = findViewById(R.id.camera_text);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        this.map.setOnMapClickListener(this);
        this.map.setOnMapLongClickListener(this);
        this.map.setOnCameraIdleListener(this);


        //Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(21.028511, 105.804817);

        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Hanoi"));

        map.setMinZoomPreference(6.0f);
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        map.setMaxZoomPreference(30.0f);
    }
    String theStreet="";
    @Override
    public void onMapClick(LatLng point) {
        tapTextView.setText("tapped, point=" + point);

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try
        {
            addresses = geocoder.getFromLocation(point.latitude, point.longitude, 1);
            if (addresses != null && addresses.size() > 0)
            {
                String address = addresses.get(0).getAddressLine(0);
                String address11 = addresses.get(0).getAddressLine(1);
                String city = addresses.get(0).getLocality();
                theStreet="";
                theStreet = addresses.get(0).getThoroughfare();
                Toast.makeText(getApplicationContext(),city+"; "+address+"; "+address11+"; Pho: "+theStreet,Toast.LENGTH_SHORT).show();
                insertData();//call retrofit
            }
        }
        catch (IOException e) {
        }
    }

    @Override
    public void onMapLongClick(LatLng point) {
        tapTextView.setText("long pressed, point=" + point);
    }

    @Override
    public void onCameraIdle() {
        cameraTextView.setText(map.getCameraPosition().toString());
    }

    //////retrofit
    public void insertData()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceInsert interfaceInsert = retrofit.create(InterfaceInsert.class);
        //chuan bi du lieu
        Product p = new Product();
        p.setName("Name1");
        p.setPrice("123");
        p.setDescription("mota");
        //ServerResuest serverResuest = new ServerResuest();
        //serverResuest.setProducts(p);
        Call<ServerResponse> call =
                interfaceInsert.thucThiInsert(p.getName(),p.getPrice(),
                        p.getDescription());
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call,
                                   Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                String msg = serverResponse.getMessage();
                Toast.makeText(getApplicationContext(),"Retrofit: "+msg,Toast.LENGTH_SHORT).show();
                //txtKetQuaInsert.setText(msg);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }
    /////end retrofit

}
package cl.electivo.colusionfarmacias;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailDrugStore extends FragmentActivity implements OnMapReadyCallback {

    SQLiteDatabase db;
    SearchDBHelper sdbh;
    Farmacia farmacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_drug_store);

        Intent intent = getIntent();
        String idFarmacia = intent.getStringExtra("farmacia");

        sdbh = new SearchDBHelper(getApplicationContext());
        db   = sdbh.getReadableDatabase();

        farmacia = sdbh.findFarmaciaById(db, Integer.parseInt(idFarmacia));

        setTitle(farmacia.getNombre());

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

        LatLng position = new LatLng(farmacia.getX(), farmacia.getY());

        map.addMarker(new MarkerOptions().position(position).title(farmacia.getNombre()));
        pointToPosition(map, position);
    }

    private void pointToPosition(GoogleMap map, LatLng position) {
        //Build camera position
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(17).build();
        //Zoom in and animate the camera.
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

}

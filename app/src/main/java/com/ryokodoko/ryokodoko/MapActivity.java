package com.ryokodoko.ryokodoko;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapActivity extends Fragment implements TaskLoadedCallback {
    Button btnGetDirection;
    MarkerOptions place1 , place2;
    private MapView mapView;
    private GoogleMap mMap;
    LatLng place1LatLng , place2LatLng;
    Polyline polyline;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ///////////////////////////
        /// 맵이 들어갈 맵뷰 생성
        ///////////////////////////
        View rootview = inflater.inflate(R.layout.activity_map,container,false);
        mapView = rootview.findViewById(R.id.fmMap);
        mapView.onCreate(savedInstanceState);
        bundle = getArguments();
        mapView.onResume();
        ///////////////////////////
        int iTemp = bundle.getInt("city");
        switchValue(iTemp);

        place1LatLng = new LatLng(35.718181, 139.722834);
        place1 = new MarkerOptions().position(place1LatLng).title("location 1");
        place2 = new MarkerOptions().position(new LatLng(35.718181, 139.700121)).title("location 2");

        try{
            MapsInitializer.initialize(getActivity().getApplicationContext());
        }catch (Exception e){
            e.printStackTrace();
        }
        //OnMapReadyCallback 인터페이스를 implement 하지 않고 Listener와 같은 개념으로 호출
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.addMarker(place1);
                mMap.addMarker(place2);
//                CameraPosition cameraPosition = new CameraPosition.Builder().target(place1LatLng).zoom(12).build();
//                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place1LatLng, 10));
                new FetchURL(getActivity().getApplicationContext()).execute(getUrl(place1.getPosition(), place2.getPosition(), "transit"), "transit");
            }
        });


        return rootview;
    }

    public void switchValue(int iTemp){
        switch (iTemp) {
            case 1:
                LatLng place = place1LatLng;
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place)); // 삿포로
                changeCameara(CameraUpdateFactory.zoomTo(10));
                break;
            case 2:
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.718181, 139.722834), 10)); // 도쿄
                break;
            case 3:
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(34.6936, 135.502), 10)); // 오사카
                break;
            case 4:
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.569776, 130.356818), 10)); // 후쿠오카
                break;
            default:
                break;
        }
    }

//    public void onMapReady(GoogleMap googleMap) {
//
//        mMap = googleMap;
//        mMap.setMinZoomPreference(6.0f);
//        mMap.setMaxZoomPreference(14.0f);
//        mMap.addMarker(place1);
//        mMap.addMarker(place2);
//        int iTemp = bundle.getInt("city");
//        int iMessage = bundle.getInt("message");


            // 카메라 이동 switch 문 (여행지를 지방으로 나누고 공항이 있는 도시로 카메라를 이동)

//        }
//    }

    private void changeCameara(CameraUpdate update)
    {
        mMap.animateCamera(update,null);

    }

    // 지도가 준비되있지 않으면 CamearaUpdateFactory를 사용할수 없으므로 토스트 띄우는 메소드
    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(getActivity(),"맵 생성 실패",Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(getActivity(),"맵 생성",Toast.LENGTH_SHORT).show();
        return true;
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route (place1)
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route (place2)
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.key);
        return url;
    }


    @Override
    public void onTaskDone(Object... values) {
        if (polyline != null)
            polyline.remove();
        polyline = mMap.addPolyline((PolylineOptions) values[0]);
    }
}


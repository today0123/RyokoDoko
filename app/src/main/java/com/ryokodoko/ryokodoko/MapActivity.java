package com.ryokodoko.ryokodoko;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Fragment implements OnMapReadyCallback {
    Button btnMapOk;
    Button btnMapCancel;
    View rootView;
    private GoogleMap mMap;
    private MapView fmMap;
    private boolean mapsSupported = true;
    LatLng startLatLng, destinationLatLng;
    Bundle bundle;
//    String strCity, strMessage;
//    String urlTransit = null;
//    String urlWaypoints = null;
//    String urlTravelTime = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_map,container,false);
//        btnMapOk = (Button) btnMapOk.findViewById(R.id.btnMapOk);
//        btnMapCancel = (Button) btnMapCancel.findViewById(R.id.btnMapCancel);
        fmMap = (MapView) rootView.findViewById(R.id.fmMap);
        fmMap.onCreate(savedInstanceState);
        fmMap.onResume();
        fmMap.getMapAsync(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        fmMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        fmMap.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fmMap.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        fmMap.onLowMemory();
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MapsInitializer.initialize(getActivity().getApplicationContext());
        if(fmMap != null)
            fmMap.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


//        strCity = bundle.getString("city");
//        strMessage = bundle.getString("message");
        if(mMap == null && mapsSupported) {
            fmMap = (MapView) getActivity().findViewById(R.id.fmMap);
            mMap = googleMap;
            bundle = getArguments();
            int iTemp = bundle.getInt("city");
            int iMessage = bundle.getInt("message");

            // 카메라 이동 switch 문 (여행지를 지방으로 나누고 공항이 있는 도시로 카메라를 이동)
            switch (iTemp) {
                case 1:
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.099615, 141.354887), 10)); // 삿포로
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

            // 출발지와 도착지 마커 생성 및 위치 저장
//            if (strMessage == "start") {
//                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                    @Override
//                    public void onMapClick(LatLng latLng) {
//                        mMap.addMarker(new MarkerOptions().position(latLng)).setTitle("start");
//                        LatLng startLatLng = latLng; // 출발지 위치값 저장
//                    }
//                });
//            }
//            if (strMessage == "destination") {
//                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                    @Override
//                    public void onMapClick(LatLng latLng) {
//                        mMap.addMarker(new MarkerOptions().position(latLng)).setTitle("destination");
//                        LatLng destinationLatLng = latLng; // 도착지 위치값 저장
//                    }
//                });
//            }
        }
    }

//// 확인을 누를시 출발지 목적지 위칫값 전송
//        btnMapOk.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            //
//        }
//    });
//
//        btnMapCancel.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            //
//        }
//    });

//    private void getTrainsitTravleData(String origin, String destination, String mode, String arrival_time) {
//
//        Intent intent = new Intent();
//        origin = intent.getStringExtra("origin");
//        destination = intent.getStringExtra("destination");
//        mode = intent.getStringExtra("mode");
//        arrival_time = intent.getStringExtra("arrival_time");
//
////        travelInfo = "origin="+origin+"&destination=" + destination+"&mode=" + mode+"&arrival_time" + arrival_time;
////        travelInfo.replaceAll(" ","+"); // url엔 띄어쓰기가 없으므로 띄어쓰기를 +로 바꾸자
//    }

//    private void getWaypointsData(String origin, String destination, String waypoints, String mode) {
//        Intent intent = new Intent();
//        origin = intent.getStringExtra("origin");
//        destination = intent.getStringExtra("destination");
//        mode = intent.getStringExtra("mode");
//        waypoints = intent.getStringExtra("waypoints");
//
//        travelInfo = "origin="+origin+"&destination=" + destination+"&mode=" + mode+"&waypoints=" + waypoints;
//        travelInfo.replaceAll(" ","+"); // url엔 띄어쓰기가 없으므로 띄어쓰기를 +로 바꾸자
//    }
}


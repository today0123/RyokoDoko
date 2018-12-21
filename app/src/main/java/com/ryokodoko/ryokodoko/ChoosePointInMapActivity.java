package com.ryokodoko.ryokodoko;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class ChoosePointInMapActivity extends AppCompatActivity {

    Toolbar toolbarCPIM;
    LinearLayout lLMapfragment;
    int iTemp;
    String strCityTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_point_in_map);


        Intent intent = getIntent();
        iTemp = intent.getIntExtra("CITY_KEY", 0);
        strCityTemp = intent.getStringExtra("CITY_NAME_KEY");


        toolbarCPIM = findViewById(R.id.toolbarCPIM);
        toolbarCPIM.setTitle(strCityTemp);
        toolbarCPIM.setTitleTextColor(getResources().getColor(R.color.headerStyle));
        setSupportActionBar(toolbarCPIM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //MapActivity를 생성하고 bundle로 값 저장
        MapActivity mapActivity = new MapActivity();
        Bundle bundle = new Bundle();
        bundle.putInt("CITY_KEY", iTemp);
        bundle.putInt("MESSAGE_KEY", 1);
        //bundle값 전달하며 맵 호출
        mapActivity.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.lLMapfragment, mapActivity, "map").commit();


        FloatingActionButton fabCPIM = (FloatingActionButton) findViewById(R.id.fabCPIM);
        fabCPIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "출발지 버튼 텍스트가 지정한 여행지명으로 변경되어야 함", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

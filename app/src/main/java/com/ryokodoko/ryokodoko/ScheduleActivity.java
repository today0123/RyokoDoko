package com.ryokodoko.ryokodoko;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScheduleActivity extends AppCompatActivity {

    Toolbar toolbarSchedule;
    LinearLayout lLDynamicDaysArea;
    int iTemp;
    String strCityTemp;
//    private String strSATemp;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Intent intent1 = getIntent();
        final int iPosition = intent1.getIntExtra("POSITION_KEY", 0);
        iTemp = intent1.getIntExtra("CITY_KEY",0);
        strCityTemp = intent1.getStringExtra("CITY_NAME_KEY");

        toolbarSchedule = (Toolbar) findViewById(R.id.toolbarSchedule);
        toolbarSchedule.setTitle(getResources().getString(R.string.day) + iPosition);
        toolbarSchedule.setTitleTextColor(getResources().getColor(R.color.headerStyle));
        setSupportActionBar(toolbarSchedule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lLDynamicDaysArea = (LinearLayout) findViewById(R.id.lLDynamicDaysArea);

        // 맨 위 설명 텍스트 뷰가 들어갈 레이아웃
        LinearLayout lLTopGuide = new LinearLayout(ScheduleActivity.this);
        LinearLayout.LayoutParams paramsLLTopGuide = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lLTopGuide.setLayoutParams(paramsLLTopGuide);
        lLTopGuide.setOrientation(LinearLayout.VERTICAL);
        lLTopGuide.setBackgroundColor(getResources().getColor(R.color.headerStyle));

        //설명 텍스트 뷰 속성
        LinearLayout.LayoutParams paramsTVGuide = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // 설명 텍스트뷰
        TextView tVPointSettingGuide = new TextView(ScheduleActivity.this);
        tVPointSettingGuide.setLayoutParams(paramsTVGuide);
        tVPointSettingGuide.setPadding(16, 8, 16, 16);
        tVPointSettingGuide.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        tVPointSettingGuide.setText(getResources().getString(R.string.if_you_selected_point));
        lLTopGuide.addView(tVPointSettingGuide);

        //맵 프래그먼트가 있는 액티비티로 값 전달
        final Intent intent2 = new Intent(getApplicationContext(), ChoosePointInMapActivity.class);
        intent2.putExtra("CITY_KEY",iTemp);
        intent2.putExtra("CITY_NAME_KEY", strCityTemp);

        // 부모 레이아웃에 작성한 레이아웃 추가
        lLDynamicDaysArea.addView(lLTopGuide);

        // 출발지, 도착지, 확인 버튼 3개가 들어갈 레이아웃
        LinearLayout lLPoint = new LinearLayout(ScheduleActivity.this);
        LinearLayout.LayoutParams paramsLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsLL.bottomMargin = 2;
        lLPoint.setLayoutParams(paramsLL);
        lLPoint.setOrientation(LinearLayout.VERTICAL);
        lLPoint.setBackgroundColor(getResources().getColor(R.color.headerStyle));
        lLPoint.setPadding(16, 16, 16, 16);

        //출발, 도착지 버튼 속성
        LinearLayout.LayoutParams paramsBtnPoint = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsBtnPoint.bottomMargin = 12;
        paramsBtnPoint.leftMargin = 16;
        paramsBtnPoint.rightMargin = 16;

        //출발지 설정 버튼
        Button btnStartingPoint = new Button(ScheduleActivity.this);
        btnStartingPoint.setText(getResources().getString(R.string.starting_point));
        btnStartingPoint.setLayoutParams(paramsBtnPoint);
        btnStartingPoint.setBackground(getResources().getDrawable(R.drawable.days_button));
        btnStartingPoint.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btnStartingPoint.setPadding(4, 0, 16, 4);
        btnStartingPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 누르면 맵 액티비티로 이동해서 그 안에서 설정 후 확인버튼 누르면
                // 출발지 라고 되어있는 버튼 텍스트를 지정한 출발지 명으로 변경해주기
                startActivity(intent2);

            }
        });
        lLPoint.addView(btnStartingPoint);

        //도착지 설정 버튼
        Button btnDestination = new Button(ScheduleActivity.this);
        btnDestination.setText(getResources().getString(R.string.destination));
        btnDestination.setLayoutParams(paramsBtnPoint);
        btnDestination.setBackground(getResources().getDrawable(R.drawable.days_button));
        btnDestination.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btnDestination.setPadding(4, 16, 16, 4);
        btnDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 누르면 맵 액티비티로 이동해서 그 안에서 설정 후 확인버튼 누르면
                // 도착지 라고 되어있는 버튼 텍스트를 지정한 도착지 명으로 변경해주기
                startActivity(intent2);
            }
        });
        lLPoint.addView(btnDestination);

        //확인 버튼 속성
        LinearLayout.LayoutParams paramsBtnInsert = new LinearLayout.LayoutParams(56, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsBtnPoint.bottomMargin = 12;
        paramsBtnPoint.leftMargin = 16;
        paramsBtnPoint.rightMargin = 16;

        //출발지 도착지 설정 후 누를 확인 버튼
        Button btnInsert = new Button(ScheduleActivity.this);
        btnInsert.setText(getResources().getString(R.string.insert));
        btnInsert.setLayoutParams(paramsBtnPoint);
        btnInsert.setBackground(getResources().getDrawable(R.drawable.insert_button));
        btnInsert.setTextColor(getResources().getColor(R.color.headerStyle));
        btnInsert.setPadding(4, 16, 16, 4);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //출발지, 도착지 설정 후 이 확인 버튼을 누르면 다이렉션즈api를 통하여 길찾기 실행
            }
        });
        lLPoint.addView(btnInsert);

        // 부모 레이아웃에 작성한 레이아웃 추가
        lLDynamicDaysArea.addView(lLPoint);
    }


}


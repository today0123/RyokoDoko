package com.ryokodoko.ryokodoko;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SelectDays extends AppCompatActivity {
    Toolbar toolbarSelectDays;
    TextView tvDays;
//    private String strSDTemp;
    int iTemp;
    String strCityTemp;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_days);

        toolbarSelectDays = (Toolbar) findViewById(R.id.toolbarSelectDays);
        setSupportActionBar(toolbarSelectDays);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int iResultDays = intent.getIntExtra("DAYS_KEY", 0);
        iTemp = intent.getIntExtra("CITY_KEY",0);
        strCityTemp = intent.getStringExtra("CITY_NAME_KEY");

        final LinearLayout lLDays = (LinearLayout) findViewById(R.id.lLBtnDays);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 12;


        for (int i = 0; i < iResultDays; i++) {
            LinearLayout lLBtnDays = new LinearLayout(this);
            lLBtnDays.setOrientation(LinearLayout.VERTICAL);

            final Button btnDays = new Button(this);
            btnDays.setId(i + 1);
            btnDays.setText(getResources().getString(R.string.day) + (i + 1));
            btnDays.setLayoutParams(params);
            btnDays.setBackground(getResources().getDrawable(R.drawable.days_button));
            btnDays.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            btnDays.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            btnDays.setPadding(32, 4, 4, 4);

            final int position = i;

            btnDays.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("log", "position : " + position);

                    Intent intent1 = new Intent(getApplicationContext(), ScheduleActivity.class);
                    intent1.putExtra("POSITION_KEY", position + 1);
//                    intent1.putExtra("city2",strSDTemp);
                    intent1.putExtra("CITY_KEY",iTemp);
                    intent1.putExtra("CITY_NAME_KEY", strCityTemp);
                    startActivity(intent1);
                }
            });

            //버튼 생성
            lLBtnDays.addView(btnDays);
            //정의된 레이아웃 추가
            lLDays.addView(lLBtnDays);
        }
    }
}

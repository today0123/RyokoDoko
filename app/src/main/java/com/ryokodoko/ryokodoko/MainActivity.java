package com.ryokodoko.ryokodoko;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    TabHost tabHostMain;
    FloatingActionButton fabAdd;
    DrawerLayout drawer;
    NavigationView navigationView;
    Button btnStartDate, btnEndDate;
    TextView tVCityResult;
    RadioButton rBHokkaido, rBKantou, rBKansai, rBKyushu;
    RadioGroup rGCity;


    private int mStartYear, mStartMonth, mStartDay,
            mEndYear, mEndMonth, mEndDay, iTemp;
    private String strStartDate, strEndDate, strCityTemp;
    private long lngCalDays = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 탭호스트 생성
        tabHostMain = (TabHost) findViewById(R.id.tabHostMain);
        tabHostMain.setup();

        tabSpecView();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnStartDate = (Button) findViewById(R.id.btnStartDate);
        btnEndDate = (Button) findViewById(R.id.btnEndDate);

        //현재 날짜와 시간을 가져오기 위한 인스턴스 선언
        Calendar cal = new GregorianCalendar();
        mStartYear = cal.get(Calendar.YEAR);
        mEndYear = cal.get(Calendar.YEAR);
        mStartMonth = cal.get(Calendar.MONTH);
        mEndMonth = cal.get(Calendar.MONTH);
        mStartDay = cal.get(Calendar.DAY_OF_MONTH);
        mEndDay = cal.get(Calendar.DAY_OF_MONTH);

        //radiobutton과 radioGroup
        rBHokkaido = (RadioButton) findViewById(R.id.rBHokkaido);
        rBKantou = (RadioButton) findViewById(R.id.rBkantou);
        rBKansai = (RadioButton) findViewById(R.id.rBKansai);
        rBKyushu = (RadioButton) findViewById(R.id.rBKyushu);

        rGCity = (RadioGroup) findViewById(R.id.rGCity);


        // 플로팅 액션버튼 (여행 일정 추가 버튼)
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateDays(view, strStartDate, strEndDate);
                fabAdd.setVisibility(View.INVISIBLE);
            }
        });
    }

    //탭호스트 동작
    void tabSpecView() {
        // 첫 번째 탭
        TabHost.TabSpec tabSpec1 = tabHostMain.newTabSpec("TravelList").setIndicator("내 여행 일정");
        // 태그를 가진 탭스펙 객체 생성.탭에 표시할 문자열 지정
        tabSpec1.setContent(R.id.lLContent1); // 탭이 눌렸을때 프레임레이아웃에 표시될 콘텐트 뷰에대한 리소스 지정
        tabHostMain.addTab(tabSpec1); // 탭호스트에 탭 추가

        // 두 번째 탭
        TabHost.TabSpec tabSpec2 = tabHostMain.newTabSpec("NewTravel").setIndicator("새 여행");
        tabSpec2.setContent(R.id.cLContent2);
        tabHostMain.addTab(tabSpec2);
    }

    //날짜 설정 버튼 클릭 시 다이얼로그뷰를 통한 데이트 피커를 사용하여 여행 가는 날, 오는 날 설정
    public void btnDateOnClick(View v) {
        switch (v.getId()) {
            //버튼이 눌리면 대화상자를 보여줌
            case R.id.btnStartDate:
                //리스너 등록
                new DatePickerDialog(MainActivity.this, mStartDateSetListener, mStartYear, mStartMonth, mStartMonth).show();
                fabAdd.setVisibility(View.VISIBLE);
                break;
            case R.id.btnEndDate:
                new DatePickerDialog(MainActivity.this, mEndDateSetListener, mEndYear, mEndMonth, mEndDay).show();
                fabAdd.setVisibility(View.VISIBLE);
                break;
        }
    }

    //두 날짜 사이의 차이를 구하는 메소드
    public void calculateDays(final View view, String strStartDate, String strEndDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //두 날짜를 데이트 형으로 변환
            Date startDate = format.parse(strStartDate);
            Date endDate = format.parse(strEndDate);

            //데이트로 형변환된 두 날짜를 계산하고 그 리턴값으로 long type 변수를 초기화
            long lngCalDate = endDate.getTime() - startDate.getTime();
            //Date.getTime()은 해당 날짜를 기준으로 1970년 00:00:00부터 몇 초가 흘렀는지 반환해줌
            lngCalDays = lngCalDate / (24 * 60 * 60 * 1000) + 1;

            if (lngCalDays < 0) {
                Snackbar.make(view, "날짜가 잘못되었습니다. 다시 선택해주세요.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            } else {
                Snackbar.make(view, "여행 기간은 총 " + (lngCalDays) + "일 입니다.", Snackbar.LENGTH_LONG)
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary)).setAction("일정 생성", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //스낵바의 일정 생성 부분을 클릭했을 시 다음 페이지로 이동하여 일 수 만큼 버튼 생성.
                        //인텐트로 다음 페이지로 이동
                        Intent intent = new Intent(getApplicationContext(), SelectDays.class);
                        intent.putExtra("DAYS_KEY", (int) lngCalDays); // 이부분 형변환을 해주던가 selectdays.java에서 형변환해서 출력해야함
//                        intent.putExtra("city",strMainTemp);
                        intent.putExtra("CITY_KEY",iTemp);
                        intent.putExtra("CITY_NAME_KEY",strCityTemp);
                        startActivity(intent);
                    }
                }).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //데이트피커다이얼로그 리스너
    DatePickerDialog.OnDateSetListener mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            //날짜 값을 가져와서
            mStartYear = year;
            mStartMonth = month + 1;
            mStartDay = dayOfMonth;
            //텍스트 업데이트
            updateStartDate();
        }
    };

    DatePickerDialog.OnDateSetListener mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            //날짜 값을 가져와서
            mEndYear = year;
            mEndMonth = month + 1;
            mEndDay = dayOfMonth;
            //텍스트 업데이트
            updateEndDate();
        }
    };

    //버튼의 텍스트를 날짜 값으로 업데이트 해주는 메소드
    void updateStartDate() {
        btnStartDate.setText(String.format("%d년 %d월 %d일", mStartYear, mStartMonth, mStartDay));
        strStartDate = String.format(mStartYear + "-" + mStartMonth + "-" + mStartDay);
    }

    void updateEndDate() {
        btnEndDate.setText(String.format("%d년 %d월 %d일", mEndYear, mEndMonth, mEndDay));
        strEndDate = String.format(mEndYear + "-" + mEndMonth + "-" + mEndDay);
    }


    @Override
    public void onBackPressed() {
        // 뒤로가기 버튼 누를 시 드로어가 사라짐
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 메뉴 인플레이트하기, 작업표시줄에 항목 추가
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 툴바 버튼의 아이템을 선택할 시
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.navSchedule) {

        } else if (id == R.id.navCheckList) {

        } else if (id == R.id.navSharing) {

        } else if (id == R.id.navBackUp) {

        } else if (id == R.id.navReview) {

        } else if (id == R.id.navSetting) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void cityIsChecked(View v) {
        iTemp = 0;
        strCityTemp = "";
        switch (v.getId()) {
            case R.id.rBHokkaido:
                if (rBHokkaido.isChecked()) {
                    strCityTemp = getResources().getString(R.string.hokkaido);
                    iTemp = 1;
                } else {
                    strCityTemp.replace(getResources().getString(R.string.hokkaido), "");
                    iTemp = 0;
                }
                break;
            case R.id.rBkantou:
                if (rBKantou.isChecked()) {
                    strCityTemp = getResources().getString(R.string.kantou);
                    iTemp = 2;
                } else {
                    strCityTemp.replace(getResources().getString(R.string.kantou), "");
                    iTemp = 0;
                }
                break;
            case R.id.rBKansai:
                if (rBKansai.isChecked()) {
                    strCityTemp = getResources().getString(R.string.kansai);
                    iTemp = 3;
                } else {
                    strCityTemp.replace(getResources().getString(R.string.kansai), "");
                    iTemp = 0;
                }
                break;
            case R.id.rBKyushu:
                if (rBKyushu.isChecked()) {
                    strCityTemp = getResources().getString(R.string.kyushu);
                    iTemp = 4;
                } else {
                    strCityTemp.replace(getResources().getString(R.string.kyushu), "");
                    iTemp = 0;
                }
                break;
        }
    }
}

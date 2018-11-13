package com.ryokodoko.ryokodoko;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.common.images.ImageRequest;

public class ChecklistActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbarChecklist;
    RelativeLayout rLChecklist;
    CoordinatorLayout cLChecklist;
    ImageView iVNothingTodo;
    TextView tVNotingTodo;
    FloatingActionButton fabChecklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        Intent intent = getIntent();

        toolbarChecklist = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarChecklist);
        rLChecklist = (RelativeLayout) findViewById(R.id.rLChecklist);
        cLChecklist = (CoordinatorLayout) findViewById(R.id.cLchecklist);
        iVNothingTodo = (ImageView) findViewById(R.id.iVNothingTodo);
        tVNotingTodo = (TextView) findViewById(R.id.tVNothingTodo);
        fabChecklist = (FloatingActionButton) findViewById(R.id.fabChecklist);

        toolbarChecklist.setTitle(getResources().getString(R.string.checklist));
        toolbarChecklist.setTitleTextColor(getResources().getColor(R.color.headerStyle));
        setSupportActionBar(toolbarChecklist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_checklist_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_delete_checklist:
                //삭제 알림창 속성 설정
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getResources().getString(R.string.notice_delete_all)).setCancelable(false)
                        .setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = builder.create();
                //알림창 객체 생성
                dialog.show();
                //알림창 띄우기

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

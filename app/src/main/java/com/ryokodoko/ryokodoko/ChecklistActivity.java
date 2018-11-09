package com.ryokodoko.ryokodoko;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
}

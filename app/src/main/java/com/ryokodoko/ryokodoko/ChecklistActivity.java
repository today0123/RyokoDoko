package com.ryokodoko.ryokodoko;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.common.images.ImageRequest;

public class ChecklistActivity extends AppCompatActivity {

    Toolbar toolbarChecklist;
    LinearLayout iLChecklist;
    CoordinatorLayout cLChecklist;
    ImageView iVNothingTodo;
    TextView tVNotingTodo;
    FloatingActionButton fabChecklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        Intent intent = getIntent();
    }
}

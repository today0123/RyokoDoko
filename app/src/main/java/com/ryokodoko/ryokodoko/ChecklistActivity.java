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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.common.images.ImageRequest;

import java.util.ArrayList;

public class ChecklistActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbarChecklist;
    RelativeLayout rLChecklist, rLChecklistNothingTodo;
    ImageView iVNothingTodo;
    TextView tVNotingTodo;
    FloatingActionButton fabChecklist;
    ChecklistDBHelper checklistDBHelper;
    ArrayAdapter<String> mAdapter;
    ListView lVChecklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        Intent intent = getIntent();
        checklistDBHelper = new ChecklistDBHelper(this);
        rLChecklistNothingTodo = (RelativeLayout) findViewById(R.id.rLChecklistNothingTodo);
        toolbarChecklist = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarChecklist);
        rLChecklist = (RelativeLayout) findViewById(R.id.rLChecklist);
        iVNothingTodo = (ImageView) findViewById(R.id.iVNothingTodo);
        tVNotingTodo = (TextView) findViewById(R.id.tVNothingTodo);
        lVChecklist = (ListView) findViewById(R.id.lVCheckList);

        loadChecklist();

        toolbarChecklist.setTitle(getResources().getString(R.string.checklist));
        toolbarChecklist.setTitleTextColor(getResources().getColor(R.color.headerStyle));
        setSupportActionBar(toolbarChecklist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadChecklist() {
        ArrayList<String> checklist = checklistDBHelper.getTaskList();
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<String>(this, R.layout.row_checklist, R.id.tVChecklistTitle, checklist);
            lVChecklist.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(checklist);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_checklist_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_checklist:
                final EditText checklistEditText = new EditText(this);
                AlertDialog dialogAddChecklist = new AlertDialog.Builder(this)
                        .setTitle(getResources().getString(R.string.add_checklist_notice))
                        .setView(checklistEditText)
                        .setPositiveButton(getResources().getString(R.string.add), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String checklist = String.valueOf(checklistEditText.getText());
                                checklistDBHelper.insertNewTask(checklist);
                                loadChecklist();
                                rLChecklistNothingTodo.setVisibility(View.GONE);
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.cancel), null)
                        .create();
                dialogAddChecklist.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteChecklist(View view) {
        View parent = (View) view.getParent();
        TextView tVChecklistTitle = (TextView) parent.findViewById(R.id.tVChecklistTitle);
        String checklist = String.valueOf(tVChecklistTitle.getText());
        checklistDBHelper.deleteTask(checklist);
        loadChecklist();
    }
}

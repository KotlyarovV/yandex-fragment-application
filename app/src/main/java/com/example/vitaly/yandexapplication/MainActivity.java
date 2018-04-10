package com.example.vitaly.yandexapplication;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ListFragment listFragment = ListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, listFragment)
                    .commit();
        }
    }

    public void setActionBar(int color, String title) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setBackgroundDrawable(new ColorDrawable(color));
        }
        else {
            ((AppCompatActivity)this).getSupportActionBar().setTitle(title);
            ((AppCompatActivity)this).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        }
    }
    /*

    public static final int REQUEST_CODE_CREATE_NOTE = 1;
    public static final int REQUEST_CODE_REDACT_NOTE = 2;

    public static final String REDACTED_NOTE = "REDACTED_NOTE";
    public static final String REDACTED_NOTE_NUMBER = "REDACTED_NOTE_NUMBER";
    public static final String ITEMS = "ITEMS";

    private ListView listView;
    private ArrayList<ListNote> items;
    private ListNoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        items = new ArrayList<>();
        adapter = new ListNoteAdapter(this, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(handleItemClick(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(handleAddButtonClick(this));
    }


    private AdapterView.OnItemClickListener handleItemClick(final Context context) {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListNote listNote = items.get(i);
                Intent intent = new Intent(context, NoteEditorActivity.class);
                intent.putExtra(REDACTED_NOTE, listNote);
                intent.putExtra(REDACTED_NOTE_NUMBER, i);
                startActivityForResult(intent, REQUEST_CODE_REDACT_NOTE);
            }
        };
    }


    private View.OnClickListener handleAddButtonClick(final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NoteEditorActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CREATE_NOTE);
            }
        };
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == REQUEST_CODE_CREATE_NOTE && resultCode == RESULT_OK) {
             ListNote listNote = (ListNote) data.getSerializableExtra(NoteEditorActivity.NODE_EDITOR_DATA);
             items.add(listNote);
             adapter.notifyDataSetChanged();
         }

         if (requestCode == REQUEST_CODE_REDACT_NOTE && resultCode == RESULT_OK) {
             ListNote listNote = (ListNote) data.getSerializableExtra(NoteEditorActivity.NODE_EDITOR_DATA);
             int i = data.getIntExtra(REDACTED_NOTE_NUMBER, 0);
             items.set(i, listNote);
             adapter.notifyDataSetChanged();
         }

        if (requestCode == REQUEST_CODE_REDACT_NOTE && resultCode == NoteEditorActivity.DELETE_RESULT) {
            int i = data.getIntExtra(REDACTED_NOTE_NUMBER, 0);
            items.remove(i);
            adapter.notifyDataSetChanged();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ITEMS, items);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            List<ListNote> items = (ArrayList<ListNote>) savedInstanceState.getSerializable(ITEMS);
            this.items.addAll(items);
            adapter.notifyDataSetChanged();
        }
    }
    */


}

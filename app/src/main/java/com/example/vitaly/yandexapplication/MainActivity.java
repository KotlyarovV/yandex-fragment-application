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
}

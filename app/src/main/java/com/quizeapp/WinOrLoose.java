package com.quizeapp;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.quizeapp.custom.CustomActivity;

/**
 * This class Show result you loose or win
 */

public class WinOrLoose extends CustomActivity {
    /**
     * The Toolbar
     */
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_or_loose);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


}

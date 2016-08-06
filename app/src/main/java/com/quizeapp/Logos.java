package com.quizeapp;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.quizeapp.custom.CustomActivity;

import java.lang.reflect.Field;

/**
 * This class show your result with details
 * and also provide option for Rematch and play another game
 */

public class Logos extends CustomActivity {
    /**
     * The toolbar.
     */
    private Toolbar toolbar;
    /**
     * The TextView
     */
    private TextView btn_rematch,btn_play_again;
    /**
     * The ImageView for sharing Dialog
     */
    private ImageView btn_fb,btn_twit,btn_pin,btn_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logos);
        setUi();
    }


    /**
     * This method initialize all ui element and set click listener on UI elements
     *
     */
    public void setUi() {

        btn_rematch=(TextView)findViewById(R.id.btn_rematch);
        btn_play_again=(TextView)findViewById(R.id.btn_play_again);
        setTouchNClick(R.id.btn_rematch);
        setTouchNClick(R.id.btn_play_again);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        openShaingDialog();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * This method popUp dialog for sharing
     * On facebook ,twitter ,pinterest and for share url
     */
    private void openShaingDialog() {

        final Dialog dialog = new Dialog(this);

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.custom_diolog);

        dialog.setCancelable(false);
        ImageView btn_close = (ImageView) dialog.findViewById(R.id.btn_close);
        btn_fb = (ImageView) dialog.findViewById(R.id.btn_fb);
        btn_twit = (ImageView) dialog.findViewById(R.id.btn_twit);
        btn_pin = (ImageView) dialog.findViewById(R.id.btn_pin);
        btn_url = (ImageView) dialog.findViewById(R.id.btn_url);

        setTouchNClick(btn_fb);
        setTouchNClick(btn_twit);
        setTouchNClick(btn_pin);
        setTouchNClick(btn_url);


        btn_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }

    @Override
    public void onClick(View v) {
        if(v==btn_rematch){
            finish();
            startActivity(new Intent(Logos.this,Question.class));
        }
        super.onClick(v);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SearchView searchView;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        EditText v =(EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        v.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.searchbar_line);



        return true;
    }
}

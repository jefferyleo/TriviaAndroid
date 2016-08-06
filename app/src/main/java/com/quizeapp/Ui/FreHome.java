package com.quizeapp.Ui;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.quizeapp.Adepter.HomeAdpeter;
import com.quizeapp.Question;
import com.quizeapp.R;
import com.quizeapp.Topices;
import com.quizeapp.custom.CustomFragment;
import com.quizeapp.models.Data;

import java.util.ArrayList;

/**
 * This class show all topices and also show Popular topics
 */
public class FreHome extends CustomFragment {
    /**
     * The ListView for item
     */
    private ListView list_home;
    /**
     * The RelativeLayouts for buttons
     */
    private RelativeLayout btn_play, btn_play_gen, btn_play_mov, btn_log, btn_general, btn_movie;
    /**
     * The RelativeLayouts for buttons
     */
    private RelativeLayout btn_raking,btn_Challang,btn_discissin,
            btn_challang_mov,btn_raking_mov,btn_discissin_mov,btn_raking_gen,btn_Challang_gen,btn_discissin_gen;
    /**
     * The LinearLayout for headers
     */
    private LinearLayout rel_general, rel_logs, rel_movie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fre_home, container, false);
        setHasOptionsMenu(true);
        initUi(v);
        return v;
    }

    /**
     * This method initialize all ui element and set click listener on UI elements
     * Load dummy data in list
     *
     */
    private void initUi(View v) {
        list_home = (ListView) v.findViewById(R.id.list_home);
        View header = getActivity().getLayoutInflater().inflate(R.layout.home_header,
                null);
        rel_general = (LinearLayout) header.findViewById(R.id.rel_general);
        btn_play = (RelativeLayout) header.findViewById(R.id.btn_play);
        btn_play_gen = (RelativeLayout) header.findViewById(R.id.btn_play_gen);
        btn_play_mov = (RelativeLayout) header.findViewById(R.id.btn_play_mov);

        btn_raking= (RelativeLayout) header.findViewById(R.id.btn_raking);
        btn_Challang= (RelativeLayout) header.findViewById(R.id.btn_Challang);
        btn_discissin= (RelativeLayout) header.findViewById(R.id.btn_discissin);
        btn_challang_mov= (RelativeLayout) header.findViewById(R.id.btn_challang_mov);
        btn_raking_mov= (RelativeLayout) header.findViewById(R.id.btn_raking_mov);
        btn_discissin_mov= (RelativeLayout) header.findViewById(R.id.btn_discissin_mov);
        btn_raking_gen= (RelativeLayout) header.findViewById(R.id.btn_raking_gen);
        btn_Challang_gen= (RelativeLayout) header.findViewById(R.id.btn_challang_gen);
        btn_discissin_gen= (RelativeLayout) header.findViewById(R.id.btn_discissin_gen);

        rel_logs = (LinearLayout) header.findViewById(R.id.rel_logs);
        rel_movie = (LinearLayout) header.findViewById(R.id.rel_movie);

        btn_log = (RelativeLayout) header.findViewById(R.id.btn_log);
        btn_general = (RelativeLayout) header.findViewById(R.id.btn_general);
        btn_movie = (RelativeLayout) header.findViewById(R.id.btn_movie);

        btn_general.setOnClickListener(this);
        btn_log.setOnClickListener(this);
        btn_movie.setOnClickListener(this);

        setTouchNClick(btn_play);
        setTouchNClick(btn_Challang);
        setTouchNClick(btn_discissin);
        setTouchNClick(btn_raking);

        setTouchNClick(btn_play_gen);
        setTouchNClick(btn_Challang_gen);
        setTouchNClick(btn_discissin_gen);
        setTouchNClick(btn_raking_gen);

        setTouchNClick(btn_play_mov);
        setTouchNClick(btn_challang_mov);
        setTouchNClick(btn_discissin_mov);
        setTouchNClick(btn_raking_mov);
        list_home.addHeaderView(header);
        list_home.setTextFilterEnabled(true);

        final ArrayList<Data> al = new ArrayList<Data>();
        al.add(new Data("Arts"));
        al.add(new Data("Arts"));
        al.add(new Data("Arts"));
        al.add(new Data("Arts"));
        al.add(new Data("Arts "));


        final HomeAdpeter adp = new HomeAdpeter(getActivity(), al);
        list_home.setAdapter(adp);

        list_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (pos > 0)
                    startActivity(new Intent(getActivity(), Topices.class));

            }
        });

    }


    private boolean resetheader(View v) {
        Log.e("call", "calll");
        int vOld = v.getVisibility();
        rel_general.setVisibility(View.GONE);
        rel_logs.setVisibility(View.GONE);
        rel_movie.setVisibility(View.GONE);
        if (vOld == View.VISIBLE) {
            v.setVisibility(View.GONE);
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Log.e("On Click call", "calll");
        if (v == btn_log) {
            if (resetheader(rel_logs))
                rel_logs.setVisibility(View.VISIBLE);
        }
        else if (v == btn_general) {
            if (resetheader(rel_general))
                rel_general.setVisibility(View.VISIBLE);
        }
       else if (v == btn_movie) {
            if (resetheader(rel_movie))
                rel_movie.setVisibility(View.VISIBLE);
        }
       else if(v==btn_play_gen||v==btn_Challang_gen||v==btn_play_mov||v==btn_challang_mov||v==btn_play||v==btn_raking){
            startActivity(new Intent(getActivity(), Question.class));
        }
        super.onClick(v);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        SearchView searchView;
        inflater.inflate(R.menu.home, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search");

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        EditText v = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        v.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.searchbar_line);
    }

}

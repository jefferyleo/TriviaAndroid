package com.quizeapp;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.quizeapp.Adepter.AdpterTopices;
import com.quizeapp.Adepter.HomeAdpeter;
import com.quizeapp.custom.CustomActivity;
import com.quizeapp.models.Data;

import java.util.ArrayList;

/**
 * This class show topics list that available for play game
 */

public class Topices extends CustomActivity {
    /**
     * The ListView for topics List
     */
    private ListView list_topic;
    /**
     * The Toolbar
     */
    private Toolbar toolbar;
    /**
     * The AdpterTopices Adapter for list_topic
     */
    AdpterTopices adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topices);
        initUi();
    }

    /**
     * This method initialize all ui element and set click listener on UI elements
     *
     */
    private void initUi(){
        list_topic=(ListView)findViewById(R.id.list_topic);
        list_topic.setTextFilterEnabled(true);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Arts");
        final ArrayList<Data> al = new ArrayList<Data>();
        al.add(new Data("Art:General"));
        al.add(new Data("Architecture"));
        al.add(new Data("Ballet"));
        al.add(new Data("Colors"));
        al.add(new Data("Fashion Design"));
        al.add(new Data("Graphic Design"));
        al.add(new Data("Impressionism"));
        al.add(new Data("Art:General"));
        al.add(new Data("Architecture"));
        al.add(new Data("Ballet"));
        al.add(new Data("Colors"));
        al.add(new Data("Fashion Design"));
        al.add(new Data("Graphic Design"));
        al.add(new Data("Impressionism"));

        adp = new AdpterTopices(Topices.this, al);
        list_topic.setAdapter(adp);
        list_topic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                for(int i=0;i<al.size();i++){
                    if(i==pos){
                        if(al.get(i).isShow()){
                            al.get(i).setShow(false);
                        }else{
                            al.get(i).setShow(true);
                        }
                    }else{
                        al.get(i).setShow(false);
                    }
                }
               adp.notifyDataSetChanged();
            }
        });

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


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adp.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

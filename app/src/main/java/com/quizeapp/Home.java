package com.quizeapp;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.quizeapp.Adepter.LeftNavAdapter;
import com.quizeapp.Ui.FreHome;
import com.quizeapp.custom.CustomActivity;
import com.quizeapp.custom.CustomFragment;
import com.quizeapp.models.Data;

import java.util.ArrayList;

/**
 * This class show all topics on home and navigation for app.It also show popular topicss
 */

public class Home extends CustomActivity {

    /**
     * The drawer layout.
     */
    private DrawerLayout drawerLayout;

    /**
     * ListView for left side drawer.
     */
    private ListView drawerLeft;

    /**
     * The drawer toggle.
     */
    private ActionBarDrawerToggle drawerToggle;

    /**
     * The m action bar auto hide enabled.
     */
    private boolean mActionBarAutoHideEnabled = true;

    /**
     * The m action bar shown.
     */
    private boolean mActionBarShown = true;

    /**
     * The m status bar color animator.
     */
    private ObjectAnimator mStatusBarColorAnimator;

    /**
     * The Constant ARGB_EVALUATOR.
     */
    @SuppressWarnings("rawtypes")
    private static final TypeEvaluator ARGB_EVALUATOR = new ArgbEvaluator();

    /**
     * The m action bar auto hide sensivity.
     */
    private int mActionBarAutoHideSensivity = 0;

    /**
     * The m action bar auto hide min y.
     */
    private int mActionBarAutoHideMinY = 0;

    /**
     * The m action bar auto hide signal.
     */
    private int mActionBarAutoHideSignal = 0;

    /**
     * The toolbar.
     */
    public Toolbar toolbar;

    /* (non-Javadoc)
     * @see com.newsfeeder.custom.CustomActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        setupDrawer();
        setupContainer(1);

    }


    /**
     * Setup the drawer layout. This method also includes the method calls for
     * setting up the Left & Right side drawers.
     */
    private void setupDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.title_activity_home, R.string.title_activity_home) {
            @Override
            public void onDrawerClosed(View view) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.closeDrawers();

        setupLeftNavDrawer();

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                onNavDrawerStateChanged(false, false);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                onNavDrawerStateChanged(true, false);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                onNavDrawerStateChanged(
                        drawerLayout.isDrawerOpen(GravityCompat.START),
                        newState != DrawerLayout.STATE_IDLE);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }
        });

    }

    /**
     * Called On navigation drawer state changed.
     *
     * @param isOpen      true if drawer is open
     * @param isAnimating true of drawer is animating
     */
    private void onNavDrawerStateChanged(boolean isOpen, boolean isAnimating) {
        if (mActionBarAutoHideEnabled && isOpen) {
            autoShowOrHideActionBar(true);
        }
    }

    /**
     * Auto show or hide action bar.
     *
     * @param show true to show the bar
     */
    private void autoShowOrHideActionBar(boolean show) {
        if (show == mActionBarShown) {
            return;
        }

        mActionBarShown = show;
        onActionBarAutoShowOrHide(show);
    }

    /**
     * Called On action bar auto show or hide.
     *
     * @param shown true is action bar is showing
     */
    @SuppressLint("NewApi")
    private void onActionBarAutoShowOrHide(final boolean shown) {
        if (mStatusBarColorAnimator != null) {
            mStatusBarColorAnimator.cancel();
        }
        mStatusBarColorAnimator = ObjectAnimator.ofInt(
                drawerLayout,
                drawerLayout != null ? "statusBarBackgroundColor"
                        : "statusBarColor",
                shown ? getResources().getColor(R.color.pink_txt)
                        : getResources().getColor(R.color.pink_txt),
                shown ? getResources().getColor(R.color.pink_txt)
                        : getResources().getColor(R.color.pink_txt))
                .setDuration(250);
        if (drawerLayout != null) {
            mStatusBarColorAnimator
                    .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(
                                ValueAnimator valueAnimator) {
                            ViewCompat.postInvalidateOnAnimation(drawerLayout);
                            if (shown) {
                                getSupportActionBar().show();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    getWindow().setStatusBarColor(
                                            getResources().getColor(
                                                    R.color.twitter));
                                }
                            } else {
                                getSupportActionBar().hide();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    getWindow().setStatusBarColor(
                                            getResources().getColor(
                                                    R.color.twitter));
                                }
                            }
                        }
                    });
        }
        mStatusBarColorAnimator.setEvaluator(ARGB_EVALUATOR);
        mStatusBarColorAnimator.start();

    }


    /**
     * Enable action bar auto hide.
     *
     * @param recList the RecyclerView list
     */
    public void enableActionBarAutoHide(final RecyclerView recList) {
        initActionBarAutoHide();
        recList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            final static int ITEMS_THRESHOLD = 3;
            int lastFvi = 0;

            @Override
            public void onScrollStateChanged(RecyclerView view, int scrollState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // autoShowOrHideActionBar(recyclerView.getScrollY()<=100);

                try {
                    LinearLayoutManager llm = (LinearLayoutManager) recyclerView
                            .getLayoutManager();
                    int firstVisibleItem = llm.findFirstVisibleItemPosition();
                    onMainContentScrolled(
                            firstVisibleItem <= ITEMS_THRESHOLD ? 0
                                    : Integer.MAX_VALUE, lastFvi
                                    - firstVisibleItem > 0 ? Integer.MIN_VALUE
                                    : lastFvi == firstVisibleItem ? 0
                                    : Integer.MAX_VALUE);
                    lastFvi = firstVisibleItem;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

			/* @Override
             public void onScroll(RecyclerView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			     onMainContentScrolled(firstVisibleItem <= ITEMS_THRESHOLD ? 0 : Integer.MAX_VALUE,
			             lastFvi - firstVisibleItem > 0 ? Integer.MIN_VALUE :
			                     lastFvi == firstVisibleItem ? 0 : Integer.MAX_VALUE
			     );
			     lastFvi = firstVisibleItem;
			 }*/
        });
    }

    /**
     * Indicates that the main content has scrolled (for the purposes of
     * showing/hiding the action bar for the "action bar auto hide" effect).
     * currentY and deltaY may be exact (if the underlying view supports it) or
     * may be approximate indications: deltaY may be INT_MAX to mean
     * "scrolled forward indeterminately" and INT_MIN to mean
     * "scrolled backward indeterminately". currentY may be 0 to mean "somewhere
     * close to the start of the list" and INT_MAX to mean "we don't know, but
     * not at the start of the list"
     *
     * @param currentY the current y
     * @param deltaY   the delta y
     */
    private void onMainContentScrolled(int currentY, int deltaY) {
        if (deltaY > mActionBarAutoHideSensivity) {
            deltaY = mActionBarAutoHideSensivity;
        } else if (deltaY < -mActionBarAutoHideSensivity) {
            deltaY = -mActionBarAutoHideSensivity;
        }

        if (Math.signum(deltaY) * Math.signum(mActionBarAutoHideSignal) < 0) {
            // deltaY is a motion opposite to the accumulated signal, so reset
            // signal
            mActionBarAutoHideSignal = deltaY;
        } else {
            // add to accumulated signal
            mActionBarAutoHideSignal += deltaY;
        }

        boolean shouldShow = currentY < mActionBarAutoHideMinY
                || mActionBarAutoHideSignal <= -mActionBarAutoHideSensivity;
        autoShowOrHideActionBar(shouldShow);
    }

    /**
     * Initializes the Action Bar auto-hide (aka Quick Recall) effect.
     */
    public void initActionBarAutoHide() {
        mActionBarAutoHideEnabled = true;
        mActionBarAutoHideMinY = getResources().getDimensionPixelSize(
                R.dimen.action_bar_auto_hide_min_y);
        mActionBarAutoHideSensivity = getResources().getDimensionPixelSize(
                R.dimen.action_bar_auto_hide_sensivity);
    }

    /**
     * Setup the left navigation drawer/slider. You can add your logic to load
     * the contents to be displayed on the left side drawer. It will also setup
     * the Header and Footer contents of left drawer. This method also apply the
     * Theme for components of Left drawer.
     */
    @SuppressLint("InflateParams")
    private void setupLeftNavDrawer() {
        drawerLeft = (ListView) findViewById(R.id.left_drawer);

        View header = getLayoutInflater().inflate(R.layout.left_nav_header,
                null);

        drawerLeft.addHeaderView(header);

        final ArrayList<Data> al = new ArrayList<Data>();
        al.add(new Data(new String[]{"Home"}, new int[]{
                R.drawable.ic_side_menu_1, R.drawable.ic_side_menu_1_selected, 0}));
        al.add(new Data(new String[]{"Topics"}, new int[]{
                R.drawable.ic_side_menu_2, R.drawable.ic_side_menu_2_selected, 0}));
        al.add(new Data(new String[]{"Friends"}, new int[]{
                R.drawable.ic_side_menu_3, R.drawable.ic_side_menu_3_selected, 0}));
        al.add(new Data(new String[]{"History"}, new int[]{
                R.drawable.ic_side_menu_4, R.drawable.ic_side_menu_4_selected, R.drawable.notification_counter}));
        al.add(new Data(new String[]{"Messages"}, new int[]{
                R.drawable.ic_side_menu_5, R.drawable.ic_side_menu_5_selected, 0}));
        al.add(new Data(new String[]{"Achievements"}, new int[]{
                R.drawable.ic_side_menu_6, R.drawable.ic_side_menu_6_selected, 0}));
        al.add(new Data(new String[]{"Settings"}, new int[]{
                R.drawable.ic_side_menu_7, R.drawable.ic_side_menu_7_selected, 0}));

        final LeftNavAdapter adp = new LeftNavAdapter(this, al);
        drawerLeft.setAdapter(adp);
        drawerLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View arg1, int pos,
                                    long arg3) {
                if (pos != 0)
                    adp.setSelection(pos - 1);
                drawerLayout.closeDrawers();
                setupContainer(pos);
            }
        });

    }


    /**
     * Setup the container fragment for drawer layout. This method will setup
     * the grid view display of main contents. You can customize this method as
     * per your need to display specific content.
     *
     * @param pos the new up container
     */
    private void setupContainer(int pos) {
        /*if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			toolbar.setBackgroundColor(getResources().getColor(R.color.main_color));
		}*/

        CustomFragment f = null;
        String title = null;

        if (pos == 0) {
            f = new FreHome();
        } else if (pos == 1)
            f = new FreHome();
//        else if (pos == 2)
//        {
//            f = new OnSale();
//            title = "On Sale";
//        }
//        else if (pos == 3)
//        {
//            f = new Checkout();
//            title = "Checkout";
//        }
//        else if (pos == 4)
//        {
//            f = new com.glam.ui.Settings();
//            title = "Settings";
//        }
//        else if (pos == 5)
//        {
//            startActivity(new Intent(this, Login.class));
//            finish();
//        }
        if (f == null)
            return;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, f).commit();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Home");
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onPostCreate(android.os.Bundle)
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggle
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /* (non-Javadoc)
     * @see com.whatshere.custom.CustomActivity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//       // this.menu = menu;
//        SearchView searchView;
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.home, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        searchView = (SearchView) searchItem.getActionView();
//        searchView.setQueryHint("Search");
//
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        EditText v =(EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//        v.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.searchbar_line);
//        return true;
//    }

}

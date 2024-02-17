package com.mujahid.computereducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bdtopcoder.smart_slider.SliderAdapter;
import com.bdtopcoder.smart_slider.SliderItem;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {


    private static final int MY_REQUEST_CODE = 100;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList();
    HashMap<String, String> hashMap;
    LottieAnimationView facebook_page_lottie;

    TextView live_notice;
    ListView list_item;
    private MeowBottomNavigation bottomNavigation;
    LinearLayout notifications, description, homepase;


    //=====================All Veriable here & OnCreate Buindle Start here=====================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Method Impliment here start-!!!!!!!!!!!!!!!!!!!!!!!!
        checkForAppUpdate();
        ///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Method Impliment here Ends-!!!!!!!!!!!!!!!!!!!!!!!!

        ///============================Communicator here Start ================================

        ///------------------- Navigations Home pages-------------
        notifications = findViewById(R.id.notifications);
        description = findViewById(R.id.description);
        homepase = findViewById(R.id.homepase);
        bottomNavigation = findViewById(R.id.bottomNavigation);



        ///============================Communicator here End ================================


        ///=============== Navigation Start here========================================

        //----------------------------Drawer Navigations Code Start-------------------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);


        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Drawar click event
        // Drawer item Click event ------
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int itemId = item.getItemId();
                if (itemId == R.id.developer) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+8801795533888"));
                    startActivity(intent);

                    Toast.makeText(MainActivity.this, "You can call now...", Toast.LENGTH_LONG).show();


                    drawerLayout.closeDrawers();

                } else if (itemId == R.id.privacy_policy) {

                    ////////////Code here------------

                    try {
                        Intent rate = new Intent(Intent.ACTION_VIEW);
                        rate.setData(Uri.parse("https://sites.google.com/view/freelearning-bd/home"));
                        startActivity(rate);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/freelearning-bd/home")));

                    }


                    drawerLayout.closeDrawers();

                } else if (itemId == R.id.more_app) {

                    Toast.makeText(MainActivity.this, "More Apps Loading here", Toast.LENGTH_LONG).show();

                    try {
                        Intent rate = new Intent(Intent.ACTION_VIEW);
                        rate.setData(Uri.parse("https://play.google.com/store/apps/developer?id=DM+Apps+Studio"));
                        startActivity(rate);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=DM+Apps+Studio")));

                    }

                    drawerLayout.closeDrawers();

                } else if (itemId == R.id.rate_app) {

                    Toast.makeText(MainActivity.this, "Please Rate my app On google play", Toast.LENGTH_LONG).show();

                    try {
                        Intent rate = new Intent(Intent.ACTION_VIEW);
                        rate.setData(Uri.parse("market://details?id=" + getPackageName()));
                        startActivity(rate);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));

                    }

                    drawerLayout.closeDrawers();

                } else if (itemId == R.id.share_app) {

                    Toast.makeText(MainActivity.this, "Please share now", Toast.LENGTH_LONG).show();
                    share(MainActivity.this);

                    drawerLayout.closeDrawers();


                } else if (itemId == R.id.facebook_page) {

                    try {
                        Intent rate = new Intent(Intent.ACTION_VIEW);
                        rate.setData(Uri.parse("https://facebook.com/developermujahid"));
                        startActivity(rate);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/developermujahid")));
                    }

                    drawerLayout.closeDrawers();

                }


                return false;
            }
        });
        //------------------------------

        // ------------------------
        // App Bar Click Event
        imageMenu = findViewById(R.id.imageMenu);

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code Here
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //----------------------------Drawer Navigations Code End-------------------------------

        ////-----------------------------------------Bottom Navigation Code Start Here-----------------------------------

        bottomNavigation.show(2, true);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_add_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_add_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_add_home_24));

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                switch (model.getId()) {
                    //////////////////////////////
                    case 1:

                        notifications.setVisibility(View.GONE);
                        homepase.setVisibility(View.GONE);
                        description.setVisibility(View.VISIBLE);

                        break;
///////////////////////////////
                    case 2:

                        notifications.setVisibility(View.GONE);
                        homepase.setVisibility(View.VISIBLE);
                        description.setVisibility(View.GONE);

                        break;
////////////////////////////////
                    case 3:

                        notifications.setVisibility(View.VISIBLE);
                        homepase.setVisibility(View.GONE);
                        description.setVisibility(View.GONE);

                        break;
                    //////////////////////////////
                }

                return null;
            }
        });
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                switch (model.getId()) {
                    case 1:

                        break;
                }
                return null;
            }
        });
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                switch (model.getId()) {
                    case 2:

                        break;
                }
                return null;
            }
        });
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                switch (model.getId()) {
                    case 3:

                        break;
                }
                return null;
            }
        });

        ////-----------------------------------------Bottom Navigation Code End Here-----------------------------------

        ///=============== Navigation Start here========================================

        ////==========================Home Slider Code start ===========================
        ViewPager2 viewPager2 = findViewById(R.id.smartSlider);

        ///String img = "https://i.dummyjson.com/data/products/1/1.jpg";
        List<SliderItem> sliderItems = new ArrayList<>();
        //  sliderItems.add(new SliderItem(R.drawable.logo,"image 1"));


        sliderItems.add(new SliderItem(R.drawable.h2,"Image from url"));
        sliderItems.add(new SliderItem(R.drawable.h2,"Image from url"));
        sliderItems.add(new SliderItem(R.drawable.h2,"Image from url"));
        sliderItems.add(new SliderItem(R.drawable.h2,"Image from url"));
        sliderItems.add(new SliderItem(R.drawable.h2,"Image from url"));


        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2,3000));
        new SliderAdapter((position, title, view) -> {
            // Toast.makeText(this, "Position: "+position+" Title: "+title, Toast.LENGTH_SHORT).show();
        });

        ////==========================Home Slider Code Ends ===========================

        //========================Earning Notice Start================================================

        live_notice = findViewById(R.id.live_notice);
        live_notice.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        live_notice.setSelected(true);


        String URLS3 = "https://mujahid33888.000webhostapp.com/FreelearningBD/PHP/Earn_Notice.php";
        StringRequest stringRequests3 = new StringRequest(Request.Method.GET, URLS3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ///Responce Code here-----------------
                live_notice.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Erorr Code here--------------
                live_notice.setText("      আপডেট নোটিশ গুলো পেতে আপনার ইন্টারনেট কানেকশন থাকতে হবে!");
            }
        }

        );

        RequestQueue requestQueues3 = Volley.newRequestQueue(MainActivity.this);
        requestQueues3.add(stringRequests3);
        //========================Earning Notice End================================================

///############################################################################################################################################################









    }
    /////=======================On Create Buindle End here======================


    ///-----------------Alert Dialoge Start----------------------------------------
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.logo);
        builder.setTitle("Exit App");
        builder.setMessage("আপনি কি অ্যাপ থেকে বের হতে চান??");
        builder.setPositiveButton("হ্যা", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }

        });

        builder.setNegativeButton("না", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    ///-----------------Alert Dialoge Ends----------------------------------------

    /// ShareApp code here Start ////////
    public void share(Context context) {
        final String appPakageName = context.getPackageName();
        Intent send = new Intent();
        send.setAction(Intent.ACTION_SEND);
        send.putExtra(Intent.EXTRA_TEXT, "Download Now: https://play.google.com/store/apps/details?id=" + appPakageName);
        send.setType("Text/Pling");
        startActivity(send);
    }

    /// ShareApp code here End ////////

    //------------InApp Update method Start------------------------------------

    private void checkForAppUpdate() {

        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);

// Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // This example applies an immediate update. To apply a flexible update
                    // instead, pass in AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                // Request the update.

                try {
                    appUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo,
                            AppUpdateType.IMMEDIATE, MainActivity.this,
                            MY_REQUEST_CODE);
                } catch (IntentSender.SendIntentException e) {
                    throw new RuntimeException(e);
                }


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                Log.w("MainActivity", "Upadate flow failed! Result code:" + resultCode);

            }
        }
    }
    //------------InApp Update method End------------------------------------
}
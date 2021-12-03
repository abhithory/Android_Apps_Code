package com.sauapps.codemylink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.sauapps.codemylink.appconfig.AppConfigActivity;
import com.sauapps.codemylink.codemylink.CodeMyLinkActivity;
import com.sauapps.codemylink.data.MyListener;
import com.sauapps.codemylink.databinding.ActivityMainBinding;
import com.sauapps.codemylink.databinding.UpdateDialogBinding;
import com.sauapps.codemylink.login.LoginActivity;
import com.sauapps.codemylink.mainfragments.GetLinkFragment;
import com.sauapps.codemylink.mainfragments.MyLinksFragment;
import com.sauapps.codemylink.profile.ProfileActivity;

import org.jetbrains.annotations.NotNull;

import static com.sauapps.codemylink.data.MyData.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding activityMainBinding;
    private Dialog progress_Dialog;
    private UpdateDialogBinding updateDialogBinding;





    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setTheme(R.style.Theme_CodeMyLink);
        setContentView(view);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        updateDialogBinding = UpdateDialogBinding.inflate(layoutInflater);


        progress_Dialog = new Dialog(MainActivity.this);
        progress_Dialog.setContentView(R.layout.progress_dialog_layout_2);
        progress_Dialog.setCancelable(false);
        progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        progress_Dialog.show();

        if (isInternetConnected()) {


            if (appUpdateList.isEmpty()) {


                loadUpdateData(MainActivity.this, new MyListener() {
                    @Override
                    public void onSuccess() {


                        if (isUpdate()) {

                            addUpdateNotice();
                        } else {

                            loadforapp();
                        }
                    }

                    @Override
                    public void onFailer() {

                    }
                });


            } else {

                if (isUpdate()) {

                    addUpdateNotice();

                } else {
                    loadforapp();
                }

            }

        } else {

            startActivity(new Intent(MainActivity.this, NoNetActivity.class));

        }


        isUserLogin();
        navigationDrawer();
        bottomAppBar();

    }


    private void addUpdateNotice() {

        Dialog update_dialog;
        update_dialog = new Dialog(MainActivity.this);
        update_dialog.setContentView(updateDialogBinding.getRoot());
        update_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        update_dialog.setCancelable(false);

        WebView update_webview = updateDialogBinding.udWebview;
        Button update_button = updateDialogBinding.udUpdateB;

        TextView your_version = updateDialogBinding.udYourVersion;
        TextView new_version = updateDialogBinding.udNewVersion;

        showUpdateDialog(update_dialog, update_webview, update_button, your_version, new_version);

        progress_Dialog.dismiss();
    }

    private void loadforapp() {


        if (forAppList.isEmpty()) {

            init(MainActivity.this, new MyListener() {
                @Override
                public void onSuccess() {

                    if (userDataList.isEmpty()) {

                        loaduserdata(MainActivity.this, new MyListener() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onFailer() {

                            }
                        });

                    }

                    progress_Dialog.dismiss();
                }

                @Override
                public void onFailer() {

                }
            });


        } else {
            progress_Dialog.dismiss();

        }


    }

    private void isUserLogin() {


        if (mAuth.getCurrentUser() != null) {
            isLogin = true;
        } else {
            isLogin = false;
        }
    }

    private void bottomAppBar() {

        activityMainBinding.maMakeCodeFromLinkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLogin) {
                    startActivity(new Intent(MainActivity.this, CodeMyLinkActivity.class));

                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));

                }

            }
        });
        activityMainBinding.homeBottomNavView.setBackground(null);
        activityMainBinding.homeBottomNavView.getMenu().getItem(1).setEnabled(false);

        setFragment(new GetLinkFragment());
        activityMainBinding.homeBottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.hbn_get_link:
                        setFragment(new GetLinkFragment());

                        return true;
                    case R.id.hbn_my_links:

                        if (isLogin) {

                            setFragment(new MyLinksFragment());
                            return true;
                        } else {

                            startActivity(new Intent(MainActivity.this, LoginActivity.class));

                            return false;

                        }

                    default:
                        setFragment(new GetLinkFragment());
                        return true;

                }

            }
        });


    }

    private void navigationDrawer() {

        activityMainBinding.homeNavigation.bringToFront();
        activityMainBinding.homeNavigation.setNavigationItemSelectedListener(this);


        activityMainBinding.homeHamburgar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (activityMainBinding.drawerLayout.isDrawerVisible(GravityCompat.END)) {
                    activityMainBinding.drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    activityMainBinding.drawerLayout.openDrawer(GravityCompat.END);

                }

            }
        });


    }


    private boolean isInternetConnected() {

        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
        }
        return connected;
    }


    private boolean isUpdate() {

        double currectAppVersion = Double.valueOf(String.valueOf(BuildConfig.VERSION_NAME));
        double newAppVersion = Double.valueOf(String.valueOf(appUpdateList.get(0).getAppVersion()));

        if (newAppVersion > currectAppVersion) {
            Log.i("cml_checking", "3");

            return true;
        } else {

            return false;

        }

    }

    private void showUpdateDialog(Dialog update_dialog, WebView update_webview, Button update_button, TextView your_version, TextView new_version) {


        double currectAppVersion = Double.valueOf(String.valueOf(BuildConfig.VERSION_NAME));
        double newAppVersion = Double.valueOf(String.valueOf(appUpdateList.get(0).getAppVersion()));


        your_version.setText(String.valueOf(currectAppVersion));
        new_version.setText(String.valueOf(newAppVersion));


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(appUpdateList.get(0).getAppLink())));
                startActivity(intent);

            }
        });

        update_webview.requestFocus();
        update_webview.getSettings().setJavaScriptEnabled(true);
        update_webview.getSettings().setGeolocationEnabled(true);
        update_webview.setSoundEffectsEnabled(true);
        update_webview.loadData(String.valueOf(appUpdateList.get(0).getUpdateText()),
                "text/html", "UTF-8");
        update_webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {

                }
                if (progress == 100) {

                }
            }
        });


        update_dialog.show();

        progress_Dialog.dismiss();


    }


    private void startAppConfig(String config) {

        Intent intent = new Intent(MainActivity.this, AppConfigActivity.class);
        intent.putExtra("config", config);
        startActivity(intent);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(activityMainBinding.maFrameLayout.getId(), fragment);
        transaction.commit();
    }

    private void rateusOnPlaystore() {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(appUpdateList.get(0).getAppLink())));
        startActivity(browserIntent);


    }

    @Override
    public void onBackPressed() {

        if (activityMainBinding.drawerLayout.isDrawerVisible(GravityCompat.END)) {
            activityMainBinding.drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
        switch (item.getItemId()) {


            case R.id.hn_user_profile:
                if (isLogin) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                return false;

            case R.id.hn_join_telegram:
                startAppConfig("jointelegram");
                return false;

            case R.id.hn_rate_on_playstore:

                rateusOnPlaystore();
                return false;

            case R.id.hn_share:
                startAppConfig("share");

                return false;

            case R.id.hn_aboutus:
                startAppConfig("aboutus");
                return false;
            case R.id.hn_tac:

                startAppConfig("tac");

                return false;
            case R.id.hn_pp:
                startAppConfig("pp");
                return false;
            case R.id.hn_contactus:
                startAppConfig("contact");
                return false;


        }
        return true;

    }


}
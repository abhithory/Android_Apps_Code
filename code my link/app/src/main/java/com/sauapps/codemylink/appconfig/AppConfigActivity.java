package com.sauapps.codemylink.appconfig;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.sauapps.codemylink.R;
import com.sauapps.codemylink.data.MyListener;
import com.sauapps.codemylink.databinding.ActivityAppConfigBinding;

import static com.sauapps.codemylink.data.MyData.appconfigList;
import static com.sauapps.codemylink.data.MyData.loadAppConfig;

public class AppConfigActivity extends AppCompatActivity {

    private ActivityAppConfigBinding activityAppConfigBinding;

    private WebView config_web_view;

    private String config_string;

    private Dialog progress_Dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAppConfigBinding = ActivityAppConfigBinding.inflate(getLayoutInflater());

        View view = activityAppConfigBinding.getRoot();
        setContentView(view);

        progress_Dialog = new Dialog(AppConfigActivity.this);
        progress_Dialog.setContentView(R.layout.progress_dialog_layout_2);
        progress_Dialog.setCancelable(false);
        progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        config_web_view = activityAppConfigBinding.appConfigWebview;


        progress_Dialog.show();

        if (appconfigList.isEmpty()){

            loadAppConfig(AppConfigActivity.this, new MyListener() {
                @Override
                public void onSuccess() {

                    init();
                }

                @Override
                public void onFailer() {

                }
            });
        } else {

            init();
        }






    }

    private void init() {

        String config = getIntent().getStringExtra("config");


        if (config.equals("aboutus")){

            config_string = appconfigList.get(0).getAbout_us();

            show_config();

        } else if(config.equals("tac")){

            config_string = appconfigList.get(0).getTerm_and_condition();
            show_config();


        } else if (config.equals("pp")){

            config_string = appconfigList.get(0).getPrivacy_policy();

            show_config();

        } else if (config.equals("share")){

            config_string = appconfigList.get(0).getShare_text();
            shareapp();


        } else if (config.equals("jointelegram")){

            config_string = appconfigList.get(0).getTelegram_channel();
            joinTelegramchannel();

        } else if (config.equals("contact")){

            config_string = appconfigList.get(0).getContact_us();
            show_config();

        }

    }

    private void joinTelegramchannel() {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(config_string));
        startActivity(browserIntent);

        progress_Dialog.dismiss();
    }

    private void show_config() {

        config_web_view.requestFocus();
        config_web_view.getSettings().setJavaScriptEnabled(true);
        config_web_view.getSettings().setGeolocationEnabled(true);
        config_web_view.setSoundEffectsEnabled(true);
        config_web_view.loadData(config_string,
                "text/html", "UTF-8");
        config_web_view.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {

                }
                if (progress == 100) {

                    progress_Dialog.dismiss();
                }
            }
        });
    }

    private void shareapp() {

        progress_Dialog.dismiss();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,config_string);

        startActivity(intent.createChooser(intent,"Share"));

    }

}
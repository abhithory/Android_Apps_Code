package com.sauapps.codemylink.generated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.snackbar.Snackbar;
import com.sauapps.codemylink.R;
import com.sauapps.codemylink.data.MyListener;
import com.sauapps.codemylink.databinding.ActivityGetLinkBinding;

import static com.sauapps.codemylink.data.MyData.generatedLinkList;
import static com.sauapps.codemylink.data.MyData.loadLinkByCode;

public class LinkGeneratedActivity extends AppCompatActivity {


    private ActivityGetLinkBinding activityLinkGeneratedBinding;

    private TextView your_link_box, link_title, link_des;

    private RewardedAd mRewardedAd;
    private final String TAG = "rewardad_glf";
    private AdView mBanner_ad_1_view, mBanner_ad_2_view,mBanner_ad_3_view;

    private Dialog searching_progress_Dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityLinkGeneratedBinding = ActivityGetLinkBinding.inflate(getLayoutInflater());
        View view = activityLinkGeneratedBinding.getRoot();
        setContentView(view);

        searching_progress_Dialog = new Dialog(LinkGeneratedActivity.this);
        searching_progress_Dialog.setContentView(R.layout.progress_searching_dialog_layout);
        searching_progress_Dialog.setCancelable(false);
        searching_progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        searching_progress_Dialog.show();

        your_link_box = activityLinkGeneratedBinding.glaYourLinkBox;
        link_title = activityLinkGeneratedBinding.glaTitleBox;
        link_des = activityLinkGeneratedBinding.glaDesBox;


        loadRewardAdForgetlink();


        loadBannerads1();

        loadBannerads2();

        loadBannerads3();

    }

    private void loadBannerads1() {


        mBanner_ad_1_view = activityLinkGeneratedBinding.glaBannerAd1AdView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mBanner_ad_1_view.loadAd(adRequest);

        mBanner_ad_1_view.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i("gla_banner_1","1loaded");
                super.onAdLoaded();

            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {

                super.onAdFailedToLoad(adError);

                Log.i("gla_banner_1","1error "+adError);
                Log.i("gla_banner_1","1error "+adError.getMessage());

                mBanner_ad_1_view.loadAd(adRequest);

            }

            @Override
            public void onAdOpened() {

                super.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                super.onAdClicked();
            }
        });

    }

    private void loadBannerads2() {


        mBanner_ad_2_view = activityLinkGeneratedBinding.glaBannerAd2AdView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mBanner_ad_2_view.loadAd(adRequest);

        mBanner_ad_2_view.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i("gla_banner_2","2loaded");

                super.onAdLoaded();

            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                super.onAdFailedToLoad(adError);

                Log.i("gla_banner_2","2error "+adError);
                Log.i("gla_banner_2","2error "+adError.getMessage());

                mBanner_ad_2_view.loadAd(adRequest);

            }

            @Override
            public void onAdOpened() {

                super.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                super.onAdClicked();
            }
        });

    }

    private void loadBannerads3() {


        mBanner_ad_3_view = activityLinkGeneratedBinding.glaBannerAd3AdView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mBanner_ad_3_view.loadAd(adRequest);

        mBanner_ad_3_view.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i("gla_banner_3","3loaded");

                super.onAdLoaded();

            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                super.onAdFailedToLoad(adError);
                Log.i("gla_banner_3","3error "+adError);
                Log.i("gla_banner_3","3error "+adError.getMessage());
                mBanner_ad_3_view.loadAd(adRequest);

            }

            @Override
            public void onAdOpened() {

                super.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                super.onAdClicked();
            }
        });

    }

    private void loadLinksByCode() {
        String code = getIntent().getStringExtra("code");


        loadLinkByCode(LinkGeneratedActivity.this, code, new MyListener() {
            @Override
            public void onSuccess() {

                setGeneratedLink();
            }

            @Override
            public void onFailer() {

            }
        });

    }

    private void loadRewardAdForgetlink() {
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(LinkGeneratedActivity.this, "ca-app-pub-2058781353307220/6262248026",
                adRequest, new RewardedAdLoadCallback() {

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mRewardedAd = null;

                        Log.i("gla_reward","error "+loadAdError);
                        Log.i("gla_reward","error "+loadAdError.getMessage());

                        showlinkafterad();

                    }


                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.i("gla_reward","loaded");

                        showlinkafterad();

                        Toast.makeText(LinkGeneratedActivity.this, "", Toast.LENGTH_SHORT).show();

                        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d(TAG, "Ad was shown.");
                                Toast.makeText(LinkGeneratedActivity.this, "Watch Complete ad for get your link. If you close the ad before completion you will not get link.", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.d(TAG, "Ad failed to show.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                Log.d(TAG, "Ad was dismissed.");

                                Toast.makeText(LinkGeneratedActivity.this, "Watch Complete ad for get your link. If you close the ad before completion you will not get link.", Toast.LENGTH_LONG).show();

                                mRewardedAd = null;
                            }
                        });

                    }

                });
    }

    private void showlinkafterad() {

        if (mRewardedAd != null) {
            mRewardedAd.show(LinkGeneratedActivity.this, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    Log.d(TAG, "The user earned the reward.");

                    Toast.makeText(LinkGeneratedActivity.this, "Watch Complete ad for get your link. If you close the ad before completion you will not get link.", Toast.LENGTH_LONG).show();

                    loadLinksByCode();
                }

            });
        } else {
            loadLinksByCode();
        }
    }

    private void setGeneratedLink(){

        String link_status = generatedLinkList.get(0).getLink_status();

        if (link_status.equals("0")) {

            // link is available but the our of this code disabled it.....

            activityLinkGeneratedBinding.glaHeadingText.setText("Link is hided by Admin");
            your_link_box.setText("The Admin of this link has been hided this link. So, contact to Owner of this link Or try again!");


            hideOtherElements();



        }
        else if (link_status.equals("-1")) {
            // wrong code... there is no link for this code

            activityLinkGeneratedBinding.glaHeadingText.setText("Wrong Code..");

            your_link_box.setText("You Entered Wrong Code. Please Check your Code and try again.");

            hideOtherElements();


        } else if (link_status.equals("-2")) {
            // wrong code... there is no link for this code

            activityLinkGeneratedBinding.glaHeadingText.setText("Something Went Wrong");

            your_link_box.setText("Please Check your internet and try again.");

            hideOtherElements();
        }
        else {
            your_link_box.setText(generatedLinkList.get(0).getLink().toString());
            link_title.setText(generatedLinkList.get(0).getLink_title().toString());
            link_des.setText(generatedLinkList.get(0).getLink_des().toString());

            searching_progress_Dialog.dismiss();
        }


        activityLinkGeneratedBinding.glaCopyLinkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String your_url = your_link_box.getText().toString();
                ClipData clipData = ClipData.newPlainText("text", your_url);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(LinkGeneratedActivity.this, "Url Copied...", Toast.LENGTH_SHORT).show();


            }
        });
        activityLinkGeneratedBinding.glaBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void hideOtherElements() {

        activityLinkGeneratedBinding.textView4.setVisibility(View.GONE);
        activityLinkGeneratedBinding.textView5.setVisibility(View.GONE);
        link_title.setVisibility(View.GONE);
        link_des.setVisibility(View.GONE);
        searching_progress_Dialog.dismiss();
    }
}
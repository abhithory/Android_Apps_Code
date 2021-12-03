package com.sauapps.codemylink.mainfragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.sauapps.codemylink.MainActivity;
import com.sauapps.codemylink.generated.LinkGeneratedActivity;
import com.sauapps.codemylink.R;
import com.sauapps.codemylink.databinding.FragmentGetLinkBinding;

public class GetLinkFragment extends Fragment {


    private FragmentGetLinkBinding fragmentGetLinkBinding;
    private Dialog searching_progress_Dialog;
    private EditText code_edit_text_box;




    private AdView mBanner_ad_1_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentGetLinkBinding = FragmentGetLinkBinding.inflate(inflater, container, false);
        View rootview = fragmentGetLinkBinding.getRoot();

        code_edit_text_box = fragmentGetLinkBinding.glfUrlEditTextBox;

        searching_progress_Dialog = new Dialog(getActivity());
        searching_progress_Dialog.setContentView(R.layout.progress_searching_dialog_layout);
        searching_progress_Dialog.setCancelable(false);
        searching_progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        bannerad_1();


        fragmentGetLinkBinding.glfPasteCodeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasteCode();
            }
        });

        fragmentGetLinkBinding.glfGenerateLinkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ifValidate()) {

                    generateLink();

                }


            }
        });

        return rootview;

    }

    private void bannerad_1() {


        mBanner_ad_1_view = fragmentGetLinkBinding.glfBannerAd1AdView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mBanner_ad_1_view.loadAd(adRequest);

        mBanner_ad_1_view.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

                Log.i("glf_banner_1","adloaded");

            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {

                super.onAdFailedToLoad(adError);
                Log.i("glf_banner_1","aderror: "+adError );

                Log.i("glf_banner_1","aderror: "+adError.getMessage() );


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




    private boolean ifValidate() {


        if (code_edit_text_box.getText().toString().isEmpty()) {
            code_edit_text_box.setError("Please Enter Any Code");
            return false;
        }


        return true;

    }

    private void generateLink() {
        Intent intent = new Intent(getActivity(), LinkGeneratedActivity.class);

        String code = code_edit_text_box.getText().toString();
        intent.putExtra("code",code);
        startActivity(intent);

    }

    private void pasteCode() {

        try {

            ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = clipboardManager.getPrimaryClip();

            ClipData.Item item = clipData.getItemAt(0);

            String code_for_paste = item.getText().toString();

            code_edit_text_box.setText(code_for_paste);

            Toast.makeText(getActivity(), "Code Pasted...", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

            Toast.makeText(getActivity(), "Please copy any Code first...", Toast.LENGTH_SHORT).show();


        }


    }
}
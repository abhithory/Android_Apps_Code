package com.sauapps.codemylink.mylinks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sauapps.codemylink.R;

import com.sauapps.codemylink.data.MyListener;
import com.sauapps.codemylink.databinding.ActivityMyAllLinksBinding;

import static com.sauapps.codemylink.data.MyData.forAppList;
import static com.sauapps.codemylink.data.MyData.loadUserMyLinksall;
import static com.sauapps.codemylink.data.MyData.myLinksPaginationList;
import static com.sauapps.codemylink.data.MyData.userMyLinksListall;

public class MyAllLinks extends AppCompatActivity {

    private ActivityMyAllLinksBinding activityMyAllLinksBinding;
    private Dialog progress_Dialog;
    private RecyclerView myLinks_reView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMyAllLinksBinding = ActivityMyAllLinksBinding.inflate(getLayoutInflater());
        View view = activityMyAllLinksBinding.getRoot();
        setContentView(view);

        progress_Dialog = new Dialog(MyAllLinks.this);
        progress_Dialog.setContentView(R.layout.progress_dialog_layout_2);
        progress_Dialog.setCancelable(false);
        progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        progress_Dialog.show();

        loadUserMyLinksall(forAppList.get(0).getUrl() + forAppList.get(0).getGeturl(), MyAllLinks.this, new MyListener() {
            @Override
            public void onSuccess() {

                setalldata();

            }

            @Override
            public void onFailer() {

            }
        });

        activityMyAllLinksBinding.malaBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void setalldata() {

        Log.i("checking_cml","6");


        myLinks_reView = activityMyAllLinksBinding.malaMylinksReView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyAllLinks.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myLinks_reView.setLayoutManager(layoutManager);

        MyLinksAdapter adapter = new MyLinksAdapter(MyAllLinks.this, userMyLinksListall);
        myLinks_reView.setAdapter(adapter);

        progress_Dialog.dismiss();

        setpaginationButtons();

    }

    private void setpaginationButtons() {

        activityMyAllLinksBinding.malaNextPageB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myLinksPaginationList.get(0).getNextPageUrl().equals(null) || myLinksPaginationList.get(0).getCurrectpageCount().equals(myLinksPaginationList.get(0).getTotalPageCount())) {

                    Toast.makeText(MyAllLinks.this, "This is last page", Toast.LENGTH_SHORT).show();
                } else {
                    progress_Dialog.show();

                    loadmorelinks(myLinksPaginationList.get(0).getNextPageUrl());
                }

            }
        });

        activityMyAllLinksBinding.malaPreviousPageB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (myLinksPaginationList.get(0).getCurrectpageCount().toString().equals("1") || myLinksPaginationList.get(0).getPreviousPageUrl().equals(null)) {

                    Toast.makeText(MyAllLinks.this, "This is first page", Toast.LENGTH_SHORT).show();


                } else {
                    progress_Dialog.show();
                    loadmorelinks(myLinksPaginationList.get(0).getPreviousPageUrl());

                }

            }
        });

    }

    private void loadmorelinks(String PageUrl) {

        Log.i("checking_cml","3"+PageUrl);


        loadUserMyLinksall(PageUrl, MyAllLinks.this, new MyListener() {
            @Override
            public void onSuccess() {
                Log.i("checking_cml","4");


                setalldata();

            }

            @Override
            public void onFailer() {

                Log.i("checking_cml","5");


            }
        });

    }
}
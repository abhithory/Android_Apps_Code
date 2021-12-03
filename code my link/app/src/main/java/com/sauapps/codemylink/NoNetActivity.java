package com.sauapps.codemylink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sauapps.codemylink.databinding.ActivityNoNetBinding;

public class NoNetActivity extends AppCompatActivity {

    private ActivityNoNetBinding activityNoNetBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNoNetBinding = ActivityNoNetBinding.inflate(getLayoutInflater());
        View view = activityNoNetBinding.getRoot();
        setContentView(view);

        activityNoNetBinding.nnaTryAgainB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(NoNetActivity.this,MainActivity.class));
                finish();

            }
        });

    }
}
package com.sauapps.codemylink.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.sauapps.codemylink.MainActivity;
import com.sauapps.codemylink.R;
import com.sauapps.codemylink.data.MyData;
import com.sauapps.codemylink.data.MyListener;
import com.sauapps.codemylink.databinding.ActivityProfileBinding;

import static com.sauapps.codemylink.data.MyData.isLogin;
import static com.sauapps.codemylink.data.MyData.loaduserdata;
import static com.sauapps.codemylink.data.MyData.userDataList;

public class ProfileActivity extends AppCompatActivity {



    private ActivityProfileBinding activityProfileBinding;
    private Dialog progress_Dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = activityProfileBinding.getRoot();
        setContentView(view);

        progress_Dialog = new Dialog(ProfileActivity.this);
        progress_Dialog.setContentView(R.layout.progress_dialog_layout_3);
        progress_Dialog.setCancelable(false);
        progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        progress_Dialog.show();

        if (userDataList.isEmpty()){
            Log.i("cml_checking","11");


            loaduserdata(ProfileActivity.this, new MyListener() {
                @Override
                public void onSuccess() {
                    Log.i("cml_checking","12");

                    setdata();

                }

                @Override
                public void onFailer() {
                    Log.i("cml_checking","13");


                }
            });

        } else {
            setdata();
        }

        activityProfileBinding.profileChangeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ProfileActivity.this,ChangeUserDetailsActivity.class));


            }
        });
        activityProfileBinding.profileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                isLogin = false;
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                finish();

            }
        });

    }

    private void setdata() {

        activityProfileBinding.userNameProfile.setText(userDataList.get(0).getUserName());
        activityProfileBinding.userImageProfile.setText(String.valueOf(userDataList.get(0).getUserName().charAt(0)).toUpperCase());
        activityProfileBinding.profileFullName.setText(userDataList.get(0).getUserName());
        activityProfileBinding.profileEmail.setText(userDataList.get(0).getUserEmail());
        activityProfileBinding.profilePhone.setText(userDataList.get(0).getUserPhoneNo());


        progress_Dialog.dismiss();
    }
}
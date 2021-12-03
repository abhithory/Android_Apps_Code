package com.sauapps.codemylink.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sauapps.codemylink.R;
import com.sauapps.codemylink.data.MyData;
import com.sauapps.codemylink.data.MyListener;
import com.sauapps.codemylink.databinding.ActivityChangeUserDetailsBinding;

import static com.sauapps.codemylink.data.MyData.loaduserdata;
import static com.sauapps.codemylink.data.MyData.updateuserdata;
import static com.sauapps.codemylink.data.MyData.userDataList;

public class ChangeUserDetailsActivity extends AppCompatActivity {


    private ActivityChangeUserDetailsBinding activityChangeUserDetailsBinding;
    private Dialog progress_Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChangeUserDetailsBinding = ActivityChangeUserDetailsBinding.inflate(getLayoutInflater());
        View view = activityChangeUserDetailsBinding.getRoot();
        setContentView(view);

        progress_Dialog = new Dialog(ChangeUserDetailsActivity.this);
        progress_Dialog.setContentView(R.layout.progress_dialog_layout_3);
        progress_Dialog.setCancelable(false);
        progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        activityChangeUserDetailsBinding.changeFullName.setText(userDataList.get(0).getUserName());
        activityChangeUserDetailsBinding.changePhone.setText(userDataList.get(0).getUserPhoneNo());

        activityChangeUserDetailsBinding.changeDetailsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                    progress_Dialog.show();

                    updateuserdata(activityChangeUserDetailsBinding.changePhone.getText().toString(),
                            activityChangeUserDetailsBinding.changeFullName.getText().toString(), ChangeUserDetailsActivity.this, new MyListener() {
                                @Override
                                public void onSuccess() {
                                    updatedata();

                                }

                                @Override
                                public void onFailer() {

                                    Toast.makeText(ChangeUserDetailsActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                                    progress_Dialog.dismiss();

                                }
                            });



                }

            }
        });

    }

    private void updatedata() {

        loaduserdata(ChangeUserDetailsActivity.this, new MyListener() {
            @Override
            public void onSuccess() {

                Toast.makeText(ChangeUserDetailsActivity.this, "User data updated", Toast.LENGTH_SHORT).show();
                onBackPressed();

                progress_Dialog.dismiss();


            }

            @Override
            public void onFailer() {

            }
        });
    }

    private boolean validate() {


        if (activityChangeUserDetailsBinding.changeFullName.getText().toString().isEmpty()) {
            activityChangeUserDetailsBinding.changeFullName.setError("Please Enter Name");
            return false;
        }

        if (activityChangeUserDetailsBinding.changePhone.getText().toString().isEmpty()) {
            activityChangeUserDetailsBinding.changePhone.setError("Please Enter Phone No");
            return false;
        }


        return true;

    }

}
package com.sauapps.codemylink.mylinks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sauapps.codemylink.MainActivity;
import com.sauapps.codemylink.R;
import com.sauapps.codemylink.data.MyData;
import com.sauapps.codemylink.data.MyListener;
import com.sauapps.codemylink.databinding.ActivityEditMyLinkBinding;

import static com.sauapps.codemylink.data.MyData.againLoadMylinks;
import static com.sauapps.codemylink.data.MyData.updatemylink;

public class EditMyLinkActivity extends AppCompatActivity {

    private ActivityEditMyLinkBinding activityEditMyLinkBinding;
    private Dialog progress_Dialog;

    private EditText link_title_box, link_des_box, link_box;
    private RadioButton active_status, deactive_status;
    private TextView code_box;
    private String final_link_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditMyLinkBinding = ActivityEditMyLinkBinding.inflate(getLayoutInflater());
        View view = activityEditMyLinkBinding.getRoot();
        setContentView(view);

        progress_Dialog = new Dialog(EditMyLinkActivity.this);
        progress_Dialog.setContentView(R.layout.progress_dialog_layout);
        progress_Dialog.setCancelable(false);
        progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        code_box = activityEditMyLinkBinding.emlaCode;
        link_box = activityEditMyLinkBinding.emlaLinkBox;
        link_title_box = activityEditMyLinkBinding.emlaTitleBox;
        link_des_box = activityEditMyLinkBinding.emlaDesBox;
        active_status = activityEditMyLinkBinding.radioButtonOn;
        deactive_status = activityEditMyLinkBinding.radioButtonOff;

        progress_Dialog.show();


        setData();


        activityEditMyLinkBinding.emlaCopyCodeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String your_url = code_box.getText().toString();
                ClipData clipData = ClipData.newPlainText("text", your_url);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(view.getContext(), "Code Copied...", Toast.LENGTH_SHORT).show();

            }
        });

        activityEditMyLinkBinding.cmlaUpdateCodeDetailsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress_Dialog.show();

                if (isvalidate()){
                updateData();
                }

            }
        });


    }

    private boolean isvalidate() {
        if (link_box.getText().toString().isEmpty()) {
            link_box.setError("Please Enter value");
            return false;
        }
        if (link_title_box.getText().toString().isEmpty()) {
            link_title_box.setError("Please Enter value");
            return false;
        }
        if (link_des_box.getText().toString().isEmpty()) {
            link_des_box.setError("Please Enter value");
            return false;
        }

        if (active_status.isChecked()){

            final_link_status ="1";

        } else if (deactive_status.isChecked()){

            final_link_status ="0";
        } else {
            active_status.setError("Please Enter value");
            return false;
        }


        return true;
    }

    private void setData() {
        String code = getIntent().getStringExtra("code");
        String link = getIntent().getStringExtra("link");
        String title = getIntent().getStringExtra("title");
        String des = getIntent().getStringExtra("des");
        String status = getIntent().getStringExtra("status");

        code_box.setText(code);
        link_box.setText(link);
        link_title_box.setText(title);
        link_des_box.setText(des);

        if (status.equals("1")) {
            active_status.setChecked(true);

        } else {
            deactive_status.setChecked(true);
        }

        progress_Dialog.dismiss();


    }

    private void updateData() {

        updatemylink(link_title_box.getText().toString(), link_des_box.getText().toString(),
                link_box.getText().toString(), final_link_status, code_box.getText().toString(),
                EditMyLinkActivity.this, new MyListener() {
                    @Override
                    public void onSuccess() {


                        againLoadMylinks =true;
                        progress_Dialog.dismiss();
                        Toast.makeText(EditMyLinkActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(EditMyLinkActivity.this, MainActivity.class));
                        finish();

                    }

                    @Override
                    public void onFailer() {

                        progress_Dialog.dismiss();
                        Toast.makeText(EditMyLinkActivity.this, "Please try again...", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
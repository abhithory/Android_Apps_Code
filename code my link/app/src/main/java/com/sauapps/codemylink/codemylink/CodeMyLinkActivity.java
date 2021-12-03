package com.sauapps.codemylink.codemylink;

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
import android.widget.Toast;

import com.sauapps.codemylink.generated.CodeGeneratedActivity;
import com.sauapps.codemylink.R;
import com.sauapps.codemylink.data.MyData;
import com.sauapps.codemylink.data.MyListener;
import com.sauapps.codemylink.databinding.ActivityCodeMyLinkBinding;

import static com.sauapps.codemylink.data.MyData.generateCodeForLink;

public class CodeMyLinkActivity extends AppCompatActivity {

    private ActivityCodeMyLinkBinding activityCodeMyLinkBinding;

    private EditText link_box,title_box,des_box;
    private Dialog progress_Dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCodeMyLinkBinding = ActivityCodeMyLinkBinding.inflate(getLayoutInflater());
        View view = activityCodeMyLinkBinding.getRoot();
        setContentView(view);

        progress_Dialog = new Dialog(CodeMyLinkActivity.this);
        progress_Dialog.setContentView(R.layout.progress_dialog_layout);
        progress_Dialog.setCancelable(false);
        progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        link_box=activityCodeMyLinkBinding.cmlaLinkBox;
        title_box=activityCodeMyLinkBinding.cmlaTitleBox;
        des_box=activityCodeMyLinkBinding.cmlaDesBox;


        activityCodeMyLinkBinding.cmlaPasteUrlB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = clipboardManager.getPrimaryClip();

                    ClipData.Item item = clipData.getItemAt(0);

                    String url_for_paste = item.getText().toString();

                    link_box.setText(url_for_paste);

                    Toast.makeText(CodeMyLinkActivity.this, "Link Pasted...", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                    Toast.makeText(CodeMyLinkActivity.this, "Please copy any Link first...", Toast.LENGTH_SHORT).show();

                }


            }
        });

        activityCodeMyLinkBinding.cmlaGenerateCodeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateData()){
                    progress_Dialog.show();
                    generatecode();

                }

            }
        });

        activityCodeMyLinkBinding.cmlaBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void generatecode() {

        generateCodeForLink(link_box.getText().toString(), title_box.getText().toString(),
                des_box.getText().toString(), CodeMyLinkActivity.this, new MyListener() {
            @Override
            public void onSuccess() {

                startActivity(new Intent(CodeMyLinkActivity.this, CodeGeneratedActivity.class));
                finish();
                progress_Dialog.dismiss();

            }

            @Override
            public void onFailer() {

                progress_Dialog.dismiss();


            }
        });


    }

    private boolean validateData() {
        if (link_box.getText().toString().isEmpty()) {
            link_box.setError("Please Enter Any Link");
            return false;
        }
        if (title_box.getText().toString().isEmpty()) {
            title_box.setError("Please Enter Any Title");
            return false;
        }
        if (des_box.getText().toString().isEmpty()) {
            des_box.setError("Please Enter Description");
            return false;
        }

        return true;
    }
}
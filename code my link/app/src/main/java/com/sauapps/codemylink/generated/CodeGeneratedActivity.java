package com.sauapps.codemylink.generated;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sauapps.codemylink.MainActivity;
import com.sauapps.codemylink.R;
import com.sauapps.codemylink.databinding.ActivityCodeGeneratedBinding;

import static com.sauapps.codemylink.data.MyData.*;

public class CodeGeneratedActivity extends AppCompatActivity {

    private ActivityCodeGeneratedBinding activityCodeGeneratedBinding;
    private Dialog progress_Dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCodeGeneratedBinding=ActivityCodeGeneratedBinding.inflate(getLayoutInflater());
        View view = activityCodeGeneratedBinding.getRoot();
        setContentView(view);

        progress_Dialog = new Dialog(CodeGeneratedActivity.this);
        progress_Dialog.setContentView(R.layout.progress_dialog_layout);
        progress_Dialog.setCancelable(false);
        progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progress_Dialog.show();

        againLoadMylinks =true;

        if (generatedCodeList.get(0).getCode_generated_status().equals("1")){
            ///code generated successfully
            activityCodeGeneratedBinding.cgaCodeBox.setText(generatedCodeList.get(0).getCode().toString());
            activityCodeGeneratedBinding.cgaLinkBox.setText(generatedCodeList.get(0).getLink().toString());

            progress_Dialog.dismiss();

        }
        else {
            /// code not generated successfully
            activityCodeGeneratedBinding.cgaCodeBox.setText("Code not generated... Please try again...");
            activityCodeGeneratedBinding.cgaLinkBox.setVisibility(View.GONE);
            activityCodeGeneratedBinding.cgaSuccessAnimation.setVisibility(View.GONE);

            progress_Dialog.dismiss();

        }

        activityCodeGeneratedBinding.cgaCodeCopyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String your_url = activityCodeGeneratedBinding.cgaCodeBox.getText().toString();
                ClipData clipData = ClipData.newPlainText("text", your_url);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(CodeGeneratedActivity.this, "Code Copied...", Toast.LENGTH_SHORT).show();

            }
        });

        activityCodeGeneratedBinding.cdaGoToHomeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(CodeGeneratedActivity.this, MainActivity.class));
                finish();

            }
        });

        activityCodeGeneratedBinding.cgaShareCodeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// share code

                String share_text = "Use This Code in 'Code My Link' App and get your link:  "
                        + activityCodeGeneratedBinding.cgaCodeBox.getText().toString()+"       " +
                        "                                      App link:"+ String.valueOf(appUpdateList.get(0).getAppLink());
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,share_text);

                view.getContext().startActivity(intent.createChooser(intent,"Share"));
            }
        });

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(CodeGeneratedActivity.this, MainActivity.class));
        finish();

    }
}
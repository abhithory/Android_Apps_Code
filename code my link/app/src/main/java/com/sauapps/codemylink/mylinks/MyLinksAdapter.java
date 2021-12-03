package com.sauapps.codemylink.mylinks;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sauapps.codemylink.R;
import com.sauapps.codemylink.databinding.LinkItemLayoutRvBinding;
import com.sauapps.codemylink.generated.CodeGeneratedActivity;
import com.sauapps.codemylink.models.UserMyLinksModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.sauapps.codemylink.data.MyData.appUpdateList;

public class MyLinksAdapter extends RecyclerView.Adapter<MyLinksAdapter.ViewHolder> {

    private Context context;
    private  List<UserMyLinksModel> myLinksList;

    public MyLinksAdapter(Context context, List<UserMyLinksModel> myLinksList) {
        this.context = context;
        this.myLinksList = myLinksList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_item_layout_rv, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyLinksAdapter.ViewHolder holder, int position) {

        holder.code.setText(myLinksList.get(position).getLink_code());
        holder.link.setText(myLinksList.get(position).getLink());
        holder.link_title.setText(myLinksList.get(position).getLink_title());
        holder.link_views.setText(myLinksList.get(position).getLink_views());
        holder.link_date.setText(myLinksList.get(position).getLink_date());
        holder.code.setText(myLinksList.get(position).getLink_code());

        holder.copy_code_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                String your_url = holder.code.getText().toString();
                ClipData clipData = ClipData.newPlainText("text", your_url);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(view.getContext(), "Code Copied...", Toast.LENGTH_SHORT).show();


            }
        });

        holder.edit_link_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// open edit activity....

                Intent intent = new Intent(view.getContext(), EditMyLinkActivity.class);

                intent.putExtra("code",myLinksList.get(position).getLink_code());
                intent.putExtra("link",myLinksList.get(position).getLink());
                intent.putExtra("title",myLinksList.get(position).getLink_title());
                intent.putExtra("des",myLinksList.get(position).getLink_des());
                intent.putExtra("status",myLinksList.get(position).getLink_status());

                view.getContext().startActivity(intent);

            }
        });

        holder.share_link_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///intent for share this code

                String share_text = "Use This Code in 'Code My Link' App and get your link:  "
                        + myLinksList.get(position).getLink_code()+
                        "                                  App link:"+String.valueOf(appUpdateList.get(0).getAppLink());
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,share_text);

                view.getContext().startActivity(intent.createChooser(intent,"Share"));

            }
        });


    }

    @Override
    public int getItemCount() {
        return myLinksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView code, link, link_date, link_views,link_title;
        private Button copy_code_b;

        private ImageView edit_link_b,share_link_b;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            code = itemView.findViewById(R.id.lil_code);
            link = itemView.findViewById(R.id.lil_link);
            link_date = itemView.findViewById(R.id.lil_link_date);
            link_views = itemView.findViewById(R.id.lil_link_views);
            link_title = itemView.findViewById(R.id.lil_link_title);
            edit_link_b = itemView.findViewById(R.id.lil_edit_link_b);
            share_link_b = itemView.findViewById(R.id.lil_share_link_b);
            copy_code_b = itemView.findViewById(R.id.lil_code_copy_b);



        }
    }
}

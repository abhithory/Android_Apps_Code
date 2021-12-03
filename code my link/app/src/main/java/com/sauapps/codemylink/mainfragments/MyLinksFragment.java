package com.sauapps.codemylink.mainfragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sauapps.codemylink.MainActivity;
import com.sauapps.codemylink.codemylink.CodeMyLinkActivity;
import com.sauapps.codemylink.mylinks.MyAllLinks;
import com.sauapps.codemylink.mylinks.MyLinksAdapter;
import com.sauapps.codemylink.R;
import com.sauapps.codemylink.data.MyListener;
import com.sauapps.codemylink.databinding.FragmentMyLinksBinding;
import com.sauapps.codemylink.profile.ProfileActivity;

import static com.sauapps.codemylink.data.MyData.againLoadMylinks;
import static com.sauapps.codemylink.data.MyData.loadUserMyLinks5;
import static com.sauapps.codemylink.data.MyData.userMyLinksList5;

public class MyLinksFragment extends Fragment {


    private FragmentMyLinksBinding fragmentMyLinksBinding;
    private Dialog progress_Dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMyLinksBinding = FragmentMyLinksBinding.inflate(inflater,container,false);
        View rootview = fragmentMyLinksBinding.getRoot();


        progress_Dialog = new Dialog(getContext());
        progress_Dialog.setContentView(R.layout.progress_dialog_layout_2);
        progress_Dialog.setCancelable(false);
        progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        progress_Dialog.show();

        if (userMyLinksList5.isEmpty()){

            loadUserMyLinks5(getContext(), new MyListener() {
                @Override
                public void onSuccess() {

                    setallLinksData();

                }

                @Override
                public void onFailer() {


                }
            });


        }else {

            if (againLoadMylinks){
                againLoadMylinks = false;

                loadUserMyLinks5(getContext(), new MyListener() {
                    @Override
                    public void onSuccess() {

                        setallLinksData();

                    }

                    @Override
                    public void onFailer() {


                    }
                });


            }else {

                setallLinksData();

            }

        }

        fragmentMyLinksBinding.mlfLoadAllMylinksB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), MyAllLinks.class));

            }
        });

        fragmentMyLinksBinding.mlfGotoprofileB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));

            }
        });

        fragmentMyLinksBinding.mlfCodemylinkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CodeMyLinkActivity.class));

            }
        });





        return rootview;
    }

    private void setallLinksData() {



        //adding recycler view for all links data

        if (userMyLinksList5.isEmpty()){

            Log.i("cml_checking","15");


            fragmentMyLinksBinding.mlfIfNoLinksLayout.setVisibility(View.VISIBLE);
            fragmentMyLinksBinding.mlfMylinksReView.setVisibility(View.GONE);
            fragmentMyLinksBinding.mlfLoadAllMylinksB.setVisibility(View.GONE);

        }else {

            Log.i("cml_checking","16");

            fragmentMyLinksBinding.mlfIfNoLinksLayout.setVisibility(View.GONE);
            fragmentMyLinksBinding.mlfMylinksReView.setVisibility(View.VISIBLE);
            fragmentMyLinksBinding.mlfLoadAllMylinksB.setVisibility(View.VISIBLE);

            RecyclerView myLinks_reView = fragmentMyLinksBinding.mlfMylinksReView;
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            myLinks_reView.setLayoutManager(layoutManager);

            MyLinksAdapter adapter = new MyLinksAdapter(getContext(), userMyLinksList5);
            myLinks_reView.setAdapter(adapter);

        }



        progress_Dialog.dismiss();
        Log.i("cml_checking","17");

    }
}
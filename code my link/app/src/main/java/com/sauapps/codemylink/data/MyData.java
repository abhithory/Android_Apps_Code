package com.sauapps.codemylink.data;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.sauapps.codemylink.models.AppConfigModel;
import com.sauapps.codemylink.models.AppUpdateModel;
import com.sauapps.codemylink.models.ForAppModel;
import com.sauapps.codemylink.models.GeneratedCodeModel;
import com.sauapps.codemylink.models.GeneratedLinkModel;
import com.sauapps.codemylink.models.UserDataModel;
import com.sauapps.codemylink.models.UserMyLinksModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyData {

    public static List<UserDataModel> userDataList = new ArrayList<>();
    public static List<GeneratedLinkModel> generatedLinkList = new ArrayList<>();
    public static List<GeneratedCodeModel> generatedCodeList = new ArrayList<>();

    public static List<UserMyLinksModel> userMyLinksList5 = new ArrayList<>();
    public static List<UserMyLinksModel> userMyLinksListall = new ArrayList<>();
    public static List<MyLinksPagination> myLinksPaginationList = new ArrayList<>();

    public static List<AppUpdateModel> appUpdateList = new ArrayList<>();
    public static List<AppConfigModel> appconfigList= new ArrayList<>();
    public static List<ForAppModel> forAppList = new ArrayList<>();


    public static Boolean isLogin =false;
    public static Boolean againLoadMylinks = false;

    private static final String appUpdateUrl = "http://149.129.174.59/api/cmlappupdate";



    public static void init(Context context,MyListener myListener){

        forAppList.clear();
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, appUpdateUrl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // on below line we are displaying a success toast message.
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);


                    // below are the strings which we
                    // extract from our json object.
                    String result = respObj.getString("result");

                    String status = respObj.getString("status");

                    JSONObject dataobj = respObj.getJSONObject("data");

                    String url = dataobj.getString("url");
                    String geturl = dataobj.getString("geturl");
                    String seturl = dataobj.getString("seturl");
                    String updateurl = dataobj.getString("updateurl");
                    String deleteurl = dataobj.getString("deleteurl");

                    forAppList.add(new ForAppModel(url,geturl,seturl,updateurl,deleteurl));





                } catch (Exception e) {


                }
                myListener.onSuccess();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                myListener.onFailer();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> values = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                values.put("task", "forappdata");

                return values;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);



    }

    public static void createUserData(String email,String username,String phoneno,Context context,MyListener myListener){

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, forAppList.get(0).getUrl()+forAppList.get(0).getSeturl(), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject respObj = new JSONObject(response);

                    String result = respObj.getString("result");


                    String status = respObj.getString("status");

                } catch (Exception e) {



                }
                myListener.onSuccess();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                myListener.onFailer();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> values = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                values.put("task", "createuserdata");
                values.put("name", username);
                values.put("email", email);
                values.put("phone", phoneno);
                values.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
                // at last we are
                // returning our params.
                return values;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }

    public static void loadUpdateData(Context context,MyListener myListener){


        appUpdateList.clear();
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, appUpdateUrl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // on below line we are displaying a success toast message.
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);



                    // below are the strings which we
                    // extract from our json object.
                    String result = respObj.getString("result");


                    String status = respObj.getString("status");


                    JSONObject dataobj = respObj.getJSONObject("data");

                    String isupdate = dataobj.getString("isupdate");
                    String newversion = dataobj.getString("newversion");
                    String updatetxt = dataobj.getString("updatetext");
                    String applink = dataobj.getString("applink");

                    appUpdateList.add(new AppUpdateModel(newversion,isupdate,updatetxt,applink));





                } catch (Exception e) {


                }
                myListener.onSuccess();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                myListener.onFailer();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> values = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                values.put("task", "appupdatedata");

                // at last we are
                // returning our params.
                return values;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }

    public static void loadAppConfig(Context context,MyListener myListener){

        appconfigList.clear();
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, forAppList.get(0).getUrl()+forAppList.get(0).getGeturl(), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // on below line we are displaying a success toast message.
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);


                    // below are the strings which we
                    // extract from our json object.
                    String result = respObj.getString("result");


                    String status = respObj.getString("status");


                    JSONObject dataobj = respObj.getJSONObject("data");

                    String pp = dataobj.getString("pp");
                    String tac = dataobj.getString("tac");
                    String about_us = dataobj.getString("about_us");
                    String share_txt = dataobj.getString("share_txt");
                    String telegram = dataobj.getString("telegram");
                    String contactus = dataobj.getString("contactus");

                    appconfigList.add(new AppConfigModel(pp,tac,about_us,share_txt,telegram,contactus));



                } catch (Exception e) {


                }
                myListener.onSuccess();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                myListener.onFailer();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> values = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                values.put("task", "getappconfig");

                // at last we are
                // returning our params.
                return values;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);


    }


    public static void loadLinkByCode(Context context,String code,MyListener myListener){
        generatedLinkList.clear();
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, forAppList.get(0).getUrl()+forAppList.get(0).getGeturl(), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // on below line we are displaying a success toast message.
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);

                    String result = respObj.getString("result");


                    String status = respObj.getString("status");

                    if (status.equals("1")){

                        JSONObject dataobj = respObj.getJSONObject("data");

                        String linkstatus = dataobj.getString("status");
                        if (linkstatus.equals("1")){
                            String code = dataobj.getString("code");
                            String link = dataobj.getString("link");
                            String title = dataobj.getString("title");
                            String des = dataobj.getString("des");

                            generatedLinkList.add(new GeneratedLinkModel(linkstatus,code,link,title,des));

                        } else {
                            generatedLinkList.add(new GeneratedLinkModel(linkstatus,code,"link","title","des"));
                        }





                    } else {

                        generatedLinkList.add(new GeneratedLinkModel("-1",code,"link","title","des"));

                    }

                } catch (Exception e) {
                    generatedLinkList.add(new GeneratedLinkModel("-2",code,"link","title","des"));


                }
                myListener.onSuccess();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                generatedLinkList.add(new GeneratedLinkModel("-2",code,"link","title","des"));

                myListener.onFailer();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> values = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                values.put("task", "linkbycode");
                values.put("id", code);

                // at last we are
                // returning our params.
                return values;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }


    public static void generateCodeForLink(String link, String title, String des, Context context, MyListener myListener){

        generatedCodeList.clear();
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, forAppList.get(0).getUrl()+forAppList.get(0).getSeturl(), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // on below line we are displaying a success toast message.
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);


                    // below are the strings which we
                    // extract from our json object.
                    String result = respObj.getString("result");


                    String status = respObj.getString("status");

                    JSONObject dataobj = respObj.getJSONObject("data");


                    if (status.equals("1")){
                        String code = dataobj.getString("code");
                        String link = dataobj.getString("link");

                        generatedCodeList.add(new GeneratedCodeModel("1",code,link,"",""));


                    } else {
                        generatedCodeList.add(new GeneratedCodeModel("0","",link,"",""));

                    }






                } catch (Exception e) {


                }
                myListener.onSuccess();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                myListener.onFailer();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> values = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                values.put("task", "codemylink");
                values.put("link", link);
                values.put("title", title);
                values.put("des", des);
                values.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid());
                values.put("userid", userDataList.get(0).getUserId());

                // at last we are
                // returning our params.
                return values;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);


    }






    public static  void loaduserdata(Context context , MyListener myListener)
    {
        userDataList.clear();
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, forAppList.get(0).getUrl()+forAppList.get(0).getGeturl(), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // on below line we are displaying a success toast message.
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);


                    // below are the strings which we
                    // extract from our json object.
                    String result = respObj.getString("result");


                    JSONArray dataArray = respObj.getJSONArray("data");
                    JSONObject dataobj =dataArray.getJSONObject(0);

                    String status = respObj.getString("status");

                    if (status.equals("1")){

                            String id = dataobj.getString("id");
                            String name = dataobj.getString("name");
                            String email = dataobj.getString("email");
                            String phone = dataobj.getString("phone");

                            userDataList.add(new UserDataModel(id,name,phone,email));



                    } else {
                        userDataList.add(new UserDataModel("id","name","phone","email"));

                    }

                } catch (Exception e) {

                    userDataList.add(new UserDataModel("id","name","phone","email"));


                }
                myListener.onSuccess();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                userDataList.add(new UserDataModel("id","name","phone","email"));


                myListener.onFailer();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> values = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                values.put("task", "getuserdata");
                values.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid());

                // at last we are
                // returning our params.
                return values;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }


    public static void updateuserdata(String phone,String name,Context context,MyListener myListener){

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, forAppList.get(0).getUrl()+forAppList.get(0).getUpdateurl(), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // on below line we are displaying a success toast message.
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);


                    // below are the strings which we
                    // extract from our json object.
                    String result = respObj.getString("result");


                    JSONObject dataobj = respObj.getJSONObject("data");

                    String status = respObj.getString("status");

                    if (status.equals("1")){
                        Toast.makeText(context, " user data updated succefully", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(context, "Not User data updated succefully", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {


                }
                myListener.onSuccess();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                myListener.onFailer();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> values = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                values.put("task", "updateuserdata");
                values.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid());
                values.put("userid",userDataList.get(0).getUserId());
                values.put("name",name);
                values.put("phone",phone);

                // at last we are
                // returning our params.
                return values;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }


    public static void loadUserMyLinks5(Context context, MyListener myListener){

        userMyLinksList5.clear();
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, forAppList.get(0).getUrl()+forAppList.get(0).getGeturl(), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // on below line we are displaying a success toast message.
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);

                    // below are the strings which we
                    // extract from our json object.
                    String result = respObj.getString("result");


                    String status = respObj.getString("status");
                    JSONObject dataobj = respObj.getJSONObject("data");




                    if (status.equals("1")){
                        JSONArray dataarray = dataobj.getJSONArray("data");

                        for (int i =0;i<dataarray.length();i++){

                            JSONObject linkobj = dataarray.getJSONObject(i);
                            String linkid = linkobj.getString("id");
                            String link = linkobj.getString("link");
                            String linkstatus = linkobj.getString("status");
                            String linkcode = linkobj.getString("code");
                            String linktitle = linkobj.getString("title");
                            String linkdes = linkobj.getString("des");
                            String linktime = linkobj.getString("time");
                            String linkview = linkobj.getString("views");

                            userMyLinksList5.add(new UserMyLinksModel(linkid,link,linkstatus,linkcode,linktitle,linkdes,linktime,linkview));

                        }



                    } else {

                    }





                } catch (Exception e) {

                }
                myListener.onSuccess();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                myListener.onFailer();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> values = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                values.put("task", "getmylinks5");
                values.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid());
                values.put("userid", userDataList.get(0).getUserId());


                // at last we are
                // returning our params.
                return values;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }

    public static void loadUserMyLinksall(String pageurl,Context context,MyListener myListener){

        userMyLinksListall.clear();
        myLinksPaginationList.clear();
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, pageurl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // on below line we are displaying a success toast message.
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);


                    // below are the strings which we
                    // extract from our json object.
                    String result = respObj.getString("result");
                    JSONObject dataobj = respObj.getJSONObject("data");


                    String status = respObj.getString("status");

                    if (status.equals("1")){
                        Toast.makeText(context, "update data succefully", Toast.LENGTH_SHORT).show();

                        JSONArray dataarray = dataobj.getJSONArray("data");

                        for (int i =0;i<dataarray.length();i++){

                            JSONObject linkobj = dataarray.getJSONObject(i);
                            String linkid = linkobj.getString("id");
                            String link = linkobj.getString("link");
                            String linkstatus = linkobj.getString("status");
                            String linkcode = linkobj.getString("code");
                            String linktitle = linkobj.getString("title");
                            String linkdes = linkobj.getString("des");
                            String linktime = linkobj.getString("time");
                            String linkview = linkobj.getString("views");

                            userMyLinksListall.add(new UserMyLinksModel(linkid,link,linkstatus,linkcode,linktitle,linkdes,linktime,linkview));

                        }


                        String currentpagecount = dataobj.getString("current_page");
                        String totalpagecount = dataobj.getString("last_page");
                        String firstpageurl = dataobj.getString("first_page_url");
                        String lastpageurl = dataobj.getString("last_page_url");
                        String nextpageurl = dataobj.getString("next_page_url");
                        String previouspageurl = dataobj.getString("prev_page_url");


                        myLinksPaginationList.add(new MyLinksPagination(currentpagecount,totalpagecount,nextpageurl,
                                previouspageurl,firstpageurl,lastpageurl));


                    } else {
                        Toast.makeText(context, "not update data succefully", Toast.LENGTH_SHORT).show();
                    }



                } catch (Exception e) {


                }
                myListener.onSuccess();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                myListener.onFailer();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> values = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                values.put("task", "getmylinks15");
                values.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid());
                values.put("userid", userDataList.get(0).getUserId());

                // at last we are
                // returning our params.
                return values;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }

    public static void updatemylink(String title,String des,String link,String status,String code,Context context,MyListener myListener){

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, forAppList.get(0).getUrl()+forAppList.get(0).getUpdateurl(), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // on below line we are displaying a success toast message.
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);


                    // below are the strings which we
                    // extract from our json object.
                    String result = respObj.getString("result");


                    JSONObject dataobj = respObj.getJSONObject("data");

                    String status = respObj.getString("status");

                    if (status.equals("1")){
                        Toast.makeText(context, " user data updated succefully", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(context, "Not User data updated succefully", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {


                }
                myListener.onSuccess();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                myListener.onFailer();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> values = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                values.put("task", "updatecodedata");
                values.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid());
                values.put("userid",userDataList.get(0).getUserId());
                values.put("code",code);
                values.put("link",link);
                values.put("status",status);
                values.put("title",title);
                values.put("des",des);

                // at last we are
                // returning our params.
                return values;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }



}

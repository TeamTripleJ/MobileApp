
//CHANGE THIS FOR YOUR PROJECT
package com.example.user.calendar_test;

import android.content.Context;
import android.content.Intent;
import android.net.LinkAddress;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.data;
import static com.example.user.calendar_test.R.styleable.View;

public class MainActivity extends AppCompatActivity {
    public int q=0;
    String data;
    String finalstring;
    int numberposts=0;
    TextView ATextView;
    RequestQueue requestQueue;
    String JsonURL="this text should be overridden";
    boolean rState=false;
    String test="asdasdasd";

    Button NewPostButton;
    Button HomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        NewPostButton = (Button) findViewById(R.id.NewPostButton);
        HomeButton = (Button) findViewById(R.id.HomeButton);
        TextView ScheduleMonth = (TextView)findViewById(R.id.MonthTitle);
        int result[] = getCurrentDateTime();

        ScheduleMonth.setText(getMonth(result[1], false));

        NewPostButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditPost.class));
            }
        });





        System.out.println("req queue being set");
        requestQueue = Volley.newRequestQueue(this);
        System.out.println("REQ QUEUE SET");
        int date[]=getCurrentDateTime();

        getPosts(date, 1);

        System.out.println("now going to run the runnable");



        //Runnable r = new Runnable() {
           // @Override
           // public void run() {
                System.out.println("Runnable was run");
                //getData("https://aqueous-americans.000webhostapp.com/PostList.php");
               // getData2();
        // getPosts();
                //InsertPost();
                //System.out.println("post was inserted");
                //getDataBasicTest();
                //System.out.println(postContainer[0][1]);
                //System.out.println(postContainer[1][1]);
           // }
        //};

        //r.run();

        //fillSchedule();

        //LinearLayout thing = (LinearLayout)findViewById(saldkfaslkjdflakjsdff)
        //thing.setOnClickListener(new View.OnClickListener(){
        //    public void onClick(View v){
        //
        //    }
        //});

    }


    public void fillSchedule(int date[], JSONArray PostArray){
        //TODO: uncomment this, try this out to prevent excessive memory use
        //LinearLayout scheduleList = (LinearLayout) findViewById(R.id.ScheduleContainer);

        int userID= 1;

        int oldDay=-1;
        String newDate="nah";
        View DayContainer = null;

        if(PostArray!=null) {
            System.out.println(PostArray.length());
        }else{
            System.out.println("nah fam its null");
        }

        for(int i = 0; i < PostArray.length(); i++) {

            LinearLayout scheduleList = (LinearLayout) findViewById(R.id.ScheduleContainer);
            try {

                JSONObject PostObj = PostArray.getJSONObject(i);

                //grab appropriate content form the query result for the post to be displayed
                //first, get values for time
                newDate=PostObj.getString("Time");
                int postMonth= Integer.parseInt(newDate.substring(5,7));
                int postDay = Integer.parseInt(newDate.substring(8,10));
                int postHour = Integer.parseInt(newDate.substring(11,13));
                int postMin = Integer.parseInt(newDate.substring(14,16));
                String AMorPM = "not set";
                if(postHour>=12){
                    AMorPM="PM";
                }else{
                    AMorPM="AM";
                }
                String postHeader=PostObj.getString("Header");
                String postBody=PostObj.getString("words");
                String postNetwork=PostObj.getString("network_type");

                //TODO: get media type for post

                //trim the text of the content if it's too long to display



                //if this post is on a separate day from the previous post in the scheduler,
                // make a new day container.
                if(postDay!=oldDay){
                    DayContainer = LayoutInflater.from(this).inflate(R.layout.day_container, scheduleList, false);
                    scheduleList.addView(DayContainer);

                    TextView textDay = (TextView) DayContainer.findViewById(R.id.Day);
                    TextView textMonth = (TextView) DayContainer.findViewById(R.id.Month);
                    System.out.println(postDay);
                    textDay.setText(Integer.toString(postDay));
                    textMonth.setText(getMonth(postMonth, true));  //only first 3 letters of month are displayed in day container
                }

                //specifies what layout post elements should be added to,
                // then adds a post to the current day container
                LinearLayout postList = (LinearLayout) DayContainer.findViewById(R.id.PostCol);
                View PostContainer = LayoutInflater.from(this).inflate(R.layout.post_container, postList, false);
                postList.addView(PostContainer);

                //get all the text objects and views of the new post element
                LinearLayout post=(LinearLayout) PostContainer.findViewById(R.id.PostTextContainer);
                FrameLayout postBG = (FrameLayout) PostContainer.findViewById(R.id.PostBackground);
                TextView HeaderText = (TextView) PostContainer.findViewById(R.id.postHeader);
                TextView BodyText = (TextView) PostContainer.findViewById(R.id.postBody);
                ImageView NetworkIcon = (ImageView) PostContainer.findViewById(R.id.postNetworkIcon);
                ImageView MediaTypeIcon = (ImageView) PostContainer.findViewById(R.id.postContentTypeIcon);

                //now SET THE CONTENT OF THE POST CONTAINER
                HeaderText.setText(postHeader);
                BodyText.setText(postBody);

                //if(postNetwork=="facebook"){
                //    post.setBackgroundColor();
                //}else if(postNetwork=="twitter"){
                //
                //}

                //post ID is saved to tag, so that if it is clicked, the postID if clicked element is
                //sent to the edit post along with the intent- this is queried by a method in Edit Post to get
                //that post's data and display it, so it can be edited
                //post.setTag(PostObj.getString("PostID"));

                post.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                    //System.out.println(v.getTag());
                        startActivity(new Intent(MainActivity.this, EditPost.class));
                    }
                });

                oldDay=postDay;


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        System.out.println("schedule was filled");
    }


    //public void getPosts(int[] date, int userID){
    public void getPosts(int[] date, int userID){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://aqueous-americans.000webhostapp.com/1NewPostList.php";
        //String url ="http://aqueous-americans.000webhostapp.com/1InsertPost.php"
        final int[]input = date;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        System.out.println(response);
                        try {

                            JSONArray myArray = new JSONArray(response);

                            for(int i = 0; i < myArray.length(); i++) {
                                JSONObject myObj = myArray.getJSONObject(i);
                                //test=myObj.getString("Header");
                                //System.out.println(test);
                                //PostArray.put(myObj);
                                test+=myObj.getString("Header");
                            }
                            fillSchedule(input, myArray);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                           System.out.println("volley error");
                        }
                }
        ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("userid","1");
                    //params.put("post_id","1");
                    params.put("Time", "'2017-10-15 23:23:23'");
                    params.put("action", "2");
                    return params;
                }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //return results;



        //return PostArray;
    }

    public void InsertPost(){
        RequestQueue queue = Volley.newRequestQueue(this);

        //String url ="http://aqueous-americans.000webhostapp.com/CreatePost.php";
        String url ="http://aqueous-americans.000webhostapp.com/1InsertPost.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        //Log.d("Error.Response", response);
                        System.out.println("error occured with update post method");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("userid", "1");
                params.put("timezone", "1");
                params.put("words", "'helloworld once more'");
                params.put("header", "'header here lol'");
                params.put("photo_id", "1");
                params.put("Time", "'2017-10-15 23:23:23'");
                params.put("network_type", "'facebook'");
                return params;
            }
        };
        queue.add(postRequest);
    }


    public int[] getCurrentDateTime(){
        int[] result=new int[3];

        Calendar cal = Calendar.getInstance();
        result[0]=cal.get(Calendar.DAY_OF_MONTH);
        result[1]= cal.get(Calendar.MONTH);
        result[2]= cal.get(Calendar.YEAR);

        return result;
    }

    public String getMonth(int month, boolean shorten){
        String result;
        switch(month){
            case 1: result= "JANUARY"; break;
            case 2: result= "FEBRUARY"; break;
            case 3: result= "MARCH"; break;
            case 4: result= "APRIL"; break;
            case 5: result= "MAY "; break;
            case 6: result= "JUNE"; break;
            case 7: result= "JULY"; break;
            case 8: result= "AUGUST"; break;
            case 9: result= "SEPTEMBER"; break;
            case 10: result= "OCTOBER"; break;
            case 11: result= "NOVEMBER"; break;
            case 12: result= "DECEMBER"; break;
            default: result= "ZZZ"; break;
        }
        if(shorten==true){
            result=result.substring(0,3);
        }

        return result;
    }
}









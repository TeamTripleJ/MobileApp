
//CHANGE THIS FOR YOUR PROJECT
package com.example.user.calendar_test;

import android.content.Context;
import android.net.LinkAddress;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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

import static android.R.attr.data;
import static com.example.user.calendar_test.R.styleable.View;

public class MainActivity extends AppCompatActivity {
    public int q=0;
    String data;
    String finalstring;
    TextView ATextView;
    RequestQueue requestQueue;
    String JsonURL="this text should be overridden";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillSchedule();
        System.out.println("now going to run the runnable");

        System.out.println("req queue being set");
        requestQueue = Volley.newRequestQueue(this);
        System.out.println("REQ QUEUE SET");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable was run");
                //getData("https://aqueous-americans.000webhostapp.com/PostList.php");
               // getData2();
                getDataBasicTest();
            }
        };

        r.run();

        //fillSchedule();

        //LinearLayout thing = (LinearLayout)findViewById(saldkfaslkjdflakjsdff)
        //thing.setOnClickListener(new View.OnClickListener(){
        //    public void onClick(View v){
        //
        //    }
        //});

    }


    public void fillSchedule(){
           //LinearLayout scheduleList = (LinearLayout) findViewById(R.id.ScheduleContainer);


            for(int i=0;i<5;i++) {

                LinearLayout scheduleList = (LinearLayout) findViewById(R.id.ScheduleContainer);
                View DayContainer = LayoutInflater.from(this).inflate(R.layout.day_container, scheduleList, false);
                scheduleList.addView(DayContainer);
                LinearLayout postList = (LinearLayout) DayContainer.findViewById(R.id.PostCol);

                ////////////////////////////////
                //                            //
                //  GET DATA FROM QUERY HERE  //
                //                            //
                ////////////////////////////////

                //String[][] PostList =getData("http://aqueous-americans.000webhostapp.com/PostList.php","000123");



                for (int j = 0; j < 3; j++) {
                    //final LinearLayout postList = (LinearLayout) DayContainer.findViewById(R.id.PostCol);

                    View PostContainer = LayoutInflater.from(this).inflate(R.layout.post_container, postList, false);

                    postList.addView(PostContainer);
                    q++;

                    LinearLayout post=(LinearLayout) PostContainer.findViewById(R.id.PostTextContainer);
                    post.setTag(1000+q);
                    post.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){
                            System.out.println(v.getTag());
                        }
                    });
                }
            }
    }


    //THIS DOESN'T WORK DON'T USE
    private void getData(String url) {
        // URL of object to be parsed

        //Uri.Builder builder = new Uri.Builder()
        //        .appendQueryParameter("userid", userid);
        //String URL = builder.build().getEncodedQuery();
        //JsonURL = URL;
            JsonURL=url;
        System.out.println("JsonURL was set without issue");
        //Runnable r = new Runnable() {

            //@Override
            //public void run() {
                JsonArrayRequest arrayreq = new JsonArrayRequest(JsonURL,
                        // The second parameter Listener overrides the method onResponse() and passes
                        //JSONArray as a parameter

                        new Response.Listener<JSONArray>() {
                            // Takes the response from the JSON request
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    System.out.println("OnResponse is being run...");
                                    // Retrieves first JSON object in outer array
                                    JSONObject colorObj = response.getJSONObject(0);

                                    System.out.println("JSon object was retrieved");
                                    // Retrieves "colorArray" from the JSON object
                                    JSONArray colorArry = colorObj.getJSONArray("Header");
                                    // Iterates through the JSON Array getting objects and adding them
                                    //to the list view until there are no more objects in colorArray

                                    //String[][] PostList = new String[colorArry.length()][6];

                                    for (int i = 0; i < colorArry.length(); i++) {
                                        //gets each JSON object within the JSON array
                                        JSONObject jsonObject = colorArry.getJSONObject(i);

                                        // Retrieves the string labeled "colorName" and "hexValue",
                                        // and converts them into javascript objects

                                        String Header = jsonObject.getString("Header");

                                        // Adds strings from the current object to the data string
                                        //spacing is included at the end to separate the results from
                                        //one another
                                        data += Header + "  nnn  ";
                                    }


                                }
                                // Try and catch are included to handle any errors due to JSON
                                catch (JSONException e) {
                                    // If an error occurs, this prints the error to the log
                                    e.printStackTrace();
                                }
                            }
                        },
                        // The final parameter overrides the method onErrorResponse() and passes VolleyError
                        //as a parameter
                        new Response.ErrorListener() {
                            @Override
                            // Handles errors that occur due to Volley
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Volley", "Error");
                            }
                        }
                );
                // Adds the JSON array request "arrayreq" to the request queue
                requestQueue.add(arrayreq);

            //}

            //Thread myThread = new Thread();
            //myThread.start();
        //};
        //arrayreq.;

        System.out.println(data);
        System.out.println("ahahahahahah");


    }


    //THIS DOESN'T WORK DON'T USE
    public void getData2(){
        JsonURL = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Array.JSON";
        JsonArrayRequest arrayreq = new JsonArrayRequest(JsonURL,
                // The second parameter Listener overrides the method onResponse() and passes
                //JSONArray as a parameter
                new Response.Listener<JSONArray>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Retrieves first JSON object in outer array
                            JSONObject colorObj = response.getJSONObject(0);
                            // Retrieves "colorArray" from the JSON object
                            JSONArray colorArry = colorObj.getJSONArray("colorArray");
                            // Iterates through the JSON Array getting objects and adding them
                            //to the list view until there are no more objects in colorArray
                            for (int i = 0; i < colorArry.length(); i++) {
                                //gets each JSON object within the JSON array
                                JSONObject jsonObject = colorArry.getJSONObject(i);

                                // Retrieves the string labeled "colorName" and "hexValue",
                                // and converts them into javascript objects
                                String color = jsonObject.getString("colorName");
                                String hex = jsonObject.getString("hexValue");

                                // Adds strings from the current object to the data string
                                //spacing is included at the end to separate the results from
                                //one another
                                data += "Color Number " + (i + 1) + "nColor Name: " + color +
                                        "nHex Value : " + hex + "nnn";
                                System.out.println(data);
                            }
                            // Adds the data string to the TextView "results"
                            finalstring=data;
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        requestQueue.add(arrayreq);
    }


    //THIS DOESN'T WORK DON'T USE
    public void getDataTest(){
        String URL="http://aqueous-americans.000webhostapp.com/PostList.php";

        StringRequest get = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray myArray = new JSONArray(response);

                    for(int i = 0; i < myArray.length(); i++)
                    {
                        //JSONObject jObj = myArray.getJSONObject(i);
                        // get your data here
                        //String Header = jObj.getString("Header");
                        System.out.println("here's a row");
                    }
                    System.out.println("lmao");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               System.out.println("That didn't work!");
            }
        });
        requestQueue.add(get);
    }

    public void getDataBasicTest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://aqueous-americans.000webhostapp.com/PostList.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray myArray = new JSONArray(response);

                            for(int i = 0; i < myArray.length(); i++) {
                                JSONObject myObj = myArray.getJSONObject(i);
                                String Header = myObj.getString("Header");
                                System.out.println(Header);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               System.out.println("volley error");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}









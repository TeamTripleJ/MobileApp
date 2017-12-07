package com.circlesuite.httpwww.tempspinner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
public class EditPost extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    //RequestQueue queue = Volley.newRequestQueue(this);
    EditText datefield;
    EditText headerfield;
    EditText wordsfield;
    Button submitPost;
    DatePickerDialog DateSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        datefield = (EditText) findViewById(R.id.DateField);
        headerfield = (EditText) findViewById(R.id.HeaderField);
        wordsfield = (EditText) findViewById(R.id.WordsField);
        submitPost = (Button) findViewById(R.id.SubmitPostButton);
        datefield.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // int[] info = getCurrentDateTime();


                // DateSelect = new DatePickerDialog(this, newO)
            }
        });
    }

    //this is the old function
    /*public void InsertPost(String[] info){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://aqueous-americans.000webhostapp.com/CreatePost.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
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
                params.put("firstname", "tripleJ");
                //params.put("domain", "http://itsalif.info");
                return params;
            }
        };
        queue.add(postRequest);
    }*/
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

    public int[] getCurrentDateTime(){
        int[] result=new int[3];

        Calendar cal = Calendar.getInstance();
        result[0]=cal.get(Calendar.DAY_OF_MONTH);
        result[1]= cal.get(Calendar.MONTH);
        result[2]= cal.get(Calendar.YEAR);

        return result;
    }

}
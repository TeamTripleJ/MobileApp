package com.circlesuite.httpwww.cs;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import android.os.Environment;
import java.net.URL;
import android.os.Handler;
import android.os.Message;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import android.widget.Spinner;

import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.text.method.ScrollingMovementMethod;

//https://www.thorntech.com/2016/03/parsing-json-android-using-volley-library/
//http://androidcss.com/android/android-php-mysql-login-tutorial/
//https://developer.android.com/training/volley/index.html
//https://www.android-examples.com/android-json-parsing-retrieve-from-url-and-set-mysql-db-data/
import java.util.List;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Toolbar myToolbar;
    Spinner mySpinner;
    String JsonURL = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Object.JSON";
    // This string will hold the results
    String data = "";
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;
    //JSONObject jsonRequest;

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private EditText etEmail;
    private EditText etPassword;
    TextView results;
    TextView tempost;

    // For any code you want running in the main thread after user action ( ie changing the text of button after user click)
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] thisIsAStringArray = new String[5];
        requestQueue = Volley.newRequestQueue(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://github.com/codingdemos/CustomToolbar
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        myToolbar.setTitle(getResources().getString(R.string.app_name));

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                R.layout.custom_spinner_item,
                getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,
                        mySpinner.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //https://github.com/codingdemos/CustomToolbar
        //http://mrbool.com/how-to-create-a-scrollbar-in-android/27070
        //https://developer.android.com/reference/android/text/method/ScrollingMovementMethod.html
        //https://github.com/codepath/android_guides/wiki/Working-with-the-ScrollView


        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable was run");
                //getData("https://aqueous-americans.000webhostapp.com/PostList.php");
                // getData2();
                getDataBasicTest();
                /*tempost = (TextView) findViewById(R.id.Button01);
                tempost.setText(thisIsAStringArray[0]);
                tempost = (TextView) findViewById(R.id.Button02);
                tempost.setText(thisIsAStringArray[1]);*/
            }
        };

        r.run();

    }



    private void postText() {
        Runnable r = new Runnable() {
            HttpURLConnection conn;

            @Override
            public void run() {
                URL url = null;
                try {
                    // Enter URL address where your php file resides
                    url = new URL("http://localhost/test/login.inc.php");
                    // Setup HttpURLConnection class to send and receive data from php and mysql
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(READ_TIMEOUT);
                    conn.setConnectTimeout(CONNECTION_TIMEOUT);
                    conn.setRequestMethod("POST");
                    // setDoInput and setDoOutput method depict handling of both send and receive
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    // Append parameters to URL
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("username", "JB")
                            .appendQueryParameter("password", "JB");
                    String query = builder.build().getEncodedQuery();
                    // Open connection for sending data
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();
                    conn.connect();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    int response_code = conn.getResponseCode();
                    // Check if successful connection made
                    if (response_code == HttpURLConnection.HTTP_OK) {
                        // Read data sent from server
                        InputStream input = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                        // Pass data to onPostExecute method
                    } else {
                        return;
                    }

                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    conn.disconnect();
                }

            }


        };
        Thread myThread = new Thread();
        myThread.start();
    }

    private void postImage() {
        Runnable r = new Runnable() {
            HttpURLConnection conn;

            @Override
            public void run() {
                URL url = null;
                try {
                    String textFile = Environment.getExternalStorageDirectory() + "/sample.txt";
                    // Enter URL address where your php file resides
                    url = new URL("http://localhost/test/login.inc.php");
                    // Setup HttpURLConnection class to send and receive data from php and mysql
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(READ_TIMEOUT);
                    conn.setConnectTimeout(CONNECTION_TIMEOUT);
                    conn.setRequestMethod("POST");
                    // setDoInput and setDoOutput method depict handling of both send and receive
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    // Append parameters to URL
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("picture", textFile);
                    String query = builder.build().getEncodedQuery();
                    // Open connection for sending data
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();
                    conn.connect();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    int response_code = conn.getResponseCode();
                    // Check if successful connection made
                    if (response_code == HttpURLConnection.HTTP_OK) {
                        // Read data sent from server
                        InputStream input = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                        // Pass data to onPostExecute method
                    } else {
                        return;
                    }

                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    conn.disconnect();
                }

            }


        };
        Thread myThread = new Thread();
        myThread.start();
    }

    private void getData(URL url, String userid) {
        // URL of object to be parsed
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("userid", userid);
        String URL = builder.build().getEncodedQuery();
        JsonURL=URL;
        Runnable r = new Runnable() {

            @Override
            public void run() {
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
                                    }
                                    // Adds the data string to the TextView "results"
                                    results.setText(data);
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

            }
        };
        Thread myThread = new Thread();
        myThread.start();
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
                                if(i==0) {
                                    tempost = (TextView) findViewById(R.id.Button01);
                                    tempost.setText(Header);
                                }
                                if(i==1) {
                                    tempost = (TextView) findViewById(R.id.Button02);
                                    tempost.setText(Header);
                                }

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


package com.circlesuite.httpwww.cs;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
// stuff needed for storing
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import android.os.Environment;
// stuff needed for retrival
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import android.os.Handler;
// fist two links show how to get data from the server
//http://www.softwarepassion.com/android-series-get-post-and-multipart-post-requests/
//https://www.androidcode.ninja/android-http-client/
// shows how to get data from the server
//https://stackoverflow.com/questions/16545378/how-can-i-fetch-data-from-a-web-server-in-an-android-application
public class MainActivity extends AppCompatActivity {
    // For any code you want running in the main thread after user action ( ie changing the text of button after user click)
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private void postText(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try{
                    // url where the data will be posted
                    String postReceiverUrl = "http://yourdomain.com/post_data_receiver.php";
                    Log.v(TAG, "postURL: " + postReceiverUrl);
                    // HttpClient
                    HttpClient httpClient = new DefaultHttpClient();
                    // post header
                    HttpPost httpPost = new HttpPost(postReceiverUrl);
                    // add your data
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                    nameValuePairs.add(new BasicNameValuePair("firstname", "Mike"));
                    nameValuePairs.add(new BasicNameValuePair("lastname", "Dalisay"));
                    nameValuePairs.add(new BasicNameValuePair("email", "mike@testmail.com"));
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    // execute HTTP post request
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        String responseStr = EntityUtils.toString(resEntity).trim();
                        Log.v(TAG, "Response: " +  responseStr);
                        // you can add an if statement here and do other actions based on the response
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread myThread = new Thread();
        myThread.start();
    }
    // will post our text file
    private void postFile(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try{
                    // the file to be posted
                    String textFile = Environment.getExternalStorageDirectory() + "/sample.txt";
                    Log.v(TAG, "textFile: " + textFile);
                    // the URL where the file will be posted
                    String postReceiverUrl = "http://yourdomain.com/post_data_receiver.php";
                    Log.v(TAG, "postURL: " + postReceiverUrl);
                    // new HttpClient
                    HttpClient httpClient = new DefaultHttpClient();
                    // post header
                    HttpPost httpPost = new HttpPost(postReceiverUrl);
                    File file = new File(textFile);
                    FileBody fileBody = new FileBody(file);
                    MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
                    reqEntity.addPart("file", fileBody);
                    httpPost.setEntity(reqEntity);
                    // execute HTTP post request
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        String responseStr = EntityUtils.toString(resEntity).trim();
                        Log.v(TAG, "Response: " +  responseStr);
                        // you can add an if statement here and do other actions based on the response
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread myThread = new Thread();
        myThread.start();
    }
    // retriving data
    private void getData(String url, String method,
                         List<NameValuePair> params) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                // Response from the HTTP Request
                static InputStream httpResponseStream = null;
                // JSON Response String to create JSON Object
                static String jsonString = "";
                try {
                    // get a Http client
                    DefaultHttpClient httpClient = new DefaultHttpClient();

                    // If required HTTP method is POST
                    if (method == "POST") {
                        // Create a Http POST object
                        HttpPost httpPost = new HttpPost(url);
                        // Encode the passed parameters into the Http request
                        httpPost.setEntity(new UrlEncodedFormEntity(params));
                        // Execute the request and fetch Http response
                        HttpResponse httpResponse = httpClient.execute(httpPost);
                        // Extract the result from the response
                        HttpEntity httpEntity = httpResponse.getEntity();
                        // Open the result as an input stream for parsing
                        httpResponseStream = httpEntity.getContent();
                    }
                    // Else if it is GET
                    else if (method == "GET") {
                        // Format the parameters correctly for HTTP transmission
                        String paramString = URLEncodedUtils.format(params, "utf-8");
                        // Add parameters to url in GET format
                        url += "?" + paramString;
                        // Execute the request
                        HttpGet httpGet = new HttpGet(url);
                        // Execute the request and fetch Http response
                        HttpResponse httpResponse = httpClient.execute(httpGet);
                        // Extract the result from the response
                        HttpEntity httpEntity = httpResponse.getEntity();
                        // Open the result as an input stream for parsing
                        httpResponseStream = httpEntity.getContent();
                    }
                    // Catch Possible Exceptions
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    // Create buffered reader for the httpResponceStream
                    BufferedReader httpResponseReader = new BufferedReader(new InputStreamReader(httpResponseStream, "iso-8859-1"), 8);
                    // String to hold current line from httpResponseReader
                    String line = null;
                    // Clear jsonString
                    jsonString = "";
                    // While there is still more response to read
                    while ((line = httpResponseReader.readLine()) != null) {
                        // Add line to jsonString
                        jsonString += (line + "\n");
                    }
                    // Close Response Stream
                    httpResponseStream.close();
                } catch (Exception e) {
                    Log.e("Buffer Error", "Error converting result " + e.toString());
                }

                try {
                    // Create jsonObject from the jsonString and return it
                    return new JSONObject(jsonString);
                } catch (JSONException e) {
                    Log.e("JSON Parser", "Error parsing data " + e.toString());
                    // Return null if in error
                    return null;
                }





        Thread myThread = new Thread();
        myThread.start();
            };
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

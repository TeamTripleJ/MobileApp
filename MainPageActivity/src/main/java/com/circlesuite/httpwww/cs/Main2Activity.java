package com.circlesuite.httpwww.tempspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    EditText mEdit;
    TextView mText;
    String Header;
    String Content;
    String ScheduleTime;
    String PictureVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                mEdit   = (EditText)findViewById(R.id.editText3);
                Header=mEdit.getText().toString();
                mEdit   = (EditText)findViewById(R.id.editText4);
                Content =mEdit.getText().toString();
                mEdit   = (EditText)findViewById(R.id.editText5);
                ScheduleTime =mEdit.getText().toString();
                mEdit   = (EditText)findViewById(R.id.editText7);
                PictureVideo =mEdit.getText().toString();

                //mText = (TextView)findViewById(R.id.textView3);
               // mText.setText("Welcome "+mEdit.getText().toString()+"!");
            }
        });
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
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", "1");
                params.put("timezone", "1");
                params.put("words", Content);
                params.put("header", Header);
                params.put("photo_id", "1");
                params.put("Time", "'2017-10-15 23:23:23'");
                params.put("network_type", "'facebook'");
                return params;
            }
        };
        queue.add(postRequest);
    }
}

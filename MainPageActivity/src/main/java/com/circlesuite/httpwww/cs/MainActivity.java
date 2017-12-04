package com.circlesuite.httpwww.tempspinner;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    Toolbar myToolbar;
    Spinner mySpinner;
    int count =0;
    TextView results;
    TextView tempost;
    Button btn ;
    Button sch;
    Button refre;
    String username;
    String password;
    String name;
    TextView firstname;
    JSONArray myArray;
    int LFV=0;// the last feed value
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle data = getIntent().getExtras();
        if(data!=null)
        {
            username=data.getString("username");
            password=data.getString("password");
            name=data.getString("name");
            firstname   = (TextView) findViewById(R.id.Name);
            firstname.setText(name);
        }


                myToolbar = (Toolbar) findViewById(R.id.toolbar);
                mySpinner = (Spinner) findViewById(R.id.spinner);
                btn = (Button) findViewById(R.id.NewPost);
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                    }
                });
                sch = (Button) findViewById(R.id.Scheduler);
                sch.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //
                        Intent intent = new Intent(MainActivity.this, Scheduler.class);
                        startActivity(intent);
                    }
                });
                refre = (Button) findViewById(R.id.refresh);
                refre.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                        R.layout.custom_spinner_item,
                        getResources().getStringArray(R.array.names));
                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter(myAdapter);
                mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               /*Toast.makeText(MainActivity.this,
                       mySpinner.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT)
                        .show();*/
                        //System.out.println(mySpinner.getSelectedItem().toString());
                        if (new String("Account Settings").equals(mySpinner.getSelectedItem().toString()) && count != 0) {
                            //Intent i = new Intent(this, Main2Activity.class);
                            Intent intent = new Intent(MainActivity.this, ManageAcc.class);
                            startActivity(intent);
                        } else if (new String("Support").equals(mySpinner.getSelectedItem().toString())) {
                            System.out.println(mySpinner.getSelectedItem().toString());
                            Toast.makeText(MainActivity.this,
                                    "Support button was clicked",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        } else if (new String("Log Out").equals(mySpinner.getSelectedItem().toString())) {
                            System.out.println(mySpinner.getSelectedItem().toString());
                            Toast.makeText(MainActivity.this,
                                    "Log Out button was clicked",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                        count++;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


        //System.out.println("Entering Runnable");
        Runnable r = new Runnable() {
            @Override
            public void run() {
               // System.out.println("Running");
                getDataBasicTest();
                Button button = (Button)findViewById(R.id.button8);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        getOlderPosts();
                    }
                });
                }

        };
        r.run();
    }
    public void getOlderPosts() {
        if (LFV == myArray.length()) {
            LFV=0;
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://aqueous-americans.000webhostapp.com/1Feedlist.php";
            // String url ="http://aqueous-americans.000webhostapp.com/PostList.php";
            //System.out.println("entered get DataBasicTest");
          //  System.out.println("in basic data test");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Log.d("Response", response);
                           // System.out.println("In response");
                            //System.out.println(response);
                            try {
                                Resources res = getResources();
                                Drawable bg = res.getDrawable(R.drawable.fb);
                                myArray = new JSONArray(response);
                                for (int i = 0; i < 5; i++) {
                                    JSONObject myObj = myArray.getJSONObject(i);
                                    String network_type = myObj.getString("network_type");
                                    String T = myObj.getString("T");
                                    String activity = myObj.getString("activity");
                                    String title = myObj.getString("title");
                                    if (network_type.contentEquals("Facebook")) {
                                        bg = res.getDrawable(R.drawable.fb);
                                    } else if (network_type.contentEquals("Twitter")) {
                                        bg = res.getDrawable(R.drawable.t);
                                    } else if (network_type.contentEquals("Linkedin")) {
                                        bg = res.getDrawable(R.drawable.lin);
                                    } else if (network_type.contentEquals("Instagram")) {
                                        bg = res.getDrawable(R.drawable.ig);
                                    }
                                    //System.out.println(Header);
                                    if (i == 0) {
                                        tempost = (TextView) findViewById(R.id.Button01);
                                        tempost.setBackground(bg);
                                        tempost.setText("At " + T + " " + activity + "\n                                    " + title);
                                    }
                                    if (i == 1) {
                                        tempost = (TextView) findViewById(R.id.Button02);
                                        tempost.setBackground(bg);
                                        tempost.setText("At " + T + " " + activity + "\n                                    " + title);
                                    }
                                    if (i == 2) {
                                        tempost = (TextView) findViewById(R.id.Button03);
                                        tempost.setBackground(bg);
                                        tempost.setText("At " + T + " " + activity + "\n                                    " + title);
                                    }
                                    if (i == 3) {
                                        tempost = (TextView) findViewById(R.id.Button07);
                                        tempost.setBackground(bg);
                                        tempost.setText("At " + T + " " + activity + "\n                                    " + title);

                                    }
                                    if (i == 4) {
                                        tempost = (TextView) findViewById(R.id.Button08);
                                        tempost.setBackground(bg);
                                        tempost.setText("At " + T + " " + activity + "\n                                    " + title);

                                    }
                                    LFV = i;
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
            }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("userid", "1");
                    //params.put("post_id","1")
                    return params;
                }
            };
            // Add the request to the RequestQueue.
            //System.out.println("Request Added");
            queue.add(stringRequest);
        }
        else
        {
            try {
                Resources res = getResources();
                Drawable bg = res.getDrawable(R.drawable.fb);
                for (int i = LFV; i < myArray.length()-1; i++) {
                    System.out.println("LFV VALUE:"+LFV+"\n");
                    System.out.println("i:"+i+"\n");
                    JSONObject myObj = myArray.getJSONObject(i);
                    String network_type = myObj.getString("network_type");
                    String T = myObj.getString("T");
                    String activity = myObj.getString("activity");
                    String title = myObj.getString("title");
                    if (network_type.contentEquals("Facebook")) {
                        bg = res.getDrawable(R.drawable.fb);
                    } else if (network_type.contentEquals("Twitter")) {
                        bg = res.getDrawable(R.drawable.t);
                    } else if (network_type.contentEquals("Linkedin")) {
                        bg = res.getDrawable(R.drawable.lin);
                    } else if (network_type.contentEquals("Instagram")) {
                        bg = res.getDrawable(R.drawable.ig);
                    }
                    //System.out.println(Header);
                    if (i == 5) {
                        tempost = (TextView) findViewById(R.id.Button01);
                        tempost.setBackground(bg);
                        tempost.setText("At " + T + " " + activity + "\n                                    " + title);
                    }
                    if (i == 6) {
                        tempost = (TextView) findViewById(R.id.Button02);
                        tempost.setBackground(bg);
                        tempost.setText("At " + T + " " + activity + "\n                                    " + title);
                    }
                    if (i == 7) {
                        tempost = (TextView) findViewById(R.id.Button03);
                        tempost.setBackground(bg);
                        tempost.setText("At " + T + " " + activity + "\n                                    " + title);
                    }
                    if (i == 8) {
                        tempost = (TextView) findViewById(R.id.Button07);
                        tempost.setBackground(bg);
                        tempost.setText("At " + T + " " + activity + "\n                                    " + title);

                    }
                    if (i == 9) {
                        tempost = (TextView) findViewById(R.id.Button08);
                        tempost.setBackground(bg);
                        tempost.setText("At " + T + " " + activity + "\n                                    " + title);

                    }
                    LFV = i;
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void getDataBasicTest(){
        RequestQueue queue = Volley.newRequestQueue(this);
       String url ="http://aqueous-americans.000webhostapp.com/1Feedlist.php";
       // String url ="http://aqueous-americans.000webhostapp.com/PostList.php";
        //System.out.println("entered get DataBasicTest");
        System.out.println("in basic data test");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("Response", response);
                        System.out.println("In response");
                       System.out.println(response);
                        try {
                            Resources res = getResources();
                            Drawable bg= res.getDrawable(R.drawable.fb);
                            myArray = new JSONArray(response);
                            for(int i = 0; i < 5; i++) {
                                JSONObject myObj = myArray.getJSONObject(i);
                                String network_type	 = myObj.getString("network_type");
                                String T= myObj.getString("T");
                                String activity	= myObj.getString("activity");
                                String title = myObj.getString("title");
                                if(network_type.contentEquals("Facebook"))
                                {
                                    bg= res.getDrawable(R.drawable.fb);
                                }
                                else if(network_type.contentEquals("Twitter"))
                                {
                                    bg= res.getDrawable(R.drawable.t);
                                }
                                else if(network_type.contentEquals("Linkedin"))
                                {
                                    bg= res.getDrawable(R.drawable.lin);
                                }
                                else if(network_type.contentEquals("Instagram"))
                                {
                                    bg= res.getDrawable(R.drawable.ig);
                                }
                                //System.out.println(Header);
                                if (i == 0) {
                                    tempost = (TextView) findViewById(R.id.Button01);
                                    tempost.setBackground(bg);
                                    tempost.setText("At "+T+" "+activity+"\n                                    "+ title);
                                }
                                if (i == 1) {
                                    tempost = (TextView) findViewById(R.id.Button02);
                                    tempost.setBackground(bg);
                                    tempost.setText("At "+T+" "+activity+"\n                                    "+ title);
                                }
                                if (i == 2) {
                                    tempost = (TextView) findViewById(R.id.Button03);
                                    tempost.setBackground(bg);
                                    tempost.setText("At "+T+" "+activity+"\n                                    "+ title);
                                }
                                if (i == 3) {
                                    tempost = (TextView) findViewById(R.id.Button07);
                                    tempost.setBackground(bg);
                                    tempost.setText("At "+T+" "+activity+"\n                                    "+ title);

                                }
                                if (i == 4) {
                                    tempost = (TextView) findViewById(R.id.Button08);
                                    tempost.setBackground(bg);
                                    tempost.setText("At "+T+" "+activity+"\n                                    "+ title);

                                }
                                LFV=i;
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
        }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("userid","1");
                //params.put("post_id","1")
                return params;
            }
        };
        // Add the request to the RequestQueue.
        //System.out.println("Request Added");
        queue.add(stringRequest);
    }

}

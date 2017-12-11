package com.circlesuite.httpwww.tempspinner;
//https://www.tutorialspoint.com/android/android_timepicker_control.htm
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ImageButton;
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
    ImageButton btn ;
    ImageButton sch;
    ImageButton refre;
    ImageButton fb ;
    ImageButton lin;
    ImageButton twit;
    ImageButton gram;
    String username;
    String password;
    String name;
    String userID;
    TextView firstname;
    //JSONArray myArray;
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
            userID=data.getString("userID");
            //name=data.getString("name");
            //firstname   = (TextView) findViewById(R.id.Name);
            //firstname.setText(name);
        }


                myToolbar = (Toolbar) findViewById(R.id.toolbar);

                //myToolbar.setNavigationIcon(R.drawable.menu);
                mySpinner = (Spinner) findViewById(R.id.spinner);
                btn = (ImageButton) findViewById(R.id.btnNewPost);
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //
                        Intent intent = new Intent(MainActivity.this, NewEditPost.class);
                        intent.putExtra("userID", userID);
                        intent.putExtra("behavior","new");
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        //intent.putExtra("name", name);
                        startActivity(intent);
                    }
                });
                sch = (ImageButton) findViewById(R.id.btnScheduler);
                sch.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //
                        Intent intent = new Intent(MainActivity.this, NewScheduler.class);
                        intent.putExtra("userID", userID);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        //intent.putExtra("name", name);
                        startActivity(intent);
                    }
                });
                refre = (ImageButton) findViewById(R.id.btnRefresh);
                refre.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        intent.putExtra("userID", userID);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        //intent.putExtra("name", name);
                        startActivity(intent);
                    }
                });
               fb = (ImageButton) findViewById(R.id.imageButton7);
               fb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //

            }
        });
            lin = (ImageButton) findViewById(R.id.imageButton8);
            lin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //

                }
            });
            twit = (ImageButton) findViewById(R.id.imageButton9);
            twit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //

                }
            });
            gram = (ImageButton) findViewById(R.id.imageButton10);
            gram.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //

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
                        //getOlderPosts();
                    }
                });
                }

        };
        r.run();
    }
    public void getOlderPosts(JSONArray myArray) {

    }
    public void fillSchedule(JSONArray myArray){
        //TODO: uncomment this, try this out to prevent excessive memory use
        //LinearLayout scheduleList = (LinearLayout) findViewById(R.id.ScheduleContainer);
        int userID= 1;
        int oldDay=-1;
        String newDate="nah";
        View FeedContainer = null;
        if(myArray!=null) {
            System.out.println(myArray.length());
        }else{
            System.out.println("nah fam its null");
        }
        for(int i = 0; i < myArray.length(); i++) {

            LinearLayout feedlist = (LinearLayout) findViewById(R.id.feedContainer);
            try {
               // System.out.println("value of i  is "+ i+"\n");
               // System.out.println("crash here 1");
                JSONObject PostObj = myArray.getJSONObject(i);
                //grab appropriate content form the query result for the post to be displayed
                //first, get values for time
               // System.out.println("crash here 2");
                newDate=PostObj.getString("T");
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
                String postHeader=PostObj.getString("title");
                String postBody=PostObj.getString("activity");
                String network_type=PostObj.getString("network_type");
                //TODO: get media type for post
                //trim the text of the content if it's too long to display
                //if this post is on a separate day from the previous post in the scheduler,
                // make a new day containerr
                //get all the text objects and views of the new post element
             //   System.out.println("crash here 3");
                FeedContainer = LayoutInflater.from(this).inflate(R.layout.feed_container, feedlist, false);
                //System.out.println("crash here 3.1");
                LinearLayout post=(LinearLayout) FeedContainer.findViewById(R.id.PostTextContainer);
                FrameLayout postBG = (FrameLayout) FeedContainer.findViewById(R.id.PostBackground);
                TextView HeaderText = (TextView) FeedContainer.findViewById(R.id.postHeader);
                TextView BodyText = (TextView) FeedContainer.findViewById(R.id.postBody);
                ImageView NetworkIcon = (ImageView) FeedContainer.findViewById(R.id.postNetworkIcon);
               // System.out.println("crash here 3.2");
               if (network_type.contentEquals("Facebook")) {
                   NetworkIcon.setImageResource(R.drawable.fb1);
                  //System.out.println("crash here 3.2.1");
                } else if (network_type.contentEquals("Twitter")) {
                  // System.out.println("crash here 3.2.2");
                    NetworkIcon.setImageResource(R.drawable.twitter);
                } else if (network_type.contentEquals("Linkedin")) {
                   //System.out.println("crash here 3.2.3");
                    NetworkIcon.setImageResource(R.drawable.in);
                } else if (network_type.contentEquals("Instagram")) {
                  // System.out.println("crash here 3.2.4");
                    NetworkIcon.setImageResource(R.drawable.gram);
                }
                //now SET THE CONTENT OF THE POST CONTAINER
               // System.out.println("crash here 3.3");
                HeaderText.setText(postHeader);
                //System.out.println("crash here 3.4");
                BodyText.setText(postBody);
               // System.out.println("crash here 4");
                feedlist.addView(FeedContainer);
               // System.out.println("crash here 5");
                //if(postNetwork=="facebook"){
                //    post.setBackgroundColor();
                //}else if(postNetwork=="twitter"){
                //
                //}
                //post ID is saved to tag, so that if it is clicked, the postID if clicked element is
                //sent to the edit post along with the intent- this is queried by a method in Edit Post to get
                //that post's data and display it, so it can be edited
                //post.setTag(PostObj.getString("PostID"));
                //oldDay=postDay;
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        System.out.println("schedule was filled");
    }
    public void getDataBasicTest( ){
        RequestQueue queue = Volley.newRequestQueue(this);
       String url ="http://cse120.xyz/1Feedlist.php";
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
                            System.out.println(response);
                            JSONArray myArray = new JSONArray(response);
                            fillSchedule(myArray);

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

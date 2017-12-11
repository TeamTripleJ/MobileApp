package com.circlesuite.httpwww.tempspinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NewScheduler extends AppCompatActivity {
    Toolbar myToolbar;
    Spinner mySpinner;
    int count = 0;
    TextView results;
    TextView tempost;
    ImageButton btn;
    ImageButton sch;
    ImageButton refre;
    ImageButton fb;
    ImageButton lin;
    ImageButton twit;
    ImageButton gram;
    String username;
    String password;
    String name;
    TextView firstname;
    //JSONArray myArray;
    int LFV = 0;// the last feed value

    public int q = 0;
    String data;
    int numberposts = 0;
    TextView ATextView;
    RequestQueue requestQueue;
    String JsonURL = "this text should be overridden";
    boolean rState = false;
    String test = "asdasdasd";
    Boolean ActivityInitialStart = true;

    Button NewPostButton;
    Button HomeButton;

    int result[];
    //0 is day
    //1 is month
    //2 is year


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newscheduler);

        Intent intent = getIntent();

        final String username = intent.getExtras().getString("username");
        final String behavior = intent.getExtras().getString("behavior");
        final String password = intent.getExtras().getString("password");
        final String userID = intent.getExtras().getString("userID");

        System.out.println("successfully got intents");


        myToolbar = (Toolbar) findViewById(R.id.toolbar);

        //myToolbar.setNavigationIcon(R.drawable.menu);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        btn = (ImageButton) findViewById(R.id.btnHome);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //
                Intent intent = new Intent(NewScheduler.this, MainActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
        sch = (ImageButton) findViewById(R.id.btnNewPost);
        sch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //
                Intent intent = new Intent(NewScheduler.this, NewEditPost.class);
                intent.putExtra("userID", userID);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                intent.putExtra("behavior", "new");
                startActivity(intent);
            }
        });
        refre = (ImageButton) findViewById(R.id.btnRefresh);
        refre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //
                Intent intent = new Intent(NewScheduler.this, NewScheduler.class);
                intent.putExtra("userID", userID);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                intent.putExtra("behavior", behavior);
                startActivity(intent);
            }
        });

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(NewScheduler.this,
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
                    Intent intent = new Intent(NewScheduler.this, ManageAcc.class);
                    startActivity(intent);
                } else if (new String("Support").equals(mySpinner.getSelectedItem().toString())) {
                    System.out.println(mySpinner.getSelectedItem().toString());
                    Toast.makeText(NewScheduler.this,
                            "Support button was clicked",
                            Toast.LENGTH_SHORT)
                            .show();
                } else if (new String("Log Out").equals(mySpinner.getSelectedItem().toString())) {
                    System.out.println(mySpinner.getSelectedItem().toString());
                    Toast.makeText(NewScheduler.this,
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


        Button PrevMonth = (Button) findViewById(R.id.btnPrevMonth);
        Button NextMonth = (Button) findViewById(R.id.btnNextMonth);


        result = getCurrentDateTime();
        requestQueue = Volley.newRequestQueue(this);

        System.out.println(getMonth(result[1], false));


        //if user presses button to see PREV month
        /*PrevMonth.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //if month displayed is january, make month 12 and deincrement year on button press
                if (result[1] == 1) {
                    result[1] = 12;
                    result[2]--;
                } else {
                    result[1]--;
                }
                LinearLayout scheduleList = (LinearLayout) findViewById(R.id.ScheduleContainer);
                scheduleList.removeAllViews();
                System.out.println(result[1]);
                System.out.println("prev was pressed");
                getPosts(userID);

            }
        });*/


        //if user presses button to see NEXT month
        /*NextMonth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(result[1]);
                if (result[1] == 12) {
                    result[1] = 1;
                    result[2]++;
                } else {
                    result[1]++;
                }
                LinearLayout scheduleList = (LinearLayout) findViewById(R.id.ScheduleContainer);
                scheduleList.removeAllViews();
                getPosts(userID);
                //UpdatePost();
            }
        });*/


        //System.out.println("req queue being set");
        //requestQueue = Volley.newRequestQueue(this);
        //System.out.println("REQ QUEUE SET");

        if (ActivityInitialStart == true) {
            ActivityInitialStart = false;
            result[1]=10;
            getPosts(userID);
        }

    }


    public void fillSchedule(JSONArray PostArray, final String userID) {
        LinearLayout scheduleList = (LinearLayout) findViewById(R.id.ScheduleContainer);
        scheduleList.removeAllViews();

        int oldDay = -1;
        String newDate = "nah";
        View DayContainer = null;

        TextView ScheduleMonth = (TextView) findViewById(R.id.MonthTitle);
        ScheduleMonth.setText(getMonth(result[1], false));

        if (PostArray == null) {
            System.out.println("nah fam its null");
        } else {
            System.out.println(PostArray.length());


            for (int i = 0; i < PostArray.length(); i++) {
                try {

                    final JSONObject PostObj = PostArray.getJSONObject(i);

                    //grab appropriate content form the query result for the post to be displayed
                    //first, get values for time
                    newDate = PostObj.getString("Time");
                    int postMonth = Integer.parseInt(newDate.substring(5, 7));
                    int postDay = Integer.parseInt(newDate.substring(8, 10));
                    int postHour = Integer.parseInt(newDate.substring(11, 13));
                    int postMin = Integer.parseInt(newDate.substring(14, 16));
                    String AMorPM = "not set";
                    if (postHour >= 12) {
                        AMorPM = "PM";
                        postHour = postHour - 12;
                    } else {
                        AMorPM = "AM";
                    }
                    String postHeader = PostObj.getString("Header");
                    String postBody = PostObj.getString("words");
                    String postNetwork = PostObj.getString("network_type");

                    //TODO: get media type for post

                    //trim the text of the content if it's too long to display


                    //if this post is on a separate day from the previous post in the scheduler,
                    // make a new day container to hold the next posts.
                    if (postDay != oldDay) {


                        DayContainer = LayoutInflater.from(this).inflate(R.layout.day_container, scheduleList, false);
                        scheduleList.addView(DayContainer);

                        TextView textDay = (TextView) DayContainer.findViewById(R.id.Day);
                        TextView textMonth = (TextView) DayContainer.findViewById(R.id.Month);
                        System.out.println(postDay);
                        textDay.setText(Integer.toString(postDay));
                        textMonth.setText(getMonth(postMonth, true));  //only first 3 letters of month are displayed in day container


                        //check if the day container is for the current day. If so, it will be highlighted.
                        if (postMonth == result[1] && postDay == result[0]) {
                            //DayContainer.setBackgroundColor();
                        }
                    }


                    //specifies what layout post elements should be added to,
                    // then adds a post to the current day container
                    LinearLayout postList = (LinearLayout) DayContainer.findViewById(R.id.PostCol);
                    View PostContainer = LayoutInflater.from(this).inflate(R.layout.post_container, postList, false);
                    postList.addView(PostContainer);

                    //get all the text objects and views of the new post element
                    LinearLayout post = (LinearLayout) PostContainer.findViewById(R.id.PostTextContainer);
                    FrameLayout postBG = (FrameLayout) PostContainer.findViewById(R.id.PostBackground);
                    TextView HeaderText = (TextView) PostContainer.findViewById(R.id.postHeader);
                    TextView BodyText = (TextView) PostContainer.findViewById(R.id.postBody);
                    TextView PostTime = (TextView) PostContainer.findViewById(R.id.postTime);
                    ImageView NetworkIcon = (ImageView) PostContainer.findViewById(R.id.postNetworkIcon);
                    ImageView MediaTypeIcon = (ImageView) PostContainer.findViewById(R.id.postContentTypeIcon);

                    //now SET THE CONTENT OF THE POST CONTAINER
                    HeaderText.setText(postHeader);
                    BodyText.setText(postBody);
                    PostTime.setText(postHour + ":" + postMin + " " + AMorPM);

                    //if(postNetwork=="facebook"){
                    //    post.setBackgroundColor();
                    //}else if(postNetwork=="twitter"){
                    //
                    //}


                    //if user clicks on a post, it will send them to the EditPost page with the
                    // relevant post info filled in. They can then update that information and resubmit it.
                    post.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                            Intent intent = new Intent(NewScheduler.this, NewEditPost.class);
                            intent.putExtra("behavior", "edit");
                            intent.putExtra("userID", userID);
                            intent.putExtra("username", username);
                            intent.putExtra("password", password);
                            intent.putExtra("Post", PostObj.toString());
                            startActivity(intent);

                        }
                    });

                    oldDay = postDay;


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            System.out.println("schedule was filled");
        }
    }


    //public void getPosts(int[] date, int userID){
    public void getPosts(final String userID) {
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="http://aqueous-americans.000webhostapp.com/1NewPostList.php";
        String url = "http://cse120.xyz/1NewPostList.php";
        //String url ="http://aqueous-americans.000webhostapp.com/1InsertPost.php"
        //final int[]input = date;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        System.out.println(response);

                        if (response.contentEquals(" ]")) {

                        } else {
                            try {

                                JSONArray myArray = new JSONArray(response);

                                if (myArray.length() != 0) {
                                    for (int i = 0; i < myArray.length(); i++) {
                                        JSONObject myObj = myArray.getJSONObject(i);
                                        //test += myObj.getString("Header");
                                    }
                                } else {
                                    myArray = null;
                                }
                                fillSchedule(myArray, userID);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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
                params.put("userid", "" + userID + "");
                params.put("Time", "'" + result[2] + "-" + result[1] + "%'");

                params.put("action", "2");
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //return results;


        //return PostArray;
    }

    public void InsertPost() {
        RequestQueue queue = Volley.newRequestQueue(this);

        //String url ="http://aqueous-americans.000webhostapp.com/CreatePost.php";
        String url = "http://aqueous-americans.000webhostapp.com/1InsertPost.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        //Log.d("Error.Response", response);
                        System.out.println("error occured with update post method");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
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

    public void UpdatePost() {
        RequestQueue queue = Volley.newRequestQueue(this);

        //String url ="http://aqueous-americans.000webhostapp.com/CreatePost.php";
        String url = "http://cse120.xyz/1UpdatingPost1.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        //Log.d("Error.Response", response);
                        System.out.println("error occured with update post method");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("caption", "'1'");
                params.put("timezone", "'P'");
                params.put("words", "'JAVA'");
                params.put("header", "'4REALSKILL'");
                params.put("photo_id", "1");
                params.put("Time", "'2017-10-15 23:23:23'");
                params.put("network_type", "'facebook'");
                params.put("postid", "1");
                return params;
            }
        };
        queue.add(postRequest);
    }


    public int[] getCurrentDateTime() {
        int[] result = new int[3];

        Calendar cal = Calendar.getInstance();
        result[0] = cal.get(Calendar.DAY_OF_MONTH);
        result[1] = cal.get(Calendar.MONTH) + 1;
        result[2] = cal.get(Calendar.YEAR);

        return result;
    }

    public String getMonth(int month, boolean shorten) {
        String result;
        switch (month) {
            case 1:
                result = "JANUARY";
                break;
            case 2:
                result = "FEBRUARY";
                break;
            case 3:
                result = "MARCH";
                break;
            case 4:
                result = "APRIL";
                break;
            case 5:
                result = "MAY ";
                break;
            case 6:
                result = "JUNE";
                break;
            case 7:
                result = "JULY";
                break;
            case 8:
                result = "AUGUST";
                break;
            case 9:
                result = "SEPTEMBER";
                break;
            case 10:
                result = "OCTOBER";
                break;
            case 11:
                result = "NOVEMBER";
                break;
            case 12:
                result = "DECEMBER";
                break;
            default:
                result = "ZZZ";
                break;
        }
        if (shorten == true) {
            result = result.substring(0, 3);
        }

        return result;
    }
}

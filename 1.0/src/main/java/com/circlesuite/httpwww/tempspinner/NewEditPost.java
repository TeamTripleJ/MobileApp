package com.circlesuite.httpwww.tempspinner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class NewEditPost extends AppCompatActivity {
    public static final int PICK_IMAGE=1;
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

    String name;
    TextView firstname;
    //JSONArray myArray;
    int LFV=0;// the last feed value
    @Override

    //TODO: Have a "Are you sure you want to leave? dialog box if user presses a button and any fields are full
    //TODO: Network Selection
    //TODO: Better, user-friendlier date picker
    //TODO: Inserting a new post
    //TODO: Set button to blue



    //inserting post, uploading pictures

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neweditpost);

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
                        Intent intent = new Intent(NewEditPost.this, MainActivity.class);
                        intent.putExtra("userID", userID);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        startActivity(intent);
                    }
                });
                sch = (ImageButton) findViewById(R.id.btnScheduler);
                sch.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //
                        Intent intent = new Intent(NewEditPost.this, NewScheduler.class);
                        intent.putExtra("userID", userID);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        startActivity(intent);
                    }
                });
                refre = (ImageButton) findViewById(R.id.btnRefresh);
                refre.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //
                        Intent intent = new Intent(NewEditPost.this, NewEditPost.class);
                        intent.putExtra("userID", userID);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        intent.putExtra("behavior", behavior);
                        startActivity(intent);
                    }
                });

                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(NewEditPost.this,
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
                            Intent intent = new Intent(NewEditPost.this, ManageAcc.class);
                            startActivity(intent);
                        } else if (new String("Support").equals(mySpinner.getSelectedItem().toString())) {
                            System.out.println(mySpinner.getSelectedItem().toString());
                            Toast.makeText(NewEditPost.this,
                                    "Support button was clicked",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        } else if (new String("Log Out").equals(mySpinner.getSelectedItem().toString())) {
                            System.out.println(mySpinner.getSelectedItem().toString());
                            Toast.makeText(NewEditPost.this,
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

        final EditText Header = (EditText) findViewById(R.id.HeaderField);
        final EditText Words = (EditText) findViewById(R.id.WordsField);
        final EditText Time = (EditText) findViewById(R.id.DateField);
        final Button Submit = (Button) findViewById(R.id.SubmitPostButton);

        //Behavior will either be New Post or Edit Post.
        //If this screen is clicked from the 'new post' button, it will be set to "New Post", and the
        //text fields will be empty.
        //However, if this screen is reached from the Scheduler, then it will be "edit"

        TextView ScreenHeader = (TextView) findViewById(R.id.NewOrEditScreenHeader);

        if (behavior.equals("edit")) {
            Submit.setText("Update Post");
            ScreenHeader.setText("Edit Post");
            try {
                JSONObject post = new JSONObject(intent.getStringExtra("Post"));
                Header.setText(post.getString("Header"));
                Words.setText(post.getString("words"));
                Time.setText(post.getString("Time"));

                final String SelectedPostID= (post.getString("post_id"));

                Submit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                            UpdatePost(SelectedPostID, Words.getText().toString(), Header.getText().toString(), Time.getText().toString(), "facebook", userID, username, password);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Submit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    InsertPost(Words.getText().toString(), Header.getText().toString(), Time.getText().toString(), "facebook", userID, username, password);
                }
            });
        }

        ImageButton PostImageSelect = (ImageButton) findViewById(R.id.btnPostImageSelect);
        PostImageSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent imagepick = new Intent();
                imagepick.setType("image/*");
                imagepick.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(imagepick, "Select Picture"), PICK_IMAGE);
            }
        });
    }


    //function for getting image from phone and loading onto the image button,
    //as well as storing it as a bitmap
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            try {
                Uri img = data.getData();
                InputStream inputStream = getContentResolver().openInputStream(img);
                Bitmap imgBitmap = BitmapFactory.decodeStream(inputStream);

                ImageButton PostImageSelect = (ImageButton) findViewById(R.id.btnPostImageSelect);
                PostImageSelect.setImageURI(img);
                PostImageSelect.setImageBitmap(imgBitmap);
            }catch (Exception e){
                Toast.makeText(this, "Error selecting picture", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    public void UpdatePost(final String selectedPostID, final String Words, final String Header, final String Time , final String NetworkType, final String uID, final String uName, final String pw) {
        RequestQueue queue = Volley.newRequestQueue(this);

//        if ((Header.getText().toString()).isEmpty() || (Time.getText().toString()).isEmpty() || (Words.getText().toString()).isEmpty()) {
        //          System.out.println("Not all fields are full");
        //    }else{
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
                //params.put("words", "'"+Words.getText().toString()+"'");
                //params.put("header", "'"+Header.getText().toString()+"'");
                //params.put("photo_id", "1");
                //params.put("Time", "'"+Time.getText().toString()+"'");

                params.put("words", "'"+Words+"'");
                params.put("header", "'"+Header+"'");
                params.put("photo_id", "1");
                params.put("Time", "'"+Time+"'");

                params.put("network_type", "'"+NetworkType+"'");
                params.put("postid", ""+selectedPostID+"");
                return params;
            }
        };
        queue.add(postRequest);

        Toast.makeText(getApplicationContext(),"Post updated successfully!\n Post scheduled for "+Time, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(NewEditPost.this, NewScheduler.class);
        intent.putExtra("userID", uID);
        intent.putExtra("username", uName);
        intent.putExtra("password", pw);
        startActivity(intent);

        //}
    }


    public void InsertPost(final String Words, final String Header, final String Time , final String NetworkType, final String uID, final String uName, final String pw){
        RequestQueue queue = Volley.newRequestQueue(this);

        //String url ="http://aqueous-americans.000webhostapp.com/CreatePost.php";
        String url ="http://cse120.xyz/1InsertPost.php";


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
                params.put("userid", ""+uID+"");
                params.put("timezone", "1");
                params.put("words", "'"+Words+"'");
                params.put("header", "'"+Header+"'");
                params.put("photo_id", "1");
                params.put("Time", "'"+Time+"'");
                params.put("network_type", "'facebook'");
                return params;
            }
        };
        queue.add(postRequest);
        Toast.makeText(getApplicationContext(),"New post scheduled for "+Time, Toast.LENGTH_LONG).show();
    }

    //Functions for converting image files for transfer to and from server

    private String ImgToString(Bitmap img){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bArray = baos.toByteArray();
        return Base64.encodeToString(bArray, Base64.DEFAULT);
    }

    private Bitmap StringToImg(String input){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imgString = Base64.decode(input.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imgString, 0, imgString.length);

    }

}




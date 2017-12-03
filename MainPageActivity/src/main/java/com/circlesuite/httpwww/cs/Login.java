package com.circlesuite.httpwww.tempspinner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
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
public class Login extends AppCompatActivity {
    Button login ;
    EditText pw;
    EditText user;
    String username;
    String name;
    String password;
    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //
                System.out.println("Button Clicked");
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        user   = (EditText)findViewById(R.id.editText);
                        username =user.getText().toString();
                        pw  = (EditText)findViewById(R.id.editText6);
                        password=pw.getText().toString();
                        // System.out.println("Running");
                            //System.out.println("Loging In");
                            getDataBasicTest();
                            if(name!=null)
                            {
                                intent.putExtra("username", username);
                                intent.putExtra("password", password);
                                intent.putExtra("name", name);
                               // System.out.println(name);
                                startActivity(intent);
                            }
                            else
                            {
                                res  = (TextView)findViewById(R.id.textView7);
                                res.setText("Login Attempt Failed");

                            }

                    }

                };
                r.run();


            }

        });
    }
    public void getDataBasicTest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://aqueous-americans.000webhostapp.com/Login.php";
        // String url ="http://aqueous-americans.000webhostapp.com/PostList.php";
        //System.out.println("entered get DataBasicTest");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("Response", response);
                        System.out.println(response);
                        try {
                            if(response!=null) {
                                JSONObject myObj = new JSONObject(response);
                               // System.out.println(response);
                                name = myObj.getString("fullname");
                              //  System.out.println(myObj.getString("fullname"));
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
                params.put("username",username);
                //params.put("post_id","1");
                params.put("password", password);

                return params;
            }
        };
        // Add the request to the RequestQueue.
        //System.out.println("Request Added");
        queue.add(stringRequest);
    }

}
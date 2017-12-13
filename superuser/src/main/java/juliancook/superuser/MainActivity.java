package juliancook.superuser;


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

    TextView tempost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                 System.out.println("Running");
                getDataBasicTest();
            }

        };

        r.run();

    }

    public void getDataBasicTest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://cse120.xyz/1SuperUser.php";
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
                            JSONArray myArray = new JSONArray(response);
                            for (int i = 0; i < myArray.length(); i++) {

                                JSONObject myObj = myArray.getJSONObject(i);


                                String user_id = myObj.getString("user_id");
                                String fullname = myObj.getString("fullname");
                                String timezone = myObj.getString("timezone");

                                if (i == 0) {
                                    tempost = (TextView) findViewById(R.id.user1);
                                    tempost.setText(user_id + "\t\t\t" + fullname + "\t\t\tTimezone: " + timezone);
                                }
                                if (i == 1) {
                                    tempost = (TextView) findViewById(R.id.user2);
                                    tempost.setText(user_id + "\t\t\t" + fullname + "\t\t\tTimezone: " + timezone);
                                }
                                if (i == 2) {
                                    tempost = (TextView) findViewById(R.id.user3);
                                    tempost.setText(user_id + "\t\t\t" + fullname + "\t\t\tTimezone: " + timezone);
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
}
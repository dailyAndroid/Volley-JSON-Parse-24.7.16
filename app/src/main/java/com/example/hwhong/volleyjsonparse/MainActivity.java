package com.example.hwhong.volleyjsonparse;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView one, two, three, four, five, six, seven, eight, nine, ten;
    Button button;
    RequestQueue queue;
    TextView[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        four = (TextView) findViewById(R.id.four);
        five = (TextView) findViewById(R.id.five);
        six = (TextView) findViewById(R.id.six);
        seven = (TextView) findViewById(R.id.seven);
        eight = (TextView) findViewById(R.id.eight);
        nine = (TextView) findViewById(R.id.nine);
        ten = (TextView) findViewById(R.id.ten);

        array = new TextView[10];
        array[0] = one;
        array[1] = two;
        array[2] = three;
        array[3] = four;
        array[4] = five;
        array[5] = six;
        array[6] = seven;
        array[7] = eight;
        array[8] = nine;
        array[9] = ten;

        button = (Button) findViewById(R.id.button);
        queue = Volley.newRequestQueue(getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, "http://www.androidbegin.com/tutorial/jsonparsetutorial.txt",
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("worldpopulation");

                                    for(int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);

                                        int rank = object.getInt("rank");
                                        String country = object.getString("country");
                                        String population = object.getString("population");

                                        array[i].setText("Rank :" + rank + " \n" +
                                                         "Country :" + country + "\n" +
                                                         "Population :" + population);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VOLLEY", "ERROR");
                            }
                        }

                );
                queue.add(objectRequest);
            }
        });
    }
}

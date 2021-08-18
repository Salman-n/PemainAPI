package tech.fpax.daftarpemain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class viewPemainActivity extends AppCompatActivity {
String id;
TextView tv1 , tv2 , tv3;
ImageView iv1  ,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pemain);
        Intent intent = getIntent();
       id = intent.getStringExtra("id");
      // Log.d("TAG","id " + id);

        if (id.equals("")){
            finish();
        }

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        iv1 = (ImageView) findViewById(R.id.iv1);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
finish();
            }
        });
        getPlayer();
    }

    void getPlayer() {
        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        String url ="https://www.thesportsdb.com/api/v1/json/1/lookupplayer.php?id=" + id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject main_obj = new JSONObject(response);
                            JSONArray list = main_obj.getJSONArray("players");

                            for (int i=0; i < list.length(); i++) {
                                JSONObject asede = list.getJSONObject(i);
                                String strMeal = asede.getString("strPlayer");
                                String strMealThumb = asede.getString("strFanart1");
                                String idMeal = asede.getString("idPlayer");
                                String strNationality = asede.getString("strNationality");
                                String desc = asede.getString("strDescriptionEN");
                                tv1.setText(strMeal);
                                tv2.setText("Nationality : " + strNationality);
                                tv3.setText("Description : " + desc);
                                Picasso.get().load(strMealThumb).into(iv1);
                            }

                        }catch (Exception e) {
                            Toast.makeText(viewPemainActivity.this , "Gagal Mengakses Server " + e,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });
        requestQueue.add(stringRequest);

    }
}

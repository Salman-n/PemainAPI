package tech.fpax.daftarpemain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PemainAdapter adapter;
    private ArrayList<Pemain> pemainArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPemain();
    }

    void getPemain() {
        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        String url ="https://www.thesportsdb.com/api/v1/json/1/searchplayers.php?t=Arsenal";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject main_obj = new JSONObject(response);
                            JSONArray list = main_obj.getJSONArray("player");
                            pemainArrayList = new ArrayList<>();
                            for (int i=0; i < list.length(); i++) {
                                JSONObject asede = list.getJSONObject(i);
                                String strMeal = asede.getString("strPlayer");
                                String strMealThumb = asede.getString("strThumb");
                                String idMeal = asede.getString("idPlayer");
                                pemainArrayList.add(new Pemain(strMeal, strMealThumb, idMeal));

                            }





                            setAdapter();
                        }catch (Exception e) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this , "Gagal Mengakses Server ",Toast.LENGTH_LONG).show();
                    }
                });

// Add the request to the RequestQueue.
        requestQueue.add(stringRequest);

    }

    void setAdapter() {
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        adapter = new PemainAdapter(pemainArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(MainActivity.this,2);
        RecyclerView.LayoutManager layoutManager3 = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager3);
        recyclerView.setAdapter(adapter);
    }
}

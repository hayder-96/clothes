package app.myapp.clothesuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.clothesuser.Adapter.AdapterMain;
import app.myapp.clothesuser.Model.ItemMain;
import app.myapp.clothesuser.RegAndLogin.Login;
import app.myapp.clothesuser.Server.ServerApi;
import app.myapp.clothesuser.Server.SqliteBascket;
import app.myapp.clothesuser.Shared.SaveToken;
import app.myapp.clothesuser.Shared.VolleySetting;

public class MainActivity extends AppCompatActivity {


    private RequestQueue requestQueue;
    private String token;
    RecyclerView recyclerView;
    Context context;
    TextView textNoty;
    CardView cardView;
    ImageView imageView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        textNoty=findViewById(R.id.textView_notifaction);
        cardView=findViewById(R.id.card);
        imageView=findViewById(R.id.image_notyy);
        toolbar=findViewById(R.id.toolbar);
      

        requestQueue = Volley.newRequestQueue(this);
        if (SaveToken.getInstanse(this).is_Login()) {

            if (SaveToken.getInstanse(this).getToken() != null) {
                token = SaveToken.getInstanse(this).getToken().getToken();

            }
        } else {
            finish();


            startActivity(new Intent(this, Login.class));
            return;
        }



        setSupportActionBar(toolbar);
        Intent intent=new Intent(this,MyService.class);
        startService(intent);



        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                new Fragment_Home()).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()) {
                    case R.id.home:


                        fragment=new Fragment_Home();

                        break;

                    case R.id.request:

                        fragment=new MenuBascket();

                        break;



                    case R.id.done_request:

                        fragment=new Fragment_Pay();

                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                        fragment).commit();
                return true;
            }
        });









        context=this;



        dataMessageNoty();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getBaseContext(),NotifyActivity.class));


            }
        });


    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_log,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.log:

                SaveToken.getInstanse(getBaseContext()).user_Logout();
                startActivity(new Intent(getBaseContext(),Login.class));
                finish();

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {

        SqliteBascket sqliteBascket=new SqliteBascket(this);
        sqliteBascket.delete();
        super.onDestroy();
    }

    private void dataMessageNoty() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<Integer> arrayList1 =new ArrayList<>();





        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ServerApi.GET_NOTY_YES, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemMain itemMain=new ItemMain();





                        arrayList1.add(screen.getInt("id"));


                    }


                    if (arrayList1.size()!=0){

                        cardView.setVisibility(View.VISIBLE);
                        textNoty.setText(arrayList1.size()+"");
                    }



                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> map = new HashMap<>();
                map.put("Accept", "application/json");
                map.put("Authorization", "Bearer  " + token);
                return map;
            }

        };
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);


    }
}
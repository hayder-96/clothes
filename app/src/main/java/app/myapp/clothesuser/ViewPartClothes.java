package app.myapp.clothesuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import app.myapp.clothesuser.Adapter.AdapterPart;
import app.myapp.clothesuser.Model.ItemPart;
import app.myapp.clothesuser.Server.ServerApi;
import app.myapp.clothesuser.Shared.SaveToken;
import app.myapp.clothesuser.Shared.VolleySetting;

public class ViewPartClothes extends AppCompatActivity {


    RecyclerView recyclerView;
    int id;
    Context context;
    String name;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_part_clothes);

        recyclerView=findViewById(R.id.rec_part);
        toolbar=findViewById(R.id.toolbar_part);

        setSupportActionBar(toolbar);

        context=this;

        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);
        name=intent.getStringExtra("name");



        dataMessage();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_tool,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        switch (item.getItemId()){

            case R.id.price_down:

                dataMessagee();

                break;

            case R.id.price_up:

                dataMessageee();

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void dataMessage() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<ItemPart> arrayList1 =new ArrayList<>();





        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ServerApi.GET_DETAILS_CLOTHES+id, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemPart itemPart=new ItemPart();

                        itemPart.setId(screen.getInt("id"));

                        itemPart.setName(screen.getString("name"));



                        itemPart.setPrice(screen.getString("price"));

                        itemPart.setImage1(screen.getString("image1"));
                        itemPart.setImage2(screen.getString("image2"));
                        itemPart.setImage3(screen.getString("image3"));
                        itemPart.setImage4(screen.getString("image4"));
                        itemPart.setImage5(screen.getString("image5"));

                        itemPart.setNumber1(screen.getString("number1"));
                        itemPart.setNumber2(screen.getString("number2"));
                        itemPart.setNumber3(screen.getString("number3"));
                        itemPart.setNumber4(screen.getString("number4"));
                        itemPart.setNumber5(screen.getString("number5"));
                        itemPart.setNumber6(screen.getString("number6"));


                        itemPart.setColor1(screen.getString("color1"));
                        itemPart.setColor2(screen.getString("color2"));
                        itemPart.setColor3(screen.getString("color3"));
                        itemPart.setColor4(screen.getString("color4"));
                        itemPart.setColor5(screen.getString("color5"));


                        itemPart.setType(screen.getString("type"));


                        arrayList1.add(itemPart);


                    }

                    AdapterPart adapterPart =new AdapterPart(arrayList1,context);

                    recyclerView.setAdapter(adapterPart);
                    recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
                    recyclerView.setHasFixedSize(true);
                    adapterPart.notifyDataSetChanged();



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














    private void dataMessagee() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<ItemPart> arrayList1 =new ArrayList<>();





        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ServerApi.GET_DETAILS_CLOTHES+id, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemPart itemPart=new ItemPart();

                        itemPart.setId(screen.getInt("id"));

                        itemPart.setName(screen.getString("name"));


                        itemPart.setPrice(screen.getString("price"));

                        itemPart.setImage1(screen.getString("image1"));
                        itemPart.setImage2(screen.getString("image2"));
                        itemPart.setImage3(screen.getString("image3"));
                        itemPart.setImage4(screen.getString("image4"));
                        itemPart.setImage5(screen.getString("image5"));

                        itemPart.setNumber1(screen.getString("number1"));
                        itemPart.setNumber2(screen.getString("number2"));
                        itemPart.setNumber3(screen.getString("number3"));
                        itemPart.setNumber4(screen.getString("number4"));
                        itemPart.setNumber5(screen.getString("number5"));
                        itemPart.setNumber6(screen.getString("number6"));


                        itemPart.setColor1(screen.getString("color1"));
                        itemPart.setColor2(screen.getString("color2"));
                        itemPart.setColor3(screen.getString("color3"));
                        itemPart.setColor4(screen.getString("color4"));
                        itemPart.setColor5(screen.getString("color5"));

                        itemPart.setNmore(screen.getString("nummore"));


                        arrayList1.add(itemPart);


                    }


                    Collections.sort(arrayList1, new Comparator<ItemPart>() {
                        @Override
                        public int compare(ItemPart lhs,ItemPart rhs) {

                            return Integer.parseInt(rhs.getPrice()) > Integer.parseInt(lhs.getPrice()) ? -1 : (Integer.parseInt(rhs.getPrice()) < Integer.parseInt(lhs.getPrice()) ) ? 1 : 0;
                        }
                    });




                    AdapterPart adapterPart =new AdapterPart(arrayList1,context,name);

                    recyclerView.setAdapter(adapterPart);
                    recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
                    recyclerView.setHasFixedSize(true);
                    adapterPart.notifyDataSetChanged();







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













    private void dataMessageee() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<ItemPart> arrayList1 =new ArrayList<>();





        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ServerApi.GET_DETAILS_CLOTHES+id, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemPart itemPart=new ItemPart();

                        itemPart.setId(screen.getInt("id"));

                        itemPart.setName(screen.getString("name"));


                        itemPart.setPrice(screen.getString("price"));

                        itemPart.setImage1(screen.getString("image1"));
                        itemPart.setImage2(screen.getString("image2"));
                        itemPart.setImage3(screen.getString("image3"));
                        itemPart.setImage4(screen.getString("image4"));
                        itemPart.setImage5(screen.getString("image5"));

                        itemPart.setNumber1(screen.getString("number1"));
                        itemPart.setNumber2(screen.getString("number2"));
                        itemPart.setNumber3(screen.getString("number3"));
                        itemPart.setNumber4(screen.getString("number4"));
                        itemPart.setNumber5(screen.getString("number5"));
                        itemPart.setNumber6(screen.getString("number6"));


                        itemPart.setColor1(screen.getString("color1"));
                        itemPart.setColor2(screen.getString("color2"));
                        itemPart.setColor3(screen.getString("color3"));
                        itemPart.setColor4(screen.getString("color4"));
                        itemPart.setColor5(screen.getString("color5"));




                        arrayList1.add(itemPart);


                    }


                    Collections.sort(arrayList1, new Comparator<ItemPart>() {
                        @Override
                        public int compare(ItemPart lhs,ItemPart rhs) {

                            return Integer.parseInt(lhs.getPrice()) > Integer.parseInt(rhs.getPrice()) ? -1 : (Integer.parseInt(lhs.getPrice()) < Integer.parseInt(rhs.getPrice()) ) ? 1 : 0;
                        }
                    });




                    AdapterPart adapterPart =new AdapterPart(arrayList1,context,name);

                    recyclerView.setAdapter(adapterPart);
                    recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
                    recyclerView.setHasFixedSize(true);
                    adapterPart.notifyDataSetChanged();







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
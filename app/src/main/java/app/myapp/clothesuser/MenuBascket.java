package app.myapp.clothesuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.clothesuser.Adapter.RecyclerAdapter;
import app.myapp.clothesuser.Model.ItemAccept;
import app.myapp.clothesuser.Server.ServerApi;
import app.myapp.clothesuser.Server.SqliteBascket;
import app.myapp.clothesuser.Shared.SaveToken;
import app.myapp.clothesuser.Shared.VolleySetting;

public class MenuBascket extends Fragment {


    RecyclerView recyclerView;
    EditText name,phone,adress;
    LinearLayout layout;

    static RecyclerAdapter recyclerAdapter;
    static ArrayList<ItemAccept> arrayList;
    SqliteBascket sqliteBascket;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu_bascket);



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.activity_menu_bascket, container, false);



        recyclerView =view.findViewById(R.id.recy_bascket);

        name = view.findViewById(R.id.done_name);
        phone = view.findViewById(R.id.done_phone);
        adress = view.findViewById(R.id.done_addres);
        layout = view.findViewById(R.id.laylay);
        TextView textView=view.findViewById(R.id.textView_bascket);



        sqliteBascket = new SqliteBascket(getActivity());
        arrayList = new ArrayList<>();

        arrayList = sqliteBascket.getData();


        Button b1=view.findViewById(R.id.but_pay);
        Button b2=view.findViewById(R.id.but_finish);
        if (arrayList != null) {
            recyclerAdapter = new RecyclerAdapter(arrayList, getActivity());

            recyclerView.setAdapter(recyclerAdapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            recyclerView.setHasFixedSize(true);
            b1.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }else {

            b1.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);

        }



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout.setVisibility(View.VISIBLE);
            }
        });







        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataSave();
                layout.setVisibility(View.GONE);
            }
        });








        return view;
    }
        public static void noty(){

            recyclerAdapter.notifyDataSetChanged();
        }






















    private void dataSave() {


        String token= SaveToken.getInstanse(getActivity()).getToken().getToken();


        if (TextUtils.isEmpty(name.getText().toString().trim())){

            name.setError("ادخل الاسم");
            name.requestFocus();

            return;
        }

        if (TextUtils.isEmpty(phone.getText().toString().trim())){

            phone.setError("ادخل الرقم");
            phone.requestFocus();

            return;
        }


        if (TextUtils.isEmpty(adress.getText().toString().trim())){

            adress.setError("ادخل العنوان");
            adress.requestFocus();

            return;
        }





        JSONObject js = new JSONObject();
        try {
            js.put("name",name.getText().toString().trim());
            js.put("phone",phone.getText().toString().trim());
            js.put("adress",adress.getText().toString().trim());
            js.put("enable","no");
            js.put("noty","no");

        } catch (Exception e) {
            e.printStackTrace();
        }


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ServerApi.SAVE_ACCEPT, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    if (response.getBoolean("success")) {

                        Toast.makeText(getActivity(), "doneinsert", Toast.LENGTH_SHORT).show();

                        String ac=response.getString("message");

                        Toast.makeText(getActivity(),ac+"message",Toast.LENGTH_SHORT).show();


                        arrayList=sqliteBascket.getData();

                        for(ItemAccept itemAccept:arrayList){


                            dataSavePeice(itemAccept.getName(),itemAccept.getNumber(),itemAccept.getColor(),itemAccept.getImage(),ac,String.valueOf(itemAccept.getClothes_id()),itemAccept.getNumsize(),itemAccept.getPrice());

                            Toast.makeText(getActivity(),itemAccept.getClothes_id()+"c"+itemAccept.getAccept_id()+"a",Toast.LENGTH_SHORT).show();

                        }


                    } else {

                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();

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
                map.put("Authorization", "Bearer " + token);
                return map;
            }

        };
        VolleySetting.getInstance(getActivity()).addRequest(jsonObjectRequest);


    }















    private void dataSavePeice(String namee,String size,String colorr,String image,String accept_id,String clothes_id,String numsize,String price) {


        String token= SaveToken.getInstanse(getActivity()).getToken().getToken();

        JSONObject js = new JSONObject();
        try {
            js.put("name",namee);
            js.put("size",size);
            js.put("color",colorr);
            js.put("image",image);
            js.put("accept_id",accept_id);
            js.put("price",price);
            js.put("clothes_id",clothes_id);
            js.put("numsize",numsize);

        } catch (Exception e) {
            e.printStackTrace();
        }


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ServerApi.SAVE_PEICE, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    if (response.getBoolean("success")) {

                        Toast.makeText(getActivity(), "doneinsert", Toast.LENGTH_SHORT).show();





                    } else {

                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();

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
                map.put("Authorization", "Bearer " + token);
                return map;
            }

        };
        VolleySetting.getInstance(getActivity()).addRequest(jsonObjectRequest);


    }



}
package app.myapp.clothesuser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Pay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Pay extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Pay() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Pay.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Pay newInstance(String param1, String param2) {
        Fragment_Pay fragment = new Fragment_Pay();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment__pay, container, false);


        RecyclerView recyclerView=view.findViewById(R.id.rec_more);

        dataMessage(recyclerView);

        return  view;
    }






















    private void dataMessage(RecyclerView recyclerView) {

        final String token = SaveToken.getInstanse(getActivity()).getToken().getToken();

        ArrayList<ItemPart> arrayList1 =new ArrayList<>();





        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ServerApi.GET_ALL_CLOTHES, null, new Response.Listener<JSONObject>() {


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


                        itemPart.setNmore(screen.getString("nummore"));




                        arrayList1.add(itemPart);



                    }


                    Collections.sort(arrayList1, new Comparator<ItemPart>() {
                        @Override
                        public int compare(ItemPart lhs,ItemPart rhs) {


                                return Integer.parseInt(lhs.getNmore()) > Integer.parseInt(rhs.getNmore()) ? -1 : Integer.parseInt(lhs.getNmore()) < Integer.parseInt(rhs.getNmore()) ? 1 : 0;

                            }


                    });



                    AdapterPart adapterPart =new AdapterPart(arrayList1,getActivity());

                    recyclerView.setAdapter(adapterPart);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
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
        VolleySetting.getInstance(getActivity()).addRequest(jsonObjectRequest);


    }
}
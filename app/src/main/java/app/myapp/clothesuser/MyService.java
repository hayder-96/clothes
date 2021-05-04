package app.myapp.clothesuser;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import app.myapp.clothesuser.Adapter.AdapterMain;
import app.myapp.clothesuser.Model.ItemMain;
import app.myapp.clothesuser.Model.ItemNoty;
import app.myapp.clothesuser.Server.ServerApi;
import app.myapp.clothesuser.Shared.SaveToken;
import app.myapp.clothesuser.Shared.VolleySetting;

public class MyService extends Service {
    public MyService() {
    }





    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){

                    try {
                        Thread.sleep(5000);

                        dataMessage();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }








    public void ShowNotificationn(String name,int id)
    {

        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setContentTitle(name)
                .setContentText("لديك طلب")
                .setVibrate(new long[]{500, 1000, 500, 1000, 500})
                .setSmallIcon(R.drawable.ic_notifications)
                .setDefaults(Notification.DEFAULT_ALL);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntent(intent);
        taskStackBuilder.addParentStack(MainActivity.class);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.addAction(R.drawable.ic_notifications, "مشاهدته", pendingIntent);
        notificationManager.notify(new Random().nextInt(), builder.build());




        upDate(id);
    }







    private void upDate(int id) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("noty","yes");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, ServerApi.GET_NOTY_NO+"/"+id, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {

                    if (response.getBoolean("success")) {
                        Toast.makeText(getBaseContext(), response.getString("message"), Toast.LENGTH_SHORT).show();

                        //progressDialog.dismiss();

                    } else {

                        Toast.makeText(getBaseContext(), "error", Toast.LENGTH_SHORT).show();
                        //  progressDialog.dismiss();
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
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);


    }
















    private void dataMessage() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<ItemNoty> arrayList1 =new ArrayList<>();





        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ServerApi.GET_NOTY_NO, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemNoty itemNoty=new ItemNoty();


                        itemNoty.setId(screen.getInt("id"));
                        itemNoty.setName(screen.getString("name"));
                        itemNoty.setContent(screen.getString("content"));


                        arrayList1.add(itemNoty);


                    }

                    for (ItemNoty itemNoty:arrayList1){

                        ShowNotificationn(itemNoty.getName(),itemNoty.getId());
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
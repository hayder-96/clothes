package app.myapp.clothesuser.RegAndLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.myapp.clothesuser.MainActivity;
import app.myapp.clothesuser.Model.User;
import app.myapp.clothesuser.R;
import app.myapp.clothesuser.Server.ServerApi;
import app.myapp.clothesuser.Shared.SaveToken;
import app.myapp.clothesuser.Shared.VolleySetting;

public class Login extends AppCompatActivity {

    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email=findViewById(R.id.name_log);
        password=findViewById(R.id.password_login);
    }




    public void login(View view) {

        Login();
    }


    public void reg(View view) {
        startActivity(new Intent(getBaseContext(),Rigster.class));
        finish();
    }







    private void Login(){

        final String email1=email.getText().toString().trim();
        final String password1=password.getText().toString().trim();


        if (TextUtils.isEmpty(email1)){
            email.setError("Enter your email");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password1)){
            password.setError("Enter your password");
            password.requestFocus();
            return;
        }


        StringRequest stringRequest=new StringRequest(Request.Method.POST, ServerApi.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("success")) {

                                JSONObject object = jsonObject.getJSONObject("data");
                                SaveToken.getInstanse(getBaseContext()).saveUser(new User(object.getString("token")));
                                startActivity(new Intent(getBaseContext(), MainActivity.class));
                                finish();

                            } else {
                                Toast.makeText(getBaseContext(), "message", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    }

                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }


        }
        )

        {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> map=new HashMap<>();
                map.put("Content-Type","application/json");
                map.put("email",email1);
                map.put("password",password1);

                return map ;
            }
        };
        VolleySetting.getInstance(getBaseContext()).addRequest(stringRequest);

    }
}
package com.example.abhi.newproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class second extends AppCompatActivity {
    Button register;
    EditText username,password1,email1,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        register = (Button) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.username);
        contact = (EditText) findViewById(R.id.contactno);
        password1 = (EditText) findViewById(R.id.password);
        email1 = (EditText) findViewById(R.id.email);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userregistration();
            }
        });

    }
    private void userregistration(){
        final String name=username.getText().toString();
        final String password=password1.getText().toString();
        final String email=email1.getText().toString();
        final String contactno=contact.getText().toString();


        StringRequest srequest=new StringRequest(Request.Method.POST, constant.login_url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj=new JSONObject(response);
                    Toast.makeText(second.this,jobj.getString("message"),Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(second.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", name);
                params.put("email", email);
                params.put("email", contactno);
                params.put("password", password);
                return params;
            }



        };RequestQueue q = Volley.newRequestQueue(second.this);
        q.add(srequest);





    }
}

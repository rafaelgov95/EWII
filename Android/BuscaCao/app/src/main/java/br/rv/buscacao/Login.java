package br.rv.buscacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

import br.rv.buscacao.http.AppController;
import br.rv.buscacao.http.VolleyHttp;

import org.json.JSONArray;
import org.json.JSONException;

import android.view.View;
import android.widget.Button;
import com.android.volley.toolbox.JsonArrayRequest;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Login extends AppCompatActivity {
    @BindView(R.id.login_email) EditText email;
    @BindView(R.id.login_password) EditText password;
    @BindView(R.id.login_view) TextView login_view;
    private String mJSONURLString = "http://httpbin.org/get";




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // Get the application context

       }
    @OnClick(R.id.login_button)
    public void logar() {
        login_view.setText("");

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        login_view.setText(response.toString());

                        // Process the JSON
//                        try{
//                            // Loop through the array elements
//                            for(int i=0;i<response.length();i++){
//                                // Get current json object
//                                JSONObject student = response.getJSONObject(i);
//
//                                // Get the current student (json object) data
//                                String firstName = student.getString("firstname");
//                                String lastName = student.getString("lastname");
//                                String age = student.getString("age");
//
//                                // Display the formatted json data in text view
//                                login_view.append(firstName +" " + lastName +"\nAge : " + age);
//                                login_view.append("\n\n");
//                            }
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        login_view.setText("ERRRO");
                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);

    }


}

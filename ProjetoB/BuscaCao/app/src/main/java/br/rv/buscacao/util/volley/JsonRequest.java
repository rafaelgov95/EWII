package br.rv.buscacao.util.volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rafael on 30/10/17.
 */

public class JsonRequest {
    Map<String, String> map = new HashMap<String, String>();
//        map.put("email", "rafaelgov@gmail.com");
//        map.put("password", "12345");
    String URL ="";
    JSONObject payload = new JSONObject(map);


    JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, URL, payload,
            new Response.Listener<JSONObject>() {
                @Override
                    public void onResponse(JSONObject response) {
                    Log.i("Teste","Certo");
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i("Erro","Ceu merda");

        }
    }) {
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            return headers;
        }

    };

}

package edu.harrisburgu.loginapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView usernameField;
    TextView passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);
        Button loginButton = findViewById(R.id.login_button);
        usernameField = findViewById(R.id.user_input);
        passwordField = findViewById(R.id.pass_input);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                String data = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);

                JsonRequest jsonRequest =
                        new JsonRequest(Request.Method.POST,
                                "http://10.0.0.202:5000/login", data,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            boolean success = response.getBoolean("login");
                                            Log.d("JSONObject Response", "Success: " +
                                                    success);
                                            if (success) {
                                                Toast.makeText(v.getContext(), R.string.success, Toast.LENGTH_SHORT)
                                                        .show();
                                            } else {
                                                Toast.makeText(v.getContext(), R.string.failure, Toast.LENGTH_SHORT)
                                                        .show();
                                            }
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Volley Error", "Error:" + error);
                            }
                        }) {
                            @Override
                            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                                Response<JSONObject> res;
                                try {
                                    String data = new String(response.data);
                                    JSONObject json = new JSONObject(data);
                                    res = Response.success(json, null);
                                    Log.d("Login", "parseNetworkResponse called");
                                } catch (JSONException e) {
                                    res = Response.error(new VolleyError(e));
                                    Log.e("JSON Exception", "Error parsing network response", e);
                                }

                                return res;
                            }
                        };
                queue.add(jsonRequest);
            }
        });
    }
}

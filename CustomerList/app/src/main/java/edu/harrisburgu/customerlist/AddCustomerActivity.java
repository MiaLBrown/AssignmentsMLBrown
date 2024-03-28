package edu.harrisburgu.customerlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AddCustomerActivity extends AppCompatActivity {
    protected static final String url = "http://10.1.120.67:5000/add";
    protected static RequestQueue queue;
    @SuppressLint("StaticFieldLeak")
    protected static Context context;

    public static Intent newIntent(Context context, RequestQueue queue) {
        Intent i = new Intent(context, AddCustomerActivity.class);
        AddCustomerActivity.queue = queue;
        AddCustomerActivity.context = context;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_add_layout);

        EditText nameField = findViewById(R.id.name_input);
        EditText addressField = findViewById(R.id.address_input);
        EditText phoneField = findViewById(R.id.phone_input);

        Button saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(v -> {
            String name = nameField.getText().toString();
            String address = addressField.getText().toString();
            String phone = phoneField.getText().toString();

            JSONObject data = new JSONObject();
            try {
                data.put("name", name);
                data.put("address", address);
                data.put("phone", phone);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                    url, data,
                    response -> {
                        Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                        finish();
                    }, error -> {
                        Log.d("Save Error", "Error:" + error);
                        Toast.makeText(context, "Save failed!", Toast.LENGTH_SHORT).show();
                        finish();
                    });

            queue.add(jsonRequest);
        });
    }
}

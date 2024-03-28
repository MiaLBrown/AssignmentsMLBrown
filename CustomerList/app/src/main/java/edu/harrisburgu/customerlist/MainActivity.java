package edu.harrisburgu.customerlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected static final String url = "http://10.1.120.43:5000/all";

    RequestQueue queue;

    List<Customer> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = findViewById(R.id.listView);

        customerList = new ArrayList<>();

        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        queue.start();

        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        url, null,
                        response -> {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    Customer customer = new Customer(response.getJSONObject(i));
                                    customerList.add(customer);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            CustomerListAdapter adapter = new CustomerListAdapter(list.getContext(), customerList, queue);
                            list.setAdapter(adapter);
                            list.setOnItemClickListener(adapter);

                        }, error -> Log.d("JSONArray Error", "Error:" + error));
        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            // Start AddCustomerActivity
            Intent i = AddCustomerActivity.newIntent(MainActivity.this, queue);
            startActivity(i);
        });
    }
}
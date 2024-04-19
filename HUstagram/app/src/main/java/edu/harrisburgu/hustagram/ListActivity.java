package edu.harrisburgu.hustagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the list of images from the database or server
        ArrayList<ImageModel> imageList = getImageListFromServer();

        // Initialize the adapter with the image list
        listAdapter = new ListAdapter(imageList) {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }
        };

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(listAdapter);

    }

    private ArrayList<ImageModel> getImageListFromServer() {
        ArrayList<ImageModel> imageList = new ArrayList<>();

        // Make an HTTP request to your server endpoint
        String url = "http:///get_image_list";
        // You can use Volley, Retrofit, or other HTTP client libraries to make the request

        // Example using Volley library (assuming you have set up Volley in your project)
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("images");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String imageName = jsonObject.getString("name");
                            String comment = jsonObject.getString("comment");
                            String dateTime = jsonObject.getString("datetime");

                            // Create an ImageModel object and add it to the list
                            ImageModel imageModel = new ImageModel(imageName, comment, dateTime);
                            imageList.add(imageModel);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Handle error
                    Log.e("Volley Error", "Error retrieving image list from server: " + error.getMessage());
                });

        // Add the request to the RequestQueue (assuming you have set up a RequestQueue in your activity)
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

        return imageList;
    }

}

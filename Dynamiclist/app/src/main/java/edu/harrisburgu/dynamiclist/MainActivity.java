package edu.harrisburgu.dynamiclist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    protected static final String url = "https://nua.insufficient-light.com/data/holiday_songs_spotify.json";
    protected ListView listView;
    protected RequestQueue queue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        ArrayList<HolidaySong> results = new ArrayList<>();

        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        url, null,
                        response -> {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    HolidaySong song = new HolidaySong(response.getJSONObject(i));
                                    results.add(song);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            // Pass the RequestQueue as the third argument
                            HolidaySongsAdapter adapter = new HolidaySongsAdapter(MainActivity.this, results, queue);
                            listView.setAdapter(adapter);

                            // Set click listener on the ListView
                            listView.setOnItemClickListener((parent, view, position, id) -> {
                                // Handle item click
                                HolidaySong clickedSong = (HolidaySong) parent.getItemAtPosition(position);
                                // Start AlbumActivity and pass necessary data
                                Intent intent = new Intent(MainActivity.this, Album.class);
                                intent.putExtra("albumName", clickedSong.getAlbum());
                                intent.putExtra("playlistImage", clickedSong.getPlaylistImage());
                                startActivity(intent);
                            });

                        }, error -> Log.e("JSONArray Error", "Error:" + error));
// Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);
    }
}

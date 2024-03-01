package edu.harrisburgu.image;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private NetworkImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.networkImageView);

    }

    @Override
    protected void onStart() {
        super.onStart();

        RequestQueue queue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);

            }
        });
        final String url =
                "https://static.toiimg.com/photo/msid-67586673/67586673.jpg";
        image.setImageUrl(url, imageLoader);
    }

}
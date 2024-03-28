package edu.harrisburgu.dynamiclist;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;


/** @noinspection deprecation*/
public class Album extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    static HolidaySongsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album);
        // Retrieve data from Intent extras
        String albumName = getIntent().getStringExtra("albumName");
        String playlistImage = getIntent().getStringExtra("playlistImage");

        // Display album name
        TextView albumNameTextView = findViewById(R.id.albumDisplayName);
        albumNameTextView.setText(albumName);

        // Display playlist image
        ImageView playlistImageView = findViewById(R.id.albumDisplayImageView);
        new DownloadImageTask(playlistImageView).execute(playlistImage);

        int index = getIntent().getIntExtra(HolidaySongsAdapter.EXTRA_SELECTED_ITEM, -1);
        if (index >= 0)
        {
            adapter.populateView(findViewById(R.id.albumlayout), index);
        }

        Button mShowAnswer = findViewById(R.id.done_button);
        mShowAnswer.setOnClickListener(v -> finish());

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @SuppressLint("StaticFieldLeak")
        ImageView imageView;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}

package edu.harrisburgu.dynamiclist;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.Locale;

public class HolidaySongsAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    public static final String EXTRA_SELECTED_ITEM = "edu.harrisburgu.holidaysong.selected_item";
    private final Context context;
    private final ArrayList<HolidaySong> holidaySong;
    private final ImageLoader imageLoader;

    public HolidaySongsAdapter(Context context, ArrayList<HolidaySong> holidaySong, RequestQueue queue) {
        this.context = context;
        this.holidaySong = holidaySong;

        // Initialize imageLoader
        this.imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
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
    }

    public void populateView(View view, int index) {
        HolidaySong album = holidaySong.get(index);

        TextView tv = view.findViewById(R.id.albumDisplayName);
        tv.setText(album.getAlbum());
        tv = view.findViewById(R.id.artistDisplayName);
        tv.setText(album.getArtist());

        NetworkImageView image = view.findViewById(R.id.albumDisplayImageView);
        image.setImageUrl(album.getImage(), imageLoader);
    }


    @Override
    public int getCount() {
        return holidaySong.size();
    }

    @Override
    public Object getItem(int position) {
        return holidaySong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_song, viewGroup, false);
        }

        HolidaySong album = holidaySong.get(i);

        TextView textView = view.findViewById(R.id.albumName);
        textView.setText(album.getAlbum());
        textView = view.findViewById(R.id.artistName);
        textView.setText(album.getArtist());
        textView = view.findViewById(R.id.danceability);
        textView.setText(String.format(Locale.getDefault(), "%s: %.3f", context.getString(R.string.danceability), album.getDanceability()));
        textView = view.findViewById(R.id.duration);
        int minutes = (int) (album.getDurationMs() / 60000);
        int seconds = (int) ((album.getDurationMs() % 60000) / 1000);
        textView.setText(String.format(Locale.getDefault(), "%d:%02d", minutes, seconds));

        NetworkImageView image = view.findViewById(R.id.albumImage);
        image.setImageUrl(album.getImage(), imageLoader);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

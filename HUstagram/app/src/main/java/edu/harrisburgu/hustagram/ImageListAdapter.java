package edu.harrisburgu.hustagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageListAdapter extends ArrayAdapter<ImageModel> {
    private Context mContext;
    private int mResource;

    public ImageListAdapter(Context context, int resource, ArrayList<ImageModel> imageList) {
        super(context, resource, imageList);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        // Get the image model at the current position
        ImageModel imageModel = getItem(position);

        // Populate the views with the data from the image model
        TextView imageNameTextView = convertView.findViewById(R.id.image_name_text_view);
        TextView commentTextView = convertView.findViewById(R.id.comment_text_view);
        TextView dateTimeTextView = convertView.findViewById(R.id.date_time_text_view);

        imageNameTextView.setText(imageModel.getImageName());
        commentTextView.setText(imageModel.getComment());
        dateTimeTextView.setText(imageModel.getDateTime());

        return convertView;
    }
}

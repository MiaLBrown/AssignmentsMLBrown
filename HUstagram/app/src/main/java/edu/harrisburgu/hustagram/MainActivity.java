package edu.harrisburgu.hustagram;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    BitmapDrawable drawable;
    RequestQueue queue;
    EditText commentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button capture = findViewById(R.id.camera_button);
        imageView = findViewById(R.id.cameraImageView);
        commentEditText = findViewById(R.id.comment_edit_text);

        queue = Volley.newRequestQueue(this);
        queue.start();

        capture.setOnClickListener(v -> {
            Log.d("CameraTest", "in Click");
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
        });

        Button list = findViewById(R.id.list_button);
        list.setOnClickListener(v -> {
            Log.d("CameraTest", "in list Click");
            Intent i = new Intent(MainActivity.this, ListActivity.class);
            startActivity(i);
        });

        Button uploadButton = findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(v -> uploadImage());
    }

    private void uploadImage() {
        String comment = commentEditText.getText().toString().trim();
        if (comment.isEmpty()) {
            Toast.makeText(this, "Please enter a comment", Toast.LENGTH_SHORT).show();
            return;
        }

        if (drawable == null) {
            Toast.makeText(this, "Please capture an image first", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE){
            if (resultCode == RESULT_OK){
                assert data != null;
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);

                drawable = (BitmapDrawable) imageView.getDrawable();
                final Bitmap bitmap = drawable.getBitmap();

                String base64Image = encodeToBase64(bitmap,Bitmap.CompressFormat.PNG,100 );

                // Call the method to send the image to the server
                sendImageToServer(base64Image);
            } else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void sendImageToServer(String base64Image) {
        String url = "http://your_flask_server_ip/upload_image";

        // Create POST request
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            // Handle server response (success or failure)
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
        }, error -> {
            // Handle error
            Toast.makeText(this, "Error uploading image", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public byte[] getBody() {
                return base64Image.getBytes();
            }
        };

        // Add request to the RequestQueue
        queue.add(request);
    }


    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }
}

package com.example.user.photopickertest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static final int PICK_IMAGE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            try {
                Uri img = data.getData();
                InputStream inputStream = getContentResolver().openInputStream(img);
                Bitmap imgBitmap = BitmapFactory.decodeStream(inputStream);

                ImageView displayer = findViewById(R.id.ImgDisplayer);
                displayer.setImageURI(img);
                displayer.setImageBitmap(imgBitmap);
            }catch (Exception e){
                Toast.makeText(this, "Error selecting picture", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}

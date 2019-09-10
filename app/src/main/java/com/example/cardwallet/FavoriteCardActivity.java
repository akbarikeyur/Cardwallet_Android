package com.example.cardwallet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class FavoriteCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_card);


        ImageView imageView=findViewById(R.id.image_fro_share);
        ImageView imageView1=findViewById(R.id.image_fro_share_sec);
        imageView.setImageBitmap(DashBoard.createimageforlogoname);
        imageView1.setImageBitmap(DashBoard.createimageforemailadd);


    }
}

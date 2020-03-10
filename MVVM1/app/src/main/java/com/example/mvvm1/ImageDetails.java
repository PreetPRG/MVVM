package com.example.mvvm1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvvm1.Models.Image;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class ImageDetails extends AppCompatActivity {
    private int id;
    private int album_id;
    private String title;
    private String url;
    private String thumbnailUrl;
    ImageView imageUrl;
    TextView image_id;
    TextView image_Aid;
    TextView image_title;
    Button addtoCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
       id = getIntent().getIntExtra("Image_Id",0);
        Log.d("IMAGE_DETAILS_ACTIVITY", "onCreate: "+ id );
       album_id=getIntent().getIntExtra("Image_AId",0);
        Log.d("IMAGE_DETAILS_ACTIVITY", "onCreate: "+ album_id );
       url= getIntent().getStringExtra("Image_Url");
        Log.d("IMAGE_DETAILS_ACTIVITY", "onCreate: "+ url );
       title=getIntent().getStringExtra("Image_title");
        Log.d("IMAGE_DETAILS_ACTIVITY", "onCreate: "+ title );


        image_Aid=(TextView)findViewById(R.id.details_albumId);
        image_id=(TextView)findViewById(R.id.details_id);
        image_title=(TextView)findViewById(R.id.details_title);
        imageUrl=(ImageView)findViewById(R.id.details_image);
        addtoCart=(Button)findViewById(R.id.details_addcart);
        image_id.setText(Integer.toString(id));
        image_Aid.setText(Integer.toString(album_id));
        image_title.setText(title);

        Picasso.get()
                .load(url)
                .resize(400,400)
                .into(imageUrl);
    }
    public void addToCart(View view)
    {

    }
}

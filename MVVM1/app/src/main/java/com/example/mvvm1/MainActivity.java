package com.example.mvvm1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.example.mvvm1.Adapters.ReyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mImageName=new ArrayList<>();
    private ArrayList<String> mImageUrl= new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageFilling();
    }

    private void initImageFilling()
    {
        mImageUrl.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
        mImageName.add("Tree");
        mImageUrl.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
        mImageName.add("Tree");
        mImageUrl.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
        mImageName.add("Tree");
        mImageUrl.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
        mImageName.add("Tree");
        mImageUrl.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
        mImageName.add("Tree");
        mImageUrl.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
        mImageName.add("Tree");
        mImageUrl.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
        mImageName.add("Tree");
        mImageUrl.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
        mImageName.add("Tree");
        initRecyclerView();

    }

    private void initRecyclerView()
    {
//        RecyclerView recyclerview=findViewById(R.id.recyclerview);
//        ReyclerViewAdapter adapter= new ReyclerViewAdapter(mImageName,mImageUrl, this);
//        recyclerview.setAdapter(adapter);
//        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
}

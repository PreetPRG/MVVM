package com.example.mvvm1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.mvvm1.Adapters.PostsAdapter;
import com.example.mvvm1.Adapters.ReyclerViewAdapter;
import com.example.mvvm1.Models.Image;
import com.example.mvvm1.Models.PostModel;
import com.example.mvvm1.ViewModels.PostViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.google.gson.reflect.TypeToken.get;

public class Main2Activity extends AppCompatActivity implements ReyclerViewAdapter.onImageClickListener {

    PostViewModel postViewModel;
    RecyclerView mrecyclerview;
    ReyclerViewAdapter adapter;
    private List<Image> imageList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        postViewModel=new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(PostViewModel.class);
        //postViewModel= new ViewModelProvider(this).get(PostViewModel.class);
        //postViewModel = new ViewModelProvider.NewInstanceFactory().create(PostViewModel(this).class);
        //postViewModel.getPosts();
        postViewModel.getImages();
        mrecyclerview=findViewById(R.id.recycler2);
        final PostsAdapter postadapter=new PostsAdapter();

        adapter=new ReyclerViewAdapter(this,this::onImageclick);
        mrecyclerview.setAdapter(adapter);
        mrecyclerview.setLayoutManager(new GridLayoutManager(this,2));


//        postViewModel.getPosts().observe(this, new Observer<List<PostModel>>() {
//            @Override
//            public void onChanged(List<PostModel> postModels) {
//                postadapter.setList(postModels);
//            }
//        });

        postViewModel.getImages().observe(this, new Observer<List<Image>>() {
            @Override
            public void onChanged(List<Image> images) {
                adapter.setList(images);
                imageList.addAll(images);
            }
        });
    }



    @Override
    public void onImageclick(int position) {
        Log.d("EEEE","OnImageListener: Clicked + Position" + position);
        //adapter.getItemId(position);
        Intent intent=new Intent(this,ImageDetails.class);
        intent.putExtra("Image_Id",imageList.get(position).getId());
        intent.putExtra("Image_AId",imageList.get(position).getAlbumId());
        intent.putExtra("Image_Url",imageList.get(position).getUrl());
        intent.putExtra("Image_title",imageList.get(position).getTitle());
        //intent.putExtra("Image_TUrl",imageList.get(position).getThumbnailUrl());
        startActivity(intent);
    }
}

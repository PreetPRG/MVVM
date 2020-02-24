package com.example.mvvm1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mvvm1.Adapters.PostsAdapter;
import com.example.mvvm1.Adapters.ReyclerViewAdapter;
import com.example.mvvm1.Models.Image;
import com.example.mvvm1.Models.PostModel;
import com.example.mvvm1.ViewModels.PostViewModel;

import java.util.List;

import static com.google.gson.reflect.TypeToken.get;

public class Main2Activity extends AppCompatActivity {

    PostViewModel postViewModel;
    RecyclerView mrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //postViewModel=new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(PostViewModel.class);
        postViewModel= new ViewModelProvider(this).get(PostViewModel.class);

        postViewModel.getPosts();
        postViewModel.getImages();
        mrecyclerview=findViewById(R.id.recycler2);
        final PostsAdapter postadapter=new PostsAdapter();
        ReyclerViewAdapter adapter=new ReyclerViewAdapter(this);
        mrecyclerview.setAdapter(adapter);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));


        postViewModel.getPosts().observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                postadapter.setList(postModels);
            }
        });

        postViewModel.getImages().observe(this, new Observer<List<Image>>() {
            @Override
            public void onChanged(List<Image> images) {
                adapter.setList(images);
            }
        });
    }
}

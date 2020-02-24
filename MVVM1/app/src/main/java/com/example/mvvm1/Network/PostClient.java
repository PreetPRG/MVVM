package com.example.mvvm1.Network;

import androidx.lifecycle.LiveData;

import com.example.mvvm1.Models.Image;
import com.example.mvvm1.Models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {
    public static final String BASE_URL="https://jsonplaceholder.typicode.com/";

    private PostInterface postInterface;
    private static PostClient Instance;

    public PostClient()
    {
        Retrofit retrofit= new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        postInterface =retrofit.create(PostInterface.class);
    }

    public static PostClient getInstance() {
        if(null == Instance)
        {
            Instance= new PostClient();
        }
        return Instance;
    }

    public Call<List<PostModel>> getPosts()
    {
        return postInterface.getPosts();
    }

    public Call<List<Image>> getImages(){ return postInterface.getImages();}
}

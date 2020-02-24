package com.example.mvvm1.Network;

import androidx.lifecycle.LiveData;

import com.example.mvvm1.Models.Image;
import com.example.mvvm1.Models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("posts")
    public Call<List<PostModel>> getPosts();

    @GET("photos")
    public Call<List<Image>> getImages();
}

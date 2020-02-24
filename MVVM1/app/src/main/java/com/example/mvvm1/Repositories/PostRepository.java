package com.example.mvvm1.Repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mvvm1.Models.Image;
import com.example.mvvm1.Models.PostModel;
import com.example.mvvm1.Network.PostClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private MutableLiveData<List<PostModel>> postMutuableLiveData = new MutableLiveData<>();
    private static PostRepository instance;
    MutableLiveData<String> posts = new MutableLiveData<>();

    private MutableLiveData<List<Image>> ImageMutuableLiveData= new MutableLiveData<>();
    public PostRepository() {

    }

    public static PostRepository getInstance() {
        if(instance==null)
        {
            instance=new PostRepository();
        }
        return instance;
    }

    public LiveData<List<PostModel>> getPosts()
    {
        PostClient.getInstance().getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                postMutuableLiveData.setValue(response.body());
                //livedata=postMutuableLiveData;
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                posts.setValue("error");
            }
        });
        return postMutuableLiveData;
    }

    public LiveData<List<Image>> getImages()
    {
        PostClient.getInstance().getImages().enqueue(new Callback<List<Image>>() {

            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                ImageMutuableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {

            }
        });
        return ImageMutuableLiveData;
    }
}

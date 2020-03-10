package com.example.mvvm1.ViewModels;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm1.Models.Image;
import com.example.mvvm1.Models.PostModel;
import com.example.mvvm1.Network.PostClient;
import com.example.mvvm1.Repositories.PostRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends AndroidViewModel {

    PostRepository postRepository;
    LiveData<List<PostModel>> postData;
    LiveData<List<Image>> images;

    public PostViewModel(Application application)
    {
        super(application);

    }

//    public LiveData<List<PostModel>> getPosts()
//    {
//        return postData;
//    }

    public LiveData<List<Image>> getImages(){
        postRepository=PostRepository.getInstance(getApplication());
        //postData=postRepository.getPosts();
        images=postRepository.getImages();
        return images;}


}

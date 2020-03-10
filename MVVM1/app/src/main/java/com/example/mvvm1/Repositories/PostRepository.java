package com.example.mvvm1.Repositories;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mvvm1.Models.Image;
import com.example.mvvm1.Models.ImageDAO;
import com.example.mvvm1.Models.ImageDatabase;
import com.example.mvvm1.Models.PostModel;
import com.example.mvvm1.Network.PostClient;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private MutableLiveData<List<PostModel>> postMutuableLiveData = new MutableLiveData<>();
    private static PostRepository instance;
    MutableLiveData<String> posts = new MutableLiveData<>();

    private MutableLiveData<List<Image>> ImageMutuableLiveData= new MutableLiveData<>();



    //We've created an ExecutorService with a fixed thread pool that you will use to run database operations asynchronously on a background thread.


    private ImageDAO imagedao;

    public PostRepository(Application app) {
        ImageDatabase imageDatabase=ImageDatabase.getInstance(app);
        imagedao =imageDatabase.imagedao();
        Log.d("Repository","Post Repo Constructor called");
    }

    public static PostRepository getInstance(Application app) {
        if(instance==null)
        {
            instance=new PostRepository(app);

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
        refresh();
//        PostClient.getInstance().getImages().enqueue(new Callback<List<Image>>() {
//
//            @Override
//            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
//                ImageMutuableLiveData.setValue(response.body());
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Image>> call, Throwable t) {
//
//            }
//        });
        return imagedao.getAllImages();
    }
    private Executor executor;
    private void refresh()
    {


                    //boolean imageExists = (imagedao.getAllImages().getValue().isEmpty();
            Log.d("Repository", "Data refreshed from Network");
            if (imagedao.getAllImages()==null) {
                        Log.d("Repository", "Data refreshed from Network");
                        PostClient.getInstance().getImages().enqueue(new Callback<List<Image>>() {
                            @Override
                            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                                Log.d("Repository", "Data refreshed from Network");
                                ImageDatabase.databaseWriteExecutor.execute(()->{
                                            imagedao.save(response.body());}
                                        );

                            }

                            @Override
                            public void onFailure(Call<List<Image>> call, Throwable t) {

                            }
                        });

                }

//        executor.execute(()->{
//            boolean imageExists = (imagedao.getAllImages().equals(null));
//            if(imageExists)
//            {
//                PostClient.getInstance().getImages().enqueue(new Callback<List<Image>>() {
//                    @Override
//                    public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
//                        Log.d("Repository","Data refreshed from Network");
//                        imagedao.save(response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Image>> call, Throwable t) {
//
//                    }
//                });
//            }
//        });
    }
}

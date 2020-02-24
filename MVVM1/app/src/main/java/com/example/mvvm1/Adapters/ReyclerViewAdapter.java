package com.example.mvvm1.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvm1.Models.Image;
import com.example.mvvm1.R;

import java.util.ArrayList;
import java.util.List;

public class ReyclerViewAdapter extends RecyclerView.Adapter<ReyclerViewAdapter.ViewHolder>{
    private static final String TAG = "ReyclerViewAdapter";
    private ArrayList<String> mImageName=new ArrayList<>();
    private ArrayList<String> mImageUrl= new ArrayList<>();
    private Context mContext;
    private List<Image> imageList = new ArrayList<>();

//    public ReyclerViewAdapter(ArrayList<String> mImageName, ArrayList<String> mImageUrl, Context mContext) {
//        this.mImageName = mImageName;
//        this.mImageUrl = mImageUrl;
//        this.mContext = mContext;
//    }
    public ReyclerViewAdapter(Context context)
    {
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"On BIndView Called");
        Log.d(TAG,imageList.get(position).getUrl());
        Glide.with(mContext)
                .asBitmap()
                .apply(RequestOptions.circleCropTransform())
                .load(imageList.get(position).getUrl())
                .into(holder.image);
        holder.imageName.setText(imageList.get(position).getTitle());
        holder.ParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Position" + imageList.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void setList(List<Image> images) {
        this.imageList = images;
        notifyDataSetChanged();
    }

    //ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView imageName;
        RelativeLayout ParentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.ImageView1);
            imageName=itemView.findViewById(R.id.TextView1);
            ParentLayout=itemView.findViewById(R.id.List_item1);
        }
    }
}

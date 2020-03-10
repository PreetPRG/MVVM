package com.example.mvvm1.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvm1.Models.Image;
import com.example.mvvm1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ReyclerViewAdapter extends RecyclerView.Adapter<ReyclerViewAdapter.ViewHolder>{
    private static final String TAG = "ReyclerViewAdapter";

    private Context mContext;
    private List<Image> imageList = new ArrayList<>();
    private onImageClickListener monImageClickListener;
//    public ReyclerViewAdapter(ArrayList<String> mImageName, ArrayList<String> mImageUrl, Context mContext) {
//        this.mImageName = mImageName;
//        this.mImageUrl = mImageUrl;
//        this.mContext = mContext;
//    }
    public ReyclerViewAdapter(Context context,onImageClickListener onImageClickListener)
    {
        this.mContext=context;
        this.monImageClickListener=onImageClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image,parent, false);
        return new ViewHolder(view,monImageClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"On BIndView Called");
        Log.d(TAG,imageList.get(position).getUrl());
        Picasso.get()
                .load(imageList.get(position).getUrl())
                .resize(300,300)
                .into(holder.image);
//        Glide.with(mContext)
//                .asBitmap()
//                .apply(RequestOptions.fitCenterTransform())
//                .load(imageList.get(position).getUrl())
//                .into(holder.image);
        holder.imageName.setText(String.valueOf(imageList.get(position).getId()));
//        holder.ParentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext,"Position" + imageList.get(position).getUrl(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void setList(List<Image> images) {
        this.imageList = images;
        notifyDataSetChanged();
    }

    public List<Image> getImageList()
    {
        return imageList;
    }

    //ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView imageName;
        LinearLayout ParentLayout;
        onImageClickListener onImageClickListener;
        public ViewHolder(@NonNull View itemView,onImageClickListener onImageClickListener) {
            super(itemView);
            image= itemView.findViewById(R.id.Image_view);
            imageName=itemView.findViewById(R.id.Text_view);
//            ParentLayout=itemView.findViewById(R.id.List_item1);
            ParentLayout=itemView.findViewById(R.id.Item_layout);
            this.onImageClickListener=onImageClickListener;
            itemView.setOnClickListener(this::onClick);
            Log.d("Adapter786","ViewHolder Class called");
        }

        @Override
        public void onClick(View v) {
            onImageClickListener.onImageclick(getAdapterPosition());
        }
    }
    public interface onImageClickListener
    {
        void onImageclick(int position);
    }
}

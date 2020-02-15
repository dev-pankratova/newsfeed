package com.project.newsfeed.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.newsfeed.R;
import com.project.newsfeed.model.NewsModel;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListViewHolder> {
    Context mContext;
    private List<NewsModel> mItem;
    View.OnClickListener mClickListener;

    public Adapter(@NonNull Context context, @NonNull List<NewsModel> item) {
        this.mContext = context;
        this.mItem = item;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_items, parent, false);
        ListViewHolder holder = new ListViewHolder(itemview);
        holder.itemView.setOnClickListener(v -> mClickListener.onClick(v));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.tvTitle.setText(mItem.get(position).getTitle());
        holder.tvDescr.setText(mItem.get(position).getDescription());
        String url = mItem.get(position).getUrlImage();
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .into(holder.img);

    }


    @Override
    public int getItemCount() {
        return mItem.size();
    }

    void setItem (List<NewsModel> items){
        this.mItem = items;
        notifyDataSetChanged();
    }

    public void setClickListener(View.OnClickListener callback) {
        mClickListener = callback;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
    private ImageView img;
    private TextView tvTitle;
    private TextView tvDescr;

        private ListViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            img = itemView.findViewById(R.id.image_news);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescr = itemView.findViewById(R.id.tv_descr);
        }
    }
}

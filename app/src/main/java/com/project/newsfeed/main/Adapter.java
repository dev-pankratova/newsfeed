package com.project.newsfeed.main;

import android.content.Context;
import android.text.Html;
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

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListViewHolder> {
    private Context mContext;

    @NonNull
    private List<NewsModel> items = new ArrayList<>();
    View.OnClickListener mClickListener;

    Adapter(@NonNull Context context, View.OnClickListener clickListener) {
        this.mContext = context;
        this.mClickListener = clickListener;
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
        holder.tvTitle.setText(Html.fromHtml(items.get(position).getTitle()));
        holder.tvDescr.setText(items.get(position).getDescription());
        String url = items.get(position).getUrlImage();
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void setItems(List<NewsModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

   /* List<NewsModel> getItems() {
        return items;
    }*/

    class ListViewHolder extends RecyclerView.ViewHolder{
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
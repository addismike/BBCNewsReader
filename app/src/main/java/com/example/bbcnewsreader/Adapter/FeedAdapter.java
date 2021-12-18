package com.example.bbcnewsreader.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbcnewsreader.Interface.ItemClickListener;
import com.example.bbcnewsreader.Model.RSSObject;
import com.example.bbcnewsreader.R;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{

    public TextView txtTitle,txtDescription,pubDate;
    private ItemClickListener itemClickListener;

    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtDescription = itemView.findViewById(R.id.txtDescription);
        pubDate = itemView.findViewById(R.id.pubDate);


        itemView.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),true);
        return true;
    }
}
public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private RSSObject rssObject;
    private Context context;
    private LayoutInflater inflater;

    public FeedAdapter(RSSObject rssObject, Context context) {
        this.rssObject = rssObject;
        this.context = context;
       inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View itemView = inflater.inflate(R.layout.row,parent,false);

        return  new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {

holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
holder.txtDescription.setText(rssObject.getItems().get(position).getDescription());
holder.pubDate.setText(rssObject.getItems().get(position).getPubDate());


    }

    @Override
    public int getItemCount() {
        return rssObject.items.size();
    }
}

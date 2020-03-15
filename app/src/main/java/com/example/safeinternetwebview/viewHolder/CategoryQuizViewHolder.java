package com.example.safeinternetwebview.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safeinternetwebview.R;
import com.example.safeinternetwebview.interfaces.ItemClickListener;

public class CategoryQuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView chaptername;
    public ImageView imageView;
    public TextView totalquize;
    public RelativeLayout parent;
    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public CategoryQuizViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.backgroundimageid);
        chaptername=itemView.findViewById(R.id.chapternameid);
        totalquize=itemView.findViewById(R.id.total_quizenumberid);
        parent=itemView.findViewById(R.id.relativeid);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition());
    }
}

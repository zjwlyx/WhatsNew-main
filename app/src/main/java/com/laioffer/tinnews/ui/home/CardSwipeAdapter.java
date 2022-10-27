package com.laioffer.tinnews.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.databinding.SwipeNewsCardBinding;
import com.laioffer.tinnews.model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardSwipeAdapter extends RecyclerView.Adapter<CardSwipeAdapter.CardSwipeViewHolder> {
    // 1. Supporting data:
    // TODO
    private List<News> newsList = new ArrayList<>();
    private Context context;

    public void setNewsList(List<News> newslist) {
        newsList.clear();
        newsList.addAll(newslist);
        notifyDataSetChanged(); //we call notifyDataSetChanged to let the adapter refresh and re-render the data.
    }

    // 2. Adapter overrides:
    // TODO

    @NonNull
    @Override
    public CardSwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_news_card, parent, false);
        return new CardSwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardSwipeViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.titleTextView.setText(news.title);
        holder.descriptionTextView.setText(news.description);
        Picasso.with(context).load(news.picUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    // 3. CardSwipeViewHolder:
    // TODO
    public static class CardSwipeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public CardSwipeViewHolder(@NonNull View itemView) {
            super(itemView);
            SwipeNewsCardBinding binding = SwipeNewsCardBinding.bind(itemView);
            imageView = binding.swipeCardImageView;
            titleTextView = binding.swipeCardTitle;
            descriptionTextView = binding.swipeCardDescription;
        }
    }

}

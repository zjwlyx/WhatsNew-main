package com.laioffer.tinnews.ui.save;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.databinding.SavedNewsItemBinding;
import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.News;

import java.util.ArrayList;
import java.util.List;

public class SavedNewsAdapter extends RecyclerView.Adapter<SavedNewsAdapter.SavedNewsViewHolder> {

    interface ItemCallback {
        void onOpenDetail(News news);
        void onRemoveFavorite(News news);
    }

    private ItemCallback itemCallback;
    private List<News> newss = new ArrayList<>();

    public void setItemCallback(ItemCallback itemCallback){
        this.itemCallback = itemCallback;
    }

    // 1. Supporting data:
    // TODO

    public void setArticles(List<News> newsList){
        newss.clear();
        newss.addAll(newsList);
        notifyDataSetChanged();
    }

    // 2. Adapter overrides:
    // TODO

    @NonNull
    @Override
    public SavedNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_news_item, parent, false);
        return new SavedNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedNewsViewHolder holder, int position) {
        News news = newss.get(position);
        holder.authorTextView.setText("ck");
        holder.descriptionTextView.setText(news.description);

        holder.favoriteIcon.setOnClickListener( v-> itemCallback.onRemoveFavorite(news));
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetail(news));
    }

    @Override
    public int getItemCount() {
        return newss.size();
    }

    // 3. SavedNewsViewHolder:
    // TODO


    public static class SavedNewsViewHolder extends RecyclerView.ViewHolder {
        TextView authorTextView;
        TextView descriptionTextView;
        ImageView favoriteIcon;

        public SavedNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SavedNewsItemBinding binding = SavedNewsItemBinding.bind(itemView);
            authorTextView = binding.savedItemAuthorContent;
            descriptionTextView = binding.savedItemDescriptionContent;
            favoriteIcon = binding.savedItemFavoriteImageView;
        }
    }
}

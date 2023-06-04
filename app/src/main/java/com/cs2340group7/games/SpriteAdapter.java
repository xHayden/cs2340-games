package com.cs2340group7.games;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SpriteAdapter extends RecyclerView.Adapter<SpriteAdapter.ViewHolder> {

    private List<Integer> spriteList;
    private OnSpriteClickListener onSpriteClickListener;
    private ViewHolder selectedViewHolder;

    public interface OnSpriteClickListener {
        void onSpriteClick(int spriteResId);
    }

    public SpriteAdapter(List<Integer> spriteList, OnSpriteClickListener onSpriteClickListener) {
        this.spriteList = spriteList;
        this.onSpriteClickListener = onSpriteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sprite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageButton.setImageResource(spriteList.get(position));
        holder.spriteResId = spriteList.get(position);
        holder.imageButton.setBackgroundResource(R.drawable.sprite_selector_button_highlight); // set the background here
        holder.imageButton.setSelected(false); // initially set as unselected
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSpriteClickListener.onSpriteClick(spriteList.get(position));
                holder.imageButton.setSelected(true);
                if (selectedViewHolder == holder) {
                    return;
                }
                // deselect the previously selected ImageButton
                if (selectedViewHolder != null) {
                    selectedViewHolder.imageButton.setSelected(false);
                }
                // select the clicked ImageButton and update the selectedViewHolder
                holder.imageButton.setSelected(true);
                selectedViewHolder = holder;
                onSpriteClickListener.onSpriteClick(holder.spriteResId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return spriteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        int spriteResId;

        ViewHolder(View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.spriteImageButton);
        }
    }
}

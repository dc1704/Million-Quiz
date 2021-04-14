package com.dhruv.millionquiz;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.CardViewHolder> {
    List<Category> categories;
    private LayoutInflater mInflater;
    public String[] mColors = {"#3F51B5", "#FF9800", "#009688", "#673AB7", "#F67280", "#C0C684", "#355C7D", "#6C5B7B"};

    public recyclerAdapter(Context context, List<Category> categories) {
        mInflater = LayoutInflater.from(context);
        this.categories = categories;

    }

    @NonNull
    @Override
    public recyclerAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.category_row, parent, false);
        mItemView.setOnClickListener(CategoryActivity.myOnClickListener);
        return new CardViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.CardViewHolder holder, int position) {

        TextView textViewCategory = holder.textViewCategory;
        textViewCategory.setText(categories.get(position).getName());

        int id = categories.get(position).getId();
        switch (id) {
            case 1:
                holder.itemView.setBackgroundResource(R.drawable.programming);
                break;
            case 2:
                holder.itemView.setBackgroundResource(R.drawable.sports);
                break;
            case 3:
                holder.itemView.setBackgroundResource(R.drawable.history);
                break;
            case 4:
                holder.itemView.setBackgroundResource(R.drawable.geography);
                break;
            case 5:
                holder.itemView.setBackgroundResource(R.drawable.world);
                break;
            case 6:
                holder.itemView.setBackgroundResource(R.drawable.indian);
                break;
            case 7:
                holder.itemView.setBackgroundResource(R.drawable.science);
                break;
            case 8:
                holder.itemView.setBackgroundResource(R.drawable.riddle);
                break;
            case 9:
                holder.itemView.setBackgroundResource(R.drawable.math);
                break;
            default:
                    holder.itemView.setBackgroundColor(Color.parseColor(mColors[position % 8]));
        }





    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        public final TextView textViewCategory;
        final recyclerAdapter mAdapter;

        public CardViewHolder(View itemView, recyclerAdapter adapter) {
            super(itemView);
            textViewCategory = itemView.findViewById(R.id.text_view_category);
            mAdapter = adapter;

        }


    }

}
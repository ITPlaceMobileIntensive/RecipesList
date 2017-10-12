package com.itplace.maria.recipeslist.adapters;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itplace.maria.recipeslist.R;
import com.itplace.maria.recipeslist.recipedatastruct.Recipe;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by maria on 02.10.2017.
 * Адаптер RecyclerView`ра
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<Recipe> items;

    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.ViewHolder holder, int position) {
        holder.bindData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Recipe entity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(entity);
        notifyItemInserted(items.indexOf(entity));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView ingredients;
        ImageView pic;
        CardView card;

        private OnItemClickListener onItemClickListener;

        public ViewHolder(final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            ingredients = itemView.findViewById(R.id.ingredients);
            pic = itemView.findViewById(R.id.pic);
            card = itemView.findViewById(R.id.card);

            // TODO null exception
            //card.setOnClickListener(this);
        }

        void bindData(final Recipe entity) {
            name.setText(entity.getName());
            ingredients.setText(itemView.getContext().getResources().getString(R.string.ingredients)
                    + " " + entity.getIngredients());
            pic.setImageResource(entity.getPicture());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                //itemClick(position);
                onItemClickListener.onItemClick(position);
            }
        }

        private void itemClick(int position) {
            // TODO нужно через свой интерфейс передавать событие клика по элементу в BreakfastsFragment.
            // TODO Передавать можно или сам элемент или id в Recipe
            // TODO CardActivity.createStartIntent должно вызываться в BreakfastsFragment.
            //Intent intent = CardActivity.createStartIntent(itemView.getContext());
            //itemView.getContext().startActivity(intent);
        }

        public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public interface OnItemClickListener {
            void onItemClick(int RecipeId);
        }
    }
}
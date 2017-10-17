package com.itplace.maria.recipeslist.adapters;


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

    private OnItemClickListener onItemClickListener;

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
        //items = items == null ? null : items;
        return items.size();
    }

    public void addItem(Recipe entity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        notifyItemInserted(items.size()); // notifyItemInserted нужно  вызвать после добавления в items.add
        items.add(entity);
    }

    public void initList() { // нужен этот метод. Можно сразу проинициализировать List<Recipe> items = new ArrayList<>();
        if (items == null) {
            items = new ArrayList<>();
        }
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(String RecipeId);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView ingredients;
        ImageView pic;

        ViewHolder(final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            ingredients = itemView.findViewById(R.id.ingredients);
            pic = itemView.findViewById(R.id.pic);

            itemView.setOnClickListener(this);
        }

        void bindData(final Recipe entity) {
            name.setText(entity.getName());
            ingredients.setText(itemView.getContext().getResources().getString(R.string.ingredients) + " " + entity.getIngredients());
            pic.setImageResource(entity.getPicture());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClick(items.get(position).getId());
            }
        }
    }
}

package com.itplace.maria.recipeslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maria on 02.10.2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    List<Recipe> items;  // уровень доступа private

    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);

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
        notifyDataSetChanged();
    }

    public void updateItems(List<Recipe> items) {
        if (items == null) {
            return;
        }
        this.items = items;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView ingredients;
        ImageView pic;

        public ViewHolder(final View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            ingredients = (TextView) itemView.findViewById(R.id.ingredients);
            pic = (ImageView) itemView.findViewById(R.id.pic);
        }

        public void bindData(final Recipe entity) {
            name.setText(entity.getName());
            ingredients.setText("Ингридиенты: " + entity.getIngredients()); //Ингридиенты: " строки из ресурсов нужно брать
            pic.setImageResource(entity.getPicture());
        }
    }
}

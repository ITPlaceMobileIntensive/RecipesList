package com.itplace.maria.recipeslist;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;

import static android.support.v4.app.ActivityCompat.startActivityForResult;


/**
 * Created by maria on 02.10.2017
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    public static final int REQUEST_CODE_HEX = 0;

    private List<Recipe> items;

    private static Context parentContext; // Android studio выделяет static желтым цветом и говорит о возможном memory leak
    // Не нужно сохранять контекст в статических ссылках. В данном классе вообще не необходимости использовать parentContext

    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentContext = parent.getContext();
        View v = LayoutInflater.from(parentContext)
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
        notifyDataSetChanged(); // после добавления одного элемента не нужно весь список перерисовывать
        // есть более подходящии методы, например notifyItemInserted();
    }

    public void updateItems(List<Recipe> items) { // не используемый метод
        if (items == null) {
            return;
        }
        this.items = items;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView ingredients;
        ImageView pic;
        CardView card;

        public ViewHolder(final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            ingredients = itemView.findViewById(R.id.ingredients);
            pic = itemView.findViewById(R.id.pic);
            card = itemView.findViewById(R.id.card);

            card.setOnClickListener(this);
        }

        public void bindData(final Recipe entity) {
            name.setText(entity.getName()); // context можно получить из itemView. itemView это поле внутри класса RecyclerView.ViewHolder
            ingredients.setText(parentContext.getResources().getString(R.string.ingredients)
                    + " " + entity.getIngredients());
            pic.setImageResource(entity.getPicture());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                switch (itemView.getId()) { // эта проверка ни к чему
                    case R.id.card:
                        itemClick(position);
                        break;
                }
            }
        }

        private void itemClick(int position) {
            Intent intent = CardActivity.createStartIntent(parentContext); // нужно через свой интерфейс передавать событие клика по элементу в BreakfastsFragment. Передавать можно или сам элемент или id в Recipe
            parentContext.startActivity(intent); // CardActivity.createStartIntent должно вызываться в BreakfastsFragment.
        }
    }
}

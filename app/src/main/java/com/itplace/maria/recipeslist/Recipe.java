package com.itplace.maria.recipeslist;

import android.graphics.drawable.Drawable;

/**
 * Created by maria on 02.10.2017.
 */

public class Recipe {

    RecipeType type; // уровень доступа private
    String name; // уровень доступа private
    String ingredients; // уровень доступа private
    int picture; // уровень доступа private

    public Recipe( RecipeType type, String name, String ingredients, int picture) {
        this.type = type;
        this.name = name;
        this.ingredients = ingredients;
        this.picture = picture;
    }

    public RecipeType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getPicture() {
        return picture;
    }
}

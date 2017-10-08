package com.itplace.maria.recipeslist;

/**
 * Created by maria on 02.10.2017
 */

public class Recipe {

    // нужно добавить поле id, которое будет унивальным идентификатором каждой сущности. Тип поля сделать String, потому что в при работе с сервером будет приходить id с типом String
    private RecipeType type;
    private String name;
    private String ingredients;
    private int picture;

    public Recipe(RecipeType type, String name, String ingredients, int picture) {
        this.type = type;
        this.name = name;
        this.ingredients = ingredients;
        this.picture = picture;
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

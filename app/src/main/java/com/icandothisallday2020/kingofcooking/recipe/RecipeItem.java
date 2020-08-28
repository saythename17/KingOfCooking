package com.icandothisallday2020.kingofcooking.recipe;

public class RecipeItem {
    String title,name,image,ingredient;
    String[] manual,manual_img;
    int id;

    public RecipeItem() {
    }

    public RecipeItem(String title, String name, String image, String ingredient, String[] manual, String[] manual_img, int id) {
        this.title = title;
        this.name = name;
        this.image = image;
        this.ingredient = ingredient;
        this.manual = manual;
        this.manual_img = manual_img;
        this.id = id;
    }

    public RecipeItem(String title, String name, String image, String ingredient, int id) {
        this.title = title;
        this.name = name;
        this.image = image;
        this.ingredient = ingredient;
        this.id = id;
    }
}

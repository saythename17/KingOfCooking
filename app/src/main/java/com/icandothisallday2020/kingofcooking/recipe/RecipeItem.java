package com.icandothisallday2020.kingofcooking.recipe;

public class RecipeItem {
    String title,name,image;
    int img;

    public RecipeItem() {
    }

    public RecipeItem(String title, String name, String image) {
        this.title = title;
        this.name = name;
        this.image = image;
    }

    public RecipeItem(String title, String name, int img) {
        this.title = title;
        this.name = name;
        this.img = img;
    }
}

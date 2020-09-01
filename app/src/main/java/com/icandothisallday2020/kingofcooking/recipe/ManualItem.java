package com.icandothisallday2020.kingofcooking.recipe;

public class ManualItem {
    String manual[],manual_img[];

    public ManualItem() {
    }

    public ManualItem(String manual[]) {
        this.manual = manual;
    }

    public ManualItem(String manual[], String manual_img[]) {
        this.manual = manual;
        this.manual_img = manual_img;
    }
}

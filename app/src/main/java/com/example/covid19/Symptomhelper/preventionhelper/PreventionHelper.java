package com.example.covid19.Symptomhelper.preventionhelper;

public class PreventionHelper {
    int image;
    String title,description;

    public PreventionHelper(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

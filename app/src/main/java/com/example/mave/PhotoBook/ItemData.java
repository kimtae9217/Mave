package com.example.mave.PhotoBook;

public class ItemData {
    String image;
    String Title;
    String Content;

    public ItemData(){

    }

    public ItemData(String title, String content, String image) {
        Title = title;
        Content = content;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return Title;
    }

    public String getContent() {
        return Content;
    }
}
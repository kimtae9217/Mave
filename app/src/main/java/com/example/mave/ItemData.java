package com.example.mave;

public class ItemData {
    private int familyphoto;
    private String txt_content;
    private String txt_title;

    public ItemData(int familyphoto, String title, String content) {
        this.familyphoto = familyphoto;
        this.txt_title = title;
        this.txt_content = content;
    }

    /*public int getFamilyphoto() { return familyphoto; }

    public void setFamilyphoto(int image) { this.familyphoto = familyphoto; }*/

    public String getTitle() {
        return txt_title;
    }

    public void setTitle(String title) {
        this.txt_title = title;
    }

    public String getContent() {
        return txt_content;
    }

    public void setContent(String content) {
        this.txt_content = content;
    }
}
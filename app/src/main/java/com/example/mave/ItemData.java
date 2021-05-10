package com.example.mave;

public class ItemData {
    private int familyphoto;
    private String content;
    private String title;

    public ItemData(int familyphoto, String title, String content, String 그룹_내용) {
        this.familyphoto = familyphoto;
        this.title = title;
        this.content = content;
    }

    public int getFamilyphoto() { return familyphoto; }

    public void setFamilyphoto(int image) { this.familyphoto = familyphoto; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
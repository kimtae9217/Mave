package com.example.mave.PhotoBook;

public class ItemData {
    private int Item_Data_family_photo;
    private String Item_Data_txt_content;
    private String Item_Data_txt_title;

    public ItemData(int familyphoto, String title, String content) {
        this.Item_Data_family_photo = familyphoto;
        this.Item_Data_txt_title = title;
        this.Item_Data_txt_content = content;
    }

    public int getFamilyphoto() { return Item_Data_family_photo; }

    public void setFamilyphoto(String image) { this.Item_Data_family_photo = Item_Data_family_photo; }

    public String getTitle() {
        return Item_Data_txt_title;
    }

    public void setTitle(String title) {
        this.Item_Data_txt_title = title;
    }

    public String getContent() {
        return Item_Data_txt_content;
    }

    public void setContent(String content) {
        this.Item_Data_txt_content = content;
    }

}
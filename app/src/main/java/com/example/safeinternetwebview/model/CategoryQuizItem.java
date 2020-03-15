package com.example.safeinternetwebview.model;

public class CategoryQuizItem {
    public String chaptername, imagelink, totalplayquiz, totalquiz, colors, colors2;

    public CategoryQuizItem() {
    }

    public CategoryQuizItem(String chaptername, String imagelink, String totalplayquiz, String totalquiz, String colors, String colors2) {
        this.chaptername = chaptername;
        this.imagelink = imagelink;
        this.totalplayquiz = totalplayquiz;
        this.totalquiz = totalquiz;
        this.colors = colors;
        this.colors2 = colors2;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getTotalplayquiz() {
        return totalplayquiz;
    }

    public void setTotalplayquiz(String totalplayquiz) {
        this.totalplayquiz = totalplayquiz;
    }

    public String getTotalquiz() {
        return totalquiz;
    }

    public void setTotalquiz(String totalquiz) {
        this.totalquiz = totalquiz;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getColors2() {
        return colors2;
    }

    public void setColors2(String colors2) {
        this.colors2 = colors2;
    }
}




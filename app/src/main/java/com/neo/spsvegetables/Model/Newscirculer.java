package com.neo.spsvegetables.Model;

public class Newscirculer {

    String date;
    String title;
    String message;
    String image;
    String color;
    String html_tittle;
    String html_link;
    String id;


    public Newscirculer(String date, String title, String message, String image, String color, String html_tittle, String html_link, String id) {
        this.date=date;
        this.title=title;
        this.message=message;
        this.image=image;
        this.color=color;
        this.html_tittle=html_tittle;
        this.html_link=html_link;
        this.id=id;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHtml_tittle() {
        return html_tittle;
    }

    public void setHtml_tittle(String html_tittle) {
        this.html_tittle = html_tittle;
    }

    public String getHtml_link() {
        return html_link;
    }

    public void setHtml_link(String html_link) {
        this.html_link = html_link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}

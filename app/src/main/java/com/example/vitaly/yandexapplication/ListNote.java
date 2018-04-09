package com.example.vitaly.yandexapplication;

import android.graphics.Color;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Vitaly on 17.03.2018.
 */

public class ListNote implements Serializable{

    private int color;
    private String caption;
    private String description;
    private Date creationDate;

    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",   Locale.getDefault());

    public ListNote(int color, String caption, String description, Date creationDate) {
        this.color = color;
        this.caption = caption;
        this.description = description;
        this.creationDate = creationDate;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return timeFormat.format(creationDate);
    }

    public String getDate() {
        return dateFormat.format(creationDate);
    }
}

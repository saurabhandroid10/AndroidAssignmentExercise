package com.tels.androidassignmentexercise.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by saurabhgarg on 17/02/17.
 */
public class Row {

    @SerializedName("title")
    @Expose
    private String title = "";
    @SerializedName("description")
    @Expose
    private String description = "";
    @SerializedName("imageHref")
    @Expose
    private Object imageHref = null;

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public Object getImageHref() {
        return imageHref;
    }

}

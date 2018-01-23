package com.tels.androidassignmentexercise.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by saurabhgarg on 17/02/17.
 */
public class CountryResponse {

    @SerializedName("title")
    @Expose
    private String title = "";

    @SerializedName("rows")
    @Expose
    private List<Row> rows = null;

    public String getTitle() {
        return title;
    }

    public List<Row> getRows() {
        return rows;
    }

}

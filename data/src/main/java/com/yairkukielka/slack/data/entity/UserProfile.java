package com.yairkukielka.slack.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * User Profile used in the data layer.
 */
public class UserProfile {

    private String title;
    @SerializedName("image_48")
    private String image48;
    @SerializedName("image_72")
    private String image72;
    @SerializedName("image_192")
    private String image192;


    public UserProfile() {
        //empty
    }

    public String getImage48() {
        return image48;
    }

    public void setImage48(String image48) {
        this.image48 = image48;
    }

    public String getImage72() {
        return image72;
    }

    public void setImage72(String image72) {
        this.image72 = image72;
    }

    public String getImage192() {
        return image192;
    }

    public void setImage192(String image192) {
        this.image192 = image192;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Profile Details *****\n");
        stringBuilder.append("title=" + this.getTitle() + "\n");
        stringBuilder.append("image192=" + this.getImage192() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}

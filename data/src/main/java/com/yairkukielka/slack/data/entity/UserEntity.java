package com.yairkukielka.slack.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * User Entity used in the data layer.
 */
public class UserEntity {

    private String id;
    private String name;
    @SerializedName("real_name")
    private String realName;
    private UserProfile profile;


    public UserEntity() {
        //empty
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Entity Details *****\n");
        stringBuilder.append("id=" + this.getId() + "\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("real_name=" + this.getRealName() + "\n");
        stringBuilder.append("profile=" + ((this.getProfile() != null) ? getProfile().toString() : "") + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}

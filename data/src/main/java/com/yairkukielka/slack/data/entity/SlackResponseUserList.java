package com.yairkukielka.slack.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Slack Response used in the data layer.
 */
public class SlackResponseUserList extends SlackResponse {

    @SerializedName("members")
    private List<UserEntity> members;

    public SlackResponseUserList() {
        //empty
    }

    public List<UserEntity> getMembers() {
        return members;
    }

    public void setMembers(List<UserEntity> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Slack Response List Details *****\n");
        stringBuilder.append(super.toString() + "\n");
        stringBuilder.append("error=" + this.getError() + "\n");
        stringBuilder.append("members=" + this.getMembers() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}

package com.yairkukielka.slack.data.entity;

/**
 * Slack Response used in the data layer.
 */
public class SlackResponseUser extends SlackResponse {

    private UserEntity user;

    public SlackResponseUser() {
        //empty
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Slack Response User Details *****\n");
        stringBuilder.append(super.toString() + "\n");
        stringBuilder.append("ok=" + this.getOk() + "\n");
        stringBuilder.append("error=" + this.getError() + "\n");
        stringBuilder.append("user=" + this.getUser().toString() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}

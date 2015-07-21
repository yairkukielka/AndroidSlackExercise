package com.yairkukielka.slack.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Slack Response used in the data layer.
 */
public class SlackResponse {

    @SerializedName("ok")
    private String ok;

    @SerializedName("error")
    private String error;

    public SlackResponse() {
        //empty
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Slack Response Details *****\n");
        stringBuilder.append("ok=" + this.getOk() + "\n");
        stringBuilder.append("error=" + this.getError() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}

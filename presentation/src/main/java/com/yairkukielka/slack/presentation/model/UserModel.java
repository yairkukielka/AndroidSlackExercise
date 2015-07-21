package com.yairkukielka.slack.presentation.model;

/**
 * Class that represents a user in the presentation layer.
 */
public class UserModel {
    private final String id;
    private String name;

    public UserModel(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Model Details *****\n");
        stringBuilder.append("id=" + this.getId() + "\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}

package com.yairkukielka.slack.presentation.model;

/**
 * Class that represents a user in the presentation layer.
 */
public class UserModel {
    private final String id;
    private String name;
    private String realName;
    private String title;
    private String image48;
    private String image72;
    private String image192;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Model Details *****\n");
        stringBuilder.append("id=" + this.getId() + "\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("real_name=" + this.getRealName() + "\n");
        stringBuilder.append("title=" + this.getTitle() + "\n");
        stringBuilder.append("image192=" + this.getImage192() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}

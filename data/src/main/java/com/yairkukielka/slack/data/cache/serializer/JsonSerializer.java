package com.yairkukielka.slack.data.cache.serializer;

import com.google.gson.Gson;
import com.yairkukielka.slack.domain.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class JsonSerializer {

    private final Gson gson = new Gson();

    @Inject
    public JsonSerializer() {
    }

    /**
     * Serialize an object to Json.
     *
     * @param user {@link User} to serialize.
     */
    public String serialize(User user) {
        return gson.toJson(user, User.class);
    }

    /**
     * Deserialize a json representation of an object.
     *
     * @param jsonString A json string to deserialize.
     * @return {@link User}
     */
    public User deserialize(String jsonString) {
        return gson.fromJson(jsonString, User.class);
    }
}

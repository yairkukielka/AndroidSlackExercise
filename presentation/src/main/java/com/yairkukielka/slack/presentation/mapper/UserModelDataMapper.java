package com.yairkukielka.slack.presentation.mapper;

import com.yairkukielka.slack.domain.User;
import com.yairkukielka.slack.presentation.internal.di.PerActivity;
import com.yairkukielka.slack.presentation.model.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link User} (in the domain layer) to {@link UserModel} in the
 * presentation layer.
 */
@PerActivity
public class UserModelDataMapper {

    @Inject
    public UserModelDataMapper() {
    }

    /**
     * Transform a {@link User} into an {@link UserModel}.
     *
     * @param user Object to be transformed.
     * @return {@link UserModel}.
     */
    public UserModel transform(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        UserModel userModel = new UserModel(user.getId());
        userModel.setName(user.getName());
        userModel.setRealName(user.getRealName());
        userModel.setTitle(user.getTitle());
        userModel.setImage48(user.getImage48());
        userModel.setImage72(user.getImage72());
        userModel.setImage192(user.getImage192());

        return userModel;
    }

    /**
     * Transform a Collection of {@link User} into a Collection of {@link UserModel}.
     *
     * @param usersCollection Objects to be transformed.
     * @return List of {@link UserModel}.
     */
    public Collection<UserModel> transform(Collection<User> usersCollection) {
        Collection<UserModel> userModelsCollection;
        if (usersCollection != null && !usersCollection.isEmpty()) {
            userModelsCollection = new ArrayList<>(usersCollection.size());
            for (User user : usersCollection) {
                userModelsCollection.add(transform(user));
            }
        } else {
            userModelsCollection = Collections.emptyList();
        }

        return userModelsCollection;
    }
}

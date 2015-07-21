package com.yairkukielka.slack.data.cache;

import android.content.Context;

import com.yairkukielka.slack.data.cache.serializer.JsonSerializer;
import com.yairkukielka.slack.data.exception.UserNotFoundException;
import com.yairkukielka.slack.domain.User;
import com.yairkukielka.slack.domain.executor.ThreadExecutor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link UserCache} implementation.
 */
@Singleton
public class UserCacheImpl implements UserCache {

    private static final String USER_SEPARATOR = "###";

    private static final String DEFAULT_FILE_NAME = "users_file";

    private final Context context;
    private final File cacheDir;
    private final JsonSerializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link UserCacheImpl}.
     *
     * @param context             A
     * @param userCacheSerializer {@link JsonSerializer} for object serialization.
     * @param fileManager         {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    public UserCacheImpl(Context context, JsonSerializer userCacheSerializer,
                         FileManager fileManager, ThreadExecutor executor) {
        if (context == null || userCacheSerializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = userCacheSerializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    @Override
    public synchronized Observable<List<User>> getAll() {
        List<User> userList = getUserListFromDisk();
        if (userList != null && !userList.isEmpty()) {
            return Observable.just(userList);
        } else {
            return Observable.empty();
        }
    }

    @Override
    public synchronized Observable<User> get(final String userId) {
        List<User> userList = getUserListFromDisk();
        if (userList != null && !userList.isEmpty()) {
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                if (userId.equals(user.getId())) {
                    return Observable.just(user);
                }
            }
        }
        return Observable.error(new UserNotFoundException());
    }

    @Override
    public synchronized void evictAndPutAll(List<User> users) {
        if (users != null && !users.isEmpty()) {
            //evict user list
            evictAll();

            File userFile = this.buildFile();
            String fileContent = userListToFileContent(users);
            if (fileContent != null) {
                this.executeAsynchronously(new CacheWriter(this.fileManager, userFile, fileContent));
            }

        }
    }

    @Override
    public boolean isCached(String userId) {
        File userEntitiyFile = this.buildFile();
        return this.fileManager.exists(userEntitiyFile);
    }

    @Override
    public synchronized void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
    }

    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * @return A valid file.
     */
    private File buildFile() {
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);

        return new File(fileNameBuilder.toString());
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    /**
     * {@link Runnable} class for writing to disk.
     */
    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }

    /**
     * Writes the file content from a list of users.
     * @param users the list of users.
     * @return the file content.
     */
    private String userListToFileContent(List<User> users) {
        if (users != null && !users.isEmpty()) {
            StringBuilder userListStringBuilder = new StringBuilder();
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                String jsonString = this.serializer.serialize(user);
                userListStringBuilder.append(jsonString);
                userListStringBuilder.append(USER_SEPARATOR);
            }
            return userListStringBuilder.toString();
        } else {
            return null;
        }
    }

    /**
     * Reads the file content and returns a list of users.
     * @param fileContent the file content.
     * @return the list of users.
     */
    private List<User> fileContentToUserList(String fileContent) {
        List<User> userList = null;
        if (fileContent != null && !fileContent.isEmpty()) {
            String[] usersArray = fileContent.split(USER_SEPARATOR);
            if (usersArray.length > 0) {
                userList = new ArrayList<>();
                for (String userString : usersArray) {
                    User user = UserCacheImpl.this.serializer.deserialize(userString);
                    userList.add(user);
                }
            }
        }
        return userList;
    }

    /**
     * Gets the user list from disk.
     * @return The user list.
     */
    private List<User> getUserListFromDisk() {
        File userFile = this.buildFile();
        String fileContent = UserCacheImpl.this.fileManager.readFileContent(userFile);
        return fileContentToUserList(fileContent);
    }

}
package com.yairkukielka.slack.data.cache;

import android.content.Context;

import com.yairkukielka.slack.data.ApplicationTestCase;
import com.yairkukielka.slack.data.cache.serializer.JsonSerializer;
import com.yairkukielka.slack.data.executor.JobExecutor;
import com.yairkukielka.slack.domain.User;
import com.yairkukielka.slack.domain.executor.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import rx.observers.TestSubscriber;


public class UserCacheTest extends ApplicationTestCase {

    private static final String TEST_ID_1 = "Test id 1";
    private static final String TEST_NAME_1 = "Test name 1";
    private static final String TEST_ID_2 = "Test id 2";
    private static final String TEST_NAME_2 = "Test name 2";

    private User mUser1, mUser2;
    private UserCache userCache;

    Context context;
    JsonSerializer userCacheSerializer;
    FileManager fileManager;
    ThreadExecutor executor;

    @Before
    public void setUp() {
        // Create Users to persist.
        mUser1 = new User(TEST_ID_1);
        mUser1.setName(TEST_NAME_1);
        mUser2 = new User(TEST_ID_2);
        mUser2.setName(TEST_NAME_2);


        context = RuntimeEnvironment.application.getApplicationContext();
        userCacheSerializer = new JsonSerializer();
        fileManager = new FileManager();
        executor = new JobExecutor();
        userCache = new UserCacheImpl(context, userCacheSerializer, fileManager, executor);
    }

    /**
     * Saves 2 users and retrieves them from the user cache.
     */
    @Test
    public void saveAndGet2Users() throws Exception {
        // initialization
        TestSubscriber<List<User>> testSubscriber = new TestSubscriber<>();
        List<User> listToWrite = new ArrayList<>(2);
        listToWrite.add(mUser1);
        listToWrite.add(mUser2);

        //save the users
        userCache.evictAndPutAll(listToWrite);

        //retrieve the users
        userCache.getAll().subscribe(testSubscriber);

        //assertions
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(listToWrite);
    }

}
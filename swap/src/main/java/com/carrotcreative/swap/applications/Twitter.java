package com.carrotcreative.swap.applications;

import android.content.Context;
import android.content.Intent;

import com.carrotcreative.swap.base.IntentUpdater;
import com.carrotcreative.swap.base.SwapApplication;
import com.carrotcreative.swap.base.SwapException;

public class Twitter extends SwapApplication{

    // ========== Singleton ==========

    private static Twitter sInstance;
    public static Twitter getInstance(Context context)
    {
        if(sInstance == null)
            sInstance = new Twitter(context);
        return sInstance;
    }

    public static void kill()
    {
        sInstance = null;
    }

    // ========== Base Package ==========

    private static final String TWITTER_BASE_PACKAGE = "com.twitter.android";

    // ========== Parameters ==========

    private static final String LANGUAGE = "lang";
    private static final String STATUS_ID = "status_id";
    private static final String SCREEN_NAME = "screen_name";
    private static final String USER_ID = "user_id";
    private static final String TEXT = "text";

    // ========== Intents ==========

    private static final String BASE_NATIVE_URI = "twitter://";
    private static final String BASE_WEB_URI = "https://twitter.com/intent/";

    private static final String INTENT_VIEW_TWEET = BASE_NATIVE_URI + "status?" + STATUS_ID + "=" + param(STATUS_ID);
    private static final String INTENT_VIEW_USER_FROM_SCREEN_NAME = BASE_NATIVE_URI + "user?" + SCREEN_NAME + "=" + param(SCREEN_NAME);
    private static final String INTENT_VIEW_USER_FROM_USER_ID = BASE_NATIVE_URI + "user?" + USER_ID + "=" + param(USER_ID);
    private static final String INTENT_TWEET = BASE_WEB_URI + "tweet?" + TEXT + "=" + param(TEXT);

    // ========== Class ==========

    private Twitter(Context context)
    {
        mContext = context;
    }

    @Override
    public String getBasePackage()
    {
        return TWITTER_BASE_PACKAGE;
    }

    // ========== Functions ==========

    /**
     * Views a specific tweet
     *
     * @param statusID The ID of the tweet
     */
    public void viewTweet(String statusID) throws SwapException
    {
        // Preparing URI
        String uri = INTENT_VIEW_TWEET;
        uri = uri.replace(param(STATUS_ID), statusID);

        // Starting Twitter Activity
        swap(uri, null);
    }

    /**
     * Views a specific user
     *
     * @param screenName The users twitter handle
     */
    public void viewUserFromScreenName(String screenName) throws SwapException
    {
        // Preparing URI
        String uri = INTENT_VIEW_USER_FROM_SCREEN_NAME;
        uri = uri.replace(param(SCREEN_NAME), screenName);

        // Starting Twitter Activity
        swap(uri, null);
    }

    /**
     * Views a specific user
     *
     * @param userID The user's unique ID
     */
    public void viewUserFromUserID(String userID) throws SwapException
    {
        // Preparing URI
        String uri = INTENT_VIEW_USER_FROM_USER_ID;
        uri = uri.replace(param(USER_ID), userID);

        // Starting Twitter Activity
        swap(uri, null);
    }

    /**
     * Opens the app to make a new tweet
     *
     * @param text The start text
     */
    public void tweet(String text) throws SwapException
    {
        // Preparing URI
        String uri = INTENT_TWEET;
        uri = uri.replace(param(TEXT), text);

        // Starting Twitter Activity
        swap(uri, new IntentUpdater() {
            @Override
            public void updateIntent(Intent i) {
                i.setPackage(TWITTER_BASE_PACKAGE);
            }
        });
    }

}
package com.carrotcreative.swap.applications;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.carrotcreative.swap.base.SwapApplication;
import com.carrotcreative.swap.base.SwapException;

/**
 *  From: https://dev.twitter.com/docs/intents
 */
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
    private static final String IN_REPLY_TO = "in_reply_to";
    private static final String TWEET_ID = "tweet_id";
    private static final String TEXT = "text";

    // ========== Intents ==========

    private static final String INTENT_VIEW_TWEET = "twitter://status?" + STATUS_ID + "=" + param(STATUS_ID);
    private static final String INTENT_VIEW_USER_FROM_SCREEN_NAME = "twitter://user?" + SCREEN_NAME + "=" + param(SCREEN_NAME);
    private static final String INTENT_VIEW_USER_FROM_USER_ID = "twitter://user?" + USER_ID + "=" + param(USER_ID);
    private static final String INTENT_REPLY = "twitter://tweet?" + IN_REPLY_TO + "=" + param(IN_REPLY_TO);
    private static final String INTENT_RETWEET = "twitter://retweet?" + TWEET_ID + "=" + param(TWEET_ID);
    private static final String INTENT_FAVORITE = "twitter://favorite?" + TWEET_ID + "=" + param(TWEET_ID);
    private static final String INTENT_TWEET = "twitter://tweet?" + TEXT + "=" + param(TEXT);

    // ========== Languages ==========

    public static final String LANGUAGE_APPEND = "&" + LANGUAGE + "=" + param(LANGUAGE);

    public static final String LANG_ENGLISH = "en";
    public static final String LANG_ITALIAN = "it";
    public static final String LANG_SPANISH = "es";
    public static final String LANG_FRENCH = "fr";
    public static final String LANG_KOREAN = "ko";
    public static final String LANG_JAPANESE = "ja";

    // ========== Class ==========

    private Context mContext;
    public boolean mLanguageIsSet;
    public String mActiveLanguage;

    private Twitter(Context context)
    {
        mContext = context;
        mLanguageIsSet = false;
    }

    /**
     * Set the language and adds the lang param to every
     * method call after this one.
     *
     * @param language One of the strings in this class
     *               that starts with LANG_
     */
    public void setLanguage(String language)
    {
        mLanguageIsSet = true;
        mActiveLanguage = language;
    }

    /**
     * This disables any language that was previously set
     */
    public void clearLanguage()
    {
        mLanguageIsSet = false;
        mActiveLanguage = null;
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
        if(isInstalled(mContext))
        {
            String uri = INTENT_VIEW_TWEET;
            uri = uri.replace(param(STATUS_ID), statusID);
            if (mLanguageIsSet) uri = applyLanguage(uri);

            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            mContext.startActivity(i);
        }
        else
        {
            throw new SwapException(SwapException.APPLICATION_NOT_INSTALLED);
        }
    }

    // ========== Helper ==========

    public String applyLanguage(String uri)
    {
        return uri + LANGUAGE_APPEND.replace(param(LANGUAGE), mActiveLanguage);
    }

}
package com.carrotcreative.swap.applications;

import android.content.Context;

import com.carrotcreative.swap.base.SwapApplication;
import com.carrotcreative.swap.base.SwapException;

public class Facebook extends SwapApplication
{

    // ========== Singleton ==========

    private static Facebook sInstance;

    public static Facebook getInstance(Context context)
    {
        if(sInstance == null)
        {
            sInstance = new Facebook(context);
        }
        return sInstance;
    }

    public static void kill()
    {
        sInstance = null;
    }

    // ========== Base Package ==========

    private static final String FACEBOOK_BASE_PACKAGE = "com.facebook.katana";

    // ========== Parameters ==========

    private static final String USER_NAME = "user_name";

    // ========== Intents ==========

    private static final String BASE_NATIVE_URI = "fb://";

    private static final String INTENT_PROFILE = BASE_NATIVE_URI + "profile/" + USER_NAME;

    // ========== Class ==========

    private Facebook(Context context)
    {
        mContext = context;
    }

    @Override
    public String getBasePackage()
    {
        return FACEBOOK_BASE_PACKAGE;
    }

    // ========== Functions ==========

    /**
     * Views a specific profile
     *
     * @param userName The ID of the tweet
     */
    public void viewProfile(String userName) throws SwapException
    {
        // Preparing URI
        String uri = INTENT_PROFILE;
        uri = uri.replace(param(USER_NAME), userName);

        // Starting Facebook Activity
        swap(uri, null);
    }

}
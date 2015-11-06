package com.carrotcreative.swap.applications;

import android.content.Context;
import android.content.Intent;

import com.carrotcreative.swap.base.IntentUpdater;
import com.carrotcreative.swap.base.SwapApplication;
import com.carrotcreative.swap.base.SwapException;

/**
 * Created by angelsolis on 11/6/15.
 */
public class LinkedIn extends SwapApplication
{

    // ========== Singleton ==========

    private static LinkedIn sInstance;

    public static LinkedIn getInstance(Context context)
    {
        if(sInstance == null)
        {
            sInstance = new LinkedIn(context);
        }
        return sInstance;
    }

    public static void kill()
    {
        sInstance = null;
    }

    // ========== Base Package ==========

    private static final String LINKEDIN_BASE_PACKAGE = "com.linkedin.android";

    // ========== Parameters ==========

    private static final String LANGUAGE = "lang";
    private static final String USER_NAME = "user_name";
    private static final String GROUP_ID = "user_id";

    // ========== Intents ==========

    private static final String BASE_NATIVE_URI = "linkedin://";
    private static final String BASE_WEB_URI = "https://linkedin.com/";

    private static final String INTENT_VIEW_PROFILE = BASE_NATIVE_URI + "profile/" + USER_NAME;
    private static final String INTENT_VIEW_GROUP = BASE_NATIVE_URI + "group/" + GROUP_ID;

    // ========== Class ==========

    private LinkedIn(Context context)
    {
        mContext = context;
    }

    @Override
    public String getBasePackage()
    {
        return LINKEDIN_BASE_PACKAGE;
    }

    // ========== Functions ==========

    /**
     * Views a specific profile
     *
     * @param userName The ID of user
     */
    public void viewProfile(String userName) throws SwapException
    {
        // Preparing URI
        String uri = INTENT_VIEW_PROFILE;
        uri = uri.replace(param(USER_NAME), userName);

        // Starting LinkedIn Activity
        swap(uri, new IntentUpdater()
        {
            @Override
            public void updateIntent(Intent i)
            {
                i.setPackage(LINKEDIN_BASE_PACKAGE);
            }
        });
    }

    /**
     * Views a specific group
     *
     * @param groupId The ID of group
     */
    public void viewGroup(String groupId) throws SwapException
    {
        // Preparing URI
        String uri = INTENT_VIEW_GROUP;
        uri = uri.replace(param(GROUP_ID), groupId);

        // Starting LinkedIn Activity
        swap(uri, new IntentUpdater()
        {
            @Override
            public void updateIntent(Intent i)
            {
                i.setPackage(LINKEDIN_BASE_PACKAGE);
            }
        });
    }
}

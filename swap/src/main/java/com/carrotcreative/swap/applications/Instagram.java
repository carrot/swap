package com.carrotcreative.swap.applications;

import android.content.Context;
import android.graphics.Bitmap;

import com.carrotcreative.swap.base.SwapApplication;
import com.carrotcreative.swap.base.SwapException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by angelsolis on 11/6/15.
 */
public class Instagram extends SwapApplication
{

    // ========== Singleton ==========

    private static Instagram sInstance;

    public static Instagram getInstance(Context context)
    {
        if(sInstance == null)
        {
            sInstance = new Instagram(context);
        }
        return sInstance;
    }

    public static void kill()
    {
        sInstance = null;
    }

    // ========== Base Package ==========

    private static final String TWITTER_BASE_PACKAGE = "com.instagram.android";

    // ========== Parameters ==========

    private static final String LANGUAGE = "lang";
    private static final String USER_NAME = "user_name?";
    private static final String PICTURE = "picture?";
    private static final String MIME_TYPE_IMAGE = "image/*";
    private static final String MIME_TYPE_VIDEO = "video/*";

    // ========== Intents ==========

    private static final String BASE_URI = "http://instagram.com/";

    private static final String INTENT_VIEW_PROFILE = BASE_URI + "_u/" + USER_NAME;
    private static final String INTENT_VIEW_PICTURE = BASE_URI + "_p/" + PICTURE;

    // ========== Class ==========

    private Instagram(Context context)
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
     * Views a specific profile
     *
     * @param userName username
     */
    public void viewProfile(String userName) throws SwapException
    {
        // Preparing URI
        String uri = INTENT_VIEW_PROFILE;
        uri = uri.replace(param(USER_NAME), userName);

        // Starting Instagram Activity
        swap(uri, null, null, false);
    }

    /**
     * Views a specific picture
     *
     * @param path image path
     */
    public void viewPicture(String path) throws SwapException
    {
        // Preparing URI
        String uri = INTENT_VIEW_PICTURE;
        uri = uri.replace(param(PICTURE), path);

        // Starting Instagram Activity
        swap(uri, null, null, false);
    }

    /**
     * Share an specific Image from Bitmap
     *
     * @param bitmap image path
     */
    public void shareImageBitmap(Bitmap bitmap) throws SwapException, FileNotFoundException,
            IOException
    {
        File file = new File(mContext.getCacheDir(), "temp_instagram" + ".png");
        FileOutputStream fOut = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        fOut.flush();
        fOut.close();
        file.setReadable(true, false);

        // Preparing URI
        String uri = INTENT_VIEW_PICTURE;
        uri = uri.replace(param(PICTURE), file.getAbsolutePath());

        // Starting Instagram Activity
        swap(uri, null, MIME_TYPE_IMAGE, true);
    }

    /**
     * Share an specific Image from File
     *
     * @param mediaPath image path
     */
    public void shareImageFromFile(String mediaPath) throws SwapException
    {
        // Starting Instagram Activity
        swap(mediaPath, null, MIME_TYPE_IMAGE, true);
    }

    /**
     * Share an specific Video from File
     *
     * @param mediaPath image path
     */
    public void shareVideoFromFile(String mediaPath) throws SwapException
    {
        // Starting Instagram Activity
        swap(mediaPath, null, MIME_TYPE_VIDEO, true);
    }

}

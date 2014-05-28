package com.carrotcreative.swap.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

public abstract class SwapApplication {

    protected Context mContext;

    public boolean isInstalled()
    {
        try{
            PackageManager pm = mContext.getPackageManager();
            assert pm != null;

            // This function throws the NameNotFoundException
            ApplicationInfo info = pm.getApplicationInfo(getBasePackage(), 0 );

            // If we get here, the application is installed
            return true;
        }
        catch( PackageManager.NameNotFoundException e )
        {
            return false;
        }
    }

    public void swap(String uri, IntentUpdater intentUpdater) throws SwapException
    {
        // Updating URI with constant update
        uri = updateUriBeforeSwap(uri);

        // Checking if installed
        if(!isInstalled())
            throw new SwapException(SwapException.APPLICATION_NOT_INSTALLED);

        // Sending off the intent
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        if(intentUpdater != null) intentUpdater.updateIntent(i);
        mContext.startActivity(i);
    }

    public static String param(String param)
    {
        return "{{" + param + "}}";
    }

    public abstract String getBasePackage();

    /**
     * Override this to handle any URI updates with every swap
     */
    public String updateUriBeforeSwap(String uri)
    {
        return uri;
    }

}
package com.carrotcreative.swap.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public abstract class SwapApplication {

    public boolean isInstalled(Context context)
    {
        try{
            PackageManager pm = context.getPackageManager();
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

    public abstract String getBasePackage();

    public static String param(String param)
    {
        return "{{" + param + "}}";
    }

}
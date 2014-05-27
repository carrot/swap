package com.carrotcreative.swap.base;

public class SwapException extends Exception{

    public static final String APPLICATION_NOT_INSTALLED = Swap.LOG_TAG + "Application is not installed";

    public SwapException(String message) {
        super(message);
    }

    public SwapException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
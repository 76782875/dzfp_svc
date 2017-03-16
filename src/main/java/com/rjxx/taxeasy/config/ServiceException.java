package com.rjxx.taxeasy.config;

/**
 * Created by xlm on 2017/3/14.
 */
public class ServiceException extends RuntimeException{
    private static final long serialVersionUID = 8624944628363400977L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause,
                            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}

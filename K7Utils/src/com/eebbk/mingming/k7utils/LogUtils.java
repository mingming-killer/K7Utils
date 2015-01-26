package com.eebbk.mingming.k7utils;

import android.util.Log;

/**
 * 
 * Some Log related tools.
 * 
 * @author humingming <humingming@oaserver.dw.gdbbk.com>
 *
 */
public final class LogUtils {
	
	/** This level don't show any log */
	public final static int SILENCE_LEVEL = 9999;
	
	private static int sLogLevel = Log.VERBOSE;
	
	/**
	 * Set the log level, if you set the level is higher than you 
	 * use, it will don't output the log
	 * 
	 * @param level Log level
	 */
	public final static void setLogLevel(int level) { 
		sLogLevel = level;
	}

    public final static int v(String tag, String msg) {
        return println(Log.VERBOSE, tag, msg);
    }

    public final static int v(String tag, String msg, Throwable tr) {
        return println(Log.VERBOSE, tag, msg + '\n' + getStackTraceString(tr));
    }

    public final static int d(String tag, String msg) {
        return println(Log.DEBUG, tag, msg);
    }

    public final static int d(String tag, String msg, Throwable tr) {
        return println(Log.DEBUG, tag, msg + '\n' + getStackTraceString(tr));
    }

    public final static int i(String tag, String msg) {
        return println(Log.INFO, tag, msg);
    }

    public final static int i(String tag, String msg, Throwable tr) {
        return println(Log.INFO, tag, msg + '\n' + getStackTraceString(tr));
    }

    public final static int w(String tag, String msg) {
        return println(Log.WARN, tag, msg);
    }

    public final static int w(String tag, String msg, Throwable tr) {
        return println(Log.WARN, tag, msg + '\n' + getStackTraceString(tr));
    }

    public final static int w(String tag, Throwable tr) {
        return println(Log.WARN, tag, getStackTraceString(tr));
    }

    public final static int e(String tag, String msg) {
        return println(Log.ERROR, tag, msg);
    }

    public final static int e(String tag, String msg, Throwable tr) {
        return println(Log.ERROR, tag, msg + '\n' + getStackTraceString(tr));
    }

    public final static int wtf(String tag, String msg) {
        return Log.wtf(tag, msg, null);
    }

    public final static int wtf(String tag, Throwable tr) {
        return Log.wtf(tag, tr.getMessage(), tr);
    }

    public final static int wtf(String tag, String msg, Throwable tr) {
        return Log.wtf(tag, msg, tr);
    }

    public final static String getStackTraceString(Throwable tr) {
        return Log.getStackTraceString(tr);
    }

    public final static int println(int priority, String tag, String msg) {
        //if (Log.isLoggable(tag, priority)) {
        if (priority >= sLogLevel) {
            return Log.println(priority, tag, msg);
        } else {
            return 0;
        }
    }
}

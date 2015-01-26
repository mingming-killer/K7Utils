package com.eebbk.mingming.k7utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.ColorStateList;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.view.Display;

/**
 * 
 * Some application related small tools.
 * 
 * @author humingming <humingming@oaserver.dw.gdbbk.com>
 *
 */
public final class AppUtils {
	
	@SuppressWarnings("unused")
	private final static String TAG = "AppUtils";
	
	/** Status: the specified apk is installed */
	public final static int STATUS_INSTALLED = 0;
	
	/** Status: the specified apk can be updated */
	public final static int STATUS_UPDATED = 1;
	
	/** Status: the specified apk is not installed */
	public final static int STATUS_NOT_INSTALLED = 2;
	
	
	/**
	 * Check application whether installed.
	 * 
	 * @param context Object of {@link Context}.
	 * @param pkgName Application package name.
	 * @return True means installed, false not install.
	 */
	public final static boolean checkAppInstalled(Context context, String pkgName) {
		if (STATUS_NOT_INSTALLED == AppUtils.getAppStatus(context, pkgName, 0)) {
			return false;
		} else {
			return true;
		}
    }
	
	/**
	 * Convert milliseconds to "yyyy-MM-dd" format string.
	 * 
	 * @param milliseconds Milliseconds.
	 * @return Formated string.
	 */
	public final static String convertDate(long milliseconds) {
		Date date = new Date(milliseconds);
		SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormate.format(date);
	}
	
	/**
	 * Get give application status: installed, not installed or update.
	 * 
	 * @param context Object of {@link Context}.
	 * @param pkgName Package name of app.
	 * @param code Application version code.
	 * @return {@link #STATUS_INSTALLED}, {@link #STATUS_NOT_INSTALLED}
	 * 	or {@link #STATUS_UPDATED}.
	 */
	public final static int getAppStatus(Context context, String pkgName, int code) {
		if (null == pkgName || null == context) {
			return STATUS_NOT_INSTALLED;
		}
		
		PackageInfo pkgInfo = null;
		PackageManager pm = context.getPackageManager();
		
		if (null == pm) {
			return STATUS_NOT_INSTALLED;
		}
		
		try {
			pkgInfo = pm.getPackageInfo(pkgName, 0);
			if (null == pkgInfo) {
				return STATUS_NOT_INSTALLED;
			} else {
				if (pkgInfo.versionCode == code) {
					return STATUS_INSTALLED;
				} else if (code > pkgInfo.versionCode) {
					return STATUS_UPDATED;
				} else {
					return STATUS_INSTALLED;
				}
			}
		} catch (Exception e) {
			return STATUS_NOT_INSTALLED;
		}
	}
	
	/**
	 * Get give package version name.
	 * 
	 * @param context Object of {@link Context}.
	 * @param pkg Package name.
	 * @return {@link String} of version name.
	 */
	public final static String getAppVersionName(Context context, String pkg) {
        PackageManager pm = context.getPackageManager();
        try {  
            PackageInfo info = pm.getPackageInfo(pkg, 0);
            return info.versionName;
        } catch (NameNotFoundException e) {  
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * Get give package version code.
	 * 
	 * @param context Object of {@link Context}.
	 * @param pkg package name.
	 * @return Package version code.
	 */
	public final static int getAppVersionCode(Context context, String pkg) {
        PackageManager pm = context.getPackageManager();
        try {  
            PackageInfo info = pm.getPackageInfo(pkg, 0);
            return info.versionCode;
        } catch (NameNotFoundException e) {  
            e.printStackTrace();
            return 0;
        }
    }
	
	/**
	 * Get give package apk file size.
	 * 
	 * @param context Object of {@link Context}.
	 * @param pkg Package name.
	 * @return Apk file size by bytes.
	 */
	public final static long getAppSize(Context context, String pkg) {
		if (null == context || null == pkg) {
			return 0;
		}
		
		PackageManager pm = context.getPackageManager();
		try {
			ApplicationInfo info = pm.getApplicationInfo(pkg, 0);
			File file = new File(info.sourceDir);
			return file.length();
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Get give package app install date.
	 * 
	 * @param context Object of {@link Context}.
	 * @param pkg Package name.
	 * @return Measured in milliseconds since January 1st, 1970, midnight. Returns 0 if the file does not exist.
	 */
	public final static long getAppInstallDate(Context context, String pkg) {
		if (null == context || null == pkg) {
			return System.currentTimeMillis();
		}
		
		PackageManager pm = context.getPackageManager();
		try {
			ApplicationInfo info = pm.getApplicationInfo(pkg, 0);
			
			// 2.3(API level 9) ApplicationInfo have a method to get install time.
			// but we can't use it. (for support 2.2 -_-||).
			File file = new File(info.sourceDir);
			return file.lastModified();
			
		} catch (Exception e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}
	
	/**
	 * Safely wrapper of {@link Resources#getInteger(int)}
	 * 
	 * @param context
	 * @param resID
	 * @param defValue default value when get failed.
	 * @return
	 */
	public final static int getIntegerSafely(Context context, int resID, int defValue) {
		try {
			return context.getResources().getInteger(resID);
		} catch (Exception e) {
			e.printStackTrace();
			return defValue;
		}
	}
	
	/**
	 * Convert a string format float value to Float.
	 * 
	 * @param context
	 * @param resID
	 * @param defValue
	 * @return
	 */
	public final static float getFloatSafely(Context context, int resID, float defValue) {
		try {
			String strValue = context.getResources().getString(resID);
			if (null == strValue) {
				return defValue;
			}
			
			return Float.parseFloat(strValue);
			
		} catch (Exception e) {
			e.printStackTrace();
			return defValue;
		}
	}
	
	/**
	 * Safely wrapper of {@link Resources#getString(int)}
	 * 
	 * @param context
	 * @param resID
	 * @param defValue default string when get failed.
	 * @return
	 */
	public final static String getStringSafely(Context context, int resID, String defValue) {
		try {
			return context.getResources().getString(resID);
		} catch (Exception e) {
			e.printStackTrace();
			return defValue;
		}
	}
	
	/**
	 * Safely wrapper of {@link Resources#getString(int, Object...)}
	 * 
	 * @param context
	 * @param resID
	 * @param defValue default string when get failed.
	 * @param formatArgs
	 * @return
	 */
	public final static String getStringSafely(Context context, int resID, String defValue, Object... formatArgs) {
		try {
			return context.getResources().getString(resID, formatArgs);
		} catch (Exception e) {
			e.printStackTrace();
			return defValue;
		}
	}
	
	/**
	 * Safely wrapper of {@link Resources#getDimension(int)}
	 * 
	 * @param context
	 * @param resID
	 * @param defValue default dimension pixel when get failed.
	 * @return
	 */
	public final static float getDimensionSafely(Context context, int resID, float defValue) {
		try {
			return context.getResources().getDimension(resID);
		} catch (Exception e) {
			e.printStackTrace();
			return defValue;
		}
	}
	
	/**
	 * Safely wrapper of {@link Resources#getColor(int)}
	 * 
	 * @param context
	 * @param resID
	 * @param defValue default color value when get failed.
	 * @return
	 */
	public final static int getColorSafely(Context context, int resID, int defValue) {
		try {
			return context.getResources().getColor(resID);
		} catch (Exception e) {
			e.printStackTrace();
			return defValue;
		}
	}
	
	/**
	 * Safely wrapper of {@link Resources#getColorStateListSafely(int)}
	 * 
	 * @param context
	 * @param resID
	 * @return
	 */
	public final static ColorStateList getColorStateListSafely(Context context, int resID) {
		try {
			return context.getResources().getColorStateList(resID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Safely wrapper of {@link Resources#getDrawable(int)}
	 * 
	 * @param context
	 * @param resID
	 * @param defValue default drawable when get failed.
	 * @return
	 */
	public final static Drawable getDrawableSafely(Context context, int resID, Drawable defValue) {
		try {
			return context.getResources().getDrawable(resID);
		} catch (Exception e) {
			e.printStackTrace();
			return defValue;
		}
	}
	
	/**
	 * Safely wrapper of {@link Resources#getStringArray(int)}
	 * 
	 * @param context
	 * @param resID
	 * @return
	 */
	public final static String[] getStringArraySafely(Context context, int resID) {
		try {
			return context.getResources().getStringArray(resID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Safely wrapper of {@link Resources#getIntArray(int)}
	 * 
	 * @param context
	 * @param resID
	 * @return
	 */
	public final static int[] getIntegerArraySafely(Context context, int resID) {
		try {
			return context.getResources().getIntArray(resID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Get current {@link Activity} screen width.
	 * 
	 * @param activity Object of {@link Activity}
	 * @return Screen width, failed will return 0.
	 */
	public final static int getScreenWidth(Activity activity) {
		if (null == activity) {
			return 0;
		}
		
		try {
			Display d = activity.getWindowManager().getDefaultDisplay();
			return d.getWidth();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Get current {@link Activity} screen height.
	 * 
	 * @param activity Object of {@link Activity}
	 * @return Screen height, failed will return 0.
	 */
	public final static int getScreenHeight(Activity activity) {
		if (null == activity) {
			return 0;
		}
		
		try {
			Display d = activity.getWindowManager().getDefaultDisplay();
			return d.getHeight();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Safely wrapper of {@link Context#startActivity(Intent)}.
	 * 
	 * @param context
	 * @param intent
	 * 
	 * @return True means start success, otherwise false.
	 */
	public final static boolean startActivitySafely(Context context, Intent intent) {
		try {
			context.startActivity(intent);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Safely wrapper of {@link Context#startActivityForResult}.
	 * 
	 * @param context
	 * @param intent
	 * 
	 * @return True means start success, otherwise false.
	 */
	public final static boolean startActivityForResultSafely(Activity context, Intent intent, int requestCode) {
		try {
			context.startActivityForResult(intent, requestCode);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}

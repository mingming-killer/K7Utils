package com.eebbk.mingming.k7utils;

import java.security.MessageDigest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Some network related small tools.
 * 
 * @author humingming <humingming@oaserver.dw.gdbbk.com>
 * 
 */
public class NetUtils {
	
	private final static String TAG = "NetUtils";

	/**
	 * Check whether connect to internet.
	 * see {@link #checkConnectInternet(Context, boolean)}.
	 * 
	 * @param context
	 * @return
	 */
	public final static boolean checkConnectInternet(Context context) {
		return checkConnectInternet(context, false);
	}
	
	/**
	 * Check whether connect to internet.
	 * 
	 * @param context Object of {@link Context}.
	 * @param onlyWifi True means only wifi connected as a valid connection, 
	 * false all connection is valid.
	 * @return True means connected or false.
	 */
	public final static boolean checkConnectInternet(Context context, boolean onlyWifi) {
		if (null == context) {
			return false;
		}
		
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == manager) {
        	return false;
        }
        
        NetworkInfo info = manager.getActiveNetworkInfo();        
        if (info == null || !info.isConnected()) {
           return false;
        }
        
        if (info.isRoaming()) {
        	if (onlyWifi) {
        		return false;
        	} else {
        		return true;
        	}
        }
        
        return true;
    }
	
	/**
	 * Get device mac address.
	 * 
	 * @param context Object of {@link Context}.
	 * @param encrypted True use {@link #encryptByMD5(String)} encrypted.
	 * @return String of mac address.
	 */
	public final static String getMacAddress(Context context, boolean encrypted) {
		if (null == context) {
			return null;
		}
		
		String mac = null;
		
		// get mac address.
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		mac = info.getMacAddress();
		
		if (null == mac) {
			LogUtils.d(TAG, "get mac address error!");
			return null;
		}
		
		LogUtils.d(TAG, "Mac: " + mac);
		
		// encrypt mac address.
		// base 16 encoding.
		if (encrypted) {
			String ciphertext = encryptByMD5(mac);
			if (null == ciphertext) {
				LogUtils.e(TAG, "encrypt mac address error!");
				return null;
			}
		
			LogUtils.d(TAG, "encrypt mac: " + ciphertext);
			return ciphertext;
		} else {
			return mac;
		}
	}
	
	/**
	 * Simple encrypt a string by java MD5.
	 * 
	 * @param content Source string.
	 * @return Encrypted string.
	 */
	public final static String encryptByMD5(String content) {
		if (content.length() >= 0) {
			MessageDigest md = null;
			String out = null;
			
			try {
				md = MessageDigest.getInstance("MD5");
				out = byteToHex(md.digest(content.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			return out;
		}
		
		return null;
	}
	
	private static String byteToHex(byte[] b) {
		String hs = "";
		String stmp = "";
		
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0xff));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		
		//return hs.toUpperCase();
		return hs;
	}
	
}

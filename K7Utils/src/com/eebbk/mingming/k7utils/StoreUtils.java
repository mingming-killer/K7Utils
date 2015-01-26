package com.eebbk.mingming.k7utils;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

/**
 * Some store related small tools.
 * 
 * @author humingming <humingming@oaserver.dw.gdbbk.com>
 * 
 */
public class StoreUtils {
	
	private final static String TAG = "StoreUtils";
	
	//public final static String SDCARDLOCATION = "/mnt/sdcard";
	//public final static String FLASHLOCATION = "/mnt/flash";
	
	public final static int GB = 1024 * 1024 * 1024;
	public final static int MB = 1024 * 1024;
	public final static int KB = 1024;
	
	
	/**
	 * Judge whether external store available.
	 * 
	 * @return boolean True means available, false invalid.
	 */
	public final static boolean externalStoreAvailable() {
		String status = Environment.getExternalStorageState();
        if (status == null || !status.equals(Environment.MEDIA_MOUNTED)){
            return false;
        }
        
        return true;
	}
	
	/**
	 * Get external store total size.
	 * 
	 * @return long
	 */
	public final static long getExternalStoreTotalSize() {
		if (externalStoreAvailable()) { 
			File storePath = Environment.getExternalStorageDirectory();
			StatFs sf = new StatFs(storePath.getPath());
			long blockSize = sf.getBlockSize();
			long totalBlocks = sf.getBlockCount();
			return totalBlocks * blockSize;
		} else {
			return 0;
		}
	}
	
	/**
	 * Get external store available size.
	 * 
	 * @return long
	 */
	public final static long getExternalStoreAvailableSize() {
		if (externalStoreAvailable()) { 
			File storePath = Environment.getExternalStorageDirectory();
			StatFs sf = new StatFs(storePath.getPath());
			long blockSize = sf.getBlockSize();
			long availableBlocks = sf.getAvailableBlocks();
			return availableBlocks * blockSize;
		} else {
			return 0;
		}
	}
	
	/**
	 * Get internal store total size.
	 * 
	 * @return long
	 */
	public final static long getInternalStoreTotalSize() {
		File path = Environment.getRootDirectory();
		StatFs sf = new StatFs(path.getPath());
		long blockSize = sf.getBlockSize();
		long totalBlocks = sf.getBlockCount();
		return totalBlocks * blockSize;
	}
	
	/**
	 * Get internal store available size.
	 * 
	 * @return long
	 */
	public final static long getInternalStoreAvailableSize() {
		File storePath = Environment.getRootDirectory();
		StatFs sf = new StatFs(storePath.getPath());
		long blockSize = sf.getBlockSize();
		long availableBlocks = sf.getAvailableBlocks();
		return availableBlocks * blockSize;
	}
	
	/**
	 * Get specific store total size.
	 * 
	 * @param storePath Store path.
	 * @return
	 */
	public final static long getSpecificStoreTotalSize(String storePath) {
		StatFs sf = null;
		try {
			sf = new StatFs(storePath);
		} catch (Exception e) {
			e.printStackTrace();
			sf = null;
		}
		
		if (null == sf) {
			return 0;
		}
		
		long blockSize = sf.getBlockSize();
		long totalBlocks = sf.getBlockCount();
		return totalBlocks * blockSize;
	}
	
	/**
	 * Get specific store available size.
	 * 
	 * @param storePath Store path.
	 * @return
	 */
	public final static long getSpecificStoreAvailableSize(String storePath) {
		StatFs sf = null;
		try {
			sf = new StatFs(storePath);
		} catch (Exception e) {
			e.printStackTrace();
			sf = null;
		}
		
		if (null == sf) {
			return 0;
		}
		
		long blockSize = sf.getBlockSize();
		long availableBlocks = sf.getAvailableBlocks();
		return availableBlocks * blockSize;
	}
	
	/**
	 * Get give path parent directory.
	 * 
	 * @param path Path string.
	 * @return Parent path or null.
	 */
	public final static String getParentDir(String path) {
		if (null == path) {
			return null;
		}
		
		try {
			int last = path.lastIndexOf("/");
			if (last <= -1) {
				return null;
			}
			
			return path.substring(0, last);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Get file name for a full path. It's get string since the last char '/'.
	 * 
	 * @param path String of path.
	 * @return File name.
	 */
	public final static String getFileNameFromPath(String path) {
		if (null == path) {
			return null;
		}
		
		int index = path.lastIndexOf('/');
		if (index < -1) {
			return null;
		}
		
		return path.substring(index+1);
	}
	
	/**
	 * Get give path file total size.
	 * If path is a directory, it will compute total file size.
	 * 
	 * @param path Get size path.
	 * @return Path total size.
	 */
	public final static long getFileSize(String path) {
		if (null == path) {
			return 0;
		}
		
        File file = new File(path);
        File[] files = null;
        
        if (file.isDirectory()) {
        	files = file.listFiles(); 
        	if (files == null) {
        		return 0; 
        	}
        } else {
			return file.length();
        }
        
        long size = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
            	size += getFileSize(files[i].getAbsolutePath());
            } else {
            	size += files[i].length();                    
            } 
        }
        
        return size;
	}
	
	/**
	 * Delete file name suffix, eg: file.avi --> file.
	 * 
	 * @param fileName File name with suffix.
	 * @return File name don't suffix, null if failed.
	 */
	public final static String deleteFileNameSuffix(String fileName) {
		if (null == fileName) {
			return null;
		}
		
		int index = fileName.lastIndexOf('.');
		if (index <= -1 || index >= fileName.length()) {
			return fileName;
		}
		
		return fileName.substring(0, index);
	}
	
	/**
	 * Delete folder. If give path is a file, it will delete 
	 * a single file, if is a directory it will delete all file and 
	 * child directory in this directory.
	 * 
	 * @param path Folder path.
	 * @return True delete success, otherwise false.
	 */
	public final static boolean DeleteFolder(String path) {  
	    if (null == path) {
	    	return false;
	    }
	    
	    File file = new File(path);
	    if (!file.exists()) {
	    	return false;
	    }
	    
	    if (file.isFile()) {
	    	return deleteFile(path);
	    } else {
	    	return deleteDirectory(path);
	    }
	}  
	
	/**
	 * Delete a single file.
	 * 
	 * @param path File path(Absolute).
	 * @return True delete success, otherwise false.
	 */
	public final static boolean deleteFile(String path) {  
	    if (null == path) {
	    	return false;
	    }
	    
	    File file = new File(path);
	    if (!file.exists() || !file.isFile()) {
	    	return false;
	    }
	    
	    boolean ret = true;
	    try {
	        ret = file.delete();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	ret = false;
	    }
	    
	    return ret;  
	}
	
	/**
	 * Delete a directory.
	 * 
	 * @param path Directory path.
	 * @return True delete success, otherwise false.
	 */
	public final static boolean deleteDirectory(String path) {
	    if (null == path) {
	    	return false;
	    }
		
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    //if (!sPath.endsWith(File.separator)) {  
	    //    sPath = sPath + File.separator;  
	    //}
	    
	    File dirFile = new File(path);  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }
	    
	    boolean ret = true;  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {   
	        if (files[i].isDirectory()) {
	        	ret &= deleteDirectory(files[i].getAbsolutePath());  
	        } else {  
	        	ret &= deleteFile(files[i].getAbsolutePath());
	        }
	    }
	    
	    try {
	    	ret &= dirFile.delete();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	ret = false;
	    }
	    
	    return ret;
	}  
	
	/**
	 * Get file name for URL. It's get string since the last char '/'.
	 * 
	 * @param url String of url.
	 * @return File name.
	 */
	public final static String getFileNameFromURL(String url) {
		if (null == url) {
			return null;
		}
		
		int index = url.lastIndexOf('/');
		String ret = url.substring(index+1);
	
		int pos = ret.lastIndexOf('?');
		if (pos != -1) 
			ret = ret.substring(0, pos);
		
		return ret;
	}
	
	/*public final static String deleteURLSuffix(String url) {
		if (null == url) {
			return null;
		}
		
		int pos = url.lastIndexOf('?');
		if (pos != -1) 
			url = url.substring(0, pos);
		
		return url;
	}*/
	
	/**
	 * Check give file path whether existed in storage medium.
	 * 
	 * @param filePath File path.
	 * @return True existed, false not existed.
	 */
	public final static boolean checkFileExisted(String filePath) {
		if (null == filePath) {
			return false;
		}
		
		File file = new File(filePath);
		return file.exists();
	}
	
	/**
	 * Check give parent directory of file path whether existed.
	 * If not existed will try to create it.
	 * 
	 * @param fileName Check file path name.
	 * @return True means parent directory existed or create success, other false.
	 */
	public final static boolean checkFileDirExisted(String fileName) {
		String dir = getParentDir(fileName);
		if (null == dir) {
			return false;
		}
		
		File fDir = new File(dir);
		try {
			if (!fDir.exists()) {
        		if (!fDir.mkdirs()) {
        			LogUtils.d(TAG, "create folder " + dir + " failed");
        		}
			}
			
			return true;
			
		} catch (SecurityException e) {
    		e.printStackTrace();
    		return false;
		}
	}
	
	/**
	 * Convert app size unit from byte to "xx MB" or "xx GB" string.
	 * 
	 * @param size Size in byte.
	 * @return Formated string.
	 */
	public final static String convertSizeUnit(long size) {
		String unit;
		if (size >= GB) {
			unit = String.format("%.02f GB", (float)size / (float)GB);
		} else if (size >= MB && size < GB) {
			unit = String.format("%.02f MB", (float)size / (float)MB);
		} else {
			unit = String.format("%.02f KB", (float)size / (float)KB);
		}
		
		return unit;
	}
	
	/**
	 * Convert string format size(Unit: MB) to int size(Unit: Byte). eg: "1" to 1024.
	 * 
	 * @param strSize String format size (MB)
	 * @return Converted int size.
	 */
	public final static long convertStringMBToByte(String strSize) {
		if (null == strSize) {
			return 0;
		}
		
		int size = 0;
		size = Integer.getInteger(strSize, 0);
		if (size <= 0) {
			return 0;
		}
		
		long convertSize = 0;
		convertSize = size * MB;
		return convertSize;
	}
	
}
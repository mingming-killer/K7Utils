package com.eebbk.mingming.k7utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * Debug tools.
 * 
 * @author humingming <humingming@oaserver.dw.gdbbk.com>
 *
 */
public final class DebugUtils {
	
	/**
	 * Dump give bytes to file.
	 * 
	 * @param bytes Bytes data.
	 * @param dumpFile Dump file path.
	 */
	public final static void dumpBytesToFile(byte[] bytes, String dumpFile) {
		if (null == bytes || null == dumpFile) {
			return;
		}
		
		if (!StoreUtils.checkFileDirExisted(dumpFile)) {
			return;
		}
		
		File file = null;
		FileOutputStream outputStream = null;
		
		try {
			
			file = new File(dumpFile);
			outputStream = new FileOutputStream(file);
			
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

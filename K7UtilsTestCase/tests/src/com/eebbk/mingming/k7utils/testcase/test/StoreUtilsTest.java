package com.eebbk.mingming.k7utils.testcase.test;

import com.eebbk.mingming.k7utils.StoreUtils;

import junit.framework.TestCase;

/**
 * 
 * 存储工具测试用例。
 * see {@link StoreUtils}
 * 
 * @author humingming <humingming@oaserver.dw.gdbbk.com>
 *
 */
public class StoreUtilsTest extends
		TestCase {
	
	@SuppressWarnings("unused")
	private final static String TAG = "StoreUtilsTest";
    
	public StoreUtilsTest() {
		super();
	}
	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    // 测试从路径获取文件名
    public void testGetFileNameFromPath() {
    	String path = "/mnt/sdcard/test-video/大气压强(V2.0).dih";
    	
    	String fileName = StoreUtils.getFileNameFromPath(path);
    	assertEquals("大气压强(V2.0).dih", fileName);
    	
    	String fileNameWithoutSuffix = StoreUtils.deleteFileNameSuffix(fileName);
    	assertEquals("大气压强(V2.0)", fileNameWithoutSuffix);
    }
    
    // 测试获取文件大小
    public void testGetFileSize() {
    	String path = "/mnt/sdcard/test-video/大气压强(V2.0).dih";
    	long fileSize = StoreUtils.getFileSize(path);
    	assertEquals(fileSize, 387952);
    	
    	path = "/mnt/sdcard/tmp";
    	fileSize = StoreUtils.getFileSize(path);
    	assertEquals(fileSize, 187713031);
    }
    
    // 测试删除文件夹
    public void testDeleteFolder() {
    	String path = "/mnt/sdcard/1";
    	assertTrue(StoreUtils.DeleteFolder(path));
    }
}

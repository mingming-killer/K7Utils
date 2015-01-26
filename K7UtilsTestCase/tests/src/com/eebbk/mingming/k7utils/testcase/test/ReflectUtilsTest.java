package com.eebbk.mingming.k7utils.testcase.test;

import com.eebbk.mingming.k7utils.LogUtils;
import com.eebbk.mingming.k7utils.ReflectUtils;
import com.eebbk.mingming.k7utils.StoreUtils;

import junit.framework.TestCase;

/**
 * 
 * 反射工具测试用例。
 * see {@link StoreUtils}
 * 
 * @author humingming <humingming@oaserver.dw.gdbbk.com>
 *
 */
public class ReflectUtilsTest extends
		TestCase {
	
	private final static String TAG = "ReflectUtilsTest";
	
	private ReflectTarget mTestTarget;
    
	public ReflectUtilsTest() {
		super();
	}
	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mTestTarget = new ReflectTarget();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testSetAndGetField() {
    	ReflectUtils.setFieldObject(ReflectTarget.class, mTestTarget, "mInt", 15);
    	
    	int value = -1;
    	try {
    		value = (Integer) ReflectUtils.getFieldObject(ReflectTarget.class, mTestTarget, "mInt");
    	} catch (Exception e) {
    		e.printStackTrace();
    		value = -1;
    	}

    	assertEquals(value, 15);
    }
    
    /*public void testSetAndGetStaticField() {
    	ReflectUtils.setFinalStaticFieldObject(ReflectTarget.class, "TAG", ReflectTarget.SET_TAG);
    	boolean check = ReflectTarget.checkSetTAG();
    	ReflectTarget.printTAG();
    	LogUtils.d(TAG, ReflectTarget.SET_TAG);
    	assertTrue(check);
    }*/
    
    public void testSetAndGetFinalField() {
    	ReflectUtils.setFieldObject(ReflectTarget.class, mTestTarget, "mInt3", 10);
    	int value = -1;
    	try {
    		value = (Integer) ReflectUtils.getFieldObject(
    				ReflectTarget.class, mTestTarget, "mInt3");
    	} catch (Exception e) {
    		e.printStackTrace();
    		value = -1;
    	}
    	assertEquals(10, value);
    }
    
    public void testInvokeMethod() throws ClassNotFoundException {
    	// params is base type
    	ReflectUtils.invokeMethod(ReflectTarget.class, mTestTarget, "setInts", 
    			new Class[] {int.class, int.class}, 
    			5, 15);
    	assertEquals(5, mTestTarget.getInt1());
    	assertEquals(15, mTestTarget.getInt2());
    	
    	// params is class
    	ReflectUtils.invokeMethod(ReflectTarget.class, mTestTarget, "setIntsWithClass", 
    			new Class[] {Integer.class, Integer.class}, 
    			25, 35);
    	assertEquals(25, mTestTarget.getInt1());
    	assertEquals(35, mTestTarget.getInt2());
    	
    	// params is base type array
    	ReflectUtils.invokeMethod(ReflectTarget.class, mTestTarget, "setIntsWithArray", 
    			new Class[] {int[].class}, 
    			new int[] {45, 55});
    	assertEquals(45, mTestTarget.getInt1());
    	assertEquals(55, mTestTarget.getInt2());
    	
    	// params is class array
    	ReflectTarget.Test[] tests = new ReflectTarget.Test[] {
    		new ReflectTarget.Test(65),
    		new ReflectTarget.Test(75)
    	};
    	Class<?> testClassArray = Class.forName(
    			new ReflectTarget.Test[] {}.getClass().getName());
    	
    	ReflectUtils.invokeMethod(ReflectTarget.class, mTestTarget, "setIntsWithClassArray", 
    			new Class[] {ReflectTarget.Test[].class}, 
    			testClassArray.cast(tests));
    	assertEquals(65, mTestTarget.getInt1());
    	assertEquals(75, mTestTarget.getInt2());
    	
    	try {
    		ReflectUtils.invokeMethod(
    				ReflectTarget.class, mTestTarget, "setIntPrivate", null, (Object[])null);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	assertEquals(ReflectTarget.SET_TEST, mTestTarget.getInt());
    }
    
    public void testInvokeStaticMethod() {
    	int value = -1;
    	
    	ReflectUtils.setStaticFieldObject(ReflectTarget.class, "TEST", 999);
    	
    	try {
    		value = (Integer) ReflectUtils.invokeStaticMethod(
    				ReflectTarget.class, "getTestStatic", null, (Object[])null);
    	} catch (Exception e) {
    		e.printStackTrace();
    		value = -1;
    	}
    	
    	assertEquals(999, value);
    	
    	try {
    		value = (Integer) ReflectUtils.getStaticFieldObject(ReflectTarget.class, "TEST");
    	} catch (Exception e) {
    		e.printStackTrace();
    		value = -1;
    	}
    	
    	assertEquals(999, value);
    }
    
}

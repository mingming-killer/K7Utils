package com.eebbk.mingming.k7utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 
 * Some java reflect tools.
 * But don't use frequently.
 * 
 * @author humingming <humingming@oaserver.dw.gdbbk.com>
 *
 */
public class ReflectUtils {

	/*private static Field mFlingEndField = null;
	private static Method mFlingEndMethod = null;

	static {
		try {
			// use java reflect to find the AbsListView private field mFlingRunnable.
			// and it's have a method endFling can stop fling.
			mFlingEndField = AbsListView.class.getDeclaredField("mFlingRunnable");
	        mFlingEndField.setAccessible(true);
	        mFlingEndMethod = mFlingEndField.getType().getDeclaredMethod("endFling");
	        mFlingEndMethod.setAccessible(true);
	    } catch (Exception e) {
	        mFlingEndMethod = null;
	    }
	}
	
	public static void forceStopFling(AbsListView list) {
		if (mFlingEndMethod != null) {
	    	try {
	    		mFlingEndMethod.invoke(mFlingEndField.get(list));
	        } catch (Exception e) {
	        	// ignore it
	        }	
		}
	}*/
	
	/**
	 * Create a new specified class object.
	 * This is use default constructor, than means target class 
	 * must have a access default constructor.
	 * 
	 * @param className Class name, note must be full namespace.
	 * @return New class object, null means error.
	 */
	public final static Object newObject(String className) {
		if (null == className) {
			return null;
		}
		
		Object obj = null;
		try {
			obj = Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			obj = null;
		}
		
		return obj;
	}
	
	/**
	 * Invoke specified class method.
	 * 
	 * @param objClass
	 * @param object
	 * @param methodName
	 * @param paramTypes
	 * @param args
	 * @return
	 */
	public final static Object invokeMethod(Class<?> objClass, Object object, 
			String methodName, Class<?>[] paramTypes, Object... args) {
		
		if (null == objClass || null == object ||  
				null == methodName) {
			return null;
		}
		
		Method method = null;
		
		try {
			method = objClass.getDeclaredMethod(methodName, paramTypes);
			method.setAccessible(true);
	        return method.invoke(object, args);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * Invoke specified class static method.
	 * 
	 * @param objClass
	 * @param methodName
	 * @param paramTypes
	 * @param args
	 * @return
	 */
	public final static Object invokeStaticMethod(Class<?> objClass, 
			String methodName, Class<?>[] paramTypes, Object... args) {
		
		if (null == objClass ||  
				null == methodName) {
			return null;
		}
		
		Method method = null;
		
		try {
			method = objClass.getDeclaredMethod(methodName, paramTypes);
			method.setAccessible(true);
	        return method.invoke(null, args);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * Get specified class instance member.
	 * 
	 * @param objClass
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public final static Object getFieldObject(Class<?> objClass, Object object, String fieldName) {
		if (null == objClass || null == object || 
				null == fieldName) {
			return null;
		}
		
		Field field = null;
		
		try {
			field = objClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(object);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * Set specified class instance member.
	 * 
	 * @param objClass
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public final static void setFieldObject(Class<?> objClass, Object object, String fieldName, Object value) {
		if (null == objClass || null == object || 
				null == fieldName) {
			return;
		}
		
		Field field = null;
		
		try {
			field = objClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(object, value);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Get specified class static member.
	 * 
	 * @param objClass
	 * @param fieldName
	 * @return
	 */
	public final static Object getStaticFieldObject(Class<?> objClass, String fieldName) {
		if (null == objClass || null == fieldName) {
			return null;
		}
		
		Field field = null;
		
		try {
			field = objClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(null);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * Set specified class static member.
	 * 
	 * @param objClass
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public final static void setStaticFieldObject(Class<?> objClass, String fieldName, Object value) {
		if (null == objClass || null == fieldName) {
			return;
		}
		
		Field field = null;
		
		try {
			field = objClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(null, value);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Set specified class final static member. </br>
	 * Oh, I really suggest don't use it. 
	 * 
	 * TODO: don't use, not work yet.
	 * 
	 * @param objClass
	 * @param fieldName
	 * @param value
	 */
	public final static void setFinalStaticFieldObject(Class<?> objClass, String fieldName, Object value) {
		if (null == objClass || 
				null == fieldName) {
			return;
		}
		
		Field field = null;
		Field modifiersField = null;
		
		try {
			field = objClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			
			modifiersField = Field.class.getDeclaredField("modifiers");
		    modifiersField.setAccessible(true);
		    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		    
		    field.set(null, value);
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}

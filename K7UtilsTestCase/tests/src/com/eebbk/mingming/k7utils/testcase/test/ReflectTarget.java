package com.eebbk.mingming.k7utils.testcase.test;

import java.lang.String;

import android.util.Log;

public class ReflectTarget {
	
	private final static String TAG = "RelectTarget";
	public final static String SET_TAG = "ChangeTAG";
	
	public final static int SET_TEST = 9999;
	private static int TEST = 99;
	
	private int mInt;
	private int mInt1;
	private int mInt2;
	private final int mInt3;
	
	public static class Test {
		private int mTest1;
		public Test() {
			mTest1 = -1;
		}
		public Test(int test) {
			mTest1 = test;
		}
	}
	
	public ReflectTarget() {
		mInt = 0;
		mInt3 = 5;
		setInts(0, 0);
	}
	
	public static boolean checkSetTAG() {
		//return SET_TAG.equals(TAG);
		return SET_TAG == TAG;
	}
	
	public int getInt() {
		return mInt;
	}
	
	public int getInt1() {
		return mInt1;
	}
	
	public int getInt2() {
		return mInt2;
	}
	
	public int getInt3() {
		return mInt3;
	}
	
	@SuppressWarnings("unused")
	private void setIntPrivate() {
		mInt = SET_TEST;
	}
	
	private void setInts(int int1, int int2) {
		mInt1 = int1;
		mInt2 = int2;
	}
	
	@SuppressWarnings("unused")
	private void setIntsWithClass(Integer int1, Integer int2) {
		mInt1 = int1;
		mInt2 = int2;
	}
	
	@SuppressWarnings("unused")
	private void setIntsWithArray(int[] ints) {
		mInt1 = ints[0];
		mInt2 = ints[1];
	}
	
	@SuppressWarnings("unused")
	private void setIntsWithClassArray(Test[] tests) {
		mInt1 = tests[0].mTest1;
		mInt2 = tests[1].mTest1;
	}
	
	public static void printTAG() {
		Log.d(TAG, " show the tag");
	}
	
	@SuppressWarnings("unused")
	private static int getTestStatic() {
		return TEST;
	}
	
}

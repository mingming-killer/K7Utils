package com.eebbk.mingming.k7utils.testcase;

import com.eebbk.mingming.k7utils.testcase.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class K7UtilsTestCase extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity, menu);
		return true;
	}

}

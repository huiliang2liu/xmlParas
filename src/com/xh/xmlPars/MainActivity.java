package com.xh.xmlPars;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	private final static String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		Paras paras = ParasFactory.dom();
//		Paras paras = ParasFactory.pull();
		Paras paras = ParasFactory.sax();
		try {
			List<NodeTree> nodeTrees = paras.paras(getAssets().open(
					"activity_main.xml"));
			if (nodeTrees != null || nodeTrees.size() > 0)
				for (NodeTree nodeTree : nodeTrees) {
					Log.e(TAG, nodeTree.toString());
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

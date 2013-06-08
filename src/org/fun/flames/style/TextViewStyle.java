package org.fun.flames.style;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewStyle extends TextView {

	private static final String TAG = "TextViewStyle";

	public TextViewStyle(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		TextViewHelper.initialize(this, context, attrs);
	}

}

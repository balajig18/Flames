package org.fun.flames;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.fun.flames.bgsource.Mediaplayer;

public class Your_Name extends Activity {
	private EditText your_name;
	private ImageButton next;
	private RadioGroup sex;
	private Intent sent;
	private String getdata;
	private Bundle bundle;
	private TextView mView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.your_name);
		initialize();
		Typeface floral = Typeface.createFromAsset(getAssets(), "Filxgirl.TTF");
		mView.setTypeface(floral, Typeface.BOLD);
		mView.setTextSize(50);
		mView.setTextColor(Color.RED);
		next = (ImageButton) findViewById(R.id.ur_next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				getdata = your_name.getText().toString();
				getdata = getdata.replaceAll("\\s", "");

				if (Pattern.matches("[a-zA-z]+", getdata)) {
					switch (sex.getCheckedRadioButtonId()) {
					case R.id.rmale:
						bundle.putString("Sex", "Male");
                        break;
					case R.id.rfemale:
						bundle.putString("Sex", "Female");
						break;
					}
					bundle.putString("your_name", getdata);
					sent.putExtras(bundle);
					sentdata();
				} else {
					your_name.setText("");
					Toast.makeText(Your_Name.this,
							"Please Enter Your Name \n Correcty", 10).show();
				}

			}
		});

		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(your_name.getWindowToken(), 0);

	}

	private void sentdata() {
		// TODO Auto-generated method stub
		startActivity(sent);
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

	}

	private void initialize() {
		// TODO Auto-generated method stub
		your_name = (EditText) findViewById(R.id.ur_name);
		sex = (RadioGroup) findViewById(R.id.radioGroup1);
		next = (ImageButton) findViewById(R.id.ur_next);
		sent = new Intent(Your_Name.this, Your_Crush.class);
		bundle = new Bundle();
		mView = (TextView) findViewById(R.id.name1);

		// sex.getCheckedRadioButtonId();
	}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if((keyCode==KeyEvent.KEYCODE_HOME))
        {
            Mediaplayer.player.stop();
            Mediaplayer.player.release();
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            this.finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);



    }
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getWindowManager();
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
    }
    @Override
    protected void onUserLeaveHint() {
        // TODO Auto-generated method stub
        super.onUserLeaveHint();
    }
}

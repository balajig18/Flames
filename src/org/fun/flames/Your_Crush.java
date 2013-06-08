package org.fun.flames;

import java.util.Random;
import java.util.regex.Pattern;

import org.fun.flames.bgsource.Flames;
import org.fun.flames.bgsource.Mediaplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("Registered")
public class Your_Crush extends Activity implements OnClickListener {

	private ImageButton choice, calct;
	private EditText crname;
	private static String _Name1;
	private static String _Name2;
	private Bundle sent;
	private TextView mView;
	private ImageView crush_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.your_crush);
		initialize();
		getBundle();
	}

	private void getBundle() {
		// TODO Auto-generated method stub
		sent = getIntent().getExtras();
		switch(sent.getString("Sex").charAt(0))
		{
		case 'F':
			crush_img.setImageResource(R.drawable.man2_128x128);
			break;
		case 'M':
			crush_img.setImageResource(R.drawable.woman4_128x128);
			break;
		}
	}

	private void initialize() {
		// TODO Auto-generated method stub

		choice = (ImageButton) findViewById(R.id.crchoice);
		choice.setOnClickListener(this);
		crname = (EditText) findViewById(R.id.crushname);
		mView = (TextView) findViewById(R.id.name2);
		crush_img=(ImageView)findViewById(R.id.cr_img);		
		Typeface floral = Typeface.createFromAsset(getAssets(), "Filxgirl.TTF");
		mView.setTypeface(floral, Typeface.BOLD);
		mView.setTextSize(50);
		mView.setTextColor(Color.RED);
		calct = (ImageButton) findViewById(R.id.calculate);
		calct.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.crchoice:
			Intent intent = new Intent(Your_Crush.this, ExpList.class);

			startActivityForResult(intent, 0);
			break;
		case R.id.calculate:
			_Name1 = sent.getString("your_name");
			_Name2 = crname.getText().toString();
			sent.putString("crush_name", _Name2);
			_Name2 = _Name2.replaceAll("\\s", "");

			if (Pattern.matches("[a-zA-z]+", _Name2)) {
				new CalculatingTask().execute(_Name1, _Name2);
			} else {
				Toast toast = Toast.makeText(Your_Crush.this,
						"Please Enter your crush\n name correctly",
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				crname.setText("");
			}
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK)

		{
			Bundle get = data.getExtras();
			crname.setText(get.getString("Return"));
		}
	}

	public class CalculatingTask extends AsyncTask<String, Integer,Integer> {

		Dialog calculate_dialog;
		Flames flames;
		ProgressBar loading;
		TextView display;
		String arr[] = new String[] { "", "FRIENDS", "LOVE", "AFFECTION",
				"MARRIAGE", "ENEMY", "SISTERS", };

		Random r = new Random();

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			// relation=result;
			calldialog(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			// super.onProgressUpdate(values);
			loading.setProgress(values[0]);
			display.setText(arr[r.nextInt(6)]);

		}

		@Override
		protected Integer doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Integer s = flames.calculate(arg0[0], arg0[1]);
			for (int i = 0; i < 20; i++) {
				publishProgress(i * 5);

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			calculate_dialog.dismiss();
			return s;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub

			// super.onPreExecute();
			calculate_dialog = new Dialog(Your_Crush.this,
					android.R.style.Theme_Dialog);
			calculate_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			calculate_dialog.setContentView(R.layout.calculating_dialog);
			loading = (ProgressBar) calculate_dialog
					.findViewById(R.id.calculating_loading);
			display = (TextView) calculate_dialog.findViewById(R.id.cal_tv2);
			flames = new Flames();
			loading.setProgress(0);
			loading.setMax(100);
			calculate_dialog.show();

		}

	}

	public void calldialog(Integer relation) {
		// TODO Auto-generated method stub
		final Dialog dialog = new Dialog(Your_Crush.this,
				android.R.style.Theme_Dialog);
		dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		dialog.setContentView(R.layout.result_dialog);

        sent.putInt("Result",relation.intValue());
		dialog.setTitle("Your Result is Ready");
		Toast.makeText(this, sent.getString("Sex"), 100).show();

		Button u = (Button) dialog.findViewById(R.id.dialog_bt);
		u.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent2 = new Intent(Your_Crush.this,
						ResultActivity.class);
				intent2.putExtras(sent);
				startActivity(intent2);
				overridePendingTransition(android.R.anim.fade_in,
						android.R.anim.fade_out);
				dialog.dismiss();

			}
		});
		dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.info);
		dialog.show();

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
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

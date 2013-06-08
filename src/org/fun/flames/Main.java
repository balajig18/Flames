package org.fun.flames;

import org.fun.flames.bgsource.Mediaplayer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Main extends Activity implements OnClickListener {

	private ToggleButton audio;
	private ImageButton play, history;
	public static final String prefs = "PREFERENCES";
	private SharedPreferences sp;
	private SharedPreferences.Editor edit;
	private boolean isPressed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		audio = (ToggleButton) findViewById(R.id.toggle_audio);
		sp = getApplicationContext().getSharedPreferences("flames_preferences",
				0);
		edit = sp.edit();
		audio.setChecked(sp.getBoolean("checkbox", true));

		Mediaplayer.setUpMedia(getApplication(), R.raw.backgroundsound);
		Mediaplayer.player.start();

		if (sp.getBoolean("checkbox", true)) {
			if (Mediaplayer.player == null) {
				Mediaplayer.player.setVolume(1.0f, 1.0f);
			}
		} else {
			Mediaplayer.player.setVolume(0, 0);
		}

		audio.setOnClickListener(this);

		play = (ImageButton) findViewById(R.id.play);
		play.setOnClickListener(this);

		history = (ImageButton) findViewById(R.id.history);
		history.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.toggle_audio:
			if (audio.isChecked()) {
				edit.putBoolean("checkbox", true);
				Mediaplayer.player.setVolume(1.0f, 1.0f);
				edit.commit();

			} else {
				edit.putBoolean("checkbox", false);
				Mediaplayer.player.setVolume(0, 0);
				edit.commit();

			}
			break;
		case R.id.play:
			Intent intent = new Intent(Main.this, Your_Name.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in,
					android.R.anim.fade_out);
			break;
		case R.id.history:
			Intent intent2 = new Intent(Main.this, History.class);
			startActivity(intent2);
			break;
		}
	}


@Override
public void onBackPressed() {
	// TODO Auto-generated method stub

	Mediaplayer.player.stop();
	Mediaplayer.player.release();
	Mediaplayer.player=null;
	Intent startMain = new Intent(Intent.ACTION_MAIN);
	startMain.addCategory(Intent.CATEGORY_HOME);
	startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	startActivity(startMain);
	this.finish();
	return;

}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Mediaplayer.player.stop();
			Mediaplayer.player.release();
			Intent startMain = new Intent(Intent.ACTION_MAIN);
			startMain.addCategory(Intent.CATEGORY_HOME);
			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(startMain);
			this.finish();
			return true;

		}
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
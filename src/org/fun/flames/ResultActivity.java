package org.fun.flames;

import java.io.File;
import java.sql.SQLException;

import org.fun.flames.bgsource.DataStore;
import org.fun.flames.bgsource.Mediaplayer;
import org.fun.flames.bgsource.ShareImage;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity implements OnClickListener {

	private Button menu, save, share;
	private TextView name1;
	private TextView name2;
	private Bundle bundle;
	private Intent intent;
	private String UrName, CrName;
    int result;
	private MediaPlayer sound;
	private static int seekTime;
	private ShareImage create;
    String res[]={"Friends","Lover","Attracted","Marriage","Enemy","Sister"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize();
		changecontentview(result);
        menu = (Button)this.findViewById(R.id.result_main);
        save = (Button)this.findViewById(R.id.result_save);
        share = (Button)this.findViewById(R.id.result_share);

        menu.setOnClickListener(this);
        save.setOnClickListener(this);
        share.setOnClickListener(this);

        playbgsound(result);
        settextview(result);

	}

    private void playbgsound(int result) {

        TypedArray array=getResources().obtainTypedArray(R.array.BgSound);
        Mediaplayer.player.stop();
        Mediaplayer.setUpMedia(getApplication(),array.getResourceId(result,0));
        Mediaplayer.player.start();
    }

    protected void initialize() {

		bundle = getIntent().getExtras();

		sound = new MediaPlayer();
		UrName = bundle.getString("your_name");
		CrName = bundle.getString("crush_name");
		result = bundle.getInt("Result");
		create = new ShareImage();
		


	}

	protected void changecontentview(int n) {
		// TODO Auto-generated method stub
		// String set="R.layout."+result;
		switch (n) {

		case 0:
			setContentView(R.layout.friends);
			break;

		case 1:
			setContentView(R.layout.lover);
			break;

		case 2:
			setContentView(R.layout.affection);
			break;
		case 3:
			setContentView(R.layout.marriage);

			break;
		case 4:
			setContentView(R.layout.enemey);
			break;

		case 5:
			setContentView(R.layout.sister);
			break;
		}
	}

	private void settextview(int n) {
		// TODO Auto-generated method stub
		switch (n) {
		case 0:
            break;
		case 1:
			break;

		case 2:

			break;
		case 3:
			name1 = (TextView) findViewById(R.id.mr_tv1);
			name2 = (TextView) findViewById(R.id.mr_tv3);
			if (bundle.getString("Sex") == "Male") {
				name1.setText(CrName);
				name2.setText(UrName);
			} else {
				name2.setText(CrName);
				name1.setText(UrName);
			}
			break;
		case 4:
			name1 = (TextView) findViewById(R.id.en_tv1);
			if (bundle.getString("Sex") == "Male") {
				name1.setText("HER");
			} else {
				name1.setText("HIM");
			}

			break;

		case 5:
			setContentView(R.layout.sister);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// ().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.result_main:
			intent = new Intent(ResultActivity.this, Main.class);
            Mediaplayer.player.stop();
            Mediaplayer.player.release();
            Mediaplayer.player=null;
			startActivity(intent);
			break;
		case R.id.result_save:

			DataStore dbStore = new DataStore(ResultActivity.this);
			try {
				dbStore.open();
				dbStore.createEntry(UrName, CrName, res[result]);
				dbStore.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case R.id.result_share:
			try {
				create.writeTextOnDrawable(ResultActivity.this,
						R.drawable.marriage_back, CrName + "@" + UrName);
			} catch (Exception e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}

			Uri imageUri = Uri.parse(Environment.getExternalStorageState()
					+ File.pathSeparatorChar + "result.jpeg");
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("image/jpeg");

			intent.putExtra(Intent.EXTRA_STREAM, imageUri);
			startActivity(Intent.createChooser(intent, "Share Via"));

			break;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(ResultActivity.this, Main.class);
		startActivity(intent);
	    Mediaplayer.player.stop();
        Mediaplayer.player.release();
        Mediaplayer.player=null;


	}

}
/**
 *
 */
package org.fun.flames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

/**
 * @author Balaji
 */
public class Splash extends Activity {

	private ProgressBar loadingBar;
	private int mProgressStatus = 0;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		loadingBar = (ProgressBar) findViewById(R.id.progressBar);
		// loadingBar.setProgress(0);

		setContentView(R.layout.startup);

		// Thread to update progress bar

		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while (mProgressStatus < 1000) {
						mProgressStatus += 1;

						// Update the progress bar
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								if (loadingBar != null)
									loadingBar.setProgress(mProgressStatus);
							}

						});
					}

					sleep(5000);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					Intent sent = new Intent(Splash.this, Main.class);

					startActivity(sent);

				}
			}
		}.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}

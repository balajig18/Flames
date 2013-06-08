package org.fun.flames.style;

import java.util.Random;

import org.fun.flames.R;
import org.fun.flames.bgsource.Flames;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Result_Dialog extends Dialog implements OnClickListener {

	public Result_Dialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setContentView(R.layout.calculating_dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}
/*
	public class CalculatingTask extends AsyncTask<String, Integer,Integer> {

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
			dismiss();
			return s;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub

			// super.onPreExecute();
			loading = (ProgressBar) findViewById(R.id.calculating_loading);
			display = (TextView) findViewById(R.id.cal_tv2);
			flames = new Flames();
			loading.setProgress(0);
			loading.setMax(100);
			show();

		}

	}
*/
}

package org.fun.flames;

import java.sql.SQLException;
import java.util.ArrayList;

import org.fun.flames.bgsource.DataStore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class History extends Activity implements OnLongClickListener {

	private TableLayout histable;
	private TextView your_name, your_crush, relation;
	private TableRow newRow;
	private ImageView line_sep;
	private ImageView line_sep1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);
		histable = (TableLayout) findViewById(R.id.historyTable);

		DataStore info = new DataStore(this);
		try {
			info.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> result = info.getEntry();
		info.close();
		createRow(result);
	}

	private void createRow(ArrayList<String> result) {
		// TODO Auto-generated method stub
		for (String get : result) {
			initialize();
			String[] row = get.split("-");
			your_name.setText(row[1].trim());
			your_crush.setText(row[2]);
			relation.setText(row[3]);
			newRow.addView(your_name);
			newRow.addView(line_sep);
			newRow.addView(your_crush);
			newRow.addView(line_sep1);
			newRow.addView(relation);
			final float scale = getBaseContext().getResources()
					.getDisplayMetrics().density;
			int pixels = (int) (500 * scale + 0.5f);
			histable.addView(newRow, new TableLayout.LayoutParams(
					android.view.ViewGroup.LayoutParams.FILL_PARENT, pixels,
					2.5f));
		}
	}

	private void initialize() {
		// TODO Auto-generated method stub
		newRow = new TableRow(this);
		newRow.setOnLongClickListener(this);
		newRow.setClickable(true);

		your_name = new TextView(this);
		your_crush = new TextView(this);
		relation = new TextView(this);

		line_sep = new ImageView(this);
		line_sep.setImageResource(R.drawable.line_separator);

		line_sep1 = new ImageView(this);
		line_sep1.setImageResource(R.drawable.line_separator);

		your_name.setLayoutParams(new TableRow.LayoutParams(
				android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT, 1F));
		your_crush.setLayoutParams(new TableRow.LayoutParams(
				android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT, 1F));
		relation.setLayoutParams(new TableRow.LayoutParams(
				android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT, 0.5F));

		your_name.setGravity(Gravity.CENTER);
		your_crush.setGravity(Gravity.CENTER);
		relation.setGravity(Gravity.CENTER);

		your_name.setMaxWidth(30);
		your_crush.setMaxWidth(30);
		relation.setMaxWidth(30);

		your_name.setSingleLine(false);

		your_crush.setMaxWidth(30);
		relation.setMaxWidth(30);

		line_sep.setLayoutParams(new TableRow.LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		line_sep1.setLayoutParams(new TableRow.LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

	@Override
	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub
		TableRow change = (TableRow) arg0;
		TextView v = (TextView) change.getChildAt(3);
		Toast.makeText(this, v.getText().toString(), 10).show();
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(History.this, Main.class);
		startActivity(intent);

	}

}

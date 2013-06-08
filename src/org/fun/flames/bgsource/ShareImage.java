package org.fun.flames.bgsource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.os.Environment;

public class ShareImage {
	public void writeTextOnDrawable(Context con, int drawableId, String text)
			throws Exception {

		Bitmap bm = BitmapFactory
				.decodeResource(con.getResources(), drawableId).copy(
						Bitmap.Config.RGB_565, true);

		Typeface tf = Typeface.createFromAsset(con.getAssets(), "WhimsyTT.ttf");

		Paint paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(Color.RED);
		paint.setTypeface(tf);
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(48);

		// Rect textRect = new Rect();
		// paint.getTextBounds(text, 0, text.length(), textRect);

		String arr[] = text.split("@");

		Canvas canvas = new Canvas(bm);

		// If the text is bigger than the canvas , reduce the font size
		/*
		 * if(textRect.width() >= (canvas.getWidth() - 4)) //the padding on
		 * either sides is considered as 4, so as to appropriately fit in the
		 * text paint.setTextSize(convertToPixels(mContext, 7)); //Scaling needs
		 * to be used for different dpi's
		 */
		// Calculate the positions
		int xPos = (canvas.getWidth() / 4) - 15; // -2 is for regulating the
		// x position offset

		// "- ((paint.descent() + paint.ascent()) / 2)" is the distance from
		// the baseline to the center.
		int yPos = (int) ((canvas.getHeight() - canvas.getHeight() / 4)
				- ((paint.descent() + paint.ascent()) / 2) - 10);

		canvas.drawText(arr[0], xPos, yPos, paint);
		canvas.drawText(arr[1], xPos + arr[0].length() + 10,
				yPos + arr[0].length() + 10, paint);

		save(bm);
		// return new BitmapDrawable(getResources(), bm);
	}

	public int convertToPixels(Context context, int nDP) {
		final float conversionScale = context.getResources()
				.getDisplayMetrics().density;

		return (int) ((nDP * conversionScale) + 0.5f);

	}

	public void save(Bitmap bm)

	{
		File sdCardDirectory = Environment.getExternalStorageDirectory();

		// file for image storage

		File image_file = new File(sdCardDirectory, "result.jpeg");

		// changing image format as png and FileOutputStream writesbytes to
		// a file

		FileOutputStream outStream;

		try {

			// the outStream writes to file image_file

			outStream = new FileOutputStream(image_file);

			// compresses bmap and store it in the file using outstream

			bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);

			// 100 to keep full quality of the image

			outStream.flush();// Flushes the outStream stream

			outStream.close();// Closes this stream.

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}
package org.fun.flames.bgsource;

import android.content.Context;
import android.media.MediaPlayer;

public class Mediaplayer {

	public static MediaPlayer player;

	public static void setUpMedia(Context con, int raw_id) {

		// TODO Auto-generated constructor stub

		player = MediaPlayer.create(con, raw_id);
		player.setLooping(true);
	}
}

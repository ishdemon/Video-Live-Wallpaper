package com.cyunrei.videolivewallpaper;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import java.io.IOException;


public class VideoLiveWallpaper extends WallpaperService
{
	
    public Engine onCreateEngine()
	{
        return new VideoEngine();
    }

    public static void setToWallPaper(Context context)
	{
		
        final Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
						new ComponentName(context, VideoLiveWallpaper.class));
		
        context.startActivity(intent);
		
    }

	
    class VideoEngine extends Engine
	{

		private MediaPlayer mMediaPlayer;

        @Override
        public void onVisibilityChanged(boolean visible)
		{
            if (visible)
			{
                mMediaPlayer.start();
            }
			else
			{
				mMediaPlayer.pause();

            }
        }


        @Override
        public void onSurfaceCreated(SurfaceHolder holder)
		{

            super.onSurfaceCreated(holder);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setSurface(holder.getSurface());

            try
			{

				mMediaPlayer.setDataSource("/data/data/com.cyunrei.videolivewallpaper/file.mp4");
                mMediaPlayer.setLooping(true);
				mMediaPlayer.setVolume(0, 0);
                mMediaPlayer.prepare();
                mMediaPlayer.start();

            }
			catch (IOException e)
			{

            }
        }
	}
	
}  

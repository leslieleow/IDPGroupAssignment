package com.example.masterbedalarm;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class Volumizer extends Activity {
  SeekBar alarm=null;
  //SeekBar music=null;
  //SeekBar ring=null;
  //SeekBar system=null;
  //SeekBar voice=null;
  AudioManager mgr=null;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    mgr=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
    
    alarm=(SeekBar)findViewById(R.id.settings);
    //music=(SeekBar)findViewById(R.id.music);
    //ring=(SeekBar)findViewById(R.id.ring);
    //system=(SeekBar)findViewById(R.id.system);
    //voice=(SeekBar)findViewById(R.id.voice);
    
    initBar(alarm, AudioManager.STREAM_ALARM);
    //initBar(music, AudioManager.STREAM_MUSIC);
    //initBar(ring, AudioManager.STREAM_RING);
    //initBar(system, AudioManager.STREAM_SYSTEM);
    //initBar(voice, AudioManager.STREAM_VOICE_CALL);
  }
  
  private void initBar(SeekBar bar, final int stream) {
    bar.setMax(mgr.getStreamMaxVolume(stream));
    bar.setProgress(mgr.getStreamVolume(stream));
    
    bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      public void onProgressChanged(SeekBar bar, int progress,
                                    boolean fromUser) {
        mgr.setStreamVolume(stream, progress,
                            AudioManager.FLAG_PLAY_SOUND);
      }
      
      public void onStartTrackingTouch(SeekBar bar) {
        // no-op
      }
      
      public void onStopTrackingTouch(SeekBar bar) {
        // no-op
      }
    });
  }
}
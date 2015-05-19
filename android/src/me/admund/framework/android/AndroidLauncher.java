package me.admund.framework.android;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import me.admund.framework.FrameworkTest;
import me.admund.framework.game.AbstractGame;

public class AndroidLauncher extends AndroidApplication {

	private AndroidAchivmentsProvider provider = null;
	private AbstractGame game = null;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.numSamples = 2;
		config.hideStatusBar = true;
		config.useAccelerometer = false;
		config.useCompass = false;
		provider = new AndroidAchivmentsProvider(this);
		game = new FrameworkTest(provider);
		initialize(game, config);
	}

	@Override
	protected void onStart() {
		super.onStart();
		provider.onStart(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		game.pause();
	}

	@Override
	protected void onStop() {
		super.onStop();
		provider.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		provider.onActivityResult(requestCode, resultCode, data);
	}
}

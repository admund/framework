package me.admund.framework.android;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import me.admund.framework.FrameworkTest;

public class AndroidLauncher extends AndroidApplication {

	private AndroidAchivmentsProvider provider = null;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.numSamples = 2;
		provider = new AndroidAchivmentsProvider(this);
		initialize(new FrameworkTest(provider), config);
	}

	@Override
	protected void onStart() {
		super.onStart();
		provider.onStart(this);
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

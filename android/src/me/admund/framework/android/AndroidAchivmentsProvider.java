package me.admund.framework.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import me.admund.framework.achievements.AchievementState;
import me.admund.framework.achievements.IAchievementsProvider;

/**
 * Created by admund on 2015-03-12.
 */
public class AndroidAchivmentsProvider implements IAchievementsProvider,
                                            GameHelper.GameHelperListener {
    private Activity activity = null;
    private GameHelper gameHelper = null;

    public AndroidAchivmentsProvider(Activity activity) {
        this.activity = activity;
        gameHelper = new GameHelper(activity, GameHelper.CLIENT_GAMES);
        gameHelper.enableDebugLog(false);
        gameHelper.setup(this);
    }

    public void onStart(Activity activity) {
        gameHelper.onStart(activity);
    }

    public void onStop() {
        gameHelper.onStop();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void signIn() {
        try {
            activity.runOnUiThread(new Runnable() {
                //@Override
                public void run() {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (Exception e){
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void signOut() {
        try {
            activity.runOnUiThread(new Runnable() {
                //@Override
                public void run() {
                    gameHelper.signOut();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void rateGame() {
        String str ="https://play.google.com/store/apps/details?id=" + activity.getPackageName();
        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    @Override
    public void showLeaderboard() {
        Intent intent = Games.Leaderboards.getAllLeaderboardsIntent(gameHelper.getApiClient());
        activity.startActivityForResult(intent, 1);
    }

    @Override
    public void showAchievements() {
        Intent intent = Games.Achievements.getAchievementsIntent(gameHelper.getApiClient());
        activity.startActivityForResult(intent, 1);
    }

    @Override
    public boolean submitScore(int score, String guestName) {
        if (isSignedIn()) {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(), activity.getString(R.string.leaderboard_id), score);
            return true;
        }
        return false;
    }

    @Override
    public boolean earnAchievement(String achivmentId) {
        if(isSignedIn()) {
            Games.Achievements.unlock(gameHelper.getApiClient(), achivmentId);
        }
        return false;
    }

    @Override
    public boolean incrementAchievement(String achivmentId, int incrementValue) {
        if(isSignedIn()) {
            Games.Achievements.increment(gameHelper.getApiClient(), achivmentId, incrementValue);
        }
        return false;
    }

    @Override
    public Array<AchievementState> getAchievementsStates() {
        //return Games.Achievements.load(gameHelper.getApiClient(), true);
        return new Array<AchievementState>(); // TODO
    }

    @Override
    public void showScores() {}

    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
    }

    @Override
    public void onSignInFailed() {}

    @Override
    public void onSignInSucceeded() {}
}

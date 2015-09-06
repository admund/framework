package me.admund.framework.client;

import com.badlogic.gdx.utils.Array;
import me.admund.framework.achievements.AchievementState;
import me.admund.framework.achievements.IAchievementsProvider;

/**
 * Created by admund on 2015-08-24.
 */
public class HtmlAchivmentsProvider implements IAchievementsProvider {
    private static final int GAME_ID = -1;
    private static final String PRIVATE_KEY = "TODO";

    private GJAPI gjapi = null;

    public HtmlAchivmentsProvider() {
        gjapi = new GJAPI(PRIVATE_KEY, GAME_ID);
    }

    @Override
    public void signIn() {

    }

    @Override
    public void signOut() {

    }

    @Override
    public void rateGame() {

    }

    @Override
    public void showLeaderboard() {

    }

    @Override
    public void showAchievements() {

    }

    @Override
    public boolean submitScore(int score, String guestName) {
        return gjapi.addScore(score, guestName);
    }

    @Override
    public boolean earnAchievement(String achivmentId) {
        return false;
    }

    @Override
    public boolean incrementAchievement(String achivmentId, int incrementValue) {
        return false;
    }

    @Override
    public Array<AchievementState> getAchievementsStates() {
        return null;
    }

    @Override
    public void showScores() {
    }

    @Override
    public boolean isSignedIn() {
        return false;
    }
}

package me.admund.framework.achievements;

import com.badlogic.gdx.utils.Array;

/**
 * Created by admund on 2015-03-08.
 */
public interface IAchievementsProvider {
    void signIn();
    void signOut();
    void rateGame();
    void showLeaderboard();
    void showAchievements();
    boolean submitScore(int score);
    boolean earnAchievement(String achivmentId);
    boolean incrementAchievement(String achivmentId, int incrementValue);
    Array<AchievementState> getAchievementsStates();
    void showScores();
    boolean isSignedIn();
}

package me.admund.framework.achievements;

/**
 * Created by admund on 2015-03-08.
 */
public interface IAchievementsProvider {
    public void signIn();
    public void signOut();
    public void rateGame();
    public boolean submitScore(int score);
    public void showScores();
    public boolean isSignedIn();
}

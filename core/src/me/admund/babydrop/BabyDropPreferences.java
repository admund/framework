package me.admund.babydrop;

import com.badlogic.gdx.Preferences;
import me.admund.babydrop.logic.Logic;
import me.admund.framework.logic.GamePreferences;
import me.admund.framework.logic.Score;

/**
 * Created by admund on 2015-02-23.
 */
public class BabyDropPreferences extends GamePreferences {

    public BabyDropPreferences(Preferences preferences) {
        super(preferences);
    }

    @Override
    public void init() {
        Logic.score.addScore(ScoreNameList.BEST_SCORE, preferences.getInteger(ScoreNameList.BEST_SCORE, 0));
        Logic.score.addScore(ScoreNameList.COINS, preferences.getInteger(ScoreNameList.COINS, 0));
    }

    @Override
    public void save(Score score) {
        setBestScore();
        setCoins();
        preferences.flush();
    }

    private void setBestScore() {
        int oldBestScore = (int)Logic.score.getScore(ScoreNameList.BEST_SCORE);
        int newBestScore = (int)Logic.score.getScore(ScoreNameList.SCORE);
        if(newBestScore > oldBestScore) {
            preferences.putInteger(ScoreNameList.BEST_SCORE, newBestScore);
            Logic.score.setScore(ScoreNameList.BEST_SCORE, newBestScore);

            boolean result = Logic.achievementsProvider.submitScore(newBestScore);
            System.out.println("highscore " + result);
        }
    }

    private void setCoins() {
        preferences.putInteger(ScoreNameList.COINS, (int)Logic.score.getScore(ScoreNameList.COINS));
    }
}

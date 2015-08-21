package me.admund.framework.achievements;

import me.admund.framework.logic.Score;

/**
 * Created by admund on 2015-07-05.
 */
public class ScoreAchievement extends Achievement {

    private Score score = null;
    private String scoreName = null;
    private float scoreValue = 0;

    public ScoreAchievement(String ID) {
        this(ID, "", false, false);
    }

    public ScoreAchievement(String ID, String displayName, boolean isAchieved, boolean isIncremental) {
        super(ID, displayName, isAchieved, isIncremental);
    }

    public Achievement init(Score score, String scoreName, float scoreValue) {
        this.score = score;
        this.scoreName = scoreName;
        this.scoreValue = scoreValue;
        return this;
    }

    @Override
    public boolean check() {
        if(score.getScore(scoreName) >= scoreValue) {
            return true;
        }
        return false;
    }
}

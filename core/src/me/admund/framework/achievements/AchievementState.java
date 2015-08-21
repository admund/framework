package me.admund.framework.achievements;

/**
 * Created by admund on 2015-07-05.
 */
public class AchievementState {
    private String ID = null;
    private boolean isAchieved = false;

    public AchievementState(String ID, boolean isAchieved) {
        this.ID = ID;
        this.isAchieved = isAchieved;
    }

    public String getID() {
        return ID;
    }

    public boolean isAchieved() {
        return isAchieved;
    }
}

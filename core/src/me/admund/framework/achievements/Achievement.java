package me.admund.framework.achievements;

/**
 * Created by admund on 2015-07-05.
 */
public class Achievement {
    private String ID = null;
    private String displayName = null;
    private boolean isAchieved = false;
    private boolean isIncremental = false;

    public Achievement(String ID, String displayName, boolean isAchieved, boolean isIncremental) {
        this.ID = ID;
        this.displayName = displayName;
        this.isAchieved = isAchieved;
        this.isIncremental = isIncremental;
    }

    public boolean check() {
        return false;
    }

    public String getID() {
        return ID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isAchieved() {
        return isAchieved;
    }

    public void setAchieved(boolean isAchieved) {
        this.isAchieved = isAchieved;
    }

    public boolean isIncremental() {
        return isIncremental;
    }
}

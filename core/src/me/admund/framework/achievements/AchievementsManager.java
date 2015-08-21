package me.admund.framework.achievements;

import com.badlogic.gdx.utils.Array;

/**
 * Created by admund on 2015-07-05.
 */
public abstract class AchievementsManager {
    private Array<Achievement> achievmentsList = new Array<Achievement>();
    private IAchievementsProvider achievementsProvider;

    public AchievementsManager(IAchievementsProvider achievementsProvider) {
        this.achievementsProvider = achievementsProvider;
    }

    public void init() {
        updateAchievementsState(achievementsProvider.getAchievementsStates());
    }

    public void addAchievement(Achievement achievement) {
        achievmentsList.add(achievement);
    }

    public void checkAchievements() {
        for(Achievement achievement : achievmentsList) {
            if(!achievement.isAchieved() && achievement.check()) {
                achievementsProvider.earnAchievement(achievement.getID());
                achievement.setAchieved(true);
            }
        }
    }

    public void updateAchievementsState(Array<AchievementState> achievementStates) {
        for(AchievementState state : achievementStates) {
            for(Achievement achievement : achievmentsList) {
                if(state.getID().equals(achievement.getID())) {
                    achievement.setAchieved(state.isAchieved());
                }
            }
        }
    }
}

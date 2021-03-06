package me.admund.framework.desktop;

import com.badlogic.gdx.utils.Array;
import me.admund.framework.achievements.AchievementState;
import me.admund.framework.achievements.IAchievementsProvider;
import org.gamejolt.GameJoltAPI;
import org.gamejolt.Trophy;

import java.util.ArrayList;

/**
 * Created by admund on 2015-03-07.
 */
public class DesktopAchievementsProvider implements IAchievementsProvider {
    private static final int GAME_ID = -1;
    private static final String PRIVATE_KEY = "TODO";

    private GameJoltAPI api = null;

    public DesktopAchievementsProvider() {
        api = new GameJoltAPI(GAME_ID, PRIVATE_KEY);
    }

    @Override
    public void signIn() {}

    @Override
    public void signOut() {}

    @Override
    public void rateGame() {}

    @Override
    public void showLeaderboard() {}

    @Override
    public void showAchievements() {}

    @Override
    public boolean submitScore(int score, String guestName) {
        if(api.isVerified()) {
            return api.addHighscore(score + " pts", score);
        } else {
            return api.addHighscore(guestName == null ? "Guest" : guestName, score + " pts", score);
        }
    }

    @Override
    public boolean earnAchievement(String trophyId) {
        if(api.isVerified()) {
            return api.achieveTrophy(Integer.parseInt(trophyId));
        }
        return false;
    }

    @Override
    public boolean incrementAchievement(String achivmentId, int incrementValue) {
        return false; // GameJolt don't provide increment achievements
    }

    @Override
    public void showScores() {}

    @Override
    public boolean isSignedIn() {
        return false;
    }

    @Override
    public Array<AchievementState> getAchievementsStates() {
        Array<AchievementState> achievementsList = new Array<AchievementState>();
        ArrayList<Trophy> trophyArrayList = api.getTrophies();
        for(Trophy trophy : trophyArrayList) {
            achievementsList.add(parse(trophy));
        }
        return achievementsList;
    }

    private AchievementState parse(Trophy trophy) {
        return new AchievementState(trophy.getId(), isAchived(trophy));
    }

    private boolean isAchived(Trophy trophy) {
        String isAchived = trophy.getProperty("achieved");
        if(isAchived.equals("false"))
            return false;
        else
            return true;
    }
}

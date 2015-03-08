package me.admund.framework.desktop;

import me.admund.framework.achievements.IAchievementsProvider;
import org.gamejolt.GameJoltAPI;

/**
 * Created by admund on 2015-03-07.
 */
public class DesktopAchievementsProvider implements IAchievementsProvider {
    private GameJoltAPI api = null;

    public DesktopAchievementsProvider() {
        api = new GameJoltAPI(GameJoltPrivate.GAME_ID, GameJoltPrivate.PRIVATE_KEY);
        api.setVerbose(true);
    }

    @Override
    public boolean sendHighscore(int score) {
        System.out.println("verbose " + api.isVerbose());
        if(api.isVerified()) {
            return api.addHighscore(score + " pts", score);
        } else {
            return api.addHighscore("BabyDropGuest", score + " pts", score);
        }
    }
}

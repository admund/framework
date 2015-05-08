package me.admund.framework.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import me.admund.framework.GameConfig;

/**
 * Created by admund on 2014-12-23.
 */
public abstract class AbstractScene implements IScene {
    private Stage stage = null;
    protected Stage guiStage = null;
    //protected IGamepadMenuElement firstElement = null; TODO

    private ObjectMap<String, Group> groupList = null;

    public AbstractScene() {
        stage = new Stage(new ExtendViewport(GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT));
        guiStage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        groupList = new ObjectMap<String, Group>();
        setInputProcessor();
    }

    public void createGroups(String[] groupNames) {
        for(int i=0; i<groupNames.length; i++) {
            Group group = new Group();
            groupList.put(groupNames[i], group);
            stage.addActor(group);
        }
    }

    public void addActor(String groupName, Actor actor) {
        Group group = groupList.get(groupName);
        if(group == null) {
            throw new RuntimeException("AbstractScene: Can't find group named " + groupName);
        }
        group.addActor(actor);
    }

    public Group getGroup(String groupName) {
        Group group = groupList.get(groupName);
        if(group == null) {
            throw new RuntimeException("AbstractScene: Can't find group named " + groupName);
        }
        return group;
    }

    @Override
    public void resize (int width, int height) {
        stage.getViewport().update(width, height, false);
        guiStage.getViewport().update(width, height, false);
    }

    @Override
    public void act(float deltaTime) {
        stage.act(deltaTime);
        guiStage.act(deltaTime);
    }

    @Override
    public void draw(Batch batch) {
        stage.draw();
        guiStage.draw();
    }

    @Override
    public void refresh() {
        setInputProcessor();
    }

    @Override
    public void dispose() {
        stage.dispose();
        guiStage.dispose();
    }

    public Camera getCurrentCamera() {
        return stage.getCamera();
    }

    protected void setInputProcessor() {
        InputMultiplexer input = new InputMultiplexer();
        input.addProcessor(guiStage);
        input.addProcessor(stage);
        Gdx.input.setInputProcessor(input);
    }

//    private long time = 0;
//    private void startLog() {
//        time = System.currentTimeMillis();
//    }
//    private void printLog(String str) {
//        System.out.println((System.currentTimeMillis() - time) + " " + str);
//    }
}

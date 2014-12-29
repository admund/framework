package me.admund.framework.draw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

import java.util.Iterator;

/**
 * Created by admund on 2014-12-29.
 */
public class TextureRepo implements Disposable {
    private static final TextureRepo inst = new TextureRepo();
    private TextureRepo(){}
    public static TextureRepo inst(){
        return inst;
    }

    private ObjectMap<String, Texture> textureMap = new ObjectMap<String, Texture>();

    public Texture getTexture(String name) {
        if(name == null) {
            return null;
        }
        Texture result = textureMap.get(name);
        if(result == null) {
            result = new Texture(name);
            textureMap.put(name, result);
        }
        return result;
    }

    @Override
    public void dispose() {
        Iterator<Entry<String, Texture>> iterator = textureMap.iterator();
        while(iterator.hasNext()) {
            Entry<String, Texture> next = iterator.next();
            if(next.value != null) {
                next.value.dispose();
            }
        }
    }
}

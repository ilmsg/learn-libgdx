package io.github.ilmsg;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Apple {
    private Texture apple;

    private Vector2 position;

    public Apple() {
        apple = new Texture("apple.png");
        position = new Vector2();
    }

    public void render(SpriteBatch sb) {
        sb.draw(apple, position.x, position.y);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return this.position;
    }
}

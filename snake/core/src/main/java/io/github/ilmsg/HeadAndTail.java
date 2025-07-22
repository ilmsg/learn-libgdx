package io.github.ilmsg;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class HeadAndTail {
    private Texture snakeHead;
    private Texture snakeTail;

    private final float DELAY_TIME = 0.1f;
    private float timer = DELAY_TIME;
    private Array<Vector2> positions;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private int noCells;

    public HeadAndTail(int x, int y) {
        snakeHead = new Texture("snakehead.png");
        snakeTail = new Texture("snaketail.png");

        left = false;
        right = true;
        up = false;
        down = false;

        positions = new Array<Vector2>();
        noCells = 3;
        for (int i = 0; i < noCells; i++) {
            positions.add(new Vector2(x - i * SnakeGame.CELL_SIZE, y));
        }
    }

    public void update(float dt) {
        timer -= dt;
        if (timer <= 0) {
            timer = DELAY_TIME;

            for (int i = positions.size - 1; i > 0; i--) {
                positions.get(i).x = positions.get(i - 1).x;
                positions.get(i).y = positions.get(i - 1).y;
            }

            if (left) {
                positions.get(0).x -= SnakeGame.CELL_SIZE;
            } else if (right) {
                positions.get(0).x += SnakeGame.CELL_SIZE;
            } else if (up) {
                positions.get(0).y += SnakeGame.CELL_SIZE;
            } else if (down) {
                positions.get(0).y -= SnakeGame.CELL_SIZE;
            }
        }
    }

    public void render(SpriteBatch sb) {
        for (int i = 0; i < positions.size; i++) {
            if (i == 0) {
                sb.draw(snakeHead, positions.get(i).x, positions.get(i).y);
            } else {
                sb.draw(snakeTail, positions.get(i).x, positions.get(i).y);
            }
        }
    }

    public void increaseTailCells() {
        positions.add(new Vector2(positions.get(positions.size - 1).x, positions.get(positions.size - 1).y));
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean getLeft() {
        return left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean getRight() {
        return right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean getUp() {
        return up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean getDown() {
        return down;
    }

    public Array<Vector2> getPositions() {
        return positions;
    }
}

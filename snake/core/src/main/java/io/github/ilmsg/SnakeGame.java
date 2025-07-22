package io.github.ilmsg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SnakeGame extends ApplicationAdapter {
    public static final String TITLE = "Snake Game";
    public static final int WIDTH = 300; // Width of the game window
    public static final int HEIGHT = 300; // Height of the game window
    public static final int CELL_SIZE = 10; // Size of each cell in the grid

    private SpriteBatch sb;
    private OrthographicCamera cam;
    private HeadAndTail snake;
    private Apple apple;
    private int randPositionXApple = (WIDTH / CELL_SIZE) - 1;
    private int randPositionYApple = (HEIGHT / CELL_SIZE) - 1;
    private boolean samePosition = false;

    @Override
    public void create() {
        // Initialize game resources, such as textures, sounds, etc.
        sb = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, WIDTH, HEIGHT);

        snake = new HeadAndTail(50, 50);
        apple = new Apple();
        randomApplePosition();

        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        draw();
    }

    public void update(float dt) {
        handleInput();
        snake.update(dt);
        checkAppleCollision();
        cam.update();
    }

    public void draw() {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        snake.render(sb);
        apple.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        // Clean up resources when the game is closed.
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT) && !snake.getRight()) {
            snake.setLeft(true);
            snake.setUp(false);
            snake.setDown(false);
        } else if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT) && !snake.getLeft()) {
            snake.setRight(true);
            snake.setUp(false);
            snake.setDown(false);
        } else if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP) && !snake.getDown()) {
            snake.setUp(true);
            snake.setLeft(false);
            snake.setRight(false);
        } else if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.DOWN) && !snake.getUp()) {
            snake.setDown(true);
            snake.setLeft(false);
            snake.setRight(false);
        }
    }

    private void randomApplePosition() {
        do {
            samePosition = false;

            int randX = (int) (Math.random() * randPositionXApple) * CELL_SIZE;
            int randY = (int) (Math.random() * randPositionYApple) * CELL_SIZE;
            apple.setPosition(new Vector2(randX, randY));

            for (int i = 0; i < snake.getPositions().size; i++) {
                if (apple.getPosition().equals(snake.getPositions().get(i))) {
                    samePosition = true;
                    break;
                }
            }
        } while (samePosition);
    }

    private void checkAppleCollision() {
        if (apple.getPosition().equals(snake.getPositions().get(0))) {
            snake.increaseTailCells();
            randomApplePosition();
        }
    }
}

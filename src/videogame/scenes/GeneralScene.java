package videogame.scenes;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashSet;
import java.util.Set;

/**
 * Generic, abstract class for every scene of the game (welcome, game and credits)
 * Extend of class {@link javafx.scene.Scene}
 * @author Valentina Cegarra
 */
public abstract class GeneralScene extends Scene
{
    public static final int GAME_WIDTH = 480;
    public static final int GAME_HEIGHT = 716;

    protected StackPane root = new StackPane();
    protected GraphicsContext gc;
    protected Set<KeyCode> activeKeys;
    protected Set<KeyCode> releasedKeys;
    protected MediaPlayer mediaPlayer;
    protected Media sound;

    /**
     * <p>GeneralScene constructor</p>
     */
    public GeneralScene()
    {
        super(new StackPane(), GAME_WIDTH, GAME_HEIGHT);

        root = new StackPane();
        this.setRoot(root);

        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        activeKeys = new HashSet<>();
        releasedKeys = new HashSet<>();
        this.setOnKeyPressed(e -> {
            activeKeys.add(e.getCode());
        });
        this.setOnKeyReleased(e -> {
            activeKeys.remove(e.getCode());
            releasedKeys.add(e.getCode());
        });
    }

    /**
     * <p>Abstract method to draw in the scenes</p>
     */
    public abstract void draw();
}

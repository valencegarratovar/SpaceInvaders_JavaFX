package videogame.sprites;

import javafx.scene.image.Image;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class to represent Sprite of type Shot.
 * Extend of class {@link videogame.sprites.Sprite}
 * @author Valentina Cegarra
 */
public class Shot extends Sprite {
    public static int SHOT_WIDTH = 10;
    public static int SHOT_HEIGHT = 15;

    /**
     * <p>Shot constructor</p>
     * @param imagePath path of image
     */
    public Shot(String imagePath) {
        super(SHOT_WIDTH, SHOT_HEIGHT);
        try {
            spriteImage = new Image(Files.newInputStream(Paths.get(imagePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Decrement Y value</p>
     */
    public void moveUp() {
        this.y -= 2;
    }

    /**
     * <p>Increment Y value</p>
     */
    public void moveDown() {
        this.y += 2;
    }
}

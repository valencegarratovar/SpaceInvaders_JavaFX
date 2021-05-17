package videogame.sprites;
import javafx.scene.image.Image;
import videogame.scenes.GeneralScene;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class to represent Sprite of type Alien.
 * Extend of class {@link videogame.sprites.Sprite}
 * @author Valentina Cegarra
 */
public class Alien extends Sprite
{
    private static final String IMAGE_PATH = "assets/Space_invaders_alien.jpg";

    public static int ALIEN_WIDTH = 70;
    public static int ALIEN_HEIGHT = 33;

    /**
     * <p>Alien constructor</p>
     */
    public Alien() {
        super(ALIEN_WIDTH, ALIEN_HEIGHT);
        try {
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

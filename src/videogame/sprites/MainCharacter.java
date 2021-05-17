package videogame.sprites;

import javafx.scene.image.Image;
import videogame.scenes.GeneralScene;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class to represent Sprite of type MainCharacter.
 * Extend of class {@link videogame.sprites.Sprite}
 * @author Valentina Cegarra
 */
public class MainCharacter extends Sprite

{
    public static final int MAIN_CHARACTER_WIDTH = 70;
    public static final int  MAIN_CHARACTER_HEIGHT = 70;
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    private static final String IMAGE_PATH = "assets/ship.jpg";
    private static final int STEP = 4;

    /**
     * <p>MainCharacter constructor</p>
     */
    public MainCharacter()
    {
        super(MAIN_CHARACTER_WIDTH, MAIN_CHARACTER_HEIGHT);
        try
        {
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        } catch (Exception e){
            e.printStackTrace();
        }

        spriteX = 0;
        spriteY = 0;
    }

    /**
     * <p>Move according to key pressed</p>
     * @param movement key pressed
     */
    public void move (int movement)
    {
        int newX = x;
        if (movement == LEFT)
            newX -= Math.min(STEP, x);
        else if (movement == RIGHT)
            newX += Math.min(STEP, GeneralScene.GAME_WIDTH - MAIN_CHARACTER_WIDTH - x);
        moveTo(newX, y);
    }

    /**
     * <p>Move to default position</p>
     */
    public void resetPosition()
    {
        moveTo(GeneralScene.GAME_WIDTH / 2 - MAIN_CHARACTER_WIDTH / 2,
                GeneralScene.GAME_HEIGHT - 30 - MAIN_CHARACTER_HEIGHT);
    }
}

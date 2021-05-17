package videogame.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import videogame.SpaceInvaders;

public class WelcomeScene extends GeneralScene implements Hud
{
    public static final String BACKGROUND_SONG = "assets/spaceinvaders1.wav";
    public WelcomeScene()
    {
        super();
        updateHUD();
    }

    public void updateHUD()
    {
        Font myFont = Font.font("Arial", FontWeight.BOLD, 40);
        gc.setFont(myFont);
        gc.setFill(Color.YELLOW);
        gc.fillText("SPACE INVADERS", 60, 300);

        myFont = Font.font("Arial", FontWeight.NORMAL, 20);
        gc.setFont(myFont);
        gc.setFill(Color.WHITE);
        gc.fillText("Press Enter to play", 150, 350);
    }

    @Override
    public void draw()
    {
        activeKeys.clear();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

                updateHUD();

                if (activeKeys.contains(KeyCode.ENTER)) {
                    this.stop();
                    SpaceInvaders.setScene(SpaceInvaders.GAME_SCENE);
                } else if (activeKeys.contains(KeyCode.ESCAPE)) {
                    this.stop();
                    SpaceInvaders.exit();
                }
            }
        }.start();
    }
}

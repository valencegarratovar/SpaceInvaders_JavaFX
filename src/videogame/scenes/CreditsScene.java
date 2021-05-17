package videogame.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import videogame.SpaceInvaders;

public class CreditsScene extends GeneralScene implements Hud
{
    public CreditsScene()
    {
        super();
    }

    public void updateHUD()
    {
        if(GameScene.points < 360){
            Font myFont = Font.font("Arial", FontWeight.NORMAL, 50);
            gc.setFont(myFont);
            gc.setFill(Color.RED);
            gc.fillText("Game Over", 100, 300);

            showScore();
        }else {
            Font myFont = Font.font("Arial", FontWeight.NORMAL, 50);
            gc.setFont(myFont);
            gc.setFill(Color.LIGHTGREEN);
            gc.fillText("YOU WIN!", 120, 300);

            showScore();
        }
    }

    private void showScore() {
        Font myFont;
        myFont = Font.font("Arial", FontWeight.NORMAL, 20);
        gc.setFont(myFont);
        gc.setFill(Color.YELLOW);
        gc.fillText("Your score: " + GameScene.points, 170, 350);
        gc.setFill(Color.WHITE);
        gc.fillText("Press Enter to go back to welcome scene", 60, 400);
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

                if (activeKeys.contains(KeyCode.ENTER))
                {
                    this.stop();
                    SpaceInvaders.setScene(
                            SpaceInvaders.WELCOME_SCENE);
                }
            }
        }.start();
    }
}

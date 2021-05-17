package videogame.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import videogame.SpaceInvaders;
import videogame.sprites.Alien;
import videogame.sprites.AliensGroup;
import videogame.sprites.MainCharacter;
import videogame.sprites.Shot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameScene extends GeneralScene implements Hud {
    private static final String BACKGROUND_IMAGE = "assets/BackgroundSpace.png";
    public static final String SHOT_EFFECT = "assets/quick-jump.wav";
    public static final String INVADERKILLED_EFFECT = "assets/invaderkilled.wav";
    public static final String ALIEN_SHOT_IMAGE = "assets/alienShot.png";
    public static final String PLAYER_SHOT_IMAGE = "assets/playerShot.png";

    private Image background;
    private MainCharacter ship;
    private MediaPlayer mediaPlayerEffects;
    private Media effect;
    public static int points = 0;
    private int lives = 3;
    float second = 0;
    private int level = 1;

    List<Shot> playerShoots = new ArrayList<Shot>();
    List<Shot> alienShoots = new ArrayList<Shot>();
    AliensGroup aliens = new AliensGroup(level);

    public GameScene() {
        super();
        try {
            background = new Image(Files.newInputStream(Paths.get(BACKGROUND_IMAGE)));
            ship = new MainCharacter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        reset();
        activeKeys.clear();
        ship.moveTo(380, 600);

        new AnimationTimer() {

            public void handle(long currentNanoTime) {
                second += 1;

                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
                gc.drawImage(background, 0, 0);
                ship.draw(gc);

                drawAliens();
                nextLevel(this);
                frequencyShotByLevel();
                playerShotActions();
                aliensShotActions();
                checkIfGameOver(this);
                updateHUD();
                KeyEvaluation(this);
            }
        }.start();
    }

    private void nextLevel(AnimationTimer animationTimer) {
        if (!aliens.getAliens().isEmpty())
            aliens.aliensMove();
        else if (aliens.getAliens().isEmpty() && level == 1) {
            level++;
            createLevel();
        } else if (aliens.getAliens().isEmpty() && level == 2) {
            level++;
            createLevel();
        } else if (aliens.getAliens().isEmpty() && level == 3) {
            animationTimer.stop();
            SpaceInvaders.setScene(
                    SpaceInvaders.CREDITS_SCENE);
        }
    }

    private void checkIfGameOver(AnimationTimer animationTimer) {
        if (lives == 0) {
            animationTimer.stop();
            SpaceInvaders.setScene(
                    SpaceInvaders.CREDITS_SCENE);
        }
    }

    private void aliensShotActions() {
        if (!alienShoots.isEmpty()) {
            for (Shot shot : alienShoots) {
                shot.draw(gc);
                shot.moveDown();

                if (ship.collisionWidth(shot)) {
                    lives--;
                    alienShoots.remove(shot);
                    break;
                }

                if (shot.getY() >= 650) {
                    alienShoots.remove(shot);
                    break;
                }
            }
        }
    }

    private void playerShotActions() {
        if (!playerShoots.isEmpty()) {
            for (Shot shot : playerShoots) {
                shot.draw(gc);
                shot.moveUp();

                if (aliens.getAliens().removeIf(shot::collisionWidth)) {
                    playEffect(INVADERKILLED_EFFECT);
                    points += 10;
                    playerShoots.remove(shot);
                    break;
                }

                if (shot.getY() <= 50) {
                    playerShoots.remove(shot);
                    break;
                }
            }
        }
    }

    private void drawAliens() {
        for (Alien alien : aliens.getAliens()) {
            alien.draw(gc);
        }
    }

    private void frequencyShotByLevel() {
        if (second >= 100 && level == 1) {
            checkIfAliensCanShoot();
        } else if (second >= 60 && level == 2) {
            checkIfAliensCanShoot();
        } else if (second >= 30 && level == 3) {
            checkIfAliensCanShoot();
        }
    }

    private void checkIfAliensCanShoot() {
        assert aliens != null;
        if (!aliens.getAliens().isEmpty()) {
            alienShoot();
            second = 0;
        }
    }

    private void KeyEvaluation(AnimationTimer animationTimer) {
        setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER -> {
                    animationTimer.stop();
                    SpaceInvaders.setScene(
                            SpaceInvaders.WELCOME_SCENE);
                }
                case ESCAPE -> {
                    animationTimer.stop();
                    SpaceInvaders.setScene(
                            SpaceInvaders.CREDITS_SCENE);
                }
                case RIGHT -> {
                    ship.move(MainCharacter.RIGHT);
                }
                case LEFT -> {
                    ship.move(MainCharacter.LEFT);
                }
                case SPACE -> {
                    playerShoot(ship.getX(), ship.getY());
                    playEffect(SHOT_EFFECT);
                }
            }
        });
    }

    private void createLevel() {
        aliens = new AliensGroup(level);
        ship.resetPosition();
        playerShoots.clear();
        alienShoots.clear();
    }

    private void playEffect(String path) {
        effect = new Media(new File(path).toURI().toString());
        mediaPlayerEffects = new MediaPlayer(effect);
        mediaPlayerEffects.play();
    }

    public void updateHUD() {
        Font myFont = Font.font("Arial", FontWeight.NORMAL, 18);
        gc.setFont(myFont);
        gc.setFill(Color.BLUE);
        gc.fillText("Score: " + points, 20, GeneralScene.GAME_HEIGHT - 15);

        gc.setFill(Color.YELLOW);
        gc.fillText("Lives: " + lives, GeneralScene.GAME_WIDTH - 100, GeneralScene.GAME_HEIGHT - 15);

        myFont = Font.font("Arial", FontWeight.BOLD, 30);
        gc.setFont(myFont);
        gc.setFill(Color.WHITE);
        gc.fillText("LEVEL " + level, GeneralScene.GAME_WIDTH - 300, GeneralScene.GAME_HEIGHT - 680);
    }

    public void playerShoot(int posX, int posY) {
        Shot s = new Shot(PLAYER_SHOT_IMAGE);
        s.moveTo(posX + 28, posY - 15);
        playerShoots.add(s);
    }

    public void alienShoot() {
        int getShootingAlienIndex = (int) (Math.random() * (aliens.getAliens().size()));
        Alien alien = aliens.getAliens().get(getShootingAlienIndex);
        Shot s = new Shot(ALIEN_SHOT_IMAGE);
        s.moveTo(alien.getX() + 15, alien.getY() + 25);
        alienShoots.add(s);
    }

    private void reset() {
        lives = 3;
        points = 0;
    }
}


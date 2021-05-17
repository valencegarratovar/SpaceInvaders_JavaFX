package videogame.sprites;

import java.util.ArrayList;
import java.util.List;

/**
 * AliensGroup are a Sprite subtype which represent a group of Alien Sprite.
 * @author Valentina Cegarra
 */
public class AliensGroup {
    private List<Alien> aliens = new ArrayList<Alien>();
    boolean toRight = true;

    /**
     * <p>AliensGroup constructor</p>
     * @param level game level
     */
    public AliensGroup(int level) {
        createAliens(level);
    }

    /**
     * <p>Getter of aliens list value</p>
     * @return aliens list
     */
    public List<Alien> getAliens() {
        return aliens;
    }

    /**
     * <p>Create different groups of aliens per level</p>
     * @param level game level
     */
    private void createAliens(int level) {
        switch (level) {
            case 1 -> addAliens(10, 100);
            case 2 -> {
                addAliens(10, 100);
                addAliens(10, 150);
            }
            case 3 -> {
                addAliens(10, 100);
                addAliens(10, 150);
                addAliens(10, 200);
            }
            default -> {}
        }
    }

    /**
     * <p>Create each alien on the given position and
     * add it to a list of aliens</p>
     * @param posX horizontal position
     * @param posY vertical position
     */
    public void addAliens(int posX, int posY) {
        for (int i = 0; i < 6; i++) {
            Alien a = new Alien();
            a.moveTo(posX, posY);
            this.aliens.add(a);
            posX += 60;
        }
    }

    /**
     * <p>Move aliens group</p>
     */
    public void aliensMove(){
        int speed;

        if (toRight)
            speed = 1;
        else
            speed = -1;

        for (Alien alien : aliens) {
            alien.setX(alien.getX() + speed);

            if (alien.getX() >= 430)
                toRight = false;
            else if (alien.getX() <= 0)
                toRight = true;
        }
    }
}

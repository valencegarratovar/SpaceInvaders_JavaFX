package videogame.sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class that contains the main methods of the game elements.
 * @author Valentina Cegarra
 */
public class Sprite
{
    protected int width, height;
    protected int x, y;
    protected int spriteX, spriteY;
    protected Image spriteImage;

    /**
     * <p>Sprite constructor</p>
     * @param width horizontal image size
     * @param height vertical image size
     */
    public Sprite(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    /**
     * <p>Move to indicated coordinates</p>
     * @param x horizontal position
     * @param y vertical position
     */
    public void moveTo(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * <p>Getter of X value</p>
     * @return x value
     */
    public int getX()
    {
        return x;
    }

    /**
     * <p>Setter of X value</p>
     * @param x horizontal position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * <p>Getter of Y value</p>
     * @return y value
     */
    public int getY()
    {
        return y;
    }

    /**
     * <p>Method that draw sprite</p>
     * @param gc graphic context that helps to draw
     */
    public void draw(GraphicsContext gc)
    {
        gc.drawImage(spriteImage, spriteX, spriteY,
                width, height, x, y, width, height);
    }

    /**
     * <p>Evaluates if two sprites collide</p>
     * @param sp Sprite to collide with
     * @return a boolean when if two sprites collide or not
     */
    public boolean collisionWidth(Sprite sp)
    {
        return (x + width / 2 > sp.x && x < sp.x + sp.width / 2 &&
                y + height / 2 > sp.y && y < sp.y + sp.height / 2);
    }
}

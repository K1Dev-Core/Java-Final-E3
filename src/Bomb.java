import java.awt.Image;
import java.util.Random;

public class Bomb {
    private Image image;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Random random;
    private double speed  = 1.0;

    public Bomb(Image image, int x, int y,double speed) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = (int)(image.getWidth(null) * 1.0);
        this.height = (int)(image.getHeight(null) * 1.0);
        this.visible = true;
        this.random = new Random();
    }

    public void update() {
        this.y = y + (int)this.speed;
        if (y > Config.WINDOW_HEIGHT){
            this.visible = true;
        }
    }



    public boolean isVisible() {
        return visible;
    }

    public void hide() {
        this.visible = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }


}

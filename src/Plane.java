import java.awt.Image;
import java.awt.Point;

public class Plane {
    private Image image;
    private int x;
    private int y;
    private int width;
    private int height;
    private int originalWidth;
    private int originalHeight;
    private Point targetPosition;

    public Plane(Image image) {
        this.image = image;
        this.originalWidth = image.getWidth(null);
        this.originalHeight = image.getHeight(null);
        this.width = 164;
        this.height = 164;
        this.x = 0;
        this.y = 0;
        this.targetPosition = new Point(0, 0);
    }

    public void update() {
        if (targetPosition != null) {
            x = targetPosition.x - width / 2;

        }
    }

    public void setPosition(int x, int y) {

        if (x < this.targetPosition.x) {
            System.out.printf("L");
            GamePanel.setPlaneImage(false);
        }else {
            GamePanel.setPlaneImage(true);
            System.out.printf("R");
        }
        this.targetPosition = new Point(x, y);
    }

    public void grow() {
        width = (int)(width * 1.1);
        height = (int)(height * 1.1);
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

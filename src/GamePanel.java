import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {
    public static Object setPlaneImage;
    private List<Car> cars;
    private List<Bomb> Bombs;
    private Plane planeL;
    private Plane planeR;
    private Image[] carImages;

    private int carIndex = 0;
    private Random random;
    private static Image planeLmage;
    private static Image planeRmage;
    private static Image planeNowImage;
    private static Image bombImage;
    public GamePanel() {
        random = new Random();
        loadImages();
        String[] carFiles = {
                "car.png"
        };
        Bombs = new ArrayList<>();
        cars = new ArrayList<>();
        planeL = new Plane(planeLmage);
        planeR = new Plane(planeRmage);

        setPreferredSize(new java.awt.Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        setBackground(Color.BLACK);
        List<int[]> positions = new ArrayList<>();


        for (String file : carFiles) {
            try {

                Image img = ImageIO.read(new File("assets/" + file));
                int x, y;
                for (int i = 0; i < Config.CarAmount; i++) {

                    x = Config.WINDOW_WIDTH  - 80 + random.nextInt(30,100);
                    y = 500;
                    double speed = random.nextInt(1,5);
                    speed = speed +i;
                    positions.add(new int[]{x, y});
                    Car car = new Car(img, x, y,speed);
                    System.out.println("car "+ car+" speed "+speed);
                    cars.add(car);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                planeL.setPosition(e.getX(), e.getY());

                Bomb Bom = new Bomb(bombImage,planeL.getX(), planeL.getY()+ 50,10.0);
                Bombs.add(Bom);
                System.out.println("Bom "+" x " + e.getX() + " "+e.getY());
            }
        });


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                planeL.setPosition(e.getX(), e.getY());

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                planeL.setPosition(e.getX(), e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                planeL.setPosition(e.getX(), e.getY());
            }
        });


        startGameLoop();
    }


    private void startGameLoop() {
        Thread gameThread = new Thread(() -> {
            while (true) {
                update();
                repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }


    private void loadImages() {
        try {
            planeLmage = ImageIO.read(new File("assets/planeL.png"));
            planeRmage = ImageIO.read(new File("assets/planeR.png"));
            bombImage = ImageIO.read(new File("assets/bomb.png"));
            planeNowImage = planeLmage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setPlaneImage(boolean r) {
        if (r) {
            planeNowImage = planeRmage;
        }else{
            planeNowImage = planeLmage;
        }



    }
    private void update() {
        planeL.update();
        for (Car cars : cars) {
            cars.update();
        }
        for (Bomb bomb : Bombs) {
            bomb.update();
        }
        for (Car cars : cars) {
            for (Bomb bomb : Bombs) {
                if (cars.isVisible() && CollisionDetector.checkCollision(cars, bomb)) {
                    if (cars.isVisible() && bomb.isVisible()) {
                        bomb.hide();
                        cars.hide();
                    }


                }
            }
        }


    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(planeNowImage, planeL.getX(), planeL.getY(),
                planeL.getWidth(), planeL.getHeight(), null);
        for (Car cars : cars) {
            if (cars.isVisible()) {
                g.drawImage(cars.getImage(), cars.getX(), cars.getY(),
                        cars.getWidth(), cars.getHeight(), null);

            }
        }
        for (Bomb bomb : Bombs) {
            if (bomb.isVisible()) {
                g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(),
                        bomb.getWidth(), bomb.getHeight(), null);


            }
        }

    }


}

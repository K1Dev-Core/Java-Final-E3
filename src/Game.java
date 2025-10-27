import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Game extends JFrame {
    private GamePanel gamePanel;

    public Game() {
        setTitle("War of War");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gamePanel = new GamePanel();
        add(gamePanel);

        pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        setVisible(true);
    }

}

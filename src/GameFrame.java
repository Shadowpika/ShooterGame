import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    JFrame frame;
    Screen screen = new Screen();
    public GameFrame(){
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocation(550, 100);

        frame.add(screen);
        screen.setPreferredSize(new Dimension(500, 600));
        frame.setBackground(Color.orange);

        frame.setLayout(new GridLayout(1, 1));
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void addListener(GameControls controls){
        frame.addKeyListener(controls);
        frame.addMouseListener(controls);
    }

    public Screen getScreen(){
        return screen;
    }
}

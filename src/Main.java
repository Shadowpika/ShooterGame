import javax.swing.*;

public class Main extends JPanel {
    static GameControls controls = new GameControls();
    static final GameFrame game = new GameFrame();
    static Screen screen = game.getScreen();
    // Player player = screen.getPlayer();

    public static GameFrame getGameFrame(){
        return game;
    }

    public static void main(String[] args) throws Exception {
        // Main main = new Main();
        Timer timer = new Timer(10, screen);
        timer.start();

        screen.addKeyListener(controls);
        screen.setFocusable(true);
        screen.requestFocusInWindow();
        screen.addMouseMotionListener(controls);
        screen.addMouseListener(controls);
        // KeyEvent clicked = new KeyEvent(game, GameControls.keyPressed, 4, SHIFT_DOWN_MASK, GameControls.keyA);
    }


}
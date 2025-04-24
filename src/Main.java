import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;

public class Main extends JPanel {
    static GameControls controls = new GameControls();
    static GameFrame game = new GameFrame();
    static Screen screen = game.getScreen();
    Player player = screen.getPlayer();

    public static GameFrame getGameFrame(){
        return game;
    }



    public static void main(String[] args) throws Exception {
        Main main = new Main();
        Timer timer = new Timer(10, screen);
        timer.start();

        game.addListener(controls);
        KeyEvent clicked = new KeyEvent(game, GameControls.keyPressed, 4, SHIFT_DOWN_MASK, GameControls.keyA);
    }


}
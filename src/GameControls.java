import jdk.jfr.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.MouseEvent.*;
import static java.util.Arrays.asList;


public class GameControls extends JPanel implements KeyListener, MouseListener, MouseMotionListener, ActionListener {

    HashMap<KeyEvent, Boolean> keyStates = new HashMap<>();
    ///arrow keys
    static int keyUp = VK_UP;
    static int keyDown = VK_DOWN;
    static int keyLeft = VK_LEFT;
    static int keyRight = VK_RIGHT;

    /// key events
    static int keyTyped = KEY_TYPED;
    static int keyPressed = KEY_PRESSED;
    static int keyRelease = KEY_RELEASED;

    /// special keys
    static int keySpace = VK_SPACE;
    static int keyESC = VK_ESCAPE;
    static int keyShift = VK_SHIFT;
    static int keyTab = VK_TAB;
    static int keyEnter = VK_ENTER;
    static int keyControl = VK_CONTROL;


    /// main letter keys
    static int keyW = VK_W;
    static int keyA = VK_A;
    static int keyS = VK_S;
    static int keyD = VK_D;
    static int keyZ = VK_Z;
    static int keyX = VK_X;
    static int keyC = VK_C;
    static int keyQ = VK_Q;
    static int keyE = VK_E;
    static int keyR = VK_R;
    static int keyL = VK_L;

    /// number keys
    static int key0 = VK_0;
    static int key1 = VK_1;
    static int key2 = VK_2;
    static int key3 = VK_3;
    static int key4 = VK_4;
    static int key5 = VK_5;
    static int key6 = VK_6;
    static int key7 = VK_7;
    static int key8 = VK_8;
    static int key9 = VK_9;

    //numpad
    static int keyNum0 = VK_NUMPAD0;
    static int keyNum1 = VK_NUMPAD1;
    static int keyNum2 = VK_NUMPAD2;
    static int keyNum3 = VK_NUMPAD3;
    static int keyNum4 = VK_NUMPAD4;
    static int keyNum5 = VK_NUMPAD5;
    static int keyNum6 = VK_NUMPAD6;
    static int keyNum7 = VK_NUMPAD7;
    static int keyNum8 = VK_NUMPAD8;
    static int keyNum9 = VK_NUMPAD9;

    /// mouse clicks
    MouseEvent mouse;
    int click = MOUSE_CLICKED;
    int press = MOUSE_PRESSED;
    int release = MOUSE_RELEASED;
    int drag = MOUSE_DRAGGED;
    int wheel = MOUSE_WHEEL;
    int leftClick = BUTTON1;
    int wheelClick = BUTTON2;
    int rightClick = BUTTON3;


    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Typed: "+e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == keyA){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Released: "+ e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    int x;

    ArrayList<GameObject> eList = new ArrayList<>(Collections.singletonList(new Boss(200, 100, 100, 100)));

    Timer EPT = new Timer(2000, new projListener());
    Timer cooldown = new Timer(10000, new Cooldown());
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        if (eList.size() < 10) {
            for (GameObject enemy : eList){
                if (!(enemy instanceof Enemy)){
                    Main.screen.enemy = new Enemy(x, -10, 8, 8);
                } else {
                    Main.screen.enemy = new AmbushEnemy(x, -10, 8, 8);
                    cooldown.start();
                }
            }
            eList.add(Main.screen.enemy);
        }
        EPT.start();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Player player = Main.screen.getPlayer();
        Projectile projectile = Main.screen.projectile;
        projectile.x = (int) (player.x+3);
        projectile.y = (int) (player.y-3);
        Main.screen.shoot = true;
    }
}

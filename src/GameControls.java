import java.awt.event.*;
import static java.awt.event.KeyEvent.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;


public class GameControls extends JPanel implements KeyListener, MouseListener, MouseMotionListener, ActionListener {
    // HashMap<KeyEvent, Boolean> keyStates = new HashMap<>();
    ///arrow keys
    // static int keyUp = VK_UP;
    // static int keyDown = VK_DOWN;
    // static int keyLeft = VK_LEFT;
    // static int keyRight = VK_RIGHT;

    /// key events
    // static int keyTyped = KEY_TYPED;
    // static int keyPressed = KEY_PRESSED;
    // static int keyRelease = KEY_RELEASED;

    /// special keys
    // static int keySpace = VK_SPACE;
    // static int keyESC = VK_ESCAPE;
    // static int keyShift = VK_SHIFT;
    // static int keyTab = VK_TAB;
    // static int keyEnter = VK_ENTER;
    // static int keyControl = VK_CONTROL;


    /// main letter keys
    static int keyW = VK_W;
    static int keyA = VK_A;
    static int keyS = VK_S;
    // static int keyD = VK_D;
    static int keyZ = VK_Z;
    // static int keyX = VK_X;
    // static int keyC = VK_C;
    static int keyQ = VK_Q;
    // static int keyE = VK_E;
    // static int keyR = VK_R;
    static int keyL = VK_L;
    static int keyP = VK_P;
    static int keyG = VK_G;
    static int keyN = VK_N;
    static int keyM = VK_M;
    static int keyB = VK_B;

    /// number keys
    // static int key0 = VK_0;
    // static int key1 = VK_1;
    // static int key2 = VK_2;
    // static int key3 = VK_3;
    // static int key4 = VK_4;
    // static int key5 = VK_5;
    // static int key6 = VK_6;
    // static int key7 = VK_7;
    // static int key8 = VK_8;
    // static int key9 = VK_9;

    //numpad
    // static int keyNum0 = VK_NUMPAD0;
    // static int keyNum1 = VK_NUMPAD1;
    // static int keyNum2 = VK_NUMPAD2;
    // static int keyNum3 = VK_NUMPAD3;
    // static int keyNum4 = VK_NUMPAD4;
    // static int keyNum5 = VK_NUMPAD5;
    // static int keyNum6 = VK_NUMPAD6;
    // static int keyNum7 = VK_NUMPAD7;
    // static int keyNum8 = VK_NUMPAD8;
    // static int keyNum9 = VK_NUMPAD9;

    /// mouse clicks
    // MouseEvent mouse;
    // int click = MOUSE_CLICKED;
    // int press = MOUSE_PRESSED;
    // int release = MOUSE_RELEASED;
    // int drag = MOUSE_DRAGGED;
    // int wheel = MOUSE_WHEEL;
    // int leftClick = BUTTON1;
    // int wheelClick = BUTTON2;
    // int rightClick = BUTTON3;

    @Override
    public void keyTyped(KeyEvent e) {
        // System.out.println("Typed: "+e);
    }

    String enemy = "Shooter";
    int size = 10;

    ArrayList<BigLasers> laser = new ArrayList<>();
    ArrayList<Gatling> glist = new ArrayList<>();
    Boss boss = new Boss(201, 100, 99, 100);
    BigLasers l1 = new BigLasers(50, -40, 8, 8);
    BigLasers l2 = new BigLasers(450, -40, 8, 8);
    BigLasers l3 = new BigLasers(245, 160, 55, 8);
    Gatling gat = new Gatling(245, 160, 51, 8);

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == keyZ){
            System.exit(0);
        }
        if (e.getKeyCode() == keyA){
            enemy = "Ambush"; //KAMIKAZE
        }
        if (e.getKeyCode() == keyS){
            enemy = "Shooter"; //summons enemy to shoot player
        }
        if (e.getKeyCode() == keyW){
            enemy = "wind"; //pushes player back
        }
        if (e.getKeyCode() == keyQ){
            enemy = "Laser left";
            if (laser.size() < 2){
                eList.addAll(Arrays.asList(Main.screen.enemy = l1, Main.screen.enemy = l2));
                laser.addAll(Arrays.asList(l1, l2));
                size += 2;
                System.out.println("LASER INCOMING");
            }
        }
        if (e.getKeyCode() == keyP){
            enemy = "Laser right"; 
            if (laser.size() < 2){
                eList.addAll(Arrays.asList(Main.screen.enemy = l1, Main.screen.enemy = l2));
                laser.addAll(Arrays.asList(l1, l2));
                size += 2;
                System.out.println("LASER INCOMING");
            }
        }
        if (e.getKeyCode() == keyG){
            enemy = "Laser Middle"; 
            gat.drop();
            if (laser.isEmpty() || laser.size() == 2){
                l3.undrop();
                eList.add(Main.screen.enemy = l3);
                laser.add(l3);
                size += 1;
                eList.add(boss);
                System.out.println("LASER INCOMING");
                bossCheck.start();
            }
        }
        if (e.getKeyCode() == keyM){
            enemy = "machine gun"; 
            l3.drop();
            if (glist.isEmpty()){
                gat.undrop();
                eList.add(gat);
                glist.add(gat);
                size += 1;
                eList.add(boss);
                System.out.println("MACHINE GUN INCOMING");
                bossCheck.start();
            }
        }
        if (e.getKeyCode() == keyN){
            enemy = "nuke"; //beeg explosion <-
        }
        // if (e.getKeyCode() == keyB){
        //     enemy = "bazooka"; //shoots bullet with shrapnel and explosion
        // }
        // if (e.getKeyCode() == keyL){
        //     enemy = "laser gun"; // asriel laser attack 
        // }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println("Released: "+ e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    int x;

    ArrayList<GameObject> eList = new ArrayList<>(Collections.singletonList(new Boss(200, 100, 100, 100)));

    Timer EPT = new Timer(2000, new projListener());
    Timer cooldown = new Timer(10000, (ActionEvent e) -> {
        for (GameObject enemy : Main.controls.eList){
            if (enemy.y > 700){
                System.out.println("reloaded");
                Main.controls.eList.remove(enemy);
            }
            Timer t = (Timer) e.getSource();
            t.stop();
        }
    });
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        if (eList.size() < size) {
            if (enemy.equals("Laser left") || enemy.equals("Laser right") || enemy.equals("Laser Middle")){
                SummonEnemy.summonEnemy(e);
            }else{
                eList.add(SummonEnemy.summonEnemy(e));
            }
        }
        EPT.start();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        for (GameObject enemy : eList){
            if (enemy instanceof BigLasers){
                if (enemy.move){
                    enemy.rotate(e.getX(), e.getY());
                }
            }
            if (enemy instanceof AmbushEnemy){
                if (!cooldown.isRunning()){
                    cooldown.start();
                }
            }
        }
        Main.screen.repaint();
    }

    Timer bossCheck = new Timer(2000, (ActionEvent e) -> {
        Timer t = (Timer) e.getSource();
        eList.remove(boss);
        System.out.println("removed");
        t.stop();
    });

    @Override
    public void actionPerformed(ActionEvent e) {
        Player player = Main.screen.getPlayer();
        Projectile projectile = Main.screen.projectile;
        projectile.x = (int) (player.x+3);
        projectile.y = (int) (player.y-3);
        Main.screen.shoot = true;
    }
}

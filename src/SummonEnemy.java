
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
// import static java.awt.event.KeyEvent;

public class SummonEnemy {
    static double counter = 300;
    static GameObject summonEnemy(MouseEvent e){
        String enemy = Main.controls.enemy;
        if (enemy.equals("Ambush")){
            Main.screen.enemy = new AmbushEnemy(e.getX(), -10, 8, 8);
        }
        if (enemy.equals("Shooter")){
            if (Main.controls.size < 20){
                Main.screen.enemy = new Enemy(e.getX(), -10, 8, 8);
                Main.controls.size += 1;
            }
        }
        if (enemy.equals("Laser left")){
            Main.controls.l1.shot();
        }
        if (enemy.equals("Laser right")){
            Main.controls.l2.shot();
        }
        if (enemy.equals("Laser Middle")){
            Main.controls.l3.shot();
        }
        if (enemy.equals("wind")){
            Timer cooldown = new Timer(5, (ActionEvent f) -> {
                counter += 0.6;
                Timer t = (Timer) f.getSource();
                if (counter >= 300){
                    t.stop();
                }
            });
            Timer wind = new Timer(5, (ActionEvent f) -> {
                if (e.getX() < Main.screen.player.x){
                    Main.screen.player.x += Math.random() * 3;
                }
                if (e.getX() > Main.screen.player.x){
                    Main.screen.player.x -= Math.random() * 3;
                }
                if (e.getX() == Main.screen.player.x){
                    Main.screen.player.y -= 100;
                }
                counter -= 0.2;
                System.out.println(counter);
                if (counter <= 0){
                    System.out.println("stopping wind");
                    Timer t = (Timer) f.getSource();
                    cooldown.start();
                    t.stop();
                }
            });
            Main.screen.enemy = new AmbushEnemy(0,0,0,0);
            if (counter >= 300){
                wind.start();
            }else{
                System.out.println("not ready");
            }
        }
        if (enemy.equals("machine gun")){
            Main.screen.enemy = new AmbushEnemy(0,0,0,0);
        }
        if (enemy.equals("nuke")){
            Nuke nuke = new Nuke(e.getX(), e.getY(), 0, 0);
            Main.screen.enemy = nuke;
            // System.out.println(e.getY());
            Timer ti = new Timer(2500, (ActionEvent e1) -> {
                Main.controls.eList.remove(nuke);
            });
            ti.start();
        }
        return Main.screen.enemy;
    }
}
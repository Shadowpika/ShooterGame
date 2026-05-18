
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Nuke extends Enemy{
    public Nuke(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    static int hold = 1;
    static int counter = 0;
    int spread = 45;
    @Override
    public void draw(Graphics g){
        g.drawRect(x, y, spread, spread);
    }
    @Override
    public void tick(){
        if (spread > 0){
            spread -= 0.5;
        }else{
            explosion();
            // spread = 1000000000;
            spread = 10;
            x = -2000000000;
        }
    }
    Explosion explosion = new Explosion(this.x, this.y, 0, 0, 150);
    Timer cooldown = new Timer(5, (ActionEvent f) -> {
        counter += 1;
        System.out.println("counter " + counter);
        Timer t = (Timer) f.getSource();
        if (counter >= 300){
            hold = 1;
            t.stop();
        }
    });
    Timer tim = new Timer(100, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            Timer t = (Timer) e.getSource();
            if (hold == 1){
                Main.controls.eList.add(explosion);
                counter = hold = 0;
                t.stop();
            }else{
                System.out.println("dud");
                t.stop();
                cooldown.start();

            }
        }
    });
    public void explosion(){
        Main.screen.enemy = explosion;
        tim.start();
    }
}
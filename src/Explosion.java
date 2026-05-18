
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.Timer;

public class Explosion extends Enemy{
    public Explosion(int x, int y, int width, int height, int exsize) {
        super(x, y, width, height);
        this.exsize = exsize;
    }
    int spread = 0;
    int exsize;
    Ellipse2D hbox = new Ellipse2D.Double();
    @Override
    public void draw(Graphics g){
        hbox = new Ellipse2D.Double(x, y, spread, spread);
        g.drawOval(x, y, spread, spread);
        g.setColor(Color.RED);
        g.fillOval(x, y, spread, spread);
        if (spread < exsize){
            spread += 1;
            if (spread < exsize / 2){
                x -= 1;
                y -=1;
            }
        }
    }
    Explosion expy = this;
    Timer time = new Timer(2000, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            Timer t = (Timer) e.getSource();
            Main.controls.eList.remove(expy);
            System.out.println(Main.controls.eList);
            spread = 0;
            t.stop();
        }
    });
    @Override
    public void tick(){
        time.start();
    }
    @Override
    public void hitCheck(GameObject other){
        if (this.hbox.intersects(other.hbox)){
            System.out.println("hit");
            other.health -= 100;
            this.x = 0;
            this.y = 0;
            System.out.println(other.health);
        }
    }
}
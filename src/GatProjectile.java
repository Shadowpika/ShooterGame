import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GatProjectile extends EnemyProjectile {
    public GatProjectile(int x, int y, int width, int height) {
        super(x, y, width, height);
        velocity = 10;
        dmg = 0.1;
    }
    Gatling gat = Main.controls.gat;
    @Override
    public void tick(){
        // System.out.println(this.y);
        this.x += Math.cos(gat.angle) * velocity;
        this.y += Math.sin(gat.angle) * velocity;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE); 
        g.fillRect(x, y, width, height); 
        hbox = new Rectangle(x,y,width,height);
    }
}
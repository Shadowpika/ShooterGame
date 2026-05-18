
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Gatling extends BigLasers {
    public Gatling(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(x, y, 2, 30); //stand
        g.fillRect(x, y, 2, 30);
        java.awt.geom.AffineTransform old = g2.getTransform();
        g2.rotate(angle, x+10, y+30);
        g2.setColor(Color.RED);
        g2.drawRect(x-15, y+20, 50, 20); //main body
        g2.fillRect(x-15, y+20, 50, 20);
        g2.setColor(Color.GRAY);
        g2.drawRect(x+35, y+27, 8, 4); //nozzle
        g2.drawRect(x+43, y+25, 8, 8);
        g2.fillRect(x+43, y+25, 8, 8);
        g.setColor(new Color(51, 51, 51));
        g2.setTransform(old);
    }
    int fireRate = 0;
    @Override
    public void tick(){
        xPos = x+10 + Math.cos(angle) * 45;
        yPos = y+30 + Math.sin(angle) * 45;
        if (this.y > 100 && this.y < 200){
            this.y += 1;
        }
        if (drop && this.y >= 200){
            this.y += 10;
        }
        GatProjectile gat = new GatProjectile((int) xPos,(int) yPos,3,3);
        if (move){
            if (fireRate < 6){
                fireRate += 1;
            }else{
                Main.screen.eProj.add(gat);
                Main.screen.eShot = true;
                fireRate = 0;
            }
        }
    }
}
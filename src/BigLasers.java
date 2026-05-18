
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.*;

public class BigLasers extends Enemy {
    public BigLasers(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
    }
    double angle = 0;
    int opacity = 0;
    boolean drop = false;
    
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
        g2.drawRect(x+35, y+25, 5, 10); //connector thingy
        g2.drawRect(x+40, y+29, 10, 2);
        g2.fillRect(x+40, y+29, 10, 2);
        g2.setColor(Color.PINK);
        g2.drawOval(x+50, y+28, 5, 5);
        g2.fillOval(x+50, y+28, 5, 5);
        Color charge = new Color(255, 0, 0, opacity);
        g2.setColor(charge);
        g2.fillRect(x+55, y+30, 1000, 3);
        g.setColor(new Color(51, 51, 51));
        if(opacity == 255){
            load.stop();
            hold.start();
        }
        check.start();
        g2.setTransform(old);
    }

    @Override
    public void tick(){
        if (this.y < 0 || this.y > 100 && this.y < 200){
            this.y += 1;
        }
        if (drop && this.y >= 200){
            this.y += 10;
        }
    }

    @Override
    public void rotate(int mx, int my){
        double pivotX = x+10;
        double pivotY = y+30;

        double dx = mx - pivotX;
        double dy = my - pivotY;

        angle =  Math.atan2(dy, dx);
    }

    Timer load = new Timer(600, new ActionListener(){
         @Override
        public void actionPerformed(ActionEvent e) {
            if (opacity < 255){
                opacity += 50;
            }
            if (opacity > 255){
                opacity = 255;
            }
            Main.screen.repaint();
        }
    });
    int time = 500;

    Timer hold = new Timer(100, new ActionListener(){
         @Override
        public void actionPerformed(ActionEvent e) {
            if (time > 0){
                time -= 100;
            }
            if (time <= 0){
                opacity = 0;
                move = true;
                Timer t =  (Timer) e.getSource();
                t.stop();
            }
        }
    });
    Timer check = new Timer(2000, (ActionEvent e) -> {
        System.out.println(Main.controls.eList);
    });
    public void shot(){
        move = false;
        load.start();
    }

    @Override
    public void hitCheck(GameObject other){
        EnemyProjectile eProj = Main.screen.eProj.get(0);
        ArrayList<java.lang.Double> playerX = eProj.playerX;
        ArrayList<java.lang.Double> playerY = eProj.playerY;

        if (opacity == 255){
            double startX = x+10 + Math.cos(angle) * 45;
            double startY = y+30 + Math.sin(angle) * 45;

            double endX = x+10 + Math.cos(angle) * 1045;
            double endY = y+30 + Math.sin(angle) * 1045;

            Line2D beam = new Line2D.Double(startX, startY, endX, endY);
            double centerX = playerX.get(0) + other.width / 2;
            double centerY = playerY.get(0) + other.height / 2;

            if(beam.ptSegDist(centerX, centerY) <= 6){
                other.health -= 25;
            }
        }

        if (drop){
            if (this.x >= playerX.get(0)) {
                if (this.x <= playerX.get(0) + other.width) {
                    if (this.y >= playerY.get(0)) {
                        if (this.y <= playerY.get(0) + other.height) {
                            other.health -= 10;
                            this.x = 0;
                            this.y = 0;
                            System.out.println(other.health);
                        }
                    }
                }
            }
            //bottom right
            if (this.x + this.width >= playerX.get(0)) {
                if (this.x + this.width <= playerX.get(0) + other.width) {
                    if (this.y + this.height >= playerY.get(0)) {
                        if (this.y + this.height <= playerY.get(0) + other.height) {
                            other.health -= 10;
                            this.x = 0;
                            this.y = 0;
                            System.out.println(other.health);
                        }
                    }
                }
            }
            //top right of block
            if (this.x + this.width >= playerX.get(0)) {
                if (this.x + this.width <= playerX.get(0) + other.width) {
                    if (this.y >= playerY.get(0)) {
                        if (this.y <= playerY.get(0) + other.height) {
                            other.health -= 10;
                            this.x = 0;
                            this.y = 0;
                            System.out.println(other.health);
                        }
                    }
                }
            }
            //bottom left
            if (this.x >= playerX.get(0)) {
                if (this.x <= playerX.get(0) + other.width) {
                    if (this.y + this.height >= playerY.get(0)) {
                        if (this.y + this.height <= playerY.get(0) + other.height) {
                            other.health -= 10;
                            this.x = 0;
                            this.y = 0;
                            System.out.println(other.health);
                        }
                    }
                }
            }

            for (double i = this.x; i <= this.x + this.width; i++){
                for (double j = this.y; j <= this.y + this.height; j++){
                    if (playerX.get(0) >= this.x && playerX.get(0) <= this.x+this.width) {
                        if (playerY.get(0) >= this.y && playerY.get(0) <= this.y+this.height){
                            other.health -= 10;
                            this.x = 0;
                            this.y = 0;
                            System.out.println(other.health);
                        }
                    }
                }
            }
        }
    }
    public void drop(){
        move = false;
        drop = true;
    }
    public void undrop(){
        move = true;
        drop = false;
    }
}
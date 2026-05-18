// import javax.swing.*;
import java.awt.*;

public class GameObject extends Rectangle {
    protected double health;
    double xPos = 0;
    double yPos = 0;
    Rectangle hbox = new Rectangle();

    public GameObject(int x, int y, int width, int height, int health){
        setBounds(x, y, width, height);
        this.health = health;
    }
    boolean dead = false;
    public void draw(Graphics g){
        g.drawRect(x, y, width, height);
    }
    public boolean deathCheck(){
        if (this.health <= 0){
            this.width = 0;
            this.height = 0;
            this.dead = true;
        }
        if (this instanceof Player){
            Main.screen.player.velocityX = 0;
            Main.screen.player.velocityY = 0;
        }
        return dead;
    }

    public void tick() {}

    public void hitCheck(GameObject other){}

    public void rotate(int x, int y){};

    boolean move = true;
}
import java.awt.*;

public class Projectile extends GameObject {
    public Projectile(int x, int y, int width, int height){
        super(x, y, width, height, 1);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g){
        g.drawRect(x, y, width, height);
        hbox = new Rectangle(x,y,width,height);
    }
    @Override
    public void tick(){
        this.y -= 5;
    }


    @Override
    public void hitCheck(GameObject other){
        if (other instanceof BigLasers){return;}
        if (other.x <= this.x && other.x + other.width > this.x){
            if (this.y <= other.y+other.height){
                if (this.y >= other.y){
                    other.health -= 10;
                    this.x = 0;
                    this.y = 0;
                    System.out.println(other.health);
                }
            }
        }
    }

}

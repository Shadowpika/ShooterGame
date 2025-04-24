import java.awt.*;

public class Projectile extends GameObject {
    public Projectile(int x, int y, int width, int height){
        super(x, y, width, height, 1);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    int x;
    int y;
    int width;
    int height;

    @Override
    public void draw(Graphics g){
        g.drawRect(x, y, width, height);
    }
    public void tick(){
        this.y -= 5;
    }


    public void hitCheck(GameObject other){
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

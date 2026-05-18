import java.awt.*;

public class Enemy extends GameObject{
    public Enemy(int x, int y, int width, int height){
        super(x, y, width, height, 10);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    int velocity = 5;

    @Override
    public void draw(Graphics g){
        g.drawRect(x, y, width, height);
    }
    double leeway = Math.random() * 100;

    @Override
    public void tick(){
        y += velocity;
        if (y > 200+leeway){
            y = (int) (200+leeway);
            velocity = 0;
        }
    }
}

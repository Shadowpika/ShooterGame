import java.awt.*;

public class Player extends GameObject {
    public Player(int x, int y, int width, int height, int dx, int dy){
        super(x, y, width, height, 10);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dx = dx;
        this.dy = dy;
    }
    double x;
    double y;
    double width;
    double height;
    int dx;
    int dy;
    int velocityX = 0;
    int velocityY = 0;

    @Override
    public void draw(Graphics g){
        g.drawRect((int) x, (int) y, (int) width, (int) height);
    }

    public void tick(){
        this.x += velocityX;
        this.y += velocityY;
    }



}

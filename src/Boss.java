import java.awt.*;

public class Boss extends GameObject{
    public Boss(int x, int y, int width, int height){
        super(x, y, width, height, 100);
        this.health = 100;
    }

    @Override
    public void draw(Graphics g){
        g.drawRect(x, y, width, height);
        g.fillRect(x, y, width, height);
    }

}
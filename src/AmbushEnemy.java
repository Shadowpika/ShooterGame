
import java.awt.Rectangle;

public class AmbushEnemy extends Enemy {
    public AmbushEnemy(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    public void tick(){
        Player player = Main.screen.player;
        this.y += 5;
        if (this.x > player.x){
            this.x -= (int) ((this.x)/player.x);
        }
        if (this.x < player.x){
            this.x += Math.ceil((this.x)/player.x);
        }
        hbox = new Rectangle(x, y, width, height);
    }

    @Override
    public void hitCheck(GameObject other){
        if (this.hbox.intersects(Main.screen.player.hbox)){
            other.health -= 10;
            this.x = 0;
            this.y = 0;
            System.out.println(other.health);
        }
    }
}

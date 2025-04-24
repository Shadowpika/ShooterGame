import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyProjectile extends Projectile {
    public EnemyProjectile(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public void tick(){
        Player player = Main.screen.player;
        this.y += 5;
        if (this.x > player.x){
            this.x += (int) ((player.x - this.x)/player.x);
        }
        if (this.x < player.x){
            this.x -= (int) ((player.x - this.x)/player.x);
        }
    }
}

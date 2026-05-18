
import java.util.ArrayList;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;


public class EnemyProjectile extends Projectile {
    public EnemyProjectile(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    int velocity = 5;
    double dmg = 10;
    ArrayList<java.lang.Double> playerX = new ArrayList<>();
    ArrayList<java.lang.Double> playerY = new ArrayList<>();
    @Override
    public void tick(){
        Player player = Main.screen.player;
        this.y += velocity;
        if (this.x > player.x){
            this.x -= (int) ((this.x)/player.x) + Math.random() * 2;
        }
        if (this.x < player.x){
            this.x += Math.ceil((this.x)/player.x) + Math.random() * 2;
        }
        if (playerX.size() < 2){
            playerX.add(player.x);
            playerY.add(player.y);
        }else{
            playerX.set(0, playerX.get(1));
            playerX.set(1, player.x);

            playerY.set(0, playerY.get(1));
            playerY.set(1, player.y);
        }
        // System.out.println(x + " " + y);
    }

    @Override
    public void hitCheck(GameObject other){
        if (this.hbox.intersects(Main.screen.player.hbox)){
            other.health -= dmg;
            this.x = 0;
            this.y = 0;
            System.out.println(other.health);
        }
    }
}

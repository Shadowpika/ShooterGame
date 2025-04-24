public class AmbushEnemy extends Enemy {
    public AmbushEnemy(int x, int y, int width, int height) {
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

    public void hitCheck(GameObject other){
        if (this.x >= other.x) {
            if (this.x <= other.x + other.width) {
                if (this.y >= other.y) {
                    if (this.y <= other.y + other.height) {
                        other.health -= 10;
                        this.x = 0;
                        this.y = 0;
                        System.out.println(other.health);
                    }
                }
            }
        }
        //bottom right
        if (this.x + this.width >= other.x) {
            if (this.x + this.width <= other.x + other.width) {
                if (this.y + this.height >= other.y) {
                    if (this.y + this.height <= other.y + other.height) {
                        other.health -= 10;
                        this.x = 0;
                        this.y = 0;
                        System.out.println(other.health);
                    }
                }
            }
        }
        //top right of block
        if (this.x + this.width >= other.x) {
            if (this.x + this.width <= other.x + other.width) {
                if (this.y >= other.y) {
                    if (this.y <= other.y + other.height) {
                        other.health -= 10;
                        this.x = 0;
                        this.y = 0;
                        System.out.println(other.health);
                    }
                }
            }
        }
        //bottom left
        if (this.x >= other.x) {
            if (this.x <= other.x + other.width) {
                if (this.y + this.height >= other.y) {
                    if (this.y + this.height <= other.y + other.height) {
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
                if (other.x >= this.x && other.x <= this.x+this.width) {
                    if (other.y >= this.y && other.y <= this.y+this.height){
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

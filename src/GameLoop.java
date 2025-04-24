//public class GameLoop implements Runnable {
//    boolean running = true;
//    GameFrame game = Main.getGameFrame();
//    Screen screen = game.getScreen();
//    Player player = screen.getPlayer();
//    public void stop(){
//        running = false;
//    }
//
//    @Override
//    public void run() {
//        int fps = 60;
//        long frametime = 1000 / 60;
//        while (running){
//            System.out.println(player.x);
//            movePlayer(1,1);
//
//
//        }
//    }
//    public void update(){
//         player.x += player.velocityX;
//         player.y += player.velocityY;
//    }
//
//    public void movePlayer(int dx, int dy){
//        int x = (int) (Math.random() * 245);
//        int y = (int) (Math.random() * 300);
//        int neg = (int) (Math.round(Math.random()));
//        int newX;
//        int newY = (int) (player.getY() - y);
//        if (neg == 1){
//            newX = (int) (player.getX() + x);
//        } else {
//            newX = (int) (player.getX() - x);
//        }
//        while (player.x != newX && player.y != newY){
//            if (player.x < newX){
//                player.velocityX = dx;
//                update();
//            } else if (player.x > newX) {
//                player.velocityX = -dx;
//                update();
//            }
//            if (player.y < newY){
//                player.velocityY = dy;
//                update();
//            } else if (player.y > newY){
//                player.velocityY = -dy;
//                update();
//            }
//
//            if (player.x > 495){
//                player.x = 495;
//            }
//            if (player.y > 595){
//                player.y = 595;
//            }
//            if (player.x < 0){
//                player.x = 0;
//            }
//            if (player.y < 0){
//                player.y = 0;
//            }
//        }
//        newX = 0;
//        newY = 0;
//    }
//}

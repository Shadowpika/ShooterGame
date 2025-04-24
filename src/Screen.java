import org.w3c.dom.css.CSS2Properties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.asList;

public class Screen extends JPanel implements ActionListener {
    Player player;
    Boss boss;
    Enemy enemy;
    Projectile projectile;
    EnemyProjectile eProj;
    
    boolean shoot = false;
    boolean eShot = false;
    
    Timer timer = new Timer(3000, Main.controls);
    public Screen(){
        player = new Player(250, 500, 10, 10, 1, 1);
        boss = (Boss) Main.controls.eList.getFirst();
        projectile = new Projectile(0, 0, 3, 3);
        eProj = new EnemyProjectile(0, 0, 3, 3);
        path();
        timer.start();
    }

    public void paintComponent(Graphics g){
        int x = (int) player.x;
        int y = (int) player.y;
        super.paintComponent(g);
        for (GameObject enemy : Main.controls.eList){
            enemy.draw(g);
        }
        player.draw(g);
        this.setBackground(Color.orange);
        if (shoot){
            projectile.draw(g);
        }
        if (eShot){
            eProj.draw(g);
        }
        if (player.deathCheck()){
            this.setBackground(Color.white);
            player.x = 250;
            player.y = 500;
            projectile.y = -5;
            eProj.y = -5;
        }
        if (boss.deathCheck()){
            this.setBackground(Color.green);
            boss.x = -500;
            boss.y = -500;
            projectile.y = -5;
            eProj.y = -5;
            for (GameObject enemy : Main.controls.eList){
                if (!(enemy instanceof Boss)){
                    enemy.x = -50;
                }
            }
        }
        for (GameObject enemy : Main.controls.eList){
            if (enemy.deathCheck()){
                if (!(enemy instanceof Boss)){
                    enemy.x = -10;
                    enemy.y = -10;
                }
            }
        }
        for (GameObject enemy : Main.controls.eList){
            if (enemy.x == -10){
                Main.controls.eList.remove(enemy);
                break;
            }
        }
    }

    public Player getPlayer(){
        return player;
    }
    public Boss getBoss(){
        return boss;
    }
    public Enemy getEnemy(){
        return enemy;
    }
    int newX;
    int newY;
    public void path(){
        int x = (int) (Math.random() * 245);
        int y = (int) (Math.random() * 200);
        newY = (int) (player.getY() - y);
        int neg = (int) (Math.round(Math.random()));
        if (neg == 1){
            newX = (int) (player.getX() + x);
        } else {
            newX = (int) (player.getX() - x);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (player.x < newX){
            player.velocityX = player.dx;
        } else if (player.x > newX) {
            player.velocityX = -player.dx;
        }
        if (player.y < newY){
            player.velocityY = player.dy;
        } else if (player.y > newY){
            player.velocityY = -player.dy;
        }

        if (player.x > 495){
            player.x = 495;
        }
        if (player.y > 595){
            player.y = 595;
        }
        if (player.x < 0){
            player.x = 0;
        }
        if (player.y < 0){
            player.y = 0;
        }
        player.tick();
        if (Math.abs(player.x - newX) <= 1 && Math.abs(player.y - newY) <= 1){
            path();
        }
        for (GameObject enemy : Main.controls.eList){
            enemy.tick();
            enemy.hitCheck(player);
        }
        projectile.tick();
        eProj.tick();
        eProj.hitCheck(player);
        for (GameObject enemy : Main.controls.eList) {
            projectile.hitCheck(enemy);
        }
        repaint();
    }
}

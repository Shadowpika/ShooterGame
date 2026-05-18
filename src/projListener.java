import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class projListener implements ActionListener {
    int i = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        EnemyProjectile eProj = Main.screen.eProj.get(i);
        System.out.println("shooting");
        GameControls controls = Main.controls;
        for (GameObject enemy : controls.eList){
            if (enemy.getClass().equals(Enemy.class)){
                eProj.x = enemy.x+3;
                eProj.y = enemy.y+3;
            }
        }
        Main.screen.eShot = true;
    }
}

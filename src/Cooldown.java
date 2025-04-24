import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cooldown implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        for (GameObject enemy : Main.controls.eList){
            if (enemy.y > 700){
                System.out.println("reloaded");
                Main.controls.eList.remove(enemy);
                break;
            }
        }
    }
}

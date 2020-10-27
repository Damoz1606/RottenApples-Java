package GameManager;

import javax.swing.JFrame;

public class Game {

    public Game() {
        JFrame window = new JFrame("Rotten Apples");
        //
        window.setContentPane(new GamePanel());
        // 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }

}

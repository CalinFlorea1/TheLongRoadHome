package TheLongRoadHome.Window;

import TheLongRoadHome.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static Window instance = null;

    private Window (){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = env.getDefaultScreenDevice();
        setTitle ("The long Road Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1920, 1080));
        defaultScreen.setFullScreenWindow(this);
        pack();
        setVisible(true);
    }

    public static Window getInstance(){
        if (instance == null){
            instance = new Window();
        }
        return instance;
    }

    public static void reset (){
        instance = null;
    }
}

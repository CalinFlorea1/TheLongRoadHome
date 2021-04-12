package TheLongRoadHome.states;

import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;

import java.awt.*;

public class PlayState extends GameState{

    private Font font;

    public PlayState (GameStateManager gameStateManager){
        super (gameStateManager);
    }

    public void update (){

    }

    public void input (MouseHandler mouse, KeyHandler key) {
        if (key.up.down){
            System.out.println("W press");
        }
    }

    public void render (Graphics2D graphics2D){
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(100, 100, 200, 200);
    }
}

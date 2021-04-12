package TheLongRoadHome.states;

import TheLongRoadHome.GamePanel;
import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;
import TheLongRoadHome.Handler.Vector2f;

import java.awt.*;
import java.util.Vector;

public class GameStateManager {

    private Vector <GameState> ListofStates;

    private static Vector2f map;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;

    public GameStateManager (){
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);

        ListofStates = new Vector<>();
        ListofStates.add(new PlayState (this));
    }

    public void pop (int state) {
        ListofStates.remove(state);
    }

    public void add (int state){
        switch (state) {
            case PLAY:
                ListofStates.add(new PlayState(this));
                break;
            case MENU:
                ListofStates.add(new MenuState(this));
                break;
            case PAUSE:
                ListofStates.add(new PauseState(this));
                break;
            case GAMEOVER:
                ListofStates.add(new GameOverState(this));
                break;
        }
    }

    public void addAndpop (int state){
        ListofStates.remove(0);
        add (state);
    }

    public void update (){
        Vector2f.setWorldVar(map.x, map.y);
        for (GameState elem : ListofStates){
            elem.update();
        }
    }

    public void input (MouseHandler mouse, KeyHandler key){
        for (GameState elem : ListofStates){
            elem.input(mouse, key);
        }
    }

    public void render (Graphics2D graphics2D){
        for (GameState elem : ListofStates){
            elem.render(graphics2D);
        }
    }
}

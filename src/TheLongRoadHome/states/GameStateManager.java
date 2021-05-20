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
    public static final int CREDITS = 4;
    public static final int WIN = 5;

    public GameStateManager () throws Exception {
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);

        ListofStates = new Vector<>();
        ListofStates.add(new MenuState (this));
    }

    public void pop (int state) {
        ListofStates.remove(state);
    }

    public void add (int state, int _level) throws Exception {
        switch (state) {
            case PLAY:
                ListofStates.add(new PlayState(this, _level));
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
            case CREDITS:
                ListofStates.add(new CreditsState(this));
                break;
            case WIN:
                ListofStates.add(new WinState(this));
                break;
        }
    }

    public void update () throws Exception {
        Vector2f.setWorldVar(map.x, map.y);
        ListofStates.lastElement().update();
    }

    public void input (MouseHandler mouse, KeyHandler key) throws Exception {
        ListofStates.lastElement().input(mouse, key);
    }

    public void render (Graphics2D graphics2D){
        ListofStates.lastElement().render(graphics2D);
    }

    public PlayState getCurrentPlayState (){
        return (PlayState) ListofStates.get(0);
    }
}

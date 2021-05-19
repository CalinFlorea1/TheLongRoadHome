package TheLongRoadHome.states;

import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;

import java.awt.*;

public abstract class GameState{
    protected GameStateManager gameStateManager;
    public GameState (GameStateManager _gameStateManager) {
        gameStateManager = _gameStateManager;
    }

    public abstract void update();
    public abstract void render (Graphics2D g);
    public abstract void input (MouseHandler mouse, KeyHandler key) throws Exception;
}

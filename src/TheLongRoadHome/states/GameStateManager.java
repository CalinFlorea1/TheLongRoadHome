package TheLongRoadHome.states;

import TheLongRoadHome.GamePanel;
import TheLongRoadHome.Handler.*;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class GameStateManager {
    private Vector <GameState> ListofStates;

    private static int points;
    private static int level;

    private static Vector2f map;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;
    public static final int CREDITS = 4;
    public static final int WIN = 5;
    public static final int LOADGAME = 6;
    public static final int SETTINGS = 7;

    private static int Difficulty = 1;
    private static Database database;
    private static int IDCurrent;
    private static Vector <Audio> music;
    private static boolean VOLUME;

    public GameStateManager () throws Exception {
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);
        VOLUME = true;

        database = new Database();
        database.createTable();
        database.LoadDataBase();

        music = new Vector<>();
        music.add(new Audio("/SFX/CarryOn.mp3"));
        music.add(new Audio("/SFX/HailToTheKing.mp3"));

        music.get(Math.abs(new Random().nextInt()) % 2).play();

        ListofStates = new Vector<>();
        ListofStates.add(new MenuState (this));
    }

    public void pop (int state) {
        GameState temp = ListofStates.get(state);
        ListofStates.remove(state);
        temp = null;
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
            case LOADGAME:
                ListofStates.add(new LoadGameState(this));
                break;
            case SETTINGS:
                ListofStates.add(new SettingsState(this));
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

    public static void increaseScore (int score){
        points += score;
    }

    public static int getPoints (){
        return points;
    }

    public static void setLevel (int _level){
        level = _level;
    }

    public static int getLevel (){
        return level;
    }

    public static int getDifficulty (){
        return Difficulty;
    }

    public static void setDifficulty (int _difficulty){
         Difficulty = _difficulty;
    }

    public static Database getDatabase (){
        return database;
    }

    public static void setIDCurrent (int _IDCurrent) {IDCurrent = _IDCurrent;}

    public static int getIDCurrent () {return IDCurrent;}

    public static void setPoints (int _points){ points = _points; }

    public static Vector<Audio> getMusic (){ return music; }

    public static void volumeOff () { VOLUME = false; }

    public static void volumeOn () { VOLUME = true; }

    public static boolean getVOLUME () { return VOLUME; }
}

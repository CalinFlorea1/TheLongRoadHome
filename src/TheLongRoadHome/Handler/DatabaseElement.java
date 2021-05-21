package TheLongRoadHome.Handler;

import java.util.Vector;

public class DatabaseElement {
    private int ID;
    private int LEVEL;
    private int SCORE;
    private int NUMBER_ENEMY;
    private int DIFFICULTY;
    Vector2f PLAYER;
    Vector <Vector2f> ENEMY;

    public DatabaseElement (int _ID, int _LEVEL, int _SCORE, int _NUMBER_ENEMY, int _DIFFICULTY, Vector2f _PLAYER){
        ID = _ID;
        LEVEL = _LEVEL;
        SCORE = _SCORE;
        NUMBER_ENEMY = _NUMBER_ENEMY;
        PLAYER = _PLAYER;
        DIFFICULTY = _DIFFICULTY;
        ENEMY = new Vector<>();
    }

    public void addEnemy (Vector2f position){
        ENEMY.add(position);
    }

    public int getID (){return ID;}
    public int getLEVEL (){return LEVEL;}
    public int getSCORE (){return SCORE;}
    public int getNUMBER_ENEMY (){return NUMBER_ENEMY;}
    public int getDIFFICULTY (){return DIFFICULTY;}
}

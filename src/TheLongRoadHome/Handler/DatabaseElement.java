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
    private int PLAYER_LIFE;
    Vector <Integer> ENEMY_LIFE;

    public DatabaseElement (int _ID, int _LEVEL, int _SCORE, int _NUMBER_ENEMY, int _DIFFICULTY, Vector2f _PLAYER, int _PLAYER_LIFE){
        ID = _ID;
        LEVEL = _LEVEL;
        SCORE = _SCORE;
        NUMBER_ENEMY = _NUMBER_ENEMY;
        PLAYER = _PLAYER;
        DIFFICULTY = _DIFFICULTY;
        PLAYER_LIFE = _PLAYER_LIFE;
        ENEMY = new Vector<>();
        ENEMY_LIFE = new Vector<>();
    }

    public void addEnemy (Vector2f position, int life){
        ENEMY.add(position);
        ENEMY_LIFE.add(life);
    }

    public int getID (){return ID;}
    public int getLEVEL (){return LEVEL;}
    public int getSCORE (){return SCORE;}
    public int getNUMBER_ENEMY (){return NUMBER_ENEMY;}
    public int getDIFFICULTY (){return DIFFICULTY;}
    public Vector2f getPLAYER () {return PLAYER;}
    public Vector <Vector2f>getENEMY() {return ENEMY;}
    public Vector <Integer> getENEMY_LIFE (){return ENEMY_LIFE;}
    public int getPLAYER_LIFE () {return PLAYER_LIFE;}
}

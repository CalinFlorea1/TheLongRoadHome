package TheLongRoadHome.states;

import TheLongRoadHome.Handler.*;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

import TheLongRoadHome.entity.Bullet;
import TheLongRoadHome.entity.Enemy;
import TheLongRoadHome.entity.Explosion;
import TheLongRoadHome.entity.Player;
import TheLongRoadHome.graphics.Font;
import TheLongRoadHome.graphics.*;
import TheLongRoadHome.tiles.TileManager;

public class PlayState extends GameState{
    private Font font;
    public static Player player;
    private TileManager tileManager;
    private Vector <Bullet> bullets;
    public static Vector<Enemy> enemy;
    public static Vector <Explosion> explosions;
    private int level;
    private static final int levelMax = 2;

    public PlayState (GameStateManager gameStateManager, int _level) throws Exception {
        super (gameStateManager);
        level = _level;
        enemy = new Vector<>();
        explosions = new Vector<>();
        bullets = new Vector<>();
        switch (_level){
            case 1:
                initLevel1();
                break;
            case 2:
                initLevel2();
                break;
        }
    }

    void initLevel1 () throws Exception {
        tileManager = new TileManager("Map/mapLvl1.xml");

        font = new Font ("Textures/Font.png", 16, 16);

        Database database = GameStateManager.getDatabase();
        database.LoadDataBase();

        Vector <DatabaseElement> databaseElements = database.getDatabaseElements();
        DatabaseElement databaseElement = null;

        int SCORE = 0, NUMBER_ENEMY = 0, DIFFICULTY = 0, PLAYER_LIFE = 0;
        Vector <Integer> enemyLife;
        Vector <Vector2f> enemyVector;
        Vector2f playerPos = null;

        try {
            databaseElement = databaseElements.get(GameStateManager.getIDCurrent() - 1);
        }
        catch (IndexOutOfBoundsException e){
            NUMBER_ENEMY = -1;
            DIFFICULTY = GameStateManager.getDifficulty();
        }

        if (NUMBER_ENEMY != -1){
            NUMBER_ENEMY = databaseElement.getNUMBER_ENEMY();
            DIFFICULTY = databaseElement.getDIFFICULTY();
        }

        if (NUMBER_ENEMY <= 0) {
            if (DIFFICULTY == 1) {
                player = new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(1000, 320), 64, 100);
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(300, 220), 64, 20));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1500, 800), 64, 20));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(700, 200), 64, 20));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1227, 550), 64, 20));
            }

            if (DIFFICULTY == 2) {
                player = new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(1000, 320), 64, 100);
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(300, 220), 64, 40));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1500, 800), 64, 40));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(700, 200), 64, 40));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1227, 550), 64, 40));
            }

            if (DIFFICULTY == 3) {
                player = new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(1000, 320), 64, 100);
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(300, 220), 64, 60));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1500, 800), 64, 60));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(700, 200), 64, 60));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1227, 550), 64, 60));
            }
        }
        else{

            SCORE = databaseElement.getSCORE();
            PLAYER_LIFE = databaseElement.getPLAYER_LIFE();
            playerPos = databaseElement.getPLAYER();
            enemyLife = databaseElement.getENEMY_LIFE();
            enemyVector = databaseElement.getENEMY();

            player = new Player(new Sprite("Textures/TankHero2.png"), playerPos, 64, PLAYER_LIFE);

            for (int i = 0; i < NUMBER_ENEMY; i++) {
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"),
                        enemyVector.elementAt(i), 64, enemyLife.elementAt(i)));
            }
        }
    }

    void initLevel2 () throws Exception {
        tileManager = new TileManager("Map/mapLvl2.xml");

        font = new Font ("Textures/Font.png", 16, 16);

        Database database = GameStateManager.getDatabase();
        database.LoadDataBase();

        Vector <DatabaseElement> databaseElements = database.getDatabaseElements();
        DatabaseElement databaseElement = null;

        int SCORE = 0, NUMBER_ENEMY = 0, DIFFICULTY = 0, PLAYER_LIFE = 0;
        Vector <Integer> enemyLife;
        Vector <Vector2f> enemyVector;
        Vector2f playerPos = null;

        try {
            databaseElement = databaseElements.get(GameStateManager.getIDCurrent() - 1);
        }
        catch (IndexOutOfBoundsException e){
            NUMBER_ENEMY = -1;
            DIFFICULTY = GameStateManager.getDifficulty();
        }

        System.out.println("Ceava");
        if (NUMBER_ENEMY != -1){
            NUMBER_ENEMY = databaseElement.getNUMBER_ENEMY();
            System.out.println(NUMBER_ENEMY);
            DIFFICULTY = databaseElement.getDIFFICULTY();
        }

        if (NUMBER_ENEMY <= 0) {
            if (GameStateManager.getDifficulty() == 1) {
                player = new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(1600, 900), 64, 60);
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(300, 220), 64, 40));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(200, 500), 64, 40));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(700, 200), 64, 40));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1227, 550), 64, 40));
            }

            if (GameStateManager.getDifficulty() == 2) {
                player = new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(1600, 900), 64, 60);
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(300, 220), 64, 60));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(200, 500), 64, 60));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(700, 200), 64, 60));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1227, 550), 64, 60));
            }

            if (GameStateManager.getDifficulty() == 3) {
                player = new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(1600, 900), 64, 20);
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(300, 220), 64, 100));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(200, 500), 64, 100));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(700, 200), 64, 100));
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1227, 550), 64, 100));
            }
        }
        else{
            SCORE = databaseElement.getSCORE();
            PLAYER_LIFE = databaseElement.getPLAYER_LIFE();
            playerPos = databaseElement.getPLAYER();
            enemyLife = databaseElement.getENEMY_LIFE();
            enemyVector = databaseElement.getENEMY();

            player = new Player(new Sprite("Textures/TankHero2.png"), playerPos, 64, PLAYER_LIFE);

            for (int i = 0; i < NUMBER_ENEMY; i++) {
                enemy.add(new Enemy(new Sprite("Textures/TankEnemy2.png"),
                        enemyVector.elementAt(i), 64, enemyLife.elementAt(i)));
            }
        }
    }

    public void update () throws Exception {
        player.update();
        for (Enemy _enemy : enemy){
            _enemy.update();
            if (_enemy.getContorShoot() <= 0){
                _enemy.setContorShoot((Math.abs(new Random().nextInt()) % 100) + 30);
                bullets.add (new Bullet(new Sprite("Textures/Bullet.png"),
                        new Vector2f(_enemy.getPos().x, _enemy.getPos().y), 64, _enemy.getCurrentDirection(), _enemy));
            }
        }

        bullets.removeIf(bullet -> bullet.isShot() || bullet.isOut());

        for (Bullet bullet : bullets){
            bullet.update();
        }

        explosions.removeIf(explosion -> explosion.getAnimation().hasPlayedOnce());

        for (Explosion explosion : explosions){
            if (!explosion.getAnimation().hasPlayedOnce())
                explosion.update();
        }

        if (player.getLife() <= 0){
            gameStateManager.add(GameStateManager.GAMEOVER, -1);
        }

        if (enemy.isEmpty() && explosions.isEmpty()){
            gameStateManager.add(GameStateManager.WIN, -1);
        }
    }

    public void input (MouseHandler mouse, KeyHandler key) throws Exception {
        if (key.escape.down){
            gameStateManager.add(GameStateManager.PAUSE, -1);
        }

        if (key.shot.down){
            if (player.getDelayShot() == 0) {
                player.setDelayShot(1);
                int lastButton = player.getLastButton();
                bullets.add(new Bullet(new Sprite("Textures/Bullet.png"),
                        new Vector2f(player.getPos().x, player.getPos().y), 64, lastButton, player));
            }
        }
        player.input(mouse, key);
    }

    public void render (Graphics2D graphics2D){
        tileManager.render(graphics2D);
        player.render(graphics2D);

        for (Enemy _enemy : enemy){
            _enemy.render(graphics2D);
        }

        for (Bullet bullet : bullets){
            bullet.render(graphics2D);
        }

        for (Explosion explosion : explosions){
            if (!explosion.getAnimation().hasPlayedOnce())
                explosion.render(graphics2D);
        }
    }


    public int getLevel (){
        return level;
    }

    public static int getLevelMax (){
        return levelMax;
    }
}

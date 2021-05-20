package TheLongRoadHome.states;

import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;

import java.awt.*;
import java.util.Vector;

import TheLongRoadHome.Handler.Vector2f;
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
    private Vector <Bullet> bullets = new Vector<>();
    public static Vector<Enemy> enemy = new Vector<>();
    public static Vector <Explosion> explosions = new Vector<>();
    private int level;

    public PlayState (GameStateManager gameStateManager, int _level) throws Exception {
        super (gameStateManager);
        level = _level;
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
        player = new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(1000, 320), 64, 100);
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(300, 220), 64, 20));
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1500, 800), 64, 20));
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(700, 200), 64, 20));
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1227, 550), 64, 20));
    }

    void initLevel2 () throws Exception {
        tileManager = new TileManager("Map/mapLvl2.xml");

        font = new Font ("Textures/Font.png", 16, 16);
        player = new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(1000, 320), 64, 60);
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(300, 220), 64, 40));
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1500, 800), 64, 40));
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(700, 200), 64, 40));
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1227, 550), 64, 40));
    }

    public void update () throws Exception {
        player.update();
        for (Enemy _enemy : enemy){
            _enemy.update();
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
                bullets.add(new Bullet(new Sprite("Textures/Bullet.png"), new Vector2f(player.getPos().x, player.getPos().y), 64, lastButton, player));
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
}

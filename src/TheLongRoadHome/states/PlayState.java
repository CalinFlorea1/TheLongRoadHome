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


    public PlayState (GameStateManager gameStateManager) throws Exception {
        super (gameStateManager);
        tileManager = new TileManager("Map/mapLvl2.xml");

        font = new Font ("Textures/Font.png", 16, 16);
        player = new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(1000, 320), 64);
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(300, 220), 64));
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1500, 800), 64));
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(700, 200), 64));
        enemy.add (new Enemy(new Sprite("Textures/TankEnemy2.png"), new Vector2f(1227, 550), 64));
    }

    public void update (){
        player.update();
        for (Enemy _enemy : enemy){
            _enemy.update();
        }

        bullets.removeIf(bullet -> bullet.isShot());

        for (Bullet bullet : bullets){
            bullet.update();
        }

        explosions.removeIf(explosion -> explosion.getAnimation().hasPlayedOnce());

        for (Explosion explosion : explosions){
            if (!explosion.getAnimation().hasPlayedOnce())
                explosion.update();
        }
    }

    public void input (MouseHandler mouse, KeyHandler key) throws Exception {
        if (key.escape.down){
            gameStateManager.add(GameStateManager.PAUSE);
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
}

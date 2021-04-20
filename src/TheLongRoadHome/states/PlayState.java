package TheLongRoadHome.states;

import TheLongRoadHome.GamePanel;
import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;

import java.awt.*;

import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.entity.Player;
import TheLongRoadHome.graphics.Font;
import TheLongRoadHome.graphics.*;
import TheLongRoadHome.tiles.TileManager;

public class PlayState extends GameState{

    private Font font;
    private Player player;
    private TileManager tileManager;

    public PlayState (GameStateManager gameStateManager) throws Exception {
        super (gameStateManager);
        tileManager = new TileManager("Map/mapLvl1.xml");

        font = new Font ("Textures/Font.png", 16, 16);
        player = new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(1000, 320), 64);
    }

    public void update (){
        player.update();
    }

    public void input (MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
    }

    public void render (Graphics2D graphics2D){
        tileManager.render(graphics2D);
        //kSprite.drawArray(graphics2D, font, "FPS", new Vector2f(100, 100), 32, 32, 32, 0);
        player.render(graphics2D);
    }
}

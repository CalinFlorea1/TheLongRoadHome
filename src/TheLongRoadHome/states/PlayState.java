package TheLongRoadHome.states;

import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;

import java.awt.*;

import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.entity.Player;
import TheLongRoadHome.graphics.Font;
import TheLongRoadHome.graphics.*;

public class PlayState extends GameState{

    private Font font;
    private Player player;

    public PlayState (GameStateManager gameStateManager){
        super (gameStateManager);
        font = new Font ("Textures/Font.png", 16, 16);
        player = new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(300, 300), 64);
    }

    public void update (){
        player.update();
    }

    public void input (MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
    }

    public void render (Graphics2D graphics2D){
        Sprite.drawArray(graphics2D, font, "Font", new Vector2f(100, 100), 32, 32, 32, 0);
        player.render(graphics2D);
    }
}

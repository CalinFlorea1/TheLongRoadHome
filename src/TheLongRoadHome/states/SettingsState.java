package TheLongRoadHome.states;

import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;
import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.Sprite;

import java.awt.*;

public class SettingsState extends GameState{
    private Sprite menuPhoto;
    public SettingsState (GameStateManager gameStateManager){
        super (gameStateManager);
        menuPhoto = new Sprite ("MainMenu/CreditsMenu.png");
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D graphics2D) {
        Sprite.drawPhoto(graphics2D, menuPhoto.getSpriteSheet(), new Vector2f(0, 0), 1920, 1080);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws Exception {
        if (mouse.getButton() == 1){
            if (mouse.getX() >= 118 && mouse.getX() <= 291 && mouse.getY() >= 890 && mouse.getY() <= 960) {
                gameStateManager.add(GameStateManager.MENU, -1);
                gameStateManager.pop(GameStateManager.PLAY);
            }
        }
    }
}

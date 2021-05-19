package TheLongRoadHome.states;

import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;
import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.Sprite;

import java.awt.*;

public class PauseState extends GameState{
    private Sprite menuPhoto;
    public PauseState (GameStateManager gameStateManager){
        super (gameStateManager);
        menuPhoto = new Sprite ("MainMenu/PauseMenu.png");
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
            if (mouse.getX() >= 539 && mouse.getX() <= 1377 && mouse.getY() >= 384 && mouse.getY() <= 496) {
                gameStateManager.pop(GameStateManager.MENU);
            }
        }

        if (mouse.getButton() == 1){
            if (mouse.getX() >= 562 && mouse.getX() <= 1363 && mouse.getY() >= 596 && mouse.getY() <= 699) {
                gameStateManager.add(GameStateManager.MENU);
                gameStateManager.pop(GameStateManager.PLAY);
                gameStateManager.pop(GameStateManager.PLAY);
            }
        }
    }
}

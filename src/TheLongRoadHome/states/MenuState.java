package TheLongRoadHome.states;

import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;
import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.Sprite;
import java.awt.*;


public class MenuState extends GameState{
    private Sprite menuPhoto;
    public MenuState (GameStateManager gameStateManager){
        super (gameStateManager);
        menuPhoto = new Sprite ("MainMenu/MainMenu.png");
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
        if (key.escape.down){
            System.exit(0);
        }

        if (mouse.getButton() == 1){
            if (mouse.getX() >= 1113 && mouse.getX() <= 1463 && mouse.getY() >= 481 && mouse.getY() <= 547) {
                gameStateManager.add(GameStateManager.PLAY);
                gameStateManager.pop(GameStateManager.PLAY);
            }

            if (mouse.getX() >= 1113 && mouse.getX() <= 1463 && mouse.getY() >= 744 && mouse.getY() <= 810){
                gameStateManager.add(GameStateManager.CREDITS);
                gameStateManager.pop(GameStateManager.PLAY);
            }
        }
    }
}

package TheLongRoadHome.states;

import TheLongRoadHome.Handler.Audio;
import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;
import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.Sprite;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class SettingsState extends GameState{
    private Sprite menuPhoto;
    public SettingsState (GameStateManager gameStateManager){
        super (gameStateManager);
        menuPhoto = new Sprite ("MainMenu/SettingsMenu.png");
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
            if (mouse.getX() >= 55 && mouse.getX() <= 202 && mouse.getY() >= 42 && mouse.getY() <= 104) {
                gameStateManager.add(GameStateManager.MENU, -1);
                gameStateManager.pop(GameStateManager.PLAY);
            }
            if (mouse.getX() >= 391 && mouse.getX() <= 574 && mouse.getY() >= 430 && mouse.getY() <= 505){
                GameStateManager.setDifficulty(1);
            }
            if (mouse.getX() >= 357 && mouse.getX() <= 606 && mouse.getY() >= 518 && mouse.getY() <= 593){
                GameStateManager.setDifficulty(2);
            }
            if (mouse.getX() >= 363 && mouse.getX() <= 600 && mouse.getY() >= 605 && mouse.getY() <= 680){
                GameStateManager.setDifficulty(3);
            }
            if (mouse.getX() >= 1349 && mouse.getX() <= 1522 && mouse.getY() >= 430 && mouse.getY() <= 505){
                if (GameStateManager.getVOLUME()) {
                    GameStateManager.volumeOff();
                    Vector<Audio> audioVector = GameStateManager.getMusic();
                    for (Audio audio : audioVector) {
                        audio.stop();
                    }
                }
            }
            if (mouse.getX() >= 1355 && mouse.getX() <= 1516 && mouse.getY() >= 518 && mouse.getY() <= 593){
                if (!GameStateManager.getVOLUME()) {
                    GameStateManager.volumeOn();
                    Vector<Audio> audioVector = GameStateManager.getMusic();
                    audioVector.get(Math.abs(new Random().nextInt()) % 2).play();
                }
            }
        }
    }
}

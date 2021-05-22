package TheLongRoadHome.states;

import TheLongRoadHome.Handler.Database;
import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;
import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.entity.Enemy;
import TheLongRoadHome.entity.Player;
import TheLongRoadHome.graphics.Sprite;
import java.awt.*;
import java.util.Vector;


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
                Database database = GameStateManager.getDatabase();
                database.LoadDataBase();
                int ID = database.getNumberOfEntries();
                GameStateManager.setIDCurrent(ID + 1);
                GameStateManager.setLevel(1);

                database.addElement(GameStateManager.getIDCurrent(), GameStateManager.getLevel(), GameStateManager.getPoints(),
                        GameStateManager.getDifficulty(),
                        new Player(new Sprite("Textures/TankHero2.png"), new Vector2f(0, 0), 64, 100),
                        new Vector<Enemy>());

                gameStateManager.add(GameStateManager.PLAY, 1);
                gameStateManager.pop(GameStateManager.PLAY);
            }

            if (mouse.getX() >= 1113 && mouse.getX() <= 1463 && mouse.getY() >= 744 && mouse.getY() <= 810){
                gameStateManager.add(GameStateManager.CREDITS, -1);
                gameStateManager.pop(GameStateManager.PLAY);
            }

            if (mouse.getX() >= 1105 && mouse.getX() <= 1485 && mouse.getY() >= 573 && mouse.getY() <= 629){
                gameStateManager.add(GameStateManager.LOADGAME, -1);
                gameStateManager.pop(GameStateManager.PLAY);
            }

            if (mouse.getX() >= 1105 && mouse.getX() <= 1485 && mouse.getY() >= 660 && mouse.getY() <= 716){
                gameStateManager.add(GameStateManager.SETTINGS, -1);
                gameStateManager.pop(GameStateManager.PLAY);
            }
        }
    }
}

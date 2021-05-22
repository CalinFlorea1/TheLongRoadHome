package TheLongRoadHome.states;

import TheLongRoadHome.Handler.*;
import TheLongRoadHome.graphics.Sprite;

import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

public class LoadGameState extends GameState{
    private int currentPage = 0;
    private Sprite menuPhoto;
    Vector<DatabaseElement> databaseElements;
    int numbersEntries;
    public LoadGameState (GameStateManager gameStateManager) throws SQLException {
        super (gameStateManager);
        menuPhoto = new Sprite ("MainMenu/LoadGameMenu.png");
        Database database = GameStateManager.getDatabase();
        database.LoadDataBase();

        databaseElements = database.getDatabaseElements();
        numbersEntries = database.getNumberOfEntries();
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
            if (mouse.getX() >= 55 && mouse.getX() <= 203 && mouse.getY() >= 42 && mouse.getY() <= 104) {
                gameStateManager.add(GameStateManager.MENU, -1);
                gameStateManager.pop(GameStateManager.PLAY);
            }
        }

    }
}

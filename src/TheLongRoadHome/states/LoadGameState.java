package TheLongRoadHome.states;

import TheLongRoadHome.Handler.*;
import TheLongRoadHome.graphics.Font;
import TheLongRoadHome.graphics.Sprite;

import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

public class LoadGameState extends GameState{
    private int currentPage = 0;
    private Sprite menuPhoto;
    Vector<DatabaseElement> databaseElements;
    int numbersEntries;
    Vector <String> fonts;
    Font font;
    private int delay;

    public LoadGameState (GameStateManager gameStateManager) throws SQLException {
        super (gameStateManager);
        font = new Font("Textures/Font.png", 16, 16);
        menuPhoto = new Sprite ("MainMenu/LoadGameMenu.png");
        Database database = GameStateManager.getDatabase();
        database.LoadDataBase();

        databaseElements = database.getDatabaseElements();
        numbersEntries = database.getNumberOfEntries();
        delay = 0;
        InitFont();
    }

    void InitFont (){
        fonts = new Vector<>();

        int indexCurrent = currentPage * 5;
        int indexStop = currentPage * 5 + 5;
        System.out.println(indexCurrent + " " + indexStop + " " + numbersEntries);
        for (int i = indexCurrent; i < indexStop && i < numbersEntries; i++){
            DatabaseElement databaseElement = databaseElements.elementAt(i);

            int LEVEL = databaseElement.getLEVEL();
            int SCORE = databaseElement.getSCORE();
            int DIFFICULTY = databaseElement.getDIFFICULTY();
            String dif = "";

            if (DIFFICULTY == 1){
                dif = "EASY";
            }
            if (DIFFICULTY == 2){
                dif = "MEDIUM";
            }
            if (DIFFICULTY == 3){
                dif = "INSANE";
            }

            fonts.add ("LEVEL " + LEVEL + " SCORE " + SCORE + " DIFFICULTY " + dif);
        }
    }

    @Override
    public void update() {
        if (delay != 0){
            delay++;
        }
        if (delay == 20) delay = 0;
    }

    @Override
    public void render(Graphics2D graphics2D) {
        Sprite.drawPhoto(graphics2D, menuPhoto.getSpriteSheet(), new Vector2f(0, 0), 1920, 1080);
        int x = 0;
        for (String fontElement : fonts){
            x++;
            Sprite.drawArray(graphics2D, font, fontElement,new Vector2f(200, x * 175), 64, 64, 32, 0);
        }
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws Exception {
        if (mouse.getButton() == 1){
            if (delay == 0) {
                delay = 1;
                if (mouse.getX() >= 55 && mouse.getX() <= 203 && mouse.getY() >= 42 && mouse.getY() <= 104) {
                    gameStateManager.add(GameStateManager.MENU, -1);
                    gameStateManager.pop(GameStateManager.PLAY);
                }

                if (mouse.getX() >= 30 && mouse.getX() <= 235 && mouse.getY() >= 1006 && mouse.getY() <= 1038) {
                    if (currentPage > 0) {
                        currentPage--;
                        InitFont();
                    }
                }

                if (mouse.getX() >= 1668 && mouse.getX() <= 1880 && mouse.getY() >= 994 && mouse.getY() <= 1036) {
                    if (((currentPage + 1) * 5 + 1) <= numbersEntries) {
                        currentPage++;
                        InitFont();
                    }
                }

                if (mouse.getY() >= 175 && mouse.getY() <= 350) {
                    if ((currentPage * 5 + 1) <= numbersEntries) {
                        GameStateManager.setIDCurrent(currentPage * 5 + 1);
                        GameStateManager.setLevel(databaseElements.get(currentPage * 5).getLEVEL());
                        GameStateManager.setDifficulty(databaseElements.get(currentPage * 5 + 3).getDIFFICULTY());
                        if (GameStateManager.getLevel() <= 2 && GameStateManager.getLevel() >= 1) {
                            gameStateManager.pop(GameStateManager.PLAY);
                            gameStateManager.add(GameStateManager.PLAY, GameStateManager.getLevel());
                        }
                    }
                }

                if (mouse.getY() >= 350 && mouse.getY() <= 525) {
                    if ((currentPage * 5 + 2) <= numbersEntries) {
                        GameStateManager.setIDCurrent(currentPage * 5 + 2);
                        GameStateManager.setLevel(databaseElements.get(currentPage * 5 + 1).getLEVEL());
                        GameStateManager.setDifficulty(databaseElements.get(currentPage * 5 + 3).getDIFFICULTY());
                        if (GameStateManager.getLevel() <= 2 && GameStateManager.getLevel() >= 1) {
                            gameStateManager.pop(GameStateManager.PLAY);
                            gameStateManager.add(GameStateManager.PLAY, GameStateManager.getLevel());
                        }
                    }
                }

                if (mouse.getY() >= 525 && mouse.getY() <= 700) {
                    if ((currentPage * 5 + 3) <= numbersEntries) {
                        GameStateManager.setIDCurrent(currentPage * 5 + 3);
                        GameStateManager.setLevel(databaseElements.get(currentPage * 5 + 2).getLEVEL());
                        GameStateManager.setDifficulty(databaseElements.get(currentPage * 5 + 3).getDIFFICULTY());
                        if (GameStateManager.getLevel() <= 2 && GameStateManager.getLevel() >= 1) {
                            gameStateManager.pop(GameStateManager.PLAY);
                            gameStateManager.add(GameStateManager.PLAY, GameStateManager.getLevel());
                        }
                    }
                }

                if (mouse.getY() >= 700 && mouse.getY() <= 875) {
                    if ((currentPage * 5 + 4) <= numbersEntries) {
                        GameStateManager.setIDCurrent(currentPage * 5 + 4);
                        GameStateManager.setLevel(databaseElements.get(currentPage * 5 + 3).getLEVEL());
                        GameStateManager.setDifficulty(databaseElements.get(currentPage * 5 + 3).getDIFFICULTY());
                        if (GameStateManager.getLevel() <= 2 && GameStateManager.getLevel() >= 1) {
                            gameStateManager.pop(GameStateManager.PLAY);
                            gameStateManager.add(GameStateManager.PLAY, GameStateManager.getLevel());
                        }
                    }
                }

                if (mouse.getY() >= 875 && mouse.getY() <= 1000) {
                    if ((currentPage * 5 + 5) <= numbersEntries) {
                        GameStateManager.setIDCurrent(currentPage * 5 + 5);
                        GameStateManager.setLevel(databaseElements.get(currentPage * 5 + 4).getLEVEL());
                        GameStateManager.setDifficulty(databaseElements.get(currentPage * 5 + 3).getDIFFICULTY());
                        if (GameStateManager.getLevel() <= 2 && GameStateManager.getLevel() >= 1) {
                            gameStateManager.pop(GameStateManager.PLAY);
                            gameStateManager.add(GameStateManager.PLAY, GameStateManager.getLevel());
                        }
                    }
                }
            }
        }
    }
}

package TheLongRoadHome.states;

import TheLongRoadHome.Handler.*;
import TheLongRoadHome.entity.Player;
import TheLongRoadHome.graphics.Font;
import TheLongRoadHome.graphics.Sprite;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class WinState extends GameState{
    private Sprite menuPhoto;
    private TheLongRoadHome.graphics.Font font;
    public WinState (GameStateManager gameStateManager){
        super (gameStateManager);
        font = new Font("Textures/Font.png", 16, 16);
        menuPhoto = new Sprite ("MainMenu/YouWinMenu.png");
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D graphics2D) {
        Sprite.drawPhoto(graphics2D, menuPhoto.getSpriteSheet(), new Vector2f(0, 0), 1920, 1080);
        Sprite.drawArray(graphics2D, font, "Points " + GameStateManager.getPoints(),new Vector2f(714, 790), 64, 64, 32, 0);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws Exception {
        if (mouse.getButton() == 1){
            if (mouse.getX() >= 600 && mouse.getX() <= 1320 && mouse.getY() >= 384 && mouse.getY() <= 496) {
                int level = gameStateManager.getCurrentPlayState().getLevel();
                int score = gameStateManager.getCurrentPlayState().player.getScoreLevelCurrent();
                if (level < PlayState.getLevelMax()) {
                    GameStateManager.setLevel(level + 1);
                    GameStateManager.increaseScore(score);
                    Database database = GameStateManager.getDatabase();
                    database.LoadDataBase();
                    int LEVEL = GameStateManager.getLevel();
                    int Difficulty = GameStateManager.getDifficulty();
                    int Score = GameStateManager.getPoints() + Player.getScoreLevelCurrent();
                    int ID = GameStateManager.getIDCurrent();
                    database.updateDataBase(ID, LEVEL, Score, Difficulty
                            , PlayState.player, PlayState.enemy);
                    GameStateManager.setLevel(level + 1);
                    GameStateManager.increaseScore(score);

                    gameStateManager.pop(GameStateManager.PLAY);
                    gameStateManager.pop(GameStateManager.PLAY);
                    gameStateManager.add(GameStateManager.PLAY, level + 1);
                }
            }
        }

        if (mouse.getButton() == 1){
            int level = gameStateManager.getCurrentPlayState().getLevel();
            int score = gameStateManager.getCurrentPlayState().player.getScoreLevelCurrent();
            if (mouse.getX() >= 562 && mouse.getX() <= 1363 && mouse.getY() >= 596 && mouse.getY() <= 699) {
                GameStateManager.setLevel(level + 1);
                GameStateManager.increaseScore(score);
                Database database = GameStateManager.getDatabase();
                database.LoadDataBase();

                int LEVEL = GameStateManager.getLevel();
                int Difficulty = GameStateManager.getDifficulty();
                int Score = GameStateManager.getPoints() + Player.getScoreLevelCurrent();
                int ID = GameStateManager.getIDCurrent();

                database.updateDataBase(ID, LEVEL, Score, Difficulty
                        , PlayState.player, PlayState.enemy);

                Vector<Audio> music = GameStateManager.getMusic();
                music.get(Math.abs(new Random().nextInt()) % 2).play();

                gameStateManager.pop(GameStateManager.PLAY);
                gameStateManager.pop(GameStateManager.PLAY);
                gameStateManager.add(GameStateManager.MENU, -1);
            }
        }
    }
}

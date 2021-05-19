package TheLongRoadHome.graphics;

import TheLongRoadHome.Handler.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class Sprite {
    private BufferedImage SPRITESHEET = null;
    private BufferedImage[][] spriteArray;

    private final int TILE_SIZE = 64;

    public int widthTile;
    public int heightTile;

    private int widthSprite;
    private int heightSprite;

    public Sprite (String file){
        widthTile = TILE_SIZE;
        heightTile = TILE_SIZE;

        System.out.println("Loading: " + file + "...");

        SPRITESHEET = loadSprite (file);

        widthSprite = SPRITESHEET.getWidth() / widthTile;
        heightSprite = SPRITESHEET.getHeight() / heightTile;

        loadSpriteArray ();
    }

    public Sprite (String file, int _widthTile, int _heightTile){
        widthTile = _widthTile;
        heightTile = _heightTile;

        System.out.println("Loading: " + file + "...");

        SPRITESHEET = loadSprite (file);

        widthSprite = SPRITESHEET.getWidth() / widthTile;
        heightSprite = SPRITESHEET.getHeight() / heightTile;

        loadSpriteArray ();
    }

    public void setSize (int _width, int _height){
        setWidth (_width);
        setHeight (_height);
    }

    public void setWidth (int _width){
        widthTile = _width;
        widthSprite = SPRITESHEET.getWidth() / widthTile;
    }

    public void setHeight (int _height){
        heightTile = _height;
        heightSprite = SPRITESHEET.getHeight() / heightTile;
    }

    public int getWidth (){
        return widthTile;
    }

    public int getHeight (){
        return heightTile;
    }

    private BufferedImage loadSprite (String file){
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        }
        catch (Exception e){
            System.out.println("ERROR! Nu avem path catre un sprite! " + file);
        }

        return sprite;
    }

    public void loadSpriteArray (){
        spriteArray = new BufferedImage[heightSprite][widthSprite];

        for (int j = 0; j < heightSprite; j++){
            for (int i = 0; i < widthSprite; i++) {
                spriteArray[j][i] = getSprite(i, j);
            }
        }
    }

    public BufferedImage getSpriteSheet (){
        return SPRITESHEET;
    }

    public BufferedImage getSprite (int x, int y) {
        return SPRITESHEET.getSubimage(x * widthTile, y * heightTile, widthTile, heightTile);
    }

    public BufferedImage[] getSpriteArray (int i){
        return spriteArray[i];
    }

    public BufferedImage[][] getSpriteMatrix (){
        return spriteArray;
    }

    public static void drawPhoto (Graphics2D graphics2D, BufferedImage image, Vector2f position, int _width, int _height){
        graphics2D.drawImage(image, (int)position.x, (int)position.y, _width, _height, null);
    }

    public static void drawArray (Graphics2D graphics2D, Vector<BufferedImage> image, Vector2f position, int _width, int _height, int xOffset, int yOffset){
        float x = position.x;
        float y = position.y;

        for (BufferedImage Elem : image){
            if (Elem != null){
                graphics2D.drawImage(Elem, (int) x, (int) y, _width, _height, null);
            }
            x += xOffset;
            y += yOffset;
        }
    }

    public static void drawArray (Graphics2D graphics2D, Font font, String word, Vector2f position, int _width, int _height, int xOffset, int yOffset){
        float x = position.x;;
        float y = position.y;

        for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) != 32)
                graphics2D.drawImage(font.getFont (word.charAt(i)), (int) x, (int) y, _width, _height, null);
            x += xOffset;
            y += yOffset;
        }

    }
}

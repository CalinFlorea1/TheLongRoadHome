package graphics;

import TheLongRoadHome.Handler.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class Font {
    private BufferedImage FONTSHEET = null;
    private BufferedImage[][] spriteArray;

    private final int TILE_SIZE = 64;

    public int widthTile;
    public int heightTile;

    private int widthFont;
    private int heightFont;

    public Font (String file){
        widthTile = TILE_SIZE;
        heightTile = TILE_SIZE;

        System.out.println("Loading: " + file + "...");

        FONTSHEET = loadFont (file);

        widthFont = FONTSHEET.getWidth() / widthTile;
        heightFont = FONTSHEET.getHeight() / heightTile;

        loadSpriteArray ();
    }

    public Font (String file, int _widthTile, int _heightTile){
        widthTile = _widthTile;
        heightTile = _heightTile;

        System.out.println("Loading: " + file + "...");

        FONTSHEET = loadFont (file);

        widthFont = FONTSHEET.getWidth() / widthTile;
        heightFont = FONTSHEET.getHeight() / heightTile;

        loadSpriteArray ();
    }

    public void setSize (int _width, int _height){
        setWidth (_width);
        setHeight (_height);
    }

    public void setWidth (int _width){
        widthTile = _width;
        widthFont = FONTSHEET.getWidth() / widthTile;
    }

    public void setHeight (int _height){
        heightTile = _height;
        heightFont = FONTSHEET.getHeight() / heightTile;
    }

    public int getWidth (){
        return widthTile;
    }

    public int getHeight (){
        return heightTile;
    }

    private BufferedImage loadFont (String file){
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
        spriteArray = new BufferedImage[widthFont][heightFont];

        for (int i = 0; i < widthFont; i++){
            for (int j = 0; j < heightFont; j++){
                spriteArray[i][j] = getFont (i, j);
            }
        }
    }

    public BufferedImage getFontSheet (){
        return FONTSHEET;
    }

    public BufferedImage getFont (int x, int y) {
        return FONTSHEET.getSubimage(x * widthTile, y * heightTile, widthTile, heightTile);
    }

    public BufferedImage getFont (char letter) {
        int value = letter - 'A';

        int x = value % widthFont;
        int y = value % heightFont;

        return FONTSHEET.getSubimage(x, y, widthTile, heightTile);
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
        }
    }

}


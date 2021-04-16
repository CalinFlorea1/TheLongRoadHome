package TheLongRoadHome.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

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
    }

    public Font (String file, int _widthTile, int _heightTile){
        widthTile = _widthTile;
        heightTile = _heightTile;

        System.out.println("Loading: " + file + "...");

        FONTSHEET = loadFont (file);

        widthFont = FONTSHEET.getWidth() / widthTile;
        heightFont = FONTSHEET.getHeight() / heightTile;
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

    public BufferedImage getFontSheet (){
        return FONTSHEET;
    }

    public void loadFontArray() {
        spriteArray = new BufferedImage[widthFont][heightFont];

        for (int x = 0; x < widthFont; x++) {
            for (int y = 0; y < heightFont; y++) {
                spriteArray[x][y] = getFont(x, y);
            }
        }
    }
    public BufferedImage getFont (int x, int y) {
        return FONTSHEET.getSubimage(x * widthTile, y * heightTile, widthTile, heightTile);
    }

    public BufferedImage getFont (char letter) {
        int value = letter - 'A';

        int x = value % widthFont;
        int y = value / widthFont;

        return getFont(x, y);
    }

}


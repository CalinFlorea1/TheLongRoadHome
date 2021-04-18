package TheLongRoadHome.tiles.blocks;

import TheLongRoadHome.Handler.AABB;
import TheLongRoadHome.Handler.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {
    protected int width;
    protected int height;

    protected BufferedImage bufferedImage;
    protected Vector2f position;

    public Block (BufferedImage _bufferedImage, Vector2f _position, int _width, int _height){
        bufferedImage = _bufferedImage;
        position = _position;
        width = _width;
        height = _height;
    }

    public abstract boolean update (AABB p);

    public void render (Graphics2D graphics2D){
        graphics2D.drawImage(bufferedImage, (int)position.x, (int)position.y, width, height, null);
    }
}

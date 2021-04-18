package TheLongRoadHome.tiles.blocks;

import TheLongRoadHome.Handler.AABB;
import TheLongRoadHome.Handler.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjBlock extends Block{

    public ObjBlock(BufferedImage _bufferedImage, Vector2f _position, int _width, int _height) {
        super(_bufferedImage, _position, _width, _height);
    }

    public boolean update (AABB p){
        return true;
    }

    public void render (Graphics2D graphics2D){
        super.render(graphics2D);
    }
}

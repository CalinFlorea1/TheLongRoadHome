package TheLongRoadHome.tiles.blocks;

import TheLongRoadHome.Handler.AABB;
import TheLongRoadHome.Handler.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormBlock extends Block{

    public NormBlock(BufferedImage _bufferedImage, Vector2f _position, int _width, int _height) {
        super(_bufferedImage, _position, _width, _height);
    }

    public boolean update (AABB p){
        return false;
    }

    public void render (Graphics2D graphics2D){
        super.render(graphics2D);
    }
}

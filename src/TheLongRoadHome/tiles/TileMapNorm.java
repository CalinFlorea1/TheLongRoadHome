package TheLongRoadHome.tiles;

import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.Sprite;
import TheLongRoadHome.tiles.blocks.Block;
import TheLongRoadHome.tiles.blocks.NormBlock;

import java.awt.*;
import java.util.Vector;

public class TileMapNorm extends TileMap {

    private Vector <Block> blocks;

    public TileMapNorm (String _data, Sprite _sprite, int _width, int _height, int _tileWidth, int _tileHeight, int _tileColumns){
        blocks = new Vector<Block>();

        String []block = _data.split (",");
        for (int i = 0; i < (_width * _height); i++){
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
            if (temp != 0){
                blocks.add(new NormBlock(_sprite.getSprite((int)((temp - 1) % _tileColumns), (int)((temp - 1) / _tileColumns)), new Vector2f((int)(i % _width) * _tileWidth, (int)(i / _width) * _tileHeight) ,_tileWidth, _tileHeight));
            }
        }
    }

    public void render (Graphics2D graphics2D){
        for(Block Element : blocks){
            Element.render (graphics2D);
        }
    }
}

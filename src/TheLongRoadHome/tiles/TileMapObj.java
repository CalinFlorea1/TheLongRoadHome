package TheLongRoadHome.tiles;

import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.Sprite;
import TheLongRoadHome.tiles.blocks.Block;
import TheLongRoadHome.tiles.blocks.NormBlock;
import TheLongRoadHome.tiles.blocks.ObjBlock;

import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

public class TileMapObj extends TileMap{

    public static HashMap <String, Block> tileMapObjects_blocks;

    public TileMapObj (String _data, Sprite _sprite, int _width, int _height, int _tileWidth, int _tileHeight, int _tileColumns){
        Block tempBlock;
        tileMapObjects_blocks = new HashMap<String, Block>();

        String []block = _data.split (",");
        for (int i = 0; i < (_width * _height); i++){
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
            if (temp != 0){
                tempBlock = new ObjBlock(_sprite.getSprite((int)((temp - 1) % _tileColumns), (int)((temp - 1) / _tileColumns)), new Vector2f((int)(i % _width) * _tileWidth, (int)(i / _width) * _tileHeight) ,_tileWidth, _tileHeight);
                tileMapObjects_blocks.put(String.valueOf((int) (i % _width)) + "," + String.valueOf((int) (i / _height)), tempBlock);
            }
        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        for (Block block : tileMapObjects_blocks.values()){
            block.render(graphics2D);
        }
    }
}

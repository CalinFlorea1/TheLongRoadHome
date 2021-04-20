package TheLongRoadHome.tiles;

import TheLongRoadHome.graphics.Sprite;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import org.w3c.dom.*;

import java.util.Vector;

public class TileManager {

    public Vector <TileMap> tileMap;

    public TileManager (){
        tileMap = new Vector<TileMap>();
    }

    public TileManager (String _path) throws Exception {
        tileMap = new Vector<TileMap>();
        addTileMap (_path, 64, 64);
    }

    private void addTileMap (String _path, int blockWidth, int blockHeight) throws Exception{
        String imagePath;

        int width = 0;
        int height = 0;
        int tileWidth;
        int tileHeight;
        int tileCount;
        final int tileColumns = 20;
        int layer = 0;
        Sprite sprite = new Sprite("Map/TileSet.png", 64, 64);

        String []data = new String[10];

        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new File(getClass().getClassLoader().getResource(_path).toURI()));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("map");
            Node node = nodeList.item(0);
            Element element = (Element) node;

            tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(element.getAttribute("tileheight"));

            nodeList = document.getElementsByTagName("layer");
            layer = nodeList.getLength();

            for (int i = 0; i < layer; i++){
                node = nodeList.item(i);
                if (i <= 0){
                    width = Integer.parseInt(element.getAttribute("width"));
                    height = Integer.parseInt(element.getAttribute("height"));
                }

                data[i] = element.getElementsByTagName("data").item(i).getTextContent();
                if (i >= 1){
                    tileMap.add(new TileMapObj(data[i], sprite, width, height, tileWidth, tileHeight,  tileColumns));
                }
                else{
                    tileMap.add (new TileMapNorm (data[i], sprite, width, height, tileWidth, tileHeight,  tileColumns));
                }
            }
        }
        catch (Exception e){
            System.out.println("Error: tilemap cannot read");
        }
    }

    public void render (Graphics2D graphics2D){
        for (TileMap Element : tileMap){
            Element.render(graphics2D);
        }
    }
}

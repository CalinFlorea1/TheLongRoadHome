package TheLongRoadHome.Handler;

import TheLongRoadHome.entity.Entity;

public class AABB {
    private Vector2f position;
    private float xOffset = 0;
    private float yOffset = 0;
    private float width;
    private float height;
    private float radius;
    private int size;
    private Entity entity;

    public AABB (Vector2f _position, int _width, int _height){
        position = _position;
        width = _width;
        height = _height;

        size = (int) Math.max (width, height);
    }

    public AABB (Vector2f _position, int _radius, Entity _entity){
        position = _position;
        radius = _radius;
        entity = _entity;

        size = (int) radius;
    }

    public Vector2f getPosition (){
        return position;
    }

    public float getRadius (){
        return radius;
    }

    public float getWidth (){
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setBox (Vector2f _position, int _width, int _height){
        position = _position;
        width = _width;
        height = _height;

        size = (int) Math.max(width, height);
    }

    public void setCircle (Vector2f _position, int _radius){
        position = _position;
        radius = _radius;

        size = (int) radius;
    }

    public void setWidth (float _width){
        width = _width;
    }

    public void setHeight (float _height){
        height = _height;
    }

    public void setxOffset (float _xOffset){
        xOffset = _xOffset;
    }

    public void setyOffset (float _yOffset){
        yOffset = _yOffset;
    }

    public boolean collides (AABB bBox){
        float ax = (position.getWorldVar().x + (xOffset) + (width / 2));
        float ay = (position.getWorldVar().y + (yOffset) + (height / 2));

        float bx = (bBox.position.getWorldVar().x + (bBox.xOffset / 2) + (width / 2));
        float by = (bBox.position.getWorldVar().y + (bBox.yOffset / 2) + (height / 2));

        if (Math.abs(ax - bx) < ((width / 2) + (bBox.width / 2))){
            if (Math.abs(ay - by) < ((height / 2) + (bBox.height / 2))){
                return true;
            }
        }

        return false;
    }

    public boolean collsionCircleBox (AABB aBox){
        float cx = (float) (position.getWorldVar().x + radius / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));
        float cy = (float) (position.getWorldVar().y + radius / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));

        float xDelta = cx - Math.max (aBox.position.getWorldVar().x + (aBox.getWidth() / 2), Math.min(cx, aBox.position.getWorldVar().x));
        float yDelta = cx - Math.max (aBox.position.getWorldVar().y + (aBox.getHeight() / 2), Math.min(cy, aBox.position.getWorldVar().y));

        if ((xDelta * xDelta + yDelta * yDelta) < ((radius / Math.sqrt(2)) * (radius / Math.sqrt(2)))){
            return true;
        }

        return false;
    }
}
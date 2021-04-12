package TheLongRoadHome.Handler;

public class Vector2f {
    public float x;
    public float y;

    public static float worldX;
    public static float worldY;

    public Vector2f (){
        x = 0;
        y = 0;
    }

    public Vector2f (Vector2f position){
        new Vector2f(position);
    }

    public Vector2f (float _x, float _y){
        x = _x;
        y = _y;
    }

    public void addX (float position) {
        x += position;
    }

    public void addY (float position) {
        y += position;
    }

    public void setX (float position){
        x = position;
    }

    public void setY (float position){
        y = position;
    }

    public void setVector (Vector2f vector2f){
        x = vector2f.x;
        y = vector2f.y;
    }

    public void setVector (float _x, float _y){
        x = _x;
        y = _y;
    }

    public static void setWorldVar (float _x, float _y){
        worldX = _x;
        worldY = _y;
    }

    public Vector2f getWorldVar (){
        return new Vector2f( x - worldX, y - worldY);
    }

    @Override
    public String toString (){
        return x + ", " + y;
    }
}

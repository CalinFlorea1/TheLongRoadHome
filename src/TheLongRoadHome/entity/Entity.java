package TheLongRoadHome.entity;

import TheLongRoadHome.Handler.AABB;
import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;
import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.*;
import javafx.geometry.Bounds;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class Entity {

    private final int UP = 2;
    private final int DOWN = 0;
    private final int RIGHT = 3;
    private final int LEFT = 1;
    protected int currentAnimation;

    protected Animation animation;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected int attackSpeed;
    protected int atackDuration;

    protected float dx;
    protected float dy;

    protected float maxSpeed = 1.0f;
    protected float acceleration = 0.6f;
    protected float deacceleration = 0.06f;

    protected AABB hitBounds;
    protected AABB bounds;

    public Entity (Sprite _sprite, Vector2f _origin, int _size) {
        sprite = _sprite;
        pos = _origin;
        size = _size;

        bounds = new AABB(_origin, size, size);
        hitBounds = new AABB(new Vector2f(_origin.x + (size / 2), _origin.y), size, size);

        animation = new Animation();
        setAnimation (RIGHT, sprite.getSpriteArray (RIGHT), 10);
    }

    public void setAnimation (int _currentAnimation, BufferedImage [] _frames, int _delay) {
        currentAnimation = _currentAnimation;
        animation.setFrames (_frames);
        animation.setDelay (_delay);
    }

    public void animate (){
        if (up){
            if (currentAnimation != UP || animation.getDelay() == -1){
                setAnimation(UP, sprite.getSpriteArray (UP), 5);
            }
        }

        else if (down){
            if (currentAnimation != DOWN || animation.getDelay() == -1){
                setAnimation(DOWN, sprite.getSpriteArray (DOWN), 5);
            }
        }

        else if (right){
            if (currentAnimation != RIGHT || animation.getDelay() == -1){
                setAnimation(RIGHT, sprite.getSpriteArray (RIGHT), 5);
            }
        }

        else if (left){
            if (currentAnimation != LEFT || animation.getDelay() == -1){
                setAnimation(LEFT, sprite.getSpriteArray (LEFT), 5);
            }
        }

        else{
            setAnimation(currentAnimation, sprite.getSpriteArray (currentAnimation), -1);
        }
    }

    public int getSize (){
        return size;
    }

    public Sprite getSprite (){
        return sprite;
    }

    public Animation getAnimation (){
        return animation;
    }

    public void setSize (int _size){
        size = _size;
    }

    public void setMaxSpeed (float _maxSpeed) {
        maxSpeed = _maxSpeed;
    }

    public void setAcceleration (float _acceleration){
        acceleration = _acceleration;
    }

    public void setDeacceleration (float _deaceleration){
        deacceleration = _deaceleration;
    }

    public AABB getBounds (){
        return bounds;
    }


    private void setHitBoxDirection (){
        if (up){
            hitBounds.setyOffset(-size / 2);
            hitBounds.setxOffset(-size / 2);
        }
        else if (down){
            hitBounds.setyOffset(size / 2);
            hitBounds.setxOffset(-size / 2);
        }
        else if (left){
            hitBounds.setyOffset(0);
            hitBounds.setxOffset(-size);
        }

        else if (right){
            hitBounds.setyOffset(0);
            hitBounds.setxOffset(0);
        }
    }

    public void update (){
        animate ();
        //setHitBoxDirection ();
        animation.update();
    }

    public abstract void render (Graphics2D graphics2D);
    public void input (KeyHandler key, MouseHandler mouse){

    }
}

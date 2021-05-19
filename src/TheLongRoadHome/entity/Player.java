package TheLongRoadHome.entity;

import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;
import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.*;
import TheLongRoadHome.states.PlayState;

import java.awt.*;
import java.util.Vector;

public class Player extends Entity{
    public Player(Sprite _sprite, Vector2f _origin, int _size) {
        super(_sprite, _origin, _size);
        life = 100;
        bounds.setWidth(40);
        bounds.setHeight(40);
        bounds.setxOffset(14);
        bounds.setyOffset(14);
    }

    public void move (){
        if (up){
            dy -= acceleration;
            if (dy < -maxSpeed){
                dy = -maxSpeed;
            }
        }
        else{
            if (dy < 0){
                dy += deacceleration;
                if (dy > 0){
                    dy = 0;
                }
            }
        }

        if (down){
            dy += acceleration;
            if (dy > maxSpeed){
                dy = maxSpeed;
            }
        }
        else{
            if (dy > 0){
                dy -= deacceleration;
                if (dy < 0){
                    dy = 0;
                }
            }
        }

        if (left){
            dx -= acceleration;
            if (dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }
        else{
            if (dx < 0){
                dx += deacceleration;
                if (dx > 0){
                    dx = 0;
                }
            }
        }

        if (right){
            dx += acceleration;
            if (dx > maxSpeed){
                dx = maxSpeed;
            }
        }
        else{
            if (dx > 0){
                dx -= deacceleration;
                if (dx < 0){
                    dx = 0;
                }
            }
        }
    }

    public void update (){
        if (delayShot != 0)
            delayShot++;

        if (delayShot == 20)
            delayShot = 0;

        super.update();
        move ();
        if (!bounds.collisionTile(dx, 0)){
            if (!bounds.collisionEntity(dx, 0))
                if (pos.x + dx > 0 && pos.x + dx <= 1856)
                    pos.x += dx;
        }

        if (!bounds.collisionTile(0, dy)){
            if (!bounds.collisionEntity(0, dy))
                if (pos.y + dy > 0 && pos.y + dy <= 1016)
                    pos.y += dy;
        }
    }
    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(animation.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }

    public void input (MouseHandler mouse, KeyHandler key){
        if (mouse.getButton () == 1){
            ;
        }

        if (key.up.down){
            up = true;
            lastButton = 0;
        }
        else{
            up = false;
        }

        if (key.down.down){
            down = true;
            lastButton = 2;
        }
        else{
            down = false;
        }

        if (key.left.down){
            lastButton = 3;
            left = true;
        }
        else{
            left = false;
        }

        if (key.right.down){
            lastButton = 1;
            right = true;
        }
        else{
            right = false;
        }

        if (key.shot.down){
            attack = true;
        }
        else {
            attack = false;
        }
    }
}

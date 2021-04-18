package TheLongRoadHome.entity;

import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;
import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.*;
import TheLongRoadHome.states.PlayState;

import java.awt.*;

public class Player extends Entity{
    public Player(Sprite _sprite, Vector2f _origin, int _size) {
        super(_sprite, _origin, _size);
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
        super.update();
        move ();
        //if (!bounds.collisionTile(dx, 0)){
            pos.x += dx;
        //}

        //if (!bounds.collisionTile(0, dy)){
            pos.y += dy;
        //}
    }
    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(animation.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }

    public void input (MouseHandler mouse, KeyHandler key){

        if (mouse.getButton () == 1){
            System.out.println("Player: " + pos.x + ", " + pos.y);
        }

        if (key.up.down){
            up = true;
        }
        else{
            up = false;
        }

        if (key.down.down){
            down = true;
        }
        else{
            down = false;
        }

        if (key.left.down){
            left = true;
        }
        else{
            left = false;
        }

        if (key.right.down){
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

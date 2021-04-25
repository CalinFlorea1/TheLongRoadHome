package TheLongRoadHome.entity;

import TheLongRoadHome.Handler.AABB;
import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.Sprite;

import java.awt.*;

public class Enemy extends Entity{

    public Enemy(Sprite _sprite, Vector2f _origin, int _size) {
        super(_sprite, _origin, _size);
        bounds.setWidth(40);
        bounds.setHeight(40);
        bounds.setxOffset(14);
        bounds.setyOffset(14);
    }

    public void update (){
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

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(animation.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }
}

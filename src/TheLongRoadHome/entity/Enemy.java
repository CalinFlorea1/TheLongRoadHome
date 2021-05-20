package TheLongRoadHome.entity;

import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.Sprite;

import java.awt.*;

public class Enemy extends Entity{

    public Enemy(Sprite _sprite, Vector2f _origin, int _size, int _life) {
        super(_sprite, _origin, _size);
        bounds.setWidth(50);
        bounds.setHeight(50);
        bounds.setxOffset(10);
        bounds.setyOffset(10);
        down = true;
        life = _life;
    }

    public void update (){
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

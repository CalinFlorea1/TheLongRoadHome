package TheLongRoadHome.entity;

import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.Sprite;
import TheLongRoadHome.states.PlayState;

import java.awt.*;
import java.util.Random;

public class Enemy extends Entity{

    private int contorDirection;
    private int contorShoot;
    public Enemy(Sprite _sprite, Vector2f _origin, int _size, int _life) {
        super(_sprite, _origin, _size);
        bounds.setWidth(50);
        bounds.setHeight(50);
        bounds.setxOffset(10);
        bounds.setyOffset(10);

        life = _life;
        contorShoot = Math.abs(new Random().nextInt()) % 100 + 20;
        contorDirection = Math.abs(new Random().nextInt()) % 100 + 20;
    }

    public void update (){
        if (contorDirection <= 0){
            int direction = Math.abs(new Random().nextInt()) % 4;

            switch (direction) {
                case 0:
                    down = true;
                    up = right = left = false;
                    break;
                case 1:
                    right = true;
                    up = down = left = false;
                    break;
                case 2:
                    up = true;
                    down = right = left = false;
                    break;
                case 3:
                    left = true;
                    down = right = up = false;
                    break;
            }

            contorDirection = Math.abs(new Random().nextInt()) % 100 + 20;
        }
        contorShoot--;
        contorDirection--;

        super.update();
        move ();
        if (!bounds.collisionTile(dx, 0)){
            if (!bounds.collisionEntity(dx, 0)) {
                if (pos.x + dx > 0 && pos.x + dx <= 1856) {
                    pos.x += dx;
                }
                else contorDirection = 0;
            }
            else
                contorDirection = 0;
        }
        else
            contorDirection = 0;

        if (!bounds.collisionTile(0, dy)){
            if (!bounds.collisionEntity(0, dy)) {
                if (pos.y + dy > 0 && pos.y + dy <= 1016) {
                    pos.y += dy;
                }
                else
                    contorDirection = 0;
            }
            else
                contorDirection = 0;
        }
        else
            contorDirection = 0;
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


    public int getContorShoot (){
        return contorShoot;
    }

    public void setContorShoot (int _contorShoot){
        contorShoot = _contorShoot;
    }

    public int getCurrentDirection (){
        if (up){
            return 0;
        }
        else
            if (right){
                return 1;
            }
            else
                if (down){
                    return 2;
                }
                else
                    if (left){
                        return 3;
                    }
        return 0;
    }
}

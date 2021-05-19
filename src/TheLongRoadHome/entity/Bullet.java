package TheLongRoadHome.entity;

import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.Sprite;
import TheLongRoadHome.states.PlayState;

import java.awt.*;

public class Bullet extends Entity{
    int direction = -1;
    Entity playerStart;
    boolean shot = false;

    public Bullet(Sprite _sprite, Vector2f _origin, int _size, int _direction, Entity _playerStart) {
        super(_sprite, _origin, _size);
        direction = _direction;
        playerStart = _playerStart;
        maxSpeed = 3.5f;
        acceleration = 1.5f;
        deacceleration = 1.0f;
        switch (direction){
            case 0:
                up = true;
                break;
            case 1:
                right = true;
                break;
            case 2:
                down = true;
                break;
            case 3:
                left = true;
                break;
        }

        bounds.setWidth(50);
        bounds.setHeight(50);
        bounds.setxOffset(10);
        bounds.setyOffset(10);
    }

    public void update (){
        super.update();
        move ();
        if (!bounds.collisionTile(dx, 0)){
            Entity temp = bounds.collisionEntityforBullet(dx, 0, playerStart);
            if (temp == null) {
                pos.x += dx;
            }
            else{
                shot = true;
                if (temp.life <= 20){
                    PlayState.explosions.add(new Explosion(new Sprite ("Textures/Explosion.png"),
                            new Vector2f(temp.getPos().x, temp.getPos().y), 64));
                    PlayState.enemy.remove(temp);
                }
            }
        }
        else{
            PlayState.explosions.add(new Explosion(new Sprite ("Textures/Explosion.png"), new Vector2f(pos.x + dx, pos.y), 64));
            shot = true;
        }

        if (!bounds.collisionTile(0, dy)){
            Entity temp = bounds.collisionEntityforBullet(0, dy, playerStart);
            if (temp == null) {
                pos.y += dy;
            }
            else{
                shot = true;
                if (temp.life <= 20){
                    PlayState.explosions.add(new Explosion(new Sprite ("Textures/Explosion.png"),
                            new Vector2f(temp.getPos().x, temp.getPos().y), 64));
                    PlayState.enemy.remove(temp);
                }
            }
        }
        else{
            PlayState.explosions.add(new Explosion(new Sprite ("Textures/Explosion.png"), new Vector2f(pos.x, pos.y + dy), 64));
            shot = true;
        }
    }

    public boolean isShot (){
        return shot;
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

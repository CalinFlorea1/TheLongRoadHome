package TheLongRoadHome.entity;

import TheLongRoadHome.Handler.Vector2f;
import TheLongRoadHome.graphics.Sprite;

import java.awt.*;

public class Explosion extends Entity{
    public Explosion(Sprite _sprite, Vector2f _origin, int _size) {
        super(_sprite, _origin, _size);
        down = true;
    }

    public void update () {
        super.update();
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(animation.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }
}

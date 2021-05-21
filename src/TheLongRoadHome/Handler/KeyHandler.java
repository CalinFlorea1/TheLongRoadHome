package TheLongRoadHome.Handler;

import TheLongRoadHome.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class KeyHandler implements KeyListener {
    public static Vector<Key> keys = new Vector<Key>();

    public KeyHandler() {

    }

    public class Key {
        public int presses, absorbs;
        public boolean down, clicked;

        public Key (){
            keys.add (this);
        }

        public void toggle (boolean pressed){
            if (pressed != down){
                down = pressed;
            }
            if (pressed){
                presses++;
            }

        }

        public void tick (){
            if (absorbs < presses){
                absorbs++;
                clicked = true;
            }
            else{
                clicked = false;
            }

        }
    }

    public Key up = new Key ();
    public Key down = new Key();
    public Key left = new Key ();
    public Key right = new Key ();
    public Key shot = new Key ();
    public Key escape = new Key ();
    public Key enter = new Key ();
    public Key menu = new Key ();

    public KeyHandler (GamePanel game){
        game.addKeyListener(this);
    }

    public void releaseAll (){
        for (Key Elem : keys){
            Elem.down = false;
        }
    }

    public void tick (){
        for (Key Elem : keys){
            Elem.tick();
        }
    }

    public void toggle (KeyEvent event, boolean _pressed){
        if (event.getKeyCode() == KeyEvent.VK_W) up.toggle(_pressed);
        if (event.getKeyCode() == KeyEvent.VK_A) left.toggle(_pressed);
        if (event.getKeyCode() == KeyEvent.VK_S) down.toggle(_pressed);
        if (event.getKeyCode() == KeyEvent.VK_D) right.toggle(_pressed);
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(_pressed);
        if (event.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(_pressed);
        if (event.getKeyCode() == KeyEvent.VK_SPACE) shot.toggle(_pressed);
        if (event.getKeyCode() == KeyEvent.VK_M) menu.toggle(_pressed);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //nema
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }

}

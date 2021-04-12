package TheLongRoadHome;

import TheLongRoadHome.Handler.KeyHandler;
import TheLongRoadHome.Handler.MouseHandler;
import TheLongRoadHome.states.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    public static int width;
    public static int height;

    private Thread thread;
    private boolean isRunning = false;

    private BufferedImage image;
    private Graphics2D graphics2D;

    private MouseHandler mouse;
    private KeyHandler key;
    private GameStateManager gameStateManager;

    public GamePanel (int _width, int _heigth){
        setPreferredSize(new Dimension(_width, _heigth));
        setFocusable(true);
        requestFocus();
        width = _width;
        height = _heigth;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null){
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void Init (){
        isRunning = true;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) image.getGraphics();

        mouse = new MouseHandler();
        key = new KeyHandler(this);

        gameStateManager = new GameStateManager();
    }

    @Override
    public void run() {
        Init();

        final double GAME_HERTZ = 60.0;
        final double timeBeforeRender = 1000000000 / GAME_HERTZ;

        final int mustUpdateBeforeRender = 5;

        double lastUpdateTime = System.nanoTime(); ///10 ^-9
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double totalTimeBeforeRender = 1000000000 / TARGET_FPS;

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCount = 0;

        while (isRunning){
            double now = System.nanoTime();
            int updateCount = 0;
            while (((now - lastUpdateTime) > timeBeforeRender) && (updateCount < mustUpdateBeforeRender)){
                update();
                input (mouse, key);
                lastUpdateTime += timeBeforeRender;
                updateCount++;
            }

            if (now - lastUpdateTime > timeBeforeRender){
                lastUpdateTime = now - timeBeforeRender;
            }
            input (mouse, key);
            render();
            draw ();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime){
                if (frameCount != oldFrameCount) {
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }
            while (now - lastRenderTime < totalTimeBeforeRender && now - lastUpdateTime < timeBeforeRender){
                Thread.yield();
                try{
                    Thread.sleep(1);
                }
                catch (Exception e){
                    System.out.println("Error at threat");
                }
                now = System.nanoTime();
            }
        }
    }

    private void input(MouseHandler mouse, KeyHandler key) {
        gameStateManager.input(mouse, key);
    }

    public void update () {
        gameStateManager.update();
    };

    public void render (){
        if (graphics2D != null){
            graphics2D.setColor(new Color(255, 0, 0));
            graphics2D.fillRect(0, 0, width, height);
            gameStateManager.render(graphics2D);
        }
    };

    public void draw (){
        Graphics graphics = (Graphics) this.getGraphics();
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();
    };
}

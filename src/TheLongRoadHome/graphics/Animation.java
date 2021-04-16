package TheLongRoadHome.graphics;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class Animation {
    private BufferedImage []frames;
    private int currentFrame;
    private int numberOfFrames;

    private int count;
    private int delay;

    private int timesPlayed;

    public Animation (BufferedImage []frames){
        timesPlayed = 0;
        setFrames (frames);
    }

    public Animation (){
        timesPlayed = 0;
    }

    public void setFrames (BufferedImage []_frames){
        frames = _frames;
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numberOfFrames = frames.length;
    }

    public void setDelay (int _delay){
        delay = _delay;
    }

    public void setCurrentFrame (int _curentFrame){
        currentFrame = _curentFrame;
    }

    public void setNumberOfFrames (int _numberOfFrames){
        numberOfFrames = _numberOfFrames;
    }

    public void update (){
        if (delay == -1) return;

        count++;

        if (count  == delay){
            currentFrame++;
            count = 0;
        }

        if (currentFrame == numberOfFrames){
            currentFrame = 0;
            timesPlayed++;
        }
    }

    public int getDelay (){
        return delay;
    }

    public int getCurrentFrame (){
        return currentFrame;
    }

    public int getCount(){
        return count;
    }

    public BufferedImage getImage (){
        return frames[currentFrame];
    }

    public boolean hasPlayedOnce (){
        return  timesPlayed > 0;
    }

    public boolean hasPlayed (int _timesPlayed){
        return timesPlayed == _timesPlayed;
    }
}

package TheLongRoadHome.Handler;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Audio {

    private Clip clip;

    public static enum Volume{
        MUTE, LOW, MEDIUM, HIGH;
    }

    public static Volume volume = Volume.HIGH;

    public Audio (String _audioFile){
        try {
            URL url = this.getClass().getResource(_audioFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void setVolume (Volume _volume){
        volume = _volume;
    }

    public void play (boolean loop){
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();
            clip.setFramePosition(0);
            clip.start();
            if(loop)
                clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop()
    {
        clip.stop();
        clip.setFramePosition(0);
    }
}

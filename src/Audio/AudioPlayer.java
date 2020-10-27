package Audio;

import javax.sound.sampled.*;

public class AudioPlayer {

    private Clip clip;
    private static AudioPlayer instance;

    private AudioPlayer() {
    }
    
    public static AudioPlayer getInstance(){
        if(instance == null){
            instance = new AudioPlayer();
        }
        
        return instance;
    }

    public void setMusic(String path) {
        if(clip != null){
            this.close();
            clip = null;
        }
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));

            AudioFormat baseFormat = audio.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);

            AudioInputStream decodeAudio = AudioSystem.getAudioInputStream(decodeFormat, audio);

            clip = AudioSystem.getClip();
            clip.open(decodeAudio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            this.play();
        } catch (Exception e) {
        }
    }

    public void play() {
        if (clip == null) {
            return;
        }
        stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public void close() {
        stop();
        clip.close();
    }

}

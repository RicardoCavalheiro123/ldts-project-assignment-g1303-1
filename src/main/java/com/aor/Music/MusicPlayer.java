package com.aor.Music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class MusicPlayer {
    private final Clip backgroundMusic, bombMusic, winMusic, loseMusic, bombExplosion, footstep;



    public MusicPlayer() {
        this.backgroundMusic= loadMusic();
        this.bombMusic = loadBombMusic();
        this.winMusic = loadWinMusic();
        this.bombExplosion = loadBombExplosion();
        this.loseMusic = loadLossMusic();
        this.footstep = loadFootStep();
    }

    private Clip loadMusic() throws NullPointerException{
        try {
            File musicFile = new File(MusicPlayer.class.getResource("src/main/resources/music/theme_song2.wav").getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            return musicClip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Clip loadBombMusic() throws NullPointerException{
        try {
            File musicFile = new File(MusicPlayer.class.getResource("src/main/resources/music/bomb.wav").getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            return musicClip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Clip loadWinMusic() throws NullPointerException{
        try {
            File musicFile = new File(MusicPlayer.class.getResource("src/main/resources/music/win.wav").getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return musicClip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Clip loadLossMusic() throws NullPointerException{
        try {
            File musicFile = new File(MusicPlayer.class.getResource("src/main/resources/music/lose.wav").getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-5.0f);
            return musicClip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Clip loadBombExplosion() throws NullPointerException{
        try {
            File musicFile = new File(MusicPlayer.class.getResource("src/main/resources/music/soft_explosion.wav").getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return musicClip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Clip loadFootStep() throws NullPointerException{
        try {
            File musicFile = new File(MusicPlayer.class.getResource("src/main/resources/music/footsteps.wav").getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
            return musicClip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void startBombMusic() {
        bombMusic.setMicrosecondPosition(0);
        bombMusic.start();
    }
    public void startMusic() {
        backgroundMusic.setMicrosecondPosition(0);
        backgroundMusic.start();
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void endMusic() {
        backgroundMusic.stop();
    }

    public void startWinMusic() {
        winMusic.setMicrosecondPosition(0);
        winMusic.start();
    }
    public void startLoseMusic() {
        loseMusic.setMicrosecondPosition(0);
        loseMusic.start();
    }
    public void startBombExplosionMusic() {
        bombExplosion.setMicrosecondPosition(0);
        bombExplosion.start();
    }
    public void startFootstep() {
        footstep.setMicrosecondPosition(0);
        footstep.start();
    }


}

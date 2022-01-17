package com.aor;

import com.aor.States.GameState;
import com.aor.States.PlayingState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class BomberMan {
    private static BomberMan bomberMan = null;
    private GameState gameState;

    public BomberMan() throws IOException, FontFormatException {
        this.gameState = new PlayingState(this);
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        if (gameState != null)
            this.gameState.start();
    }
    public static BomberMan getInstance() throws IOException, URISyntaxException, FontFormatException {
        if (bomberMan == null) {
            bomberMan = new BomberMan();
        }
        return bomberMan;
    }
    public void start() throws IOException {
        this.gameState.start();

        while (gameState != null){
            long startTime = System.currentTimeMillis();

            gameState.update(this);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = 1000 /20 - elapsedTime;

            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {

            }
        }

    }
}

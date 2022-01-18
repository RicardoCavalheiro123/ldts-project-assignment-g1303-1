package com.aor;

import com.aor.LanternaGui.LanternaGUI;

import com.aor.States.PlayingState;
import com.aor.States.GameState;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.io.IOException;

public class BomberMan {
    public Screen screen;
    private static BomberMan bomberMan = null;
    private GameState gameState;
    public AWTTerminalFrame terminal = LanternaGUI.AWTTerminalFrameFactory();

    public BomberMan() throws IOException, FontFormatException {
        terminal.setLocationRelativeTo(null);
        terminal.setVisible(true);
        terminal.pack();
        terminal.pack();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);
        screen.doResizeIfNecessary();
        this.gameState = new PlayingState(this);
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        if (gameState != null)
            this.gameState.start();
    }
    public static BomberMan getInstance() throws IOException, FontFormatException {
        if (bomberMan == null) {
            bomberMan = new BomberMan();
        }
        return bomberMan;
    }
    public void start() throws IOException, InterruptedException {
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
